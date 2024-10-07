package PresentationLayer.GUI.Delivery.DeliveryManagement;

import PresentationLayer.GUI.Delivery.DeliveryMainMenu.DeliveryMainController;
import PresentationLayer.GUI.Delivery.PlanDelivery.PlanDeliveryController;
import PresentationLayer.GUI.Delivery.ProgressDelivery.ProgressDeliveryController;
import PresentationLayer.GUI.Delivery.ShowAllDeliveries.ShowAllDeliveriesController;
import PresentationLayer.GUI.Delivery.ShowAllDeliveryOrders.ShowAllDeliveryOrdersController;
import PresentationLayer.GUI.Delivery.ShowDelivery.ShowDeliveryController;
import PresentationLayer.GUI.Delivery.ShowDeliveryOrder.ShowDeliveryOrderController;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeliveryManagementController implements ActionListener {

    DeliveryManagementView deliveryManagementView;
    SystemFacade systemFacade;
    private boolean manager;

    public DeliveryManagementController() {
        deliveryManagementView = new DeliveryManagementView();
        systemFacade = SystemFacade.getInstance();

        deliveryManagementView.backButton.addActionListener(this);
        deliveryManagementView.parseOrderButton.addActionListener(this);
        deliveryManagementView.planDeliveryButton.addActionListener(this);
        deliveryManagementView.progressDeliveryButton.addActionListener(this);
        deliveryManagementView.showDeliveryButton.addActionListener(this);
        deliveryManagementView.showDeliveryOrderButton.addActionListener(this);
        deliveryManagementView.showAllDeliveriesButton.addActionListener(this);
        deliveryManagementView.showAllDeliveryOrdersButton.addActionListener(this);
        deliveryManagementView.userInputBackButton.addActionListener(this);
        deliveryManagementView.showDeliveryConfirmButton.addActionListener(this);
        deliveryManagementView.showDeliveryOrderConfirmButton.addActionListener(this);
        deliveryManagementView.parseOrderConfirmButton.addActionListener(this);
    }

    public DeliveryManagementController(boolean manager) {
        this();
        this.manager = manager;
    }

    private void hideMainOptions() {
        deliveryManagementView.mainMenuLabel.setVisible(false);
        deliveryManagementView.selectActionLabel.setVisible(false);
        deliveryManagementView.backButton.setVisible(false);
        deliveryManagementView.parseOrderButton.setVisible(false);
        deliveryManagementView.planDeliveryButton.setVisible(false);
        deliveryManagementView.progressDeliveryButton.setVisible(false);
        deliveryManagementView.showDeliveryButton.setVisible(false);
        deliveryManagementView.showDeliveryOrderButton.setVisible(false);
        deliveryManagementView.showAllDeliveryOrdersButton.setVisible(false);
        deliveryManagementView.showAllDeliveriesButton.setVisible(false);
    }

    private void showMainOptions() {
        deliveryManagementView.mainMenuLabel.setVisible(true);
        deliveryManagementView.selectActionLabel.setVisible(true);
        deliveryManagementView.backButton.setVisible(true);
        deliveryManagementView.parseOrderButton.setVisible(true);
        deliveryManagementView.planDeliveryButton.setVisible(true);
        deliveryManagementView.progressDeliveryButton.setVisible(true);
        deliveryManagementView.showDeliveryButton.setVisible(true);
        deliveryManagementView.showDeliveryOrderButton.setVisible(true);
        deliveryManagementView.showAllDeliveryOrdersButton.setVisible(true);
        deliveryManagementView.showAllDeliveriesButton.setVisible(true);
    }

    private void hideUserInput() {
        deliveryManagementView.userInputMainLabel.setVisible(false);
        deliveryManagementView.userInput1Label.setVisible(false);
        deliveryManagementView.userInputErrorLabel.setVisible(false);
        deliveryManagementView.userInput1Text.setVisible(false);
        deliveryManagementView.userInputBackButton.setVisible(false);
        deliveryManagementView.showDeliveryConfirmButton.setVisible(false);
        deliveryManagementView.showDeliveryOrderConfirmButton.setVisible(false);
        deliveryManagementView.parseOrderConfirmButton.setVisible(false);
        deliveryManagementView.parseOrderPanel.setVisible(false);
        deliveryManagementView.userInput1Text.setText("");
        deliveryManagementView.userInputTextArea.setText("");
    }

    private void showDelivery() {
        deliveryManagementView.userInputMainLabel.setText("You are in the delivery show menu, please insert the required information");
        deliveryManagementView.userInputMainLabel.setBounds(40, 50, 550, 200);
        deliveryManagementView.userInput1Label.setText("Input delivery id:");
        deliveryManagementView.userInput1Label.setBounds(100, 100, 350, 200);
        deliveryManagementView.userInputErrorLabel.setBounds(400, 100, 350, 200);
        deliveryManagementView.userInputErrorLabel.setText("You must input an id");
        deliveryManagementView.userInput1Text.setBounds(250, 100, 100, 25);
        deliveryManagementView.showDeliveryConfirmButton.setBounds(300, 150, 200, 50);
        deliveryManagementView.userInputBackButton.setBounds(100, 150, 200, 50);
        deliveryManagementView.userInputMainLabel.setVisible(true);
        deliveryManagementView.userInput1Label.setVisible(true);
        deliveryManagementView.userInput1Text.setVisible(true);
        deliveryManagementView.showDeliveryConfirmButton.setVisible(true);
        deliveryManagementView.userInputBackButton.setVisible(true);
    }

    private void showDeliveryOrder() {
        deliveryManagementView.userInputMainLabel.setText("You are in the delivery order show menu, please insert the required information");
        deliveryManagementView.userInputMainLabel.setBounds(40, 50, 550, 200);
        deliveryManagementView.userInput1Label.setText("Input delivery order id:");
        deliveryManagementView.userInput1Label.setBounds(90, 100, 350, 200);
        deliveryManagementView.userInputErrorLabel.setBounds(400, 100, 350, 200);
        deliveryManagementView.userInputErrorLabel.setText("You must input an id");
        deliveryManagementView.userInput1Text.setBounds(250, 100, 100, 25);
        deliveryManagementView.showDeliveryOrderConfirmButton.setBounds(300, 150, 200, 50);
        deliveryManagementView.userInputBackButton.setBounds(100, 150, 200, 50);
        deliveryManagementView.userInputMainLabel.setVisible(true);
        deliveryManagementView.userInput1Label.setVisible(true);
        deliveryManagementView.userInput1Text.setVisible(true);
        deliveryManagementView.showDeliveryOrderConfirmButton.setVisible(true);
        deliveryManagementView.userInputBackButton.setVisible(true);
    }

    private void parseOrder() {
        deliveryManagementView.userInputMainLabel.setText("You are in the parse order show menu");
        deliveryManagementView.userInputMainLabel.setBounds(170, 15, 550, 200);
        deliveryManagementView.parseOrderConfirmButton.setBounds(300, 250, 200, 50);
        deliveryManagementView.userInputBackButton.setBounds(100, 250, 200, 50);
        deliveryManagementView.userInputMainLabel.setVisible(true);
        deliveryManagementView.parseOrderConfirmButton.setVisible(true);
        deliveryManagementView.userInputBackButton.setVisible(true);
        deliveryManagementView.parseOrderPanel.setVisible(true);
    }

    private void showAllDeliveries() {
        new ShowAllDeliveriesController();
    }

    private void showAllDeliveryOrders() {
        new ShowAllDeliveryOrdersController();
    }

    private void showDeliveryConfirm() {
        String deliveryIdString = deliveryManagementView.userInput1Text.getText();
        if (deliveryIdString.equals("")) {
            deliveryManagementView.userInputErrorLabel.setVisible(true);
            return;
        } else
            deliveryManagementView.userInputErrorLabel.setVisible(false);
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            new ShowDeliveryController(deliveryId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Delivery id must be a number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDeliveryOrderConfirm() {
        String deliveryOrderIdString = deliveryManagementView.userInput1Text.getText();
        if (deliveryOrderIdString.equals("")) {
            deliveryManagementView.userInputErrorLabel.setVisible(true);
            return;
        }
        try {
            int deliveryOrderId = Integer.parseInt(deliveryOrderIdString);
            new ShowDeliveryOrderController(deliveryOrderId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Delivery order id must be a number", "error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void parseOrderConfirm() {
        String order = deliveryManagementView.userInput1Text.getText();
        String result = systemFacade.parseOrder(order);
        JOptionPane.showMessageDialog(null, result, "Parse result", JOptionPane.INFORMATION_MESSAGE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deliveryManagementView.backButton) {
            if (manager)
                new DeliveryMainController(true);
            else
                new DeliveryMainController();
            deliveryManagementView.dispose();
        } else if (e.getSource() == deliveryManagementView.userInputBackButton) {
            hideUserInput();
            showMainOptions();
        } else if (e.getSource() == deliveryManagementView.planDeliveryButton) {
            new PlanDeliveryController(manager);
            deliveryManagementView.dispose();
        } else if (e.getSource() == deliveryManagementView.parseOrderButton) {
            hideMainOptions();
            parseOrder();
        } else if (e.getSource() == deliveryManagementView.progressDeliveryButton) {
            new ProgressDeliveryController(manager);
            deliveryManagementView.dispose();
        } else if (e.getSource() == deliveryManagementView.showDeliveryButton) {
            hideMainOptions();
            showDelivery();
        } else if (e.getSource() == deliveryManagementView.showDeliveryOrderButton) {
            hideMainOptions();
            showDeliveryOrder();
        } else if (e.getSource() == deliveryManagementView.showAllDeliveriesButton) {
            showAllDeliveries();
        } else if (e.getSource() == deliveryManagementView.showAllDeliveryOrdersButton) {
            showAllDeliveryOrders();
        } else if (e.getSource() == deliveryManagementView.showDeliveryConfirmButton) {
            showDeliveryConfirm();
        } else if (e.getSource() == deliveryManagementView.showDeliveryOrderConfirmButton) {
            showDeliveryOrderConfirm();
        } else if (e.getSource() == deliveryManagementView.parseOrderConfirmButton) {
            parseOrderConfirm();
        }
    }
}
