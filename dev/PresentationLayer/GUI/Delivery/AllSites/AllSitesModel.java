package PresentationLayer.GUI.Delivery.AllSites;

import PresentationLayer.GUI.Delivery.Site.SiteModel;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class AllSitesModel extends DefaultTableModel {

    public AllSitesModel(HashMap<String, SiteModel> siteModels) {
        super();
        String[] siteDetails = {"address", "phone", "contact name", "region", "type", "longitude", "latitude"};
        this.setColumnIdentifiers(siteDetails);
        Object[] values = new Object[7];
        for (String t : siteModels.keySet()) {
            values[0] = siteModels.get(t).address;
            values[1] = siteModels.get(t).phoneNumber;
            values[2] = siteModels.get(t).contactName;
            values[3] = siteModels.get(t).region;
            values[4] = siteModels.get(t).type;
            values[5] = siteModels.get(t).coordinateModel.longitude;
            values[6] = siteModels.get(t).coordinateModel.latitude;
            this.addRow(values);
        }
    }
}
