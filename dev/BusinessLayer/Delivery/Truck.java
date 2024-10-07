package BusinessLayer.Delivery;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.NoSuchElementException;

enum TruckStatus {UNAVAILABLE, AVAILABLE}

public class Truck {
    private final int id;
    private final String type;
    private final String model;
    private final float netWeight;
    private final float maxWeight;
    @JsonDeserialize(contentUsing = JsonConverter.LocalDateDeserializer.class)
    @JsonSerialize(contentUsing = JsonConverter.LocalDateSerializer.class)
    private final LinkedList<LocalDate> deliveryDates;
    private TruckStatus status;

    public Truck(int id, String type, String model, float netWeight, float maxWeight) {
        if (netWeight <= 0)
            throw new IllegalArgumentException("netWeight is illegal");
        if (maxWeight <= 0)
            throw new IllegalArgumentException("maxWeight is illegal");
        if (maxWeight <= netWeight)
            throw new IllegalArgumentException("maxWeight must be bigger than netWeight");
        this.id = id;
        this.type = type;
        this.model = model;
        this.netWeight = netWeight;
        this.maxWeight = maxWeight;
        this.status = TruckStatus.AVAILABLE;
        this.deliveryDates = new LinkedList<>();
    }

    public Truck(int id, String model, String type, float netWeight, float maxWeight, LinkedList<LocalDate> deliveryDates, int status) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.netWeight = netWeight;
        this.maxWeight = maxWeight;
        this.status = TruckStatus.values()[status];
        this.deliveryDates = deliveryDates;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public float getNetWeight() {
        return netWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public TruckStatus getStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
        this.status = status;
    }

    public int getStatusOrdinal() {
        return status.ordinal();
    }

    public LinkedList<LocalDate> getDeliveryDates() {
        return deliveryDates;
    }

    public void addDeliveryDate(LocalDate deliveryDate) {
        if (deliveryDates.contains(deliveryDate))
            throw new IllegalArgumentException("deliveryDate is already exist");
        this.deliveryDates.add(deliveryDate);
    }

    public void removeDeliveryDate(LocalDate deliveryDate) {
        if (!deliveryDates.contains(deliveryDate))
            throw new NoSuchElementException("deliveryDate doesn't exist");
        this.deliveryDates.remove(deliveryDate);
    }

    public void updateStatus(int status) {
        if (status != 0 && status != 1)
            throw new IllegalArgumentException("status is illegal");
        setStatus(TruckStatus.values()[status]);
    }

    public void checkDate(LocalDate date) {
        if (getDeliveryDates().contains(date))
            throw new IllegalStateException("Truck is unavailable");
    }
}
