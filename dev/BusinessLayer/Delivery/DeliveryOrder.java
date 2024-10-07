package BusinessLayer.Delivery;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class DeliveryOrder {
    private final int id;
    private final String destination;
    private final String source;
    private final double totalLoadWeight;
    private final HashMap<Integer, ItemOrder> items;
    private OrderStatus status;

    public DeliveryOrder(int id, double totalLoadWeight, String destination, String orderingStore, HashMap<Integer, ItemOrder> items) {
        this.id = id;
        this.totalLoadWeight = totalLoadWeight;
        this.destination = destination;
        this.source = orderingStore;
        this.status = OrderStatus.PENDING;
        this.items = items;
    }

    public DeliveryOrder(int dlvOrderId, String dlvOrderDestination, String dlvOrderSource, double dlvOrderTotalLoadWeight, HashMap<Integer, ItemOrder> items, int dlvOrderStatus) {
        this(dlvOrderId, dlvOrderTotalLoadWeight, dlvOrderDestination, dlvOrderSource, items);
        this.status = OrderStatus.values()[dlvOrderStatus];
    }

    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public HashMap<Integer,ItemOrder> getItems(){
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean checkCompleted() {
        boolean completed = true;
        for (int itemOrder : items.keySet()) {
            if (items.get(itemOrder).getStatus() == IOStatus.COMPLETED) setStatus(OrderStatus.INPROGRESS);
            else completed = false;
        }
        //TODO integration with other modules on delivery order completion
        if (completed) setStatus(OrderStatus.COMPLETED);
        return completed;
    }

    public double getTotalLoadWeight() {
        return totalLoadWeight;
    }

    public int getStatusOrdinal(){
        return status.ordinal();
    }

    enum OrderStatus {PENDING, INPROGRESS, COMPLETED}

    public static class Triplet<A, B, C> implements Serializable {
        private A first;
        private B second;
        private C third;

        public Triplet(@JsonProperty("first") A first, @JsonProperty("second") B second, @JsonProperty("third") C third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public A getFirst() {
            return first;
        }

        public void setFirst(A first) {
            this.first = first;
        }

        public B getSecond() {
            return second;
        }

        public void setSecond(B second) {
            this.second = second;
        }

        public C getThird() {
            return third;
        }

        public void setThird(C third) {
            this.third = third;
        }
    }

    public static class Shipment implements Serializable {
        private String date;
        private String sourceAddress;
        private String destAddress;
        private String sourceContactName;
        private String destContactName;
        private String sourcePhone;
        private String destPhone;
        private List<Triplet<String, Integer, Double>> products; //<SupId-ProdId , Quantity,weight>

        public Shipment(@JsonProperty("date") String date, @JsonProperty("sourceAddress") String sourceAddress, @JsonProperty("destAddress") String destAddress, @JsonProperty("sourceContactName") String sourceContactName, @JsonProperty("destContactName") String destContactName, @JsonProperty("sourcePhone") String sourcePhone, @JsonProperty("destPhone") String destPhone, @JsonProperty("products") List<Triplet<String, Integer, Double>> products) {
            this.date = date;
            this.sourceAddress = sourceAddress;
            this.destAddress = destAddress;
            this.sourceContactName = sourceContactName;
            this.destContactName = destContactName;
            this.sourcePhone = sourcePhone;
            this.destPhone = destPhone;
            this.products = products;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSourceAddress() {
            return sourceAddress;
        }

        public void setSourceAddress(String sourceAddress) {
            this.sourceAddress = sourceAddress;
        }

        public String getDestAddress() {
            return destAddress;
        }

        public void setDestAddress(String destAddress) {
            this.destAddress = destAddress;
        }

        public String getSourceContactName() {
            return sourceContactName;
        }

        public void setSourceContactName(String sourceContactName) {
            this.sourceContactName = sourceContactName;
        }

        public String getDestContactName() {
            return destContactName;
        }

        public void setDestContactName(String destContactName) {
            this.destContactName = destContactName;
        }

        public String getSourcePhone() {
            return sourcePhone;
        }

        public void setSourcePhone(String sourcePhone) {
            this.sourcePhone = sourcePhone;
        }

        public String getDestPhone() {
            return destPhone;
        }

        public void setDestPhone(String destPhone) {
            this.destPhone = destPhone;
        }

        public List<Triplet<String, Integer, Double>> getProducts() {
            return products;
        }

        public void setProducts(List<Triplet<String, Integer, Double>> products) {
            this.products = products;
        }
    }
}
