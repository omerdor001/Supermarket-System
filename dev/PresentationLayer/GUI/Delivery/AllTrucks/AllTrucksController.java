package PresentationLayer.GUI.Delivery.AllTrucks;

import PresentationLayer.GUI.Delivery.Truck.TruckController;
import PresentationLayer.GUI.Delivery.Truck.TruckModel;
import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class AllTrucksController implements ActionListener {
    AllTrucksView allTrucksView;
    SystemFacade systemFacade;

    public AllTrucksController() {
        systemFacade = SystemFacade.getInstance();
        String result = systemFacade.showAllTrucks();
        TypeReference<HashMap<Integer, TruckModel>> typeReference = new TypeReference<>() {};
        AllTrucksModel allTrucksModel;
        HashMap<Integer, TruckModel> trucks;
        try {
            trucks = JsonConverter.fromJson(result, typeReference);
            allTrucksModel = new AllTrucksModel(trucks);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        allTrucksView = new AllTrucksView(allTrucksModel, trucks);
        allTrucksView.backButton.addActionListener(this);
        allTrucksView.infoButton.addActionListener(this);
        allTrucksView.refreshButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == allTrucksView.backButton)
            allTrucksView.dispose();
        else if (e.getSource() == allTrucksView.refreshButton) {
            new AllTrucksController();
            allTrucksView.dispose();
        } else if (e.getSource() == allTrucksView.infoButton) {
            Object item = allTrucksView.userInputComboBox1.getSelectedItem();
            if (item == null || item.equals("-")) allTrucksView.userInputError1Label.setVisible(true);
            else {
                allTrucksView.userInputError1Label.setVisible(false);
                ;
                new TruckController((int) item);
            }
        }
    }
}
