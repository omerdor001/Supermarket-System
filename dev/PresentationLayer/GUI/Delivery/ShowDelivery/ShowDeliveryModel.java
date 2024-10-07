package PresentationLayer.GUI.Delivery.ShowDelivery;

import PresentationLayer.GUI.Delivery.ShowStop.ShowStopModel;
import PresentationLayer.GUI.Delivery.Site.SiteModel;
import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class ShowDeliveryModel {

    public int id;
    @JsonDeserialize(using = JsonConverter.LocalDateDeserializer.class)
    public LocalDate date;
    @JsonDeserialize(using = JsonConverter.LocalTimeDeserializer.class)
    public LocalTime time;
    public SiteModel source;
    public LinkedList<ShowStopModel> destinations;
    public int truckId;
    public String driverId;
    public float startingWeight;
    public Status status;
    public float maxWeight;

    public ShowDeliveryModel(@JsonProperty("ID") int deliveryId, @JsonProperty("date") LocalDate date, @JsonProperty("time") LocalTime time, @JsonProperty("source") SiteModel siteModel, @JsonProperty("destination") LinkedList<ShowStopModel> destinations, @JsonProperty("truckId") int truckId, @JsonProperty("driverId") String driverId, @JsonProperty("startingWeight") float startingWeight, @JsonProperty("statusOrdinal") Status status, @JsonProperty("maxWeight") float maxWeight) {
        this.id = deliveryId;
        this.date = date;
        this.time = time;
        this.truckId = truckId;
        this.driverId = driverId;
        this.source = siteModel;
        this.destinations = destinations;
        this.startingWeight = startingWeight;
        this.status = status;
        this.maxWeight = maxWeight;
    }

    enum Status {PENDING, APPROVED, COMPLETED, CANCELED, OVERWEIGHT}
}
