package PresentationLayer.GUI.Delivery.Truck;

import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TruckController implements ActionListener {
    TruckView truckView;
    SystemFacade systemFacade;
    int id;

    public TruckController(int id) {
        this.id = id;
        systemFacade = SystemFacade.getInstance();
        String result = systemFacade.showTruck(id);
        TruckModel truckModel;
        try {
            truckModel = JsonConverter.fromJson(result, TruckModel.class);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        truckView = new TruckView(truckModel);
        truckView.backButton.addActionListener(this);
        truckView.refreshButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == truckView.backButton)
            truckView.dispose();
        else if (e.getSource() == truckView.refreshButton) {
            truckView.dispose();
            new TruckController(id);
        }
    }
}
