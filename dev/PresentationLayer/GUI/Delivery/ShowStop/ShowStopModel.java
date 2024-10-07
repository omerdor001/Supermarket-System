package PresentationLayer.GUI.Delivery.ShowStop;

import PresentationLayer.GUI.Delivery.ShowItems.ItemDeliveryModel;
import PresentationLayer.GUI.Delivery.Site.SiteModel;
import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ShowStopModel {
    public int id;
    public int deliveryId;
    public SiteModel destination;
    public LinkedList<ItemDeliveryModel> loadList;
    public LinkedList<ItemDeliveryModel> unLoadList;
    public StopStatus status;
    @JsonDeserialize(using = JsonConverter.LocalDateTimeDeserializer.class)
    public LocalDateTime arriveTime;

    public ShowStopModel(@JsonProperty("id") int id, @JsonProperty("deliveryId") int deliveryId, @JsonProperty("destination") SiteModel destination, @JsonProperty("loadList") LinkedList<ItemDeliveryModel> loadList, @JsonProperty("unloadList") LinkedList<ItemDeliveryModel> unloadList, @JsonProperty("status") StopStatus status, @JsonProperty("arriveTime") LocalDateTime arriveTime) {
        this.id = id;
        this.deliveryId = deliveryId;
        this.destination = destination;
        this.loadList = loadList;
        this.unLoadList = unloadList;
        this.status = status;
        this.arriveTime = arriveTime;
    }

    public enum StopStatus {PENDING, VISITED, CANCELED, APPROVED}
}
