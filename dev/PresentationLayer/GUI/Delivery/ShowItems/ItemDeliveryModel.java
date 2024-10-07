package PresentationLayer.GUI.Delivery.ShowItems;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDeliveryModel {

    public int id;
    public int deliveryId;
    public ItemOrderModel itemOrder;
    public String source;
    public String destination;
    public IDStatus status;

    public ItemDeliveryModel(@JsonProperty("id") int id, @JsonProperty("deliveryId") int deliveryId, @JsonProperty("itemOrder") ItemOrderModel itemOrder, @JsonProperty("source") String source, @JsonProperty("destination") String destination, @JsonProperty("statusOrdinal") IDStatus status) {
        this.id = id;
        this.deliveryId = deliveryId;
        this.itemOrder = itemOrder;
        this.source = source;
        this.destination = destination;
        this.status = status;
    }

    enum IDStatus {PENDING, APPROVED, COMPLETED, CANCELED}
}
