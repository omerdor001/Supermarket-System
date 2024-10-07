package BusinessLayer.Delivery;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Delivery {
    private final int ID;
    @JsonDeserialize(using = JsonConverter.LocalDateDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalDateSerializer.class)
    private final LocalDate date;
    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalTimeSerializer.class)
    private final LocalTime time;
    private final Site source;
    private LinkedList<Stop> destinations;
    private int truckId;
    private String driverId;
    private float startingWeight;
    private Status status;
    private float maxWeight;

    public Delivery(int ID, LocalDate date, LocalTime time, int truckId, String driverId, Site source, int stopId) {
        this.ID = ID;
        this.date = date;
        this.time = time;
        this.truckId = truckId;
        this.driverId = driverId;
        this.source = source;
        this.destinations = new LinkedList<>();
        destinations.add(new Stop(stopId, ID, source, date.atTime(time)));
        this.startingWeight = -1;
        this.status = Status.PENDING;
        this.maxWeight = -1;
    }

    public Delivery(int dlvId, LocalDate dlvDate, LocalTime dlvTime, Site dlvSource, int dlvTruckId, String dlvDriverId, float dlvStartingWeight, int dlvStatus, float dlvMaxWeight, LinkedList<Stop> stops) {
        this.ID = dlvId;
        this.date = dlvDate;
        this.time = dlvTime;
        this.source = dlvSource;
        this.truckId = dlvTruckId;
        this.driverId = dlvDriverId;
        this.startingWeight = dlvStartingWeight;
        this.status = Status.values()[dlvStatus];
        this.maxWeight = dlvMaxWeight;
        this.destinations = stops;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Site getSource() {
        return source;
    }

    public LinkedList<Stop> getDestinations() {
        return destinations;
    }

    public float getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(float weight) {
        this.startingWeight = weight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getStatusOrdinal() {
        return status.ordinal();
    }

    public float getMaxWeight() {
        return this.maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Stop getStop(int stopId) {
        for (Stop s : destinations)
            if (s.getId() == stopId)
                return s;
        throw new NoSuchElementException("Stop is not part of the delivery");
    }

    public Stop removeStop(Site stop) {
        int counter = 0;
        boolean found = false;
        Stop removed = null;
        for (Stop s : getDestinations()) {
            if (counter != 0) {
                if (s.getDestination().getAddress().equals(stop.getAddress())) {
                    found = true;
                    for (ItemDelivery itemDelivery : s.getLoadList())
                        removeItemDelivery(itemDelivery.getItemOrder().getId());
                    for (ItemDelivery itemDelivery : s.getUnloadList())
                        removeItemDelivery(itemDelivery.getItemOrder().getId());
                    s.setStatus(Stop.StopStatus.CANCELED);
                    removed = s;
                    getDestinations().remove(s);
                    break;
                }
            }
            counter++;
        }
        if (!found)
            throw new NoSuchElementException("stop isn't found in the delivery");
        counter = 0;
        Stop first = null;
        for (Stop s : getDestinations()) {
            if (counter == 0) {
                first = s;
                counter++;
            } else {
                s.setArriveTime(calculateTime(first.getArriveTime(), first.getDestination(), s.getDestination()));
                first = s;
                counter++;
            }
        }
        return removed;
    }

    public void addStop(Site stop, int stopId) {
        if (destinations.getFirst().getDestination().getAddress().equals(stop.getAddress()) && destinations.size() == 1)
            throw new IllegalArgumentException("Cant deliver from source to itself with no stops in between");
        if (destinations.getFirst().getDestination().getAddress().equals(destinations.getLast().getDestination().getAddress()) && destinations.size() > 1)
            throw new IllegalArgumentException("Cannot add stops after first and last stops are the same");
        if (destinations.getLast().getDestination().getType() == Site.Type.CENTER && destinations.size() > 1)
            throw new IllegalArgumentException("Logistic center can only be the last stop, cant add more stops");
        for (Stop s : destinations)
            if (s.getDestination().getAddress().equals(stop.getAddress()) && !destinations.getFirst().getDestination().getAddress().equals(stop.getAddress()))
                throw new IllegalArgumentException("A non center site may only be visited once per delivery");

        destinations.add(new Stop(stopId, this.ID, stop, calculateTime(destinations.getLast().getArriveTime(), destinations.getLast().getDestination(), stop)));
    }

    public int updateArriveTimeToStop(String address, LocalTime time) {
        int counter = 0;
        int id = -1;
        boolean found = false;
        Stop first = null;
        for (Stop s : getDestinations()) {
            if (counter != 0) {
                if (s.getDestination().getAddress().equals(address)) {
                    found = true;
                    LocalDateTime newTime = s.getArriveTime().toLocalDate().atTime(time);
                    if (newTime.compareTo(first.getArriveTime()) <= 0)
                        throw new IllegalArgumentException("cannot update the arrive time");
                    s.setArriveTime(newTime);
                    id = s.getId();
                } else if (found) {
                    s.setArriveTime(calculateTime(first.getArriveTime(), first.getDestination(), s.getDestination()));
                }
            }
            first = s;
            counter++;
        }
        if (!found)
            throw new NoSuchElementException("stop isn't found in the delivery");
        return id;
    }

    public void approveDelivery() {
        if (destinations.getFirst().getLoadList().size() == 0 || destinations.getFirst().getUnloadList().size() != 0)
            throw new IllegalStateException("Source stop must only have loading items");
        if (destinations.getLast().getLoadList().size() != 0 || destinations.getLast().getUnloadList().size() == 0)
            throw new IllegalStateException("Final stop must only have unloading items");
        checkRegions(destinations);
        checkEmptyAtLast(destinations);
        if (getSource().getType() == Site.Type.CENTER) {
            for (ItemDelivery itemDelivery : destinations.getFirst().getLoadList()) {
                if (itemDelivery.getItemOrder().getStatus() != IOStatus.FIRSTAPPROVED)
                    throw new IllegalStateException("Cannot load items from logistic center that has no approved delivery to logistic center for them");
            }
        }
        if (destinations.getLast().getDestination().getType() == Site.Type.CENTER) {
            for (ItemDelivery itemDelivery : destinations.getLast().getUnloadList()) {
                if (itemDelivery.getItemOrder().getStatus() == IOStatus.FIRSTAPPROVED)
                    throw new IllegalStateException("Cannot unload items not designated to logistic center");
            }
        }
        approveItemDeliveries(destinations);
        if (destinations.getLast().getDestination().getType() == Site.Type.CENTER)
            for (ItemDelivery itemDelivery : destinations.getLast().getUnloadList())
                itemDelivery.getItemOrder().setStatus(IOStatus.FIRSTAPPROVED);
        for (Stop s : destinations)
            s.updateItemOrder();
        setStatus(Status.APPROVED);
    }

    private void checkRegions(LinkedList<Stop> destinations) {
        Site.Region r = Site.Region.GENERAL;
        for (Stop s : destinations)
            if (s.getDestination().getRegion() != Site.Region.GENERAL)
                r = s.getDestination().getRegion();
        for (Stop s : destinations)
            if (s.getDestination().getRegion() != r && s.getDestination().getRegion() != Site.Region.GENERAL)
                throw new IllegalStateException("all the site must be in the same area");
    }

    private void checkEmptyAtLast(LinkedList<Stop> destinations) {
        LinkedList<ItemDelivery> truckLoad = new LinkedList<>();
        for (Stop s : destinations) {
            if (s.getLoadList().size() == 0 && s.getUnloadList().size() == 0)
                throw new IllegalStateException("Stop " + s.getDestination().getAddress() + " must have loading or unloading items");
            for (ItemDelivery itemDelivery : s.getUnloadList())
                if (!truckLoad.remove(itemDelivery))
                    throw new IllegalStateException("Attempted to unload a non existing item");
            for (ItemDelivery itemDelivery : s.getLoadList()) {
                if (truckLoad.contains(itemDelivery))
                    throw new IllegalStateException("Attempted to load an already loaded item");
                else if (itemDelivery.getItemOrder().getStatus() != IOStatus.PENDING && itemDelivery.getItemOrder().getStatus() != IOStatus.FIRSTAPPROVED)
                    throw new IllegalStateException("The item is already approved");
                else truckLoad.add(itemDelivery);
            }
        }
        if (!truckLoad.isEmpty())
            throw new IllegalStateException("Finished delivery with unloaded items");
    }

    private void approveItemDeliveries(LinkedList<Stop> destinations) {
        for (Stop s : destinations) {
            for (ItemDelivery itemDelivery : s.getLoadList()) {
                itemDelivery.setStatus(IDStatus.APPROVED);
                itemDelivery.getItemOrder().setStatus(IOStatus.APPROVED);
            }
            s.setStatus(Stop.StopStatus.APPROVED);
        }
    }

    public boolean assertDeliveryType() {
        boolean update = destinations.getFirst().getLoadList().getFirst().getItemOrder().isRefrigeration();
        for (Stop s : destinations) {
            for (ItemDelivery itemDelivery : s.getLoadList())
                if (itemDelivery.getItemOrder().isRefrigeration() != update)
                    throw new IllegalStateException("All items in a delivery must be of the same refrigeration type");
            for (ItemDelivery itemDelivery : s.getUnloadList())
                if (itemDelivery.getItemOrder().isRefrigeration() != update)
                    throw new IllegalStateException("All items in a delivery must be of the same refrigeration type");
        }
        return update;
    }

    public void addItemToDelivery(ItemDelivery itemDelivery) {
        Stop stop1 = null, stop2 = null;
        for (Stop s : getDestinations()) {
            if (s.getDestination().getAddress().equals(itemDelivery.getSource()) && stop1 == null) stop1 = s;
            else if (s.getDestination().getAddress().equals(itemDelivery.getDestination()) && stop1 != null)
                stop2 = s;
        }
        if (stop1 == null) {
            itemDelivery.setStatus(IDStatus.CANCELED);
            throw new IllegalArgumentException("Missing loading stop");
        }
        if (stop2 == null) {
            itemDelivery.setStatus(IDStatus.CANCELED);
            throw new IllegalArgumentException("Missing unloading stop");
        }
        stop1.addItemDelivery(itemDelivery, true);
        stop2.addItemDelivery(itemDelivery, false);
    }

    public HashMap<Integer, Integer> removeItemDelivery(int itemOrderId) {
        boolean found = false;
        HashMap<Integer, Integer> itemsToUpdate = new HashMap<>();
        for (Stop s : getDestinations()) {
            for (ItemDelivery itemDelivery : s.getLoadList()) {
                if (itemDelivery.getItemOrder().getId() == itemOrderId) {
                    found = true;
                    s.removeItemDelivery(itemDelivery, true);
                    itemsToUpdate.put(s.getId(), itemDelivery.getId());
                    break;
                }
            }
            for (ItemDelivery itemDelivery : s.getUnloadList()) {
                if (itemDelivery.getItemOrder().getId() == itemOrderId) {
                    found = true;
                    s.removeItemDelivery(itemDelivery, false);
                    itemsToUpdate.put(s.getId(), itemDelivery.getId());
                    break;
                }
            }
        }
        if (!found)
            throw new NoSuchElementException("itemOrder doesn't exist in the delivery");
        return itemsToUpdate;
    }

    public void cancelPendingDelivery() {
        for (Stop s : destinations) {
            for (ItemDelivery itemDelivery : s.getLoadList()) itemDelivery.setStatus(IDStatus.CANCELED);
            for (ItemDelivery itemDelivery : s.getUnloadList()) itemDelivery.setStatus(IDStatus.CANCELED);
            s.setStatus(Stop.StopStatus.CANCELED);
        }
        setStatus(Status.CANCELED);
    }

    public void cancelApprovedDelivery() {
        LinkedList<ItemDelivery> items = new LinkedList<>();
        for (Stop s : destinations) {
            for (ItemDelivery itemDelivery : s.getLoadList()) {
                itemDelivery.setStatus(IDStatus.CANCELED);
                itemDelivery.getItemOrder().setStatus(IOStatus.PENDING);
                if (!items.contains(itemDelivery)) items.add(itemDelivery);
            }
            for (ItemDelivery itemDelivery : s.getUnloadList()) {
                itemDelivery.setStatus(IDStatus.CANCELED);
                itemDelivery.getItemOrder().setStatus(IOStatus.PENDING);
                if (!items.contains(itemDelivery)) items.add(itemDelivery);
            }
            s.setStatus(Stop.StopStatus.CANCELED);
        }
        for (ItemDelivery itemDelivery : items) {
            if (itemDelivery.getItemOrder().getItemDelivery2() != -1) itemDelivery.getItemOrder().setItemDelivery2(-1);
            else itemDelivery.getItemOrder().setItemDelivery1(-1);
        }
        if (destinations.getFirst().getDestination().getType() == Site.Type.CENTER) {
            for (ItemDelivery itemDelivery : destinations.getFirst().getLoadList()) {
                itemDelivery.getItemOrder().setStatus(IOStatus.FIRSTAPPROVED);
            }
        }
        setStatus(Status.CANCELED);
    }

    public Stop visitStop(String address, float weight) {
        Stop stop = null;
        for (Stop s : destinations) {
            if (s.getStatus() != Stop.StopStatus.VISITED) {
                stop = s;
                break;
            }
        }
        if (stop == null)
            throw new NoSuchElementException("Stop is not part of the delivery");
        if (!stop.getDestination().getAddress().equals(address))
            throw new IllegalArgumentException("Cannot visit a stop before visiting all previous stops");
        LinkedList<ItemDelivery> items;
        if (weight <= maxWeight) {
            items = stop.visit(true);
            if (getStatus() == Status.OVERWEIGHT)
                setStatus(Status.APPROVED);
        } else {
            items = stop.visit(false);
            setStatus(Status.OVERWEIGHT);
        }
        if (destinations.getLast().getStatus() == Stop.StopStatus.VISITED)
            setStatus(Status.COMPLETED);
        return stop;
    }

    public boolean isInDelivery(ItemOrder order) {
        for (Stop s : destinations) {
            for (ItemDelivery itemDelivery : s.getUnloadList())
                if (order.getId() == itemDelivery.getItemOrder().getId())
                    return true;
            for (ItemDelivery itemDelivery : s.getLoadList())
                if (order.getId() == itemDelivery.getItemOrder().getId())
                    return true;
        }
        return false;
    }

    public LocalDateTime calculateTime(LocalDateTime startTime, Site source, Site dest) {
        double distance = source.getCoordinate().geographicDistance(dest.getCoordinate());
        double time = distance / 80 * 60 + 30;
        long timeRoundOff = Math.round(time);
        LocalDateTime newTime = startTime.plusMinutes(timeRoundOff);
        if (!newTime.toLocalDate().equals(startTime.toLocalDate()))
            throw new IllegalArgumentException("delivery can't continue after midnight of next day");
        return newTime;
    }

    public boolean containsSite(String address) {
        for (Stop stop : destinations) {
            if (stop.getDestination().getAddress().equals(address)) return true;
        }
        return false;
    }

    public void backToPendingDelivery() {
        LinkedList<ItemDelivery> items = new LinkedList<>();
        for (Stop s : destinations) {
            for (ItemDelivery itemDelivery : s.getLoadList()) {
                itemDelivery.setStatus(IDStatus.PENDING);
                itemDelivery.getItemOrder().setStatus(IOStatus.PENDING);
                if (!items.contains(itemDelivery)) items.add(itemDelivery);
            }
            for (ItemDelivery itemDelivery : s.getUnloadList()) {
                itemDelivery.setStatus(IDStatus.PENDING);
                itemDelivery.getItemOrder().setStatus(IOStatus.PENDING);
                if (!items.contains(itemDelivery)) items.add(itemDelivery);
            }
            s.setStatus(Stop.StopStatus.PENDING);
        }
        for (ItemDelivery itemDelivery : items) {
            if (itemDelivery.getItemOrder().getItemDelivery2() != -1)
                itemDelivery.getItemOrder().setItemDelivery2(-1);
            else
                itemDelivery.getItemOrder().setItemDelivery1(-1);
        }
        if (destinations.getFirst().getDestination().getType() == Site.Type.CENTER) {
            for (ItemDelivery itemDelivery : destinations.getFirst().getLoadList()) {
                itemDelivery.getItemOrder().setStatus(IOStatus.FIRSTAPPROVED);
            }
        }
        setStatus(Status.PENDING);
    }

    public void setDestinations(LinkedList<Stop> destinations) {
        this.destinations = destinations;
    }

    enum Status {PENDING, APPROVED, COMPLETED, CANCELED, OVERWEIGHT}
}
