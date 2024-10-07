package PresentationLayer.GUI.Delivery.ShowItems;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemOrderModel {
    public int id;
    public int itemId;
    public int deliveryOrderId;
    public String source;
    public String destination;
    public int quantity;
    public boolean refrigeration;
    public IOStatus status;
    public int itemDelivery1;
    public int itemDelivery2;

    public ItemOrderModel(@JsonProperty("id") int id, @JsonProperty("itemId") int itemId, @JsonProperty("deliveryOrderId") int deliveryOrderId, @JsonProperty("source") String source, @JsonProperty("destination") String destination, @JsonProperty("quantity") int quantity, @JsonProperty("refrigeration") boolean refrigeration, @JsonProperty("statusOrdinal") int status, @JsonProperty("itemDelivery1") int itemDelivery1, @JsonProperty("itemDelivery2") int itemDelivery2) {
        this.id = id;
        this.itemId = itemId;
        this.deliveryOrderId = deliveryOrderId;
        this.source = source;
        this.destination = destination;
        this.quantity = quantity;
        this.refrigeration = refrigeration;
        this.status = IOStatus.values()[status];
        this.itemDelivery1 = itemDelivery1;
        this.itemDelivery2 = itemDelivery2;
    }

    enum IOStatus {PENDING, FIRSTAPPROVED, APPROVED, COMPLETED}
}
