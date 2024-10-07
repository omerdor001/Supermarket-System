package PresentationLayer.GUI.Delivery.AllTrucks;

import PresentationLayer.GUI.Delivery.Truck.TruckModel;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class AllTrucksModel extends DefaultTableModel {

    public AllTrucksModel(HashMap<Integer, TruckModel> truckModels) {
        super();
        String[] truckDetails = {"truckId", "type", "model", "netWeight", "maxWeight", "status"};
        this.setColumnIdentifiers(truckDetails);
        Object[] values = new Object[6];
        for (Integer t : truckModels.keySet()) {
            values[0] = truckModels.get(t).id;
            values[1] = truckModels.get(t).type;
            values[2] = truckModels.get(t).model;
            values[3] = truckModels.get(t).netWeight;
            values[4] = truckModels.get(t).maxWeight;
            values[5] = truckModels.get(t).status;
            this.addRow(values);
        }
    }
}
