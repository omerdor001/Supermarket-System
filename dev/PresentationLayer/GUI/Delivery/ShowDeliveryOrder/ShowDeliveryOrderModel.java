package PresentationLayer.GUI.Delivery.ShowDeliveryOrder;

import PresentationLayer.GUI.Delivery.ShowItems.ItemOrderModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class ShowDeliveryOrderModel {
    public int id;
    public String destination;
    public String source;
    public double totalLoadWeight;
    public HashMap<Integer, ItemOrderModel> items;
    public OrderStatus status;
    Object[] columns;
    Object[][] data;

    public ShowDeliveryOrderModel(@JsonProperty("id") int id, @JsonProperty("destination") String destination, @JsonProperty("source") String source, @JsonProperty("totalLoadWeight") double totalLoadWeight, @JsonProperty("items") HashMap<Integer, ItemOrderModel> items, @JsonProperty("statusOrdinal") OrderStatus status) {
        this.id = id;
        this.totalLoadWeight = totalLoadWeight;
        this.destination = destination;
        this.source = source;
        this.status = status;
        this.items = items;
        columns = new Object[]{"id", "itemId", "deliveryOrderId", "source", "destination", "quantity", "refrigeration", "status", "itemDelivery1", "itemDelivery2"};
        data = new Object[items.size()][columns.length];
        int row = 0;
        for (ItemOrderModel i : items.values()) {
            data[row][0] = i.id;
            data[row][1] = i.itemId;
            data[row][2] = i.deliveryOrderId;
            data[row][3] = i.source;
            data[row][4] = i.destination;
            data[row][5] = i.quantity;
            data[row][6] = i.refrigeration;
            data[row][7] = i.status;
            data[row][8] = i.itemDelivery1;
            data[row][9] = i.itemDelivery2;
            row++;
        }
    }

    enum OrderStatus {PENDING, INPROGRESS, COMPLETED}
}
