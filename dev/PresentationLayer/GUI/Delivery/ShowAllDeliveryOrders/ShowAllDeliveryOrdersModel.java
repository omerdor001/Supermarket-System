package PresentationLayer.GUI.Delivery.ShowAllDeliveryOrders;

import PresentationLayer.GUI.Delivery.ShowDeliveryOrder.ShowDeliveryOrderModel;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class ShowAllDeliveryOrdersModel extends DefaultTableModel {

    public ShowAllDeliveryOrdersModel(HashMap<Integer, ShowDeliveryOrderModel> showDeliveryOrderModel) {
        super();
        String[] columnNames = {"id", "destination", "source", "status"};
        this.setColumnIdentifiers(columnNames);
        Object[] values = new Object[6];
        for (Integer t : showDeliveryOrderModel.keySet()) {
            values[0] = showDeliveryOrderModel.get(t).id;
            values[1] = showDeliveryOrderModel.get(t).destination;
            values[2] = showDeliveryOrderModel.get(t).source;
            values[3] = showDeliveryOrderModel.get(t).status;
            this.addRow(values);
        }
    }
}
