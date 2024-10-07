package BusinessLayer.Delivery;

enum IDStatus {PENDING, APPROVED, COMPLETED, CANCELED}

public class ItemDelivery {
    private final int id;
    private final int deliveryId;
    private final ItemOrder itemOrder;
    private final String source;
    private final String destination;
    private IDStatus status;

    public ItemDelivery(int id, int deliveryId, ItemOrder itemOrder, String source, String destination) {
        this.id = id;
        this.deliveryId = deliveryId;
        this.itemOrder = itemOrder;
        this.source = source;
        this.destination = destination;
        this.status = IDStatus.PENDING;
    }

    public ItemDelivery(int itmDeliveryId, int itmDeliveryDeliveryId, ItemOrder itemOrder, String itmDeliverySource, String itmDeliveryDestination, int itmDeliveryStatus) {
        this(itmDeliveryId, itmDeliveryDeliveryId, itemOrder, itmDeliverySource, itmDeliveryDestination);
        this.status = IDStatus.values()[itmDeliveryStatus];
    }

    public int getId() {
        return id;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public ItemOrder getItemOrder() {
        return itemOrder;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public IDStatus getStatus() {
        return status;
    }

    public int getStatusOrdinal(){
        return status.ordinal();
    }

    public void setStatus(IDStatus status) {
        this.status = status;
    }

    public void visit() {
        getItemOrder().visit(getId());
    }
}
