package PresentationLayer.GUI.Delivery.ProgressDelivery;

import PresentationLayer.GUI.Delivery.DeliveryManagement.DeliveryManagementController;
import PresentationLayer.GUI.Delivery.DeliveryManagement.DeliveryManagementView;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalTime;

public class ProgressDeliveryController implements ActionListener {

    ProgressDeliveryView progressDeliveryView;
    SystemFacade systemFacade;
    private boolean manager;

    public ProgressDeliveryController() {
        progressDeliveryView = new ProgressDeliveryView();
        systemFacade = SystemFacade.getInstance();

        progressDeliveryView.backButton.addActionListener(this);
        progressDeliveryView.approveDeliveryButton.addActionListener(this);
        progressDeliveryView.visitStopButton.addActionListener(this);
        progressDeliveryView.switchTruckButton.addActionListener(this);
        progressDeliveryView.switchTruckDriverButton.addActionListener(this);
        progressDeliveryView.skipStopButton.addActionListener(this);
        progressDeliveryView.leaveItemButton.addActionListener(this);
        progressDeliveryView.userInputBackButton.addActionListener(this);
        progressDeliveryView.approveDeliveryConfirmButton.addActionListener(this);
        progressDeliveryView.visitStopConfirmButton.addActionListener(this);
        progressDeliveryView.switchTruckConfirmButton.addActionListener(this);
        progressDeliveryView.switchTruckDriverConfirmButton.addActionListener(this);
        progressDeliveryView.skipStopConfirmButton.addActionListener(this);
        progressDeliveryView.leaveItemConfirmButton.addActionListener(this);
    }

    public ProgressDeliveryController(boolean manager) {
        this();
        this.manager = manager;
    }

    private void hideMainOptions() {
        progressDeliveryView.mainMenuLabel.setVisible(false);
        progressDeliveryView.selectActionLabel.setVisible(false);
        progressDeliveryView.overweightLabel.setVisible(false);
        progressDeliveryView.backButton.setVisible(false);
        progressDeliveryView.backButton.setVisible(false);
        progressDeliveryView.approveDeliveryButton.setVisible(false);
        progressDeliveryView.visitStopButton.setVisible(false);
        progressDeliveryView.switchTruckButton.setVisible(false);
        progressDeliveryView.switchTruckDriverButton.setVisible(false);
        progressDeliveryView.skipStopButton.setVisible(false);
        progressDeliveryView.leaveItemButton.setVisible(false);
    }

    private void showMainOptions() {
        progressDeliveryView.mainMenuLabel.setVisible(true);
        progressDeliveryView.selectActionLabel.setVisible(true);
        progressDeliveryView.overweightLabel.setVisible(true);
        progressDeliveryView.backButton.setVisible(true);
        progressDeliveryView.backButton.setVisible(true);
        progressDeliveryView.approveDeliveryButton.setVisible(true);
        progressDeliveryView.visitStopButton.setVisible(true);
        progressDeliveryView.switchTruckButton.setVisible(true);
        progressDeliveryView.switchTruckDriverButton.setVisible(true);
        progressDeliveryView.skipStopButton.setVisible(true);
        progressDeliveryView.leaveItemButton.setVisible(true);
    }

    private void hideUserInput() {
        progressDeliveryView.userInput1Text.setVisible(false);
        progressDeliveryView.userInput2Text.setVisible(false);
        progressDeliveryView.userInput3Text.setVisible(false);
        progressDeliveryView.userInput4Text.setVisible(false);
        progressDeliveryView.userInput1Label.setVisible(false);
        progressDeliveryView.userInput2Label.setVisible(false);
        progressDeliveryView.userInput3Label.setVisible(false);
        progressDeliveryView.userInput4Label.setVisible(false);
        progressDeliveryView.userInputError1Label.setVisible(false);
        progressDeliveryView.userInputError2Label.setVisible(false);
        progressDeliveryView.userInputError3Label.setVisible(false);
        progressDeliveryView.userInputError4Label.setVisible(false);
        progressDeliveryView.userInputBackButton.setVisible(false);
        progressDeliveryView.userInputMainLabel.setVisible(false);
        progressDeliveryView.userInputCheckBox.setVisible(false);
        progressDeliveryView.userInput1Text.setText("");
        progressDeliveryView.userInput2Text.setText("");
        progressDeliveryView.userInput3Text.setText("");
        progressDeliveryView.userInput4Text.setText("");

        progressDeliveryView.approveDeliveryConfirmButton.setVisible(false);
        progressDeliveryView.visitStopConfirmButton.setVisible(false);
        progressDeliveryView.switchTruckConfirmButton.setVisible(false);
        progressDeliveryView.switchTruckDriverConfirmButton.setVisible(false);
        progressDeliveryView.skipStopConfirmButton.setVisible(false);
        progressDeliveryView.leaveItemConfirmButton.setVisible(false);
    }

    private void approveDelivery() {
        progressDeliveryView.userInputMainLabel.setText("You are in the delivery approve menu, please insert the required information");
        progressDeliveryView.userInputMainLabel.setBounds(25, 50, 550, 200);
        progressDeliveryView.userInput1Label.setText("Input delivery id:");
        progressDeliveryView.userInput1Label.setBounds(100, 100, 350, 200);
        progressDeliveryView.userInputError1Label.setText("You must input an id");
        progressDeliveryView.userInputError1Label.setBounds(425, 100, 350, 200);
        progressDeliveryView.userInput1Text.setBounds(250, 100, 150, 25);
        progressDeliveryView.approveDeliveryConfirmButton.setBounds(300, 150, 200, 50);
        progressDeliveryView.userInputBackButton.setBounds(100, 150, 200, 50);
        progressDeliveryView.userInputMainLabel.setVisible(true);
        progressDeliveryView.userInput1Label.setVisible(true);
        progressDeliveryView.userInput1Text.setVisible(true);
        progressDeliveryView.approveDeliveryConfirmButton.setVisible(true);
        progressDeliveryView.userInputBackButton.setVisible(true);
    }

    private void visitStop() {
        progressDeliveryView.userInputMainLabel.setText("You are in the visit stop menu, please insert the required information");
        progressDeliveryView.userInputMainLabel.setBounds(50, 15, 550, 200);
        progressDeliveryView.userInput1Label.setText("Input delivery id:");
        progressDeliveryView.userInput1Label.setBounds(50, 75, 350, 200);
        progressDeliveryView.userInput2Label.setText("Input stop address:");
        progressDeliveryView.userInput2Label.setBounds(50, 125, 300, 200);
        progressDeliveryView.userInput3Label.setText("Input new delivery weight in kilograms:");
        progressDeliveryView.userInput3Label.setBounds(50, 175, 300, 200);
        progressDeliveryView.userInputError1Label.setText("You must input an id");
        progressDeliveryView.userInputError1Label.setBounds(435, 75, 350, 200);
        progressDeliveryView.userInputError2Label.setText("You must input an address");
        progressDeliveryView.userInputError2Label.setBounds(435, 125, 350, 200);
        progressDeliveryView.userInputError3Label.setText("You must input weight");
        progressDeliveryView.userInputError3Label.setBounds(435, 175, 350, 200);
        progressDeliveryView.userInput1Text.setBounds(330, 75, 100, 25);
        progressDeliveryView.userInput2Text.setBounds(330, 125, 100, 25);
        progressDeliveryView.userInput3Text.setBounds(330, 175, 100, 25);
        progressDeliveryView.visitStopConfirmButton.setBounds(300, 225, 200, 50);
        progressDeliveryView.userInputBackButton.setBounds(100, 225, 200, 50);
        progressDeliveryView.userInputMainLabel.setVisible(true);
        progressDeliveryView.userInput1Label.setVisible(true);
        progressDeliveryView.userInput2Label.setVisible(true);
        progressDeliveryView.userInput3Label.setVisible(true);
        progressDeliveryView.userInput1Text.setVisible(true);
        progressDeliveryView.userInput2Text.setVisible(true);
        progressDeliveryView.userInput3Text.setVisible(true);
        progressDeliveryView.visitStopConfirmButton.setVisible(true);
        progressDeliveryView.userInputBackButton.setVisible(true);
    }

    private void switchTruck() {
        progressDeliveryView.userInputMainLabel.setText("Overweight delivery truck switch menu, please insert the required information");
        progressDeliveryView.userInputMainLabel.setBounds(25, 15, 550, 200);
        progressDeliveryView.userInput1Label.setText("Input delivery id:");
        progressDeliveryView.userInput1Label.setBounds(50, 75, 350, 200);
        progressDeliveryView.userInput2Label.setText("Input new truck id:");
        progressDeliveryView.userInput2Label.setBounds(50, 125, 300, 200);
        progressDeliveryView.userInput3Label.setText("Input current delivery weight in kilograms:");
        progressDeliveryView.userInput3Label.setBounds(50, 175, 300, 200);
        progressDeliveryView.userInputError1Label.setText("You must input an id");
        progressDeliveryView.userInputError1Label.setBounds(450, 75, 350, 200);
        progressDeliveryView.userInputError2Label.setText("You must input an id");
        progressDeliveryView.userInputError2Label.setBounds(450, 125, 350, 200);
        progressDeliveryView.userInputError3Label.setText("You must input weight");
        progressDeliveryView.userInputError3Label.setBounds(450, 175, 350, 200);
        progressDeliveryView.userInput1Text.setBounds(350, 75, 100, 25);
        progressDeliveryView.userInput2Text.setBounds(350, 125, 100, 25);
        progressDeliveryView.userInput3Text.setBounds(350, 175, 100, 25);
        progressDeliveryView.switchTruckConfirmButton.setBounds(300, 225, 200, 50);
        progressDeliveryView.userInputBackButton.setBounds(100, 225, 200, 50);
        progressDeliveryView.userInputMainLabel.setVisible(true);
        progressDeliveryView.userInput1Label.setVisible(true);
        progressDeliveryView.userInput2Label.setVisible(true);
        progressDeliveryView.userInput3Label.setVisible(true);
        progressDeliveryView.userInput1Text.setVisible(true);
        progressDeliveryView.userInput2Text.setVisible(true);
        progressDeliveryView.userInput3Text.setVisible(true);
        progressDeliveryView.switchTruckConfirmButton.setVisible(true);
        progressDeliveryView.userInputBackButton.setVisible(true);
    }

    private void switchTruckDriver() {
        progressDeliveryView.userInputMainLabel.setText("Overweight delivery truck and driver switch menu, insert required information");
        progressDeliveryView.userInputMainLabel.setBounds(25, 15, 550, 200);
        progressDeliveryView.userInput1Label.setText("Input delivery id:");
        progressDeliveryView.userInput1Label.setBounds(50, 50, 350, 200);
        progressDeliveryView.userInput2Label.setText("Input new truck id:");
        progressDeliveryView.userInput2Label.setBounds(50, 100, 300, 200);
        progressDeliveryView.userInput3Label.setText("Input new driver id:");
        progressDeliveryView.userInput3Label.setBounds(50, 150, 300, 200);
        progressDeliveryView.userInput4Label.setText("Input current delivery weight in kilograms:");
        progressDeliveryView.userInput4Label.setBounds(50, 200, 300, 200);
        progressDeliveryView.userInputError1Label.setText("You must input an id");
        progressDeliveryView.userInputError1Label.setBounds(460, 50, 350, 200);
        progressDeliveryView.userInputError2Label.setText("You must input an id");
        progressDeliveryView.userInputError2Label.setBounds(460, 100, 350, 200);
        progressDeliveryView.userInputError3Label.setText("You must input an id");
        progressDeliveryView.userInputError3Label.setBounds(460, 150, 350, 200);
        progressDeliveryView.userInputError4Label.setText("You must input weight");
        progressDeliveryView.userInputError4Label.setBounds(460, 200, 350, 200);
        progressDeliveryView.userInput1Text.setBounds(350, 50, 100, 25);
        progressDeliveryView.userInput2Text.setBounds(350, 100, 100, 25);
        progressDeliveryView.userInput3Text.setBounds(350, 150, 100, 25);
        progressDeliveryView.userInput4Text.setBounds(350, 200, 100, 25);
        progressDeliveryView.switchTruckDriverConfirmButton.setBounds(300, 250, 200, 50);
        progressDeliveryView.userInputBackButton.setBounds(100, 250, 200, 50);
        progressDeliveryView.userInputMainLabel.setVisible(true);
        progressDeliveryView.userInput1Label.setVisible(true);
        progressDeliveryView.userInput2Label.setVisible(true);
        progressDeliveryView.userInput3Label.setVisible(true);
        progressDeliveryView.userInput4Label.setVisible(true);
        progressDeliveryView.userInput1Text.setVisible(true);
        progressDeliveryView.userInput2Text.setVisible(true);
        progressDeliveryView.userInput3Text.setVisible(true);
        progressDeliveryView.userInput4Text.setVisible(true);
        progressDeliveryView.switchTruckDriverConfirmButton.setVisible(true);
        progressDeliveryView.userInputBackButton.setVisible(true);
    }

    private void skipStop() {
        progressDeliveryView.userInputMainLabel.setText("Overweight delivery skip stop menu, please insert the required information");
        progressDeliveryView.userInputMainLabel.setBounds(35, 50, 550, 200);
        progressDeliveryView.userInput1Label.setText("Input delivery id:");
        progressDeliveryView.userInput1Label.setBounds(100, 100, 350, 200);
        progressDeliveryView.userInputError1Label.setText("You must input an id");
        progressDeliveryView.userInputError1Label.setBounds(425, 100, 350, 200);
        progressDeliveryView.userInput1Text.setBounds(250, 100, 150, 25);
        progressDeliveryView.skipStopConfirmButton.setBounds(300, 150, 200, 50);
        progressDeliveryView.userInputBackButton.setBounds(100, 150, 200, 50);
        progressDeliveryView.userInputMainLabel.setVisible(true);
        progressDeliveryView.userInput1Label.setVisible(true);
        progressDeliveryView.userInput1Text.setVisible(true);
        progressDeliveryView.skipStopConfirmButton.setVisible(true);
        progressDeliveryView.userInputBackButton.setVisible(true);
    }

    private void leaveItem() {
        progressDeliveryView.userInputMainLabel.setText("Overweight delivery leave item menu, please insert the required information");
        progressDeliveryView.userInputMainLabel.setBounds(35, 50, 550, 200);
        progressDeliveryView.userInput1Label.setText("Input delivery id:");
        progressDeliveryView.userInput1Label.setBounds(100, 100, 350, 200);
        progressDeliveryView.userInput2Label.setText("Input item order id:");
        progressDeliveryView.userInput2Label.setBounds(100, 150, 300, 200);
        progressDeliveryView.userInputError1Label.setText("You must input an id");
        progressDeliveryView.userInputError1Label.setBounds(425, 100, 350, 200);
        progressDeliveryView.userInputError2Label.setText("You must input an id");
        progressDeliveryView.userInputError2Label.setBounds(425, 150, 350, 200);
        progressDeliveryView.userInput1Text.setBounds(250, 100, 150, 25);
        progressDeliveryView.userInput2Text.setBounds(250, 150, 150, 25);
        progressDeliveryView.leaveItemConfirmButton.setBounds(300, 200, 200, 50);
        progressDeliveryView.userInputBackButton.setBounds(100, 200, 200, 50);
        progressDeliveryView.userInputMainLabel.setVisible(true);
        progressDeliveryView.userInput1Label.setVisible(true);
        progressDeliveryView.userInput2Label.setVisible(true);
        progressDeliveryView.userInput1Text.setVisible(true);
        progressDeliveryView.userInput2Text.setVisible(true);
        progressDeliveryView.leaveItemConfirmButton.setVisible(true);
        progressDeliveryView.userInputBackButton.setVisible(true);
    }

    private void approveDeliveryConfirm() {
        String deliveryIdString = progressDeliveryView.userInput1Text.getText();
        progressDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        if (deliveryIdString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            String result = systemFacade.approveDelivery(deliveryId);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void visitStopConfirm() {
        String deliveryIdString = progressDeliveryView.userInput1Text.getText();
        String stopAddress = progressDeliveryView.userInput2Text.getText();
        String weightString = progressDeliveryView.userInput3Text.getText();
        progressDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        progressDeliveryView.userInputError2Label.setVisible(stopAddress.equals(""));
        progressDeliveryView.userInputError3Label.setVisible(weightString.equals(""));
        if (deliveryIdString.equals("") || stopAddress.equals("") || weightString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            int weight = Integer.parseInt(weightString);
            String result = systemFacade.visitStop(deliveryId, stopAddress, weight);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void switchTruckConfirm() {
        String deliveryIdString = progressDeliveryView.userInput1Text.getText();
        String truckIdString = progressDeliveryView.userInput2Text.getText();
        String weightString = progressDeliveryView.userInput3Text.getText();
        progressDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        progressDeliveryView.userInputError2Label.setVisible(truckIdString.equals(""));
        progressDeliveryView.userInputError3Label.setVisible(weightString.equals(""));
        if (deliveryIdString.equals("") || truckIdString.equals("") || weightString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            int truckId = Integer.parseInt(truckIdString);
            int weight = Integer.parseInt(weightString);
            String result = systemFacade.switchTruck(deliveryId, truckId, weight);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void switchTruckDriverConfirm() {
        String deliveryIdString = progressDeliveryView.userInput1Text.getText();
        String truckIdString = progressDeliveryView.userInput2Text.getText();
        String driverId = progressDeliveryView.userInput3Text.getText();
        String weightString = progressDeliveryView.userInput4Text.getText();
        progressDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        progressDeliveryView.userInputError2Label.setVisible(truckIdString.equals(""));
        progressDeliveryView.userInputError3Label.setVisible(driverId.equals(""));
        progressDeliveryView.userInputError4Label.setVisible(weightString.equals(""));
        if (deliveryIdString.equals("") || truckIdString.equals("") || driverId.equals("") || weightString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            int truckId = Integer.parseInt(truckIdString);
            int weight = Integer.parseInt(weightString);
            String result = systemFacade.switchTruckDriver(deliveryId, truckId, driverId, weight);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void skipStopConfirm() {
        String deliveryIdString = progressDeliveryView.userInput1Text.getText();
        progressDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        if (deliveryIdString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            String result = systemFacade.skipStopLoad(deliveryId);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void leaveItemConfirm() {
        String deliveryIdString = progressDeliveryView.userInput1Text.getText();
        String itemOrderIdString = progressDeliveryView.userInput2Text.getText();
        progressDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        progressDeliveryView.userInputError2Label.setVisible(itemOrderIdString.equals(""));
        if (deliveryIdString.equals("") || itemOrderIdString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            int itemOrderId = Integer.parseInt(itemOrderIdString);
            String result = systemFacade.leaveItem(deliveryId, itemOrderId);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == progressDeliveryView.backButton) {
            if (manager)
                new DeliveryManagementController(true);
            else
                new DeliveryManagementController();
            progressDeliveryView.dispose();
        } else if (e.getSource() == progressDeliveryView.userInputBackButton) {
            hideUserInput();
            showMainOptions();
        } else if (e.getSource() == progressDeliveryView.approveDeliveryButton) {
            hideMainOptions();
            approveDelivery();
        } else if (e.getSource() == progressDeliveryView.visitStopButton) {
            hideMainOptions();
            visitStop();
        } else if (e.getSource() == progressDeliveryView.switchTruckButton) {
            hideMainOptions();
            switchTruck();
        } else if (e.getSource() == progressDeliveryView.switchTruckDriverButton) {
            hideMainOptions();
            switchTruckDriver();
        } else if (e.getSource() == progressDeliveryView.skipStopButton) {
            hideMainOptions();
            skipStop();
        } else if (e.getSource() == progressDeliveryView.leaveItemButton) {
            hideMainOptions();
            leaveItem();
        } else if (e.getSource() == progressDeliveryView.approveDeliveryConfirmButton) {
            approveDeliveryConfirm();
        } else if (e.getSource() == progressDeliveryView.visitStopConfirmButton) {
            visitStopConfirm();
        } else if (e.getSource() == progressDeliveryView.switchTruckConfirmButton) {
            switchTruckConfirm();
        } else if (e.getSource() == progressDeliveryView.switchTruckDriverConfirmButton) {
            switchTruckDriverConfirm();
        } else if (e.getSource() == progressDeliveryView.skipStopConfirmButton) {
            skipStopConfirm();
        } else if (e.getSource() == progressDeliveryView.leaveItemConfirmButton) {
            leaveItemConfirm();
        }
    }
}
