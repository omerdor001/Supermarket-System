package PresentationLayer.GUI.Delivery.AllDrivers;

import PresentationLayer.GUI.Delivery.Driver.DriverController;
import PresentationLayer.GUI.HR.EmployeeInformationHR.DriverModel;
import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class AllDriversController implements ActionListener {

    AllDriversView allDriversView;
    SystemFacade systemFacade;

    public AllDriversController() {
        systemFacade = SystemFacade.getInstance();
        String result = systemFacade.showAllDrivers();
        TypeReference<HashMap<String, DriverModel>> typeReference = new TypeReference<>() {};
        AllDriversModel allDriversModel;
        HashMap<String, DriverModel> drivers;
        try {
            drivers = JsonConverter.fromJson(result, typeReference);
            allDriversModel = new AllDriversModel(drivers);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        allDriversView = new AllDriversView(allDriversModel, drivers);
        allDriversView.backButton.addActionListener(this);
        allDriversView.infoButton.addActionListener(this);
        allDriversView.refreshButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == allDriversView.backButton)
            allDriversView.dispose();
        else if (e.getSource() == allDriversView.refreshButton) {
            new AllDriversController();
            allDriversView.dispose();
        } else if (e.getSource() == allDriversView.infoButton) {
            Object item = allDriversView.userInputComboBox1.getSelectedItem();
            if (item == null || item.equals("-")) allDriversView.userInputError1Label.setVisible(true);
            else {
                allDriversView.userInputError1Label.setVisible(false);
                new DriverController((String) item);
            }
        }
    }
}
