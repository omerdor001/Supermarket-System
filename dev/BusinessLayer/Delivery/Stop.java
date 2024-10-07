package BusinessLayer.Delivery;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Stop {
    private final int id;
    private final int deliveryId;
    private final Site destination;
    private final LinkedList<ItemDelivery> loadList;
    private final LinkedList<ItemDelivery> unLoadList;
    private StopStatus status;
    @JsonDeserialize(using = JsonConverter.LocalDateTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalDateTimeSerializer.class)
    private LocalDateTime arriveTime;

    public Stop(int id, int deliveryId, Site destination, LocalDateTime arriveTime) {
        this.id = id;
        this.deliveryId = deliveryId;
        this.destination = destination;
        this.loadList = new LinkedList<>();
        this.unLoadList = new LinkedList<>();
        this.status = StopStatus.PENDING;
        this.arriveTime = arriveTime;
    }

    public Stop(int stpId, int stpDeliveryId, Site destination, int stpStatus, LocalDateTime stpArriveTime, LinkedList<ItemDelivery> loadItems, LinkedList<ItemDelivery> unloadItems) {
        this.id = stpId;
        this.deliveryId = stpDeliveryId;
        this.destination = destination;
        this.loadList = loadItems;
        this.unLoadList = unloadItems;
        this.status = StopStatus.values()[stpStatus];
        this.arriveTime = stpArriveTime;
    }

    public StopStatus getStatus() {
        return status;
    }

    public void setStatus(StopStatus status) {
        this.status = status;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public Site getDestination() {
        return destination;
    }

    public LinkedList<ItemDelivery> getLoadList() {
        return loadList;
    }

    public LinkedList<ItemDelivery> getUnloadList() {
        return unLoadList;
    }

    public LocalDateTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDateTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getId() {
        return id;
    }

    public void addItemDelivery(ItemDelivery itemDelivery, boolean load) {
        if (load && !getLoadList().contains(itemDelivery))
            getLoadList().add(itemDelivery);
        else if (!load && !getUnloadList().contains(itemDelivery))
            getUnloadList().add(itemDelivery);
        else
            throw new IllegalArgumentException("Item was already assigned to this delivery");
    }

    public void removeItemDelivery(ItemDelivery itemDelivery, boolean load) {
        if (load)
            getLoadList().remove(itemDelivery);
        else
            getUnloadList().remove(itemDelivery);
        itemDelivery.setStatus(IDStatus.CANCELED);
    }

    public LinkedList<ItemDelivery> visit(boolean standard) {
        for (ItemDelivery itemDelivery : getUnloadList()) {
            if (itemDelivery.getStatus() == IDStatus.COMPLETED)
                continue;
            itemDelivery.setStatus(IDStatus.COMPLETED);
            itemDelivery.visit();
        }
        if (standard)
            setStatus(StopStatus.VISITED);
        return getUnloadList();
    }

    public void updateItemOrder() {
        for (ItemDelivery itemDelivery : getLoadList()) {
            if (itemDelivery.getItemOrder().getItemDelivery1() == -1)
                itemDelivery.getItemOrder().setItemDelivery1(itemDelivery.getId());
            else itemDelivery.getItemOrder().setItemDelivery2(itemDelivery.getId());
        }
    }

    public enum StopStatus {PENDING, VISITED, CANCELED, APPROVED}
}
