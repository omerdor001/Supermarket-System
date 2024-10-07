package PresentationLayer.GUI.Delivery.ShowAllDeliveries;


import PresentationLayer.GUI.Delivery.ShowDelivery.ShowDeliveryModel;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class ShowAllDeliveriesModel extends DefaultTableModel {

    public ShowAllDeliveriesModel(HashMap<Integer, ShowDeliveryModel> showDeliveryModelHashMap) {
        super();
        String[] columnNames = {"id", "date", "time", "truckId", "driverId", "source.address", "startingWeight", "status", "maxWeight"};
        this.setColumnIdentifiers(columnNames);
        Object[] values = new Object[9];
        for (Integer t : showDeliveryModelHashMap.keySet()) {
            values[0] = showDeliveryModelHashMap.get(t).id;
            values[1] = showDeliveryModelHashMap.get(t).date;
            values[2] = showDeliveryModelHashMap.get(t).time;
            values[3] = showDeliveryModelHashMap.get(t).truckId;
            values[4] = showDeliveryModelHashMap.get(t).driverId;
            values[5] = showDeliveryModelHashMap.get(t).source.address;
            values[6] = showDeliveryModelHashMap.get(t).startingWeight;
            values[7] = showDeliveryModelHashMap.get(t).status;
            values[8] = showDeliveryModelHashMap.get(t).maxWeight;
            this.addRow(values);
        }
    }
}