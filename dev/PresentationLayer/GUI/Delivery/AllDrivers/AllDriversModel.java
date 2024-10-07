package PresentationLayer.GUI.Delivery.AllDrivers;

import PresentationLayer.GUI.HR.EmployeeInformationHR.DriverModel;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class AllDriversModel extends DefaultTableModel {

    public AllDriversModel(HashMap<String, DriverModel> driverModels) {
        super();
        String[] driverDetails = {"driverId", "name", "salary", "phone", "hire date", "licenses"};
        this.setColumnIdentifiers(driverDetails);
        Object[] values = new Object[6];
        for (String t : driverModels.keySet()) {
            values[0] = driverModels.get(t).employeeId;
            values[1] = driverModels.get(t).firstName + driverModels.get(t).lastName;
            values[2] = driverModels.get(t).salary;
            values[3] = driverModels.get(t).phoneNumber;
            values[4] = driverModels.get(t).hireDate.toLocalDate();
            values[5] = driverModels.get(t).licenses;
            this.addRow(values);
        }
    }
}
