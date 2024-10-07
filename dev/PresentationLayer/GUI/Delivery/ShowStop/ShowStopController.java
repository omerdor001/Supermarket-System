package PresentationLayer.GUI.Delivery.ShowStop;

import PresentationLayer.GUI.Delivery.ShowItems.ShowItemsController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowStopController implements ActionListener {

    ShowStopModel showStopModel;
    ShowStopView showStopView;

    public ShowStopController(ShowStopModel showStopModel) {
        this.showStopModel = showStopModel;
        this.showStopView = new ShowStopView();
        showStopView.backButton.addActionListener(this);
        showStopView.refreshButton.addActionListener(this);
        showStopView.loadItemsButton.addActionListener(this);
        showStopView.unloadItemsButton.addActionListener(this);

        showStopView.idLabel.setText(showStopView.idLabel.getText() + " " + showStopModel.id);
        showStopView.deliveryIdLabel.setText(showStopView.deliveryIdLabel.getText() + " " + showStopModel.deliveryId);
        showStopView.destinationLabel.setText(showStopView.destinationLabel.getText() + " " + showStopModel.destination.address);
        showStopView.statusLabel.setText(showStopView.statusLabel.getText() + " " + showStopModel.status);
        showStopView.arriveTimeLabel.setText(showStopView.arriveTimeLabel.getText() + " " + showStopModel.arriveTime);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showStopView.backButton)
            showStopView.dispose();
        else if (e.getSource() == showStopView.refreshButton) {
            showStopView.dispose();
            new ShowStopController(showStopModel);
        } else if (e.getSource() == showStopView.loadItemsButton)
            new ShowItemsController(showStopModel.loadList);
        else if (e.getSource() == showStopView.unloadItemsButton)
            new ShowItemsController(showStopModel.unLoadList);
    }
}
