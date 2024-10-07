package PresentationLayer.GUI.Delivery.DeliveryMainMenu;

import PresentationLayer.GUI.Delivery.DeliveryManagement.DeliveryManagementController;
import PresentationLayer.GUI.Delivery.ResourceManagement.ResourceManagementController;
import PresentationLayer.GUI.MainMenuController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeliveryMainController implements ActionListener {

    DeliveryMainView deliveryMainView;
    private boolean manager;

    public DeliveryMainController() {
        deliveryMainView = new DeliveryMainView();
        deliveryMainView.deliveryManagementButton.addActionListener(this);
        deliveryMainView.resourceManagementButton.addActionListener(this);
    }

    public DeliveryMainController(boolean manager) {
        this();
        this.manager = manager;
        if (manager) {
            deliveryMainView.backButton.setVisible(true);
            deliveryMainView.backButton.addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deliveryMainView.deliveryManagementButton) {
            if (manager)
                new DeliveryManagementController(true);
            else
                new DeliveryManagementController();
            deliveryMainView.dispose();
        } else if (e.getSource() == deliveryMainView.resourceManagementButton) {
            if (manager)
                new ResourceManagementController(true);
            else
                new ResourceManagementController();
            deliveryMainView.dispose();
        } else if (e.getSource() == deliveryMainView.backButton) {
            new MainMenuController();
            deliveryMainView.dispose();
        }
    }
}
