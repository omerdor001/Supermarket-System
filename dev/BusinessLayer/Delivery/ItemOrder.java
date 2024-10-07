package BusinessLayer.Delivery;

enum IOStatus {PENDING, FIRSTAPPROVED, APPROVED, COMPLETED}

public class ItemOrder {
    private final int id;
    private final int itemId;
    private final int deliveryOrderId;
    private final String source;
    private final String destination;
    private final int quantity;
    private final boolean refrigeration;
    private IOStatus status;
    private int itemDelivery1;
    private int itemDelivery2;

    public ItemOrder(int id, int itemId, int deliveryOrderId, String source, String destination, int quantity, boolean refrigeration) {
        this.id = id;
        this.itemId = itemId;
        this.deliveryOrderId = deliveryOrderId;
        this.source = source;
        this.destination = destination;
        this.quantity = quantity;
        this.refrigeration = refrigeration;
        this.status = IOStatus.PENDING;
        this.itemDelivery1 = -1;
        this.itemDelivery2 = -1;
    }

    public ItemOrder(int itmOrderId, int itmOrderDeliveryId, int itmOrderItemOrderId, String itmOrderSource, String itmOrderDestination, int itmOrderQuantity, boolean refrigeration, int itmOrderStatus, int itmOrderItemDelivery1Id, int itmOrderItemDelivery2Id) {
        this(itmOrderId, itmOrderDeliveryId, itmOrderItemOrderId, itmOrderSource, itmOrderDestination, itmOrderQuantity, refrigeration);
        this.status = IOStatus.values()[itmOrderStatus];
        this.itemDelivery1 = itmOrderItemDelivery1Id;
        this.itemDelivery2 = itmOrderItemDelivery2Id;
    }

    public int getId() {
        return id;
    }

    public int getItemId() {
        return itemId;
    }

    public int getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getQuantity() {
        return quantity;
    }

    public IOStatus getStatus() {
        return status;
    }

    public void setStatus(IOStatus status) {
        this.status = status;
    }

    public int getStatusOrdinal() {
        return status.ordinal();
    }

    public boolean isRefrigeration() {
        return refrigeration;
    }

    public int getItemDelivery1() {
        return itemDelivery1;
    }

    public void setItemDelivery1(int itemDelivery1) {
        this.itemDelivery1 = itemDelivery1;
    }

    public int getItemDelivery2() {
        return itemDelivery2;
    }

    public void setItemDelivery2(int itemDelivery2) {
        this.itemDelivery2 = itemDelivery2;
    }

    public void visit(int itemDeliveryId) {
        if ((getItemDelivery1() == itemDeliveryId && getItemDelivery2() == -1) || getItemDelivery2() == itemDeliveryId)
            setStatus(IOStatus.COMPLETED);
    }
}
