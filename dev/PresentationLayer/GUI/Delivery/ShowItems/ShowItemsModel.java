package PresentationLayer.GUI.Delivery.ShowItems;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

public class ShowItemsModel extends DefaultTableModel {

    public ShowItemsModel(LinkedList<ItemDeliveryModel> itemDeliveryModels) {
        super();
        String[] columnNames = {"id", "deliveryId", "item id", "refrigeration", "quantity", "source", "destination", "status"};
        this.setColumnIdentifiers(columnNames);
        Object[] values = new Object[8];
        for (ItemDeliveryModel t : itemDeliveryModels) {
            values[0] = t.id;
            values[1] = t.deliveryId;
            values[2] = t.itemOrder.itemId;
            values[3] = t.itemOrder.refrigeration;
            values[4] = t.itemOrder.quantity;
            values[5] = t.source;
            values[6] = t.destination;
            values[7] = t.status;
            this.addRow(values);
        }
    }
}