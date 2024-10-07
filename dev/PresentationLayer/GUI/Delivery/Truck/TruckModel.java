package PresentationLayer.GUI.Delivery.Truck;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.LinkedList;

public class TruckModel {
    public int id;
    public String type;
    public String model;
    public float netWeight;
    public float maxWeight;
    @JsonDeserialize(contentUsing = JsonConverter.LocalDateDeserializer.class)
    @JsonSerialize(contentUsing = JsonConverter.LocalDateSerializer.class)
    public LinkedList<LocalDate> deliveryDates;
    public TruckStatusModel status;
    public int statusOrdinal;

    public TruckModel(@JsonProperty("id") int id, @JsonProperty("type") String type, @JsonProperty("model") String model, @JsonProperty("netWeight") float netWeight, @JsonProperty("maxWeight") float maxWeight, @JsonProperty("deliveryDates") LinkedList<LocalDate> deliveryDates, @JsonProperty("status") TruckStatusModel status, @JsonProperty("statusOrdinal") int statusOrdinal) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.netWeight = netWeight;
        this.maxWeight = maxWeight;
        this.status = status;
        this.statusOrdinal = statusOrdinal;
        this.deliveryDates = deliveryDates;
    }

    enum TruckStatusModel {UNAVAILABLE, AVAILABLE}
}
