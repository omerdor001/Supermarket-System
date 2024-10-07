package PresentationLayer.GUI.Delivery.ShowDelivery;

import PresentationLayer.GUI.Delivery.ShowStop.ShowStopController;
import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ShowDeliveryController implements ActionListener {

    int id;
    ShowDeliveryModel showDeliveryModel;
    ShowDeliveryView showDeliveryView;

    public ShowDeliveryController(int id) throws IOException {
        String result = SystemFacade.getInstance().showDelivery(id);
        if (result.charAt(0) != '{') {
            JOptionPane.showMessageDialog(null, result, "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.id = id;
        showDeliveryModel = JsonConverter.fromJson(result, ShowDeliveryModel.class);
        showDeliveryView = new ShowDeliveryView(showDeliveryModel, showDeliveryModel.destinations);
        showDeliveryView.backButton.addActionListener(this);
        showDeliveryView.refreshButton.addActionListener(this);
        showDeliveryView.showStopButton.addActionListener(this);

        showDeliveryView.idLabel.setText(showDeliveryView.idLabel.getText() + " " + showDeliveryModel.id);
        showDeliveryView.dateLabel.setText(showDeliveryView.dateLabel.getText() + " " + showDeliveryModel.date);
        showDeliveryView.timeLabel.setText(showDeliveryView.timeLabel.getText() + " " + showDeliveryModel.time);
        showDeliveryView.truckIdLabel.setText(showDeliveryView.truckIdLabel.getText() + " " + showDeliveryModel.truckId);
        showDeliveryView.driverIdLabel.setText(showDeliveryView.driverIdLabel.getText() + " " + showDeliveryModel.driverId);
        showDeliveryView.sourceLabel.setText(showDeliveryView.sourceLabel.getText() + " " + showDeliveryModel.source.address);
        showDeliveryView.startingWeightLabel.setText(showDeliveryView.startingWeightLabel.getText() + " " + showDeliveryModel.startingWeight);
        showDeliveryView.statusLabel.setText(showDeliveryView.statusLabel.getText() + " " + showDeliveryModel.status);
        showDeliveryView.maxWeightLabel.setText(showDeliveryView.maxWeightLabel.getText() + " " + showDeliveryModel.maxWeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showDeliveryView.backButton)
            showDeliveryView.dispose();
        else if (e.getSource() == showDeliveryView.refreshButton) {
            showDeliveryView.dispose();
            try {
                new ShowDeliveryController(id);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == showDeliveryView.showStopButton) {
            int stopIndex = showDeliveryView.userInputComboBox.getSelectedIndex() - 1;
            if (stopIndex == -1) {
                showDeliveryView.userInputErrorLabel.setVisible(true);
                return;
            } else showDeliveryView.userInputErrorLabel.setVisible(false);
            new ShowStopController(showDeliveryModel.destinations.get(stopIndex));
        }
    }
}
