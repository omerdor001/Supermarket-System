package PresentationLayer.GUI.Delivery.ShowAllItemOrders;

import PresentationLayer.GUI.Delivery.ShowItems.ItemOrderModel;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class ShowAllItemOrdersModel extends DefaultTableModel {

    public ShowAllItemOrdersModel(HashMap<Integer, ItemOrderModel> showDeliveryOrderModel) {
        super();
        String[] columnNames = {"id", "itemId", "deliveryOrderId", "source", "destination", "quantity", "refrigeration", "status", "itemDelivery1", "itemDelivery2"};
        this.setColumnIdentifiers(columnNames);
        Object[] values = new Object[10];
        for (Integer t : showDeliveryOrderModel.keySet()) {
            values[0] = showDeliveryOrderModel.get(t).id;
            values[1] = showDeliveryOrderModel.get(t).itemId;
            values[2] = showDeliveryOrderModel.get(t).deliveryOrderId;
            values[3] = showDeliveryOrderModel.get(t).source;
            values[4] = showDeliveryOrderModel.get(t).destination;
            values[5] = showDeliveryOrderModel.get(t).quantity;
            values[6] = showDeliveryOrderModel.get(t).refrigeration;
            values[7] = showDeliveryOrderModel.get(t).status;
            values[8] = showDeliveryOrderModel.get(t).itemDelivery1;
            values[9] = showDeliveryOrderModel.get(t).itemDelivery2;
            this.addRow(values);
        }
    }
}