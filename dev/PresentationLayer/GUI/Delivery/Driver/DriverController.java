package PresentationLayer.GUI.Delivery.Driver;

import PresentationLayer.GUI.HR.EmployeeInformationHR.DriverModel;
import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DriverController implements ActionListener {
    DriverView driverView;
    SystemFacade systemFacade;
    String id;

    public DriverController(String id) {
        this.id = id;
        systemFacade = SystemFacade.getInstance();
        String result = systemFacade.showDriver(id);
        DriverModel driverModel;
        try {
            driverModel = JsonConverter.fromJson(result, DriverModel.class);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        driverView = new DriverView(driverModel);
        driverView.backButton.addActionListener(this);
        driverView.refreshButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == driverView.backButton)
            driverView.dispose();
        else if (e.getSource() == driverView.refreshButton) {
            driverView.dispose();
            new DriverController(id);
        }
    }
}
