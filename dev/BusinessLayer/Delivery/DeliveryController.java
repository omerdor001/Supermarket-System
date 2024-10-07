package BusinessLayer.Delivery;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import BusinessLayer.Human_Resources.Driver;
import BusinessLayer.Human_Resources.ShiftController;
import DataAccessLayer.DALFacade;
import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DeliveryController {

    private HashMap<Integer, Delivery> deliveries;
    private HashMap<Integer, DeliveryOrder> deliveryOrders;
    private HashMap<Integer, ItemOrder> itemOrders;
    private HashMap<Integer, ItemDelivery> itemDeliveries;
    private final ResourceController resources;
    private final ShiftController shifts;
    private final DALFacade dalController;
    private int nextDeliveryId;
    private int nextStopId;
    private int nextDeliveryOrderId;
    private int nextItemOrderId;
    private int nextItemDeliveryId;

    public DeliveryController(ResourceController resources, ShiftController shifts) {
        this.resources = resources;
        this.shifts = shifts;
        this.deliveries = new HashMap<>();
        this.deliveryOrders = new HashMap<>();
        this.itemOrders = new HashMap<>();
        this.itemDeliveries = new HashMap<>();
        this.nextDeliveryId = 0;
        this.nextStopId = 0;
        this.nextDeliveryOrderId = 0;
        this.nextItemOrderId = 0;
        this.nextItemDeliveryId = 0;
        this.dalController = DALFacade.getInstance();
        loadData();
    }

    private void setDeliveries(HashMap<Integer, Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    private void setDeliveryOrders(HashMap<Integer, DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    private void setItemOrders(HashMap<Integer, ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    private void setItemDeliveries(HashMap<Integer, ItemDelivery> itemDeliveries) {
        this.itemDeliveries = itemDeliveries;
    }

    private void setNextDeliveryId(int nextDeliveryId) {
        this.nextDeliveryId = nextDeliveryId;
    }

    private void setNextStopId(int nextStopId) {
        this.nextStopId = nextStopId;
    }

    private void setNextDeliveryOrderId(int nextDeliveryOrderId) {
        this.nextDeliveryOrderId = nextDeliveryOrderId;
    }

    private void setNextItemOrderId(int nextItemOrderId) {
        this.nextItemOrderId = nextItemOrderId;
    }

    private void setNextItemDeliveryId(int nextItemDeliveryId) {
        this.nextItemDeliveryId = nextItemDeliveryId;
    }

    public void loadData(){
        try{
            HashMap <String,Integer> counters = dalController.getAllCounters();
            setNextItemDeliveryId(counters.get("nextItemDeliveryId"));
            setNextItemOrderId(counters.get("nextItemOrderId"));
            setNextDeliveryOrderId(counters.get("nextDeliveryOrderId"));
            setNextStopId(counters.get("nextStopId"));
            setNextDeliveryId(counters.get("nextDeliveryId"));
            setItemOrders(dalController.getAllItemOrders());
            setDeliveryOrders(dalController.getAllDeliveryOrders());
            setItemDeliveries(dalController.getAllItemDeliveries());
            setDeliveries(dalController.getAllDeliveries());
            for (Integer i : deliveries.keySet()){
                Delivery delivery = deliveries.get(i);
                if (delivery.getStatus() != Delivery.Status.CANCELED){
                    LinkedList <Stop> fixStops = new LinkedList<>();
                    for (Stop s : delivery.getDestinations())
                        if (s.getStatus()!= Stop.StopStatus.CANCELED)
                            fixStops.add(s);
                    delivery.setDestinations(fixStops);
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException("DAL ERROR - DeliveryController");
        }
    }

    //get delivery order json, parse it into item orders, then add stops into delivery and then add items into
    public HashMap<Integer, Delivery> getDeliveries() {
        return deliveries;
    }

    public HashMap<Integer, DeliveryOrder> getDeliveryOrders() {
        return deliveryOrders;
    }

    public HashMap<Integer, ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public HashMap<Integer, ItemDelivery> getItemDeliveries() {
        return itemDeliveries;
    }

    public void parseOrder(String order) throws IOException {
        DeliveryOrder.Shipment shipment = JsonConverter.fromJson(order, DeliveryOrder.Shipment.class);
        if (resources.getSite(shipment.getSourceAddress()) == null)
            throw new NoSuchElementException("source address doesn't exist");
        if (resources.getSite(shipment.getDestAddress()) == null)
            throw new NoSuchElementException("destination address doesn't exist");
        HashMap<Integer, ItemOrder> loadList = new HashMap<>();
        double totalLoadWeight = 0;
        for (DeliveryOrder.Triplet<String, Integer, Double> t : shipment.getProducts()) {
            ItemOrder itemOrder = addItemOrderToSystem(nextItemOrderId, nextDeliveryOrderId, shipment.getSourceAddress(), shipment.getDestAddress(), t.getSecond(), false);
            loadList.put(itemOrder.getId(), itemOrder);
            totalLoadWeight = totalLoadWeight + t.getSecond() * t.getThird();
        }
        addDeliveryOrder(totalLoadWeight, shipment.getSourceAddress(), shipment.getDestAddress(), loadList);
    }

    public void addDeliveryOrder(double totalLoadWeight, String destination, String orderingStore, HashMap<Integer, ItemOrder> items) {
        DeliveryOrder newDeliveryOrder = new DeliveryOrder(nextDeliveryOrderId, totalLoadWeight, destination, orderingStore, items);
        deliveryOrders.put(nextDeliveryOrderId, newDeliveryOrder);
        nextDeliveryOrderId++;
        dalController.insertDeliveryOrder(newDeliveryOrder);
        dalController.updateCounter("nextDeliveryOrderId", nextDeliveryOrderId);
    }

    public ItemOrder addItemOrderToSystem(int itemId, int orderId, String source, String dest, int quantity, boolean refrigeration) {
        ItemOrder output = new ItemOrder(nextItemOrderId, itemId, orderId, source, dest, quantity, refrigeration);
        itemOrders.put(nextItemOrderId, output);
        nextItemOrderId++;
        dalController.insertItemOrder(output);
        dalController.updateCounter("nextItemOrderId", nextItemOrderId);
        return output;
    }

    //region Delivery basic functions
    public void addDelivery(LocalDate date, LocalTime time, int truckId, String driverId, String address) {
        Site source = resources.getSite(address);
        resources.getDriver(driverId);
        resources.getTruck(truckId);
        Delivery newDelivery = new Delivery(nextDeliveryId, date, time, truckId, driverId, source, nextStopId);
        deliveries.put(nextDeliveryId, newDelivery);
        nextDeliveryId++;
        nextStopId++;
        dalController.insertDelivery(newDelivery);
        dalController.insertStop(newDelivery.getDestinations().get(0));
        dalController.updateCounter("nextDeliveryId", nextDeliveryId);
        dalController.updateCounter("nextStopId", nextStopId);
    }

    public Delivery getDelivery(int deliveryId) {
        Delivery find = deliveries.get(deliveryId);
        if (find == null)
            throw new NoSuchElementException("BusinessLayer.PresentationLayer.GUI.Delivery does not exist");
        return find;
    }

    public void updateDriver(int deliveryId, String driverId) {
        Driver driver = resources.getDriver(driverId);
        if (driver == null)
            throw new NoSuchElementException("driver doesn't exist");
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery == null)
            throw new NoSuchElementException("delivery doesn't exist");
        if (delivery.getStatus() != Delivery.Status.PENDING)
            throw new IllegalStateException("cannot update driver to unpending delivery");
        delivery.setDriverId(driverId);
        dalController.updateDelivery(delivery, "DriverId");
    }

    public void updateTruck(int deliveryId, int truckId) {
        resources.getTruck(truckId);
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery == null)
            throw new NoSuchElementException("delivery doesn't exist");
        if (delivery.getStatus() != Delivery.Status.PENDING)
            throw new IllegalStateException("cannot update truck to unpending delivery");
        delivery.setTruckId(truckId);
        dalController.updateDelivery(delivery, "TruckId");
    }

    public void addStopToDelivery(int deliveryId, String address) {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery != null) {
            if (delivery.getStatus() != Delivery.Status.PENDING)
                throw new IllegalStateException("cannot add stop to unpending delivery");
            Site stop = resources.getSite(address);
            if (stop != null) {
                delivery.addStop(stop, nextStopId);
                dalController.insertStop(delivery.getStop(nextStopId));
                nextStopId++;
                dalController.updateCounter("nextStopId", nextStopId);
            } else
                throw new NoSuchElementException("stop doesn't exist");
        } else
            throw new NoSuchElementException("No such delivery ID");
    }

    public void removeStopFromDelivery(int deliveryId, String address) {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery != null) {
            if (delivery.getStatus() != Delivery.Status.PENDING)
                throw new IllegalStateException("cannot remove stop from unpending delivery");
            Site stop = resources.getSite(address);
            if (stop != null) {
                Stop update = delivery.removeStop(stop);
                dalController.updateStop(update, "Status");
                for (ItemDelivery i : update.getLoadList())
                    dalController.updateItemDeliveryStatus(i);
                for (ItemDelivery i : update.getUnloadList())
                    dalController.updateItemDeliveryStatus(i);
            } else
                throw new NoSuchElementException("No such site");
        } else
            throw new NoSuchElementException("No such delivery ID");
    }

    public void updateArriveTimeToStop(int deliveryId, String address, LocalTime time) {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery != null) {
            int id = delivery.updateArriveTimeToStop(address, time);
            dalController.updateStop(delivery.getStop(id), "ArriveTime");
        }
        throw new NoSuchElementException("No such delivery ID");
    }

    public void addItemToDelivery(int deliveryId, int itemOrderId, boolean direct, int stage) {
        if (stage < 0 || stage > 1)
            throw new IllegalArgumentException("Item delivery stages can only be 0 if to logistic center or 1 if from logistic center");
        if (stage == 1 && direct)
            throw new IllegalArgumentException("Cannot split a direct delivery");
        Delivery delivery = deliveries.get(deliveryId);
        ItemOrder order = itemOrders.get(itemOrderId);
        if (delivery != null) {
            if (delivery.getStatus() != Delivery.Status.PENDING)
                throw new IllegalStateException("cannot add item to unpending delivery");
            if (order != null) {
                if (delivery.isInDelivery(order))
                    throw new IllegalStateException("item order is already in the delivery");
                ItemDelivery itemDelivery;
                if (direct)
                    itemDelivery = new ItemDelivery(nextItemDeliveryId, deliveryId, order, order.getSource(), order.getDestination());
                else {
                    if (stage == 0)
                        itemDelivery = new ItemDelivery(nextItemDeliveryId, deliveryId, order, order.getSource(), resources.getLogisticCenter());
                    else
                        itemDelivery = new ItemDelivery(nextItemDeliveryId, deliveryId, order, resources.getLogisticCenter(), order.getDestination());
                }
                delivery.addItemToDelivery(itemDelivery);
                itemDeliveries.put(nextItemDeliveryId, itemDelivery);
                nextItemDeliveryId++;
                dalController.insertItemDelivery(itemDelivery);
                dalController.updateCounter("nextItemDeliveryId", nextItemDeliveryId);
                for (Stop s : delivery.getDestinations()) {
                    if (s.getLoadList().contains(itemDelivery))
                        dalController.insertStopLoadItem(s.getId(), itemDelivery.getId());
                    else if (s.getUnloadList().contains(itemDelivery))
                        dalController.insertStopUnloadItem(s.getId(), itemDelivery.getId());
                }
            } else
                throw new NoSuchElementException("No such item order");
        } else
            throw new NoSuchElementException("No such delivery ID");
    }

    public void removeItemFromDelivery(int deliveryId, int itemOrderId) {
        Delivery delivery = deliveries.get(deliveryId);
        ItemOrder itemOrder = itemOrders.get(itemOrderId);
        if (delivery != null) {
            if (delivery.getStatus() != Delivery.Status.PENDING)
                throw new IllegalStateException("cannot remove item from unpending delivery");
            if (itemOrder != null) {
                HashMap<Integer, Integer> updateMap = delivery.removeItemDelivery(itemOrderId);
                for (Integer i : updateMap.keySet()) {
                    dalController.updateItemDeliveryStatus(itemDeliveries.get(updateMap.get(i)));
                    dalController.deleteStopLoadItem(i, updateMap.get(i));
                    dalController.deleteStopUnloadItem(i, updateMap.get(i));
                }
            } else
                throw new NoSuchElementException("No such item order");
        } else
            throw new NoSuchElementException("No such delivery ID");
    }

    public void approveDelivery(int deliveryId) throws Exception {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery != null) {
            if (delivery.getStatus() != Delivery.Status.PENDING)
                throw new IllegalStateException("Can only approve a pending delivery");
            if (delivery.getDestinations().getFirst().getLoadList().size() == 0)
                throw new IllegalStateException("Source must load items");
            if (!shifts.isDriverScheduled(delivery.getDriverId(), delivery.getDate()))
                throw new IllegalArgumentException("Driver isn't in the shift");
            for (Stop stop : delivery.getDestinations())
                if (stop.getDestination().getType() == Site.Type.STORE)
                    shifts.isStoreKeeperExist(delivery.getDate(), stop.getDestination().getAddress(), stop.getArriveTime().toLocalTime());
            resources.approveDelivery(delivery.getDriverId(), delivery.getTruckId(), delivery.getDate(), delivery.assertDeliveryType());
            delivery.approveDelivery();
            delivery.setMaxWeight(resources.getTruck(delivery.getTruckId()).getMaxWeight());
            resources.addTruckDeliveryDate(delivery.getTruckId(), delivery.getDate());
            resources.getEmployeeController().addDriverDeliveryDate(delivery.getDriverId(), delivery.getDate());
            dalController.updateDelivery(delivery, "Status");
            dalController.updateDelivery(delivery, "MaxWeight");
            for (Stop s : delivery.getDestinations()) {
                dalController.updateStop(s, "Status");
                for (ItemDelivery i : s.getLoadList()) {
                    dalController.updateItemDeliveryStatus(i);
                    dalController.updateItemOrder(i.getItemOrder(), "Status");
                    dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery1Id");
                    dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery2Id");
                }
                for (ItemDelivery i : s.getUnloadList()) {
                    dalController.updateItemDeliveryStatus(i);
                    dalController.updateItemOrder(i.getItemOrder(), "Status");
                    dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery1Id");
                    dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery2Id");
                }
            }
        } else
            throw new NoSuchElementException("No such delivery ID");
    }

    public void cancelDelivery(int deliveryId) {
        Delivery toCancel = deliveries.get(deliveryId);
        if (toCancel != null) {
            if (toCancel.getStatus() == Delivery.Status.PENDING) {
                toCancel.cancelPendingDelivery();
                dalController.updateDelivery(toCancel, "Status");
                for (Stop s : toCancel.getDestinations()) {
                    dalController.updateStop(s, "Status");
                    for (ItemDelivery i : s.getLoadList()) {
                        dalController.deleteStopLoadItem(s.getId(), i.getId());
                        dalController.updateItemDeliveryStatus(i);
                    }
                    for (ItemDelivery i : s.getUnloadList()) {
                        dalController.deleteStopUnloadItem(s.getId(), i.getId());
                        dalController.updateItemDeliveryStatus(i);
                    }
                }
            } else if (toCancel.getStatus() == Delivery.Status.APPROVED) {
                if (toCancel.getDestinations().getLast().getDestination().getType() == Site.Type.CENTER) {
                    for (ItemDelivery itemDelivery : toCancel.getDestinations().getLast().getUnloadList()) {
                        ItemOrder itemOrder = itemDelivery.getItemOrder();
                        ItemDelivery toRemove = itemDeliveries.get(itemOrder.getItemDelivery2());
                        if (deliveries.get(toRemove.getDeliveryId()).getStatus() == Delivery.Status.APPROVED) {
                            HashMap<Integer, Integer> updateItems = deliveries.get(toRemove.getDeliveryId()).removeItemDelivery(itemOrder.getId());
                            for (Integer i : updateItems.keySet()) {
                                dalController.updateItemDeliveryStatus(itemDeliveries.get(updateItems.get(i)));
                                dalController.deleteStopLoadItem(i, updateItems.get(i));
                                dalController.deleteStopUnloadItem(i, updateItems.get(i));
                            }
                            itemOrder.setItemDelivery2(-1);
                            dalController.updateItemOrder(itemOrder, "ItemDelivery2Id");
                        }
                    }
                }
                toCancel.cancelApprovedDelivery();
                resources.removeTruckDeliveryDate(toCancel.getTruckId(), toCancel.getDate());
                resources.getEmployeeController().removeDriverDeliveryDate(toCancel.getDriverId(), toCancel.getDate());
                dalController.updateDelivery(toCancel, "Status");
                for (Stop s : toCancel.getDestinations()) {
                    dalController.updateStop(s, "Status");
                    for (ItemDelivery i : s.getLoadList()) {
                        dalController.updateItemDeliveryStatus(i);
                        dalController.deleteStopLoadItem(s.getId(), i.getId());
                        dalController.updateItemOrder(i.getItemOrder(), "Status");
                        dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery1Id");
                        dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery2Id");
                    }
                    for (ItemDelivery i : s.getUnloadList()) {
                        dalController.updateItemDeliveryStatus(i);
                        dalController.deleteStopUnloadItem(s.getId(), i.getId());
                        dalController.updateItemOrder(i.getItemOrder(), "Status");
                        dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery1Id");
                        dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery2Id");
                    }
                }
            } else
                throw new IllegalStateException("cannot cancel this delivery");
        } else
            throw new NoSuchElementException("No such delivery ID");
    }

    public void visitStop(int deliveryId, String address, float weight) {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery != null) {
            if (delivery.getStatus() != Delivery.Status.APPROVED && delivery.getStatus() != Delivery.Status.OVERWEIGHT)
                throw new IllegalArgumentException("Cannot visit stops in an unapproved delivery");
            if (delivery.getStatus() == Delivery.Status.COMPLETED)
                throw new IllegalArgumentException("Cannot visit a stop in a completed delivery");
            Stop update = delivery.visitStop(address, weight);
            if (update.getUnloadList() != null) {
                updateDeliveryOrders(update.getUnloadList());
                dalController.updateStop(update, "Status");
                for (ItemDelivery i : update.getLoadList()) {
                    dalController.updateItemDeliveryStatus(i);
                    dalController.updateItemOrder(i.getItemOrder(), "Status");
                    dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery1Id");
                    dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery2Id");
                }
                for (ItemDelivery i : update.getUnloadList()) {
                    dalController.updateItemDeliveryStatus(i);
                    dalController.updateItemOrder(i.getItemOrder(), "Status");
                    dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery1Id");
                    dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery2Id");
                }
                if (delivery.getStatus() == Delivery.Status.COMPLETED)
                    dalController.updateDelivery(delivery, "Status");
            }
            if (delivery.getStatus() == Delivery.Status.OVERWEIGHT)
                throw new IllegalStateException("Truck is overweight with weight: " + weight + " at stop " + address + " possible solutions: switch truck, remove this stop, remove items from this stop");
        } else
            throw new NoSuchElementException("No such delivery ID");
    }

    public void updateDeliveryOrders(LinkedList<ItemDelivery> items) {
        for (ItemDelivery itemDelivery : items) {
            if (itemDelivery.getStatus() == IDStatus.COMPLETED) {
                itemDelivery.getItemOrder().setStatus(IOStatus.COMPLETED);
                int orderId = itemDelivery.getItemOrder().getDeliveryOrderId();
                DeliveryOrder d = deliveryOrders.get(orderId);
                if (d.checkCompleted())
                    dalController.updateDeliveryOrderStatus(d);
            }
        }
    }
    //endregion

    //region Handle overweight delivery
    public void switchTruck(int deliveryId, int truckId, float currentWeight) {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery == null)
            throw new IllegalArgumentException("No such delivery");
        switchTruckDriver(deliveryId, truckId, delivery.getDriverId(), currentWeight);
    }

    public void switchTruckDriver(int deliveryId, int truckId, String driverId, float currentWeight) {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery == null)
            throw new IllegalArgumentException("No such delivery");
        if (delivery.getStatus() != Delivery.Status.OVERWEIGHT)
            throw new IllegalStateException("Can only change problematic deliveries");
        if (!shifts.isDriverScheduled(driverId, delivery.getDate()))
            throw new IllegalArgumentException("Driver isn't in the shift");
        Truck truck = resources.getTruck(truckId);
        Driver driver = resources.getDriver(driverId);
        String oldDriver = delivery.getDriverId();
        truck.checkDate(delivery.getDate());
        if (truck.getMaxWeight() < currentWeight)
            throw new IllegalArgumentException("Truck max weight is lower than current weight");
        boolean refrigeration = delivery.getDestinations().getFirst().getLoadList().getFirst().getItemOrder().isRefrigeration();
        resources.checkCompatibility(driverId, truckId, refrigeration);
        if (!driverId.equals(oldDriver)) {
            driver.checkDate(delivery.getDate());
            resources.getEmployeeController().addDriverDeliveryDate(driverId,delivery.getDate());
        }
        resources.addTruckDeliveryDate(truckId,delivery.getDate());
        delivery.setTruckId(truckId);
        delivery.setDriverId(driverId);
        delivery.setMaxWeight(truck.getMaxWeight());
        delivery.setStatus(Delivery.Status.APPROVED);
        dalController.updateDelivery(delivery, "Status");
        dalController.updateDelivery(delivery, "TruckID");
        dalController.updateDelivery(delivery, "DriverId");
        dalController.updateDelivery(delivery, "MaxWeight");
    }

    public void skipStopLoad(int deliveryId) {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery == null)
            throw new IllegalArgumentException("No such delivery");
        if (delivery.getStatus() != Delivery.Status.OVERWEIGHT)
            throw new IllegalStateException("Cannot switch items from non overweight delivery");
        for (Stop s : delivery.getDestinations()) {
            if (s.getStatus() != Stop.StopStatus.VISITED) {
                for (ItemDelivery itemDelivery : s.getUnloadList())
                    leaveItem(deliveryId, itemDelivery.getItemOrder().getId());
                s.setStatus(Stop.StopStatus.VISITED);
                delivery.setStatus(Delivery.Status.APPROVED);
                if (delivery.getDestinations().getLast().getUnloadList().isEmpty()) {
                    delivery.removeStop(delivery.getDestinations().getLast().getDestination());
                    delivery.setStatus(Delivery.Status.COMPLETED);
                }
                dalController.updateStop(s, "Status");
                dalController.updateDelivery(delivery, "Status");
                return;
            }
        }
        throw new IllegalStateException("All stops for delivery were already visited");
    }

    public void leaveItem(int deliveryId, int itemOrderId) {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery == null)
            throw new IllegalArgumentException("No such delivery");
        ItemOrder itemOrder = itemOrders.get(itemOrderId);
        if (itemOrder == null)
            throw new IllegalArgumentException("No such item in delivery");
        if (delivery.getStatus() != Delivery.Status.OVERWEIGHT)
            throw new IllegalStateException("Cannot switch items from non overweight delivery");
        for (Stop s : delivery.getDestinations()) {
            if (s.getStatus() != Stop.StopStatus.VISITED) {
                for (ItemDelivery itemDelivery : s.getLoadList()) {
                    if (itemDelivery.getItemOrder().getId() == itemOrderId) {
                        delivery.removeItemDelivery(itemOrderId);
                        if (itemOrder.getItemDelivery1() == itemDelivery.getId() && itemOrder.getItemDelivery2() == -1) {
                            itemOrder.setStatus(IOStatus.PENDING);
                        } else if (itemOrder.getItemDelivery1() != itemDelivery.getId() && itemOrder.getItemDelivery2() == itemDelivery.getId()) {
                            itemOrder.setStatus(IOStatus.FIRSTAPPROVED);
                        } else if (itemOrder.getItemDelivery1() == itemDelivery.getId() && itemOrder.getItemDelivery2() != -1) {
                            ItemDelivery toRemove = itemDeliveries.get(itemOrder.getItemDelivery2());
                            Delivery toUpdate = deliveries.get(toRemove.getDeliveryId());
                            HashMap<Integer, Integer> itemsToUpdate = toUpdate.removeItemDelivery(itemOrderId);
                            itemOrder.setStatus(IOStatus.PENDING);
                            for (Integer i : itemsToUpdate.keySet()) {
                                dalController.updateItemDeliveryStatus(itemDeliveries.get(itemsToUpdate.get(i)));
                                dalController.deleteStopLoadItem(i, itemsToUpdate.get(i));
                                dalController.deleteStopUnloadItem(i, itemsToUpdate.get(i));
                            }
                        }
                        dalController.updateItemOrder(itemOrder, "Status");
                        dalController.updateItemDeliveryStatus(itemDelivery);
                        dalController.deleteStopLoadItem(s.getId(), itemDelivery.getId());
                        return;
                    }
                }
                throw new NoSuchElementException("Item is not related to this stop");
            }
        }
    }
    //endregion

    public String showDelivery(int deliveryId) throws JsonProcessingException {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery != null) {
            return JsonConverter.toJson(delivery);
        }
        throw new NoSuchElementException("No such delivery ID");
    }

    public String showAllDeliveries() throws JsonProcessingException {
        return JsonConverter.toJson(deliveries);
    }

    public String showDeliveryOrder(int deliveryOrderId) throws JsonProcessingException {
        DeliveryOrder deliveryOrder = deliveryOrders.get(deliveryOrderId);
        if (deliveryOrder != null) {
            return JsonConverter.toJson(deliveryOrder);
        }
        throw new NoSuchElementException("No such deliveryOrder ID");
    }

    public String showAllDeliveryOrders() throws JsonProcessingException {
        return JsonConverter.toJson(deliveryOrders);
    }

    public String showAllItemOrders() throws JsonProcessingException {
        return JsonConverter.toJson(itemOrders);
    }

    public String getDeliveriesByShift(int shiftId) throws JsonProcessingException {
        Site siteShift = resources.getSite(shifts.getBranch(shiftId));
        if (siteShift == null) throw new IllegalArgumentException("address doesn't exist");
        LinkedList<Delivery> output = new LinkedList<>();
        for (int deliveryId : deliveries.keySet()) {
            Delivery delivery = deliveries.get(deliveryId);
            if (delivery.getDate().equals(shifts.getDate(shiftId))) {
                for (Stop stop : delivery.getDestinations()) {
                    if (stop.getDestination().getAddress().equals(siteShift.getAddress()))
                        if (shifts.isInShift(shiftId, stop.getArriveTime().toLocalTime())) {
                            output.add(delivery);
                            break;
                        }
                }
            }
        }
        if (output.isEmpty())
            throw new IllegalArgumentException("No deliveries on this day that stop at this site");
        return JsonConverter.toJson(output);
    }

    public void checkDriverDelivery(String driverId, LocalDate date) {
        Driver driver = resources.getDriver(driverId);
        if (driver.getDeliveryDates().contains(date)) {
            int deliveryId = findDriverDelivery(driverId, date);
            if (deliveryId != -1)
                backToPending(deliveryId);
        }
    }

    private void backToPending(int deliveryId) {
        Delivery toUpdate = deliveries.get(deliveryId);
        if (toUpdate != null) {
            if (toUpdate.getStatus() == Delivery.Status.APPROVED) {
                if (toUpdate.getDestinations().getLast().getDestination().getType() == Site.Type.CENTER) {
                    for (ItemDelivery itemDelivery : toUpdate.getDestinations().getLast().getUnloadList()) {
                        ItemOrder itemOrder = itemDelivery.getItemOrder();
                        ItemDelivery toRemove = itemDeliveries.get(itemOrder.getItemDelivery2());
                        if (deliveries.get(toRemove.getDeliveryId()).getStatus() == Delivery.Status.APPROVED) {
                            HashMap<Integer, Integer> updateItems = deliveries.get(toRemove.getDeliveryId()).removeItemDelivery(itemOrder.getId());
                            for (Integer i : updateItems.keySet()) {
                                dalController.updateItemDeliveryStatus(itemDeliveries.get(updateItems.get(i)));
                                dalController.deleteStopLoadItem(i, updateItems.get(i));
                                dalController.deleteStopUnloadItem(i, updateItems.get(i));
                            }
                            itemOrder.setItemDelivery2(-1);
                            dalController.updateItemOrder(itemOrder, "ItemDelivery2Id");
                        }
                    }
                }
                toUpdate.backToPendingDelivery();
                resources.removeTruckDeliveryDate(toUpdate.getTruckId(), toUpdate.getDate());
                resources.getEmployeeController().removeDriverDeliveryDate(toUpdate.getDriverId(), toUpdate.getDate());
                dalController.updateDelivery(toUpdate, "Status");
                for (Stop s : toUpdate.getDestinations()) {
                    dalController.updateStop(s, "Status");
                    for (ItemDelivery i : s.getLoadList()) {
                        dalController.deleteStopLoadItem(s.getId(), i.getId());
                        dalController.updateItemDeliveryStatus(i);
                        dalController.updateItemOrder(i.getItemOrder(), "Status");
                        dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery1Id");
                        dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery2Id");
                    }
                    for (ItemDelivery i : s.getUnloadList()) {
                        dalController.deleteStopUnloadItem(s.getId(), i.getId());
                        dalController.updateItemDeliveryStatus(i);
                        dalController.updateItemOrder(i.getItemOrder(), "Status");
                        dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery1Id");
                        dalController.updateItemOrder(i.getItemOrder(), "ItemDelivery2Id");
                    }
                }
            } else
                throw new IllegalStateException("cannot update this delivery to pending");
        } else
            throw new NoSuchElementException("No such delivery ID");
    }

    private int findDriverDelivery(String driverId, LocalDate date) {
        for (int deliveryId : deliveries.keySet()) {
            if (deliveries.get(deliveryId).getDriverId().equals(driverId) && deliveries.get(deliveryId).getDate().equals(date))
                return deliveryId;
        }
        return -1;
    }

    public void deliveriesToCancel(String address, LocalDate date) {
        for (int deliveryId : deliveries.keySet()) {
            Delivery delivery = deliveries.get(deliveryId);
            if (delivery.getDate().equals(date) && delivery.containsSite(address) && delivery.getStatus() == Delivery.Status.APPROVED)
                backToPending(delivery.getID());
        }
    }
}
