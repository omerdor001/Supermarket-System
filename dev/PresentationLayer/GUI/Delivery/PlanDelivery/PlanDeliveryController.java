package PresentationLayer.GUI.Delivery.PlanDelivery;

import PresentationLayer.GUI.Delivery.DeliveryManagement.DeliveryManagementController;
import PresentationLayer.GUI.Delivery.ShowAllItemOrders.ShowAllItemOrdersController;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlanDeliveryController implements ActionListener {

    PlanDeliveryView planDeliveryView;
    SystemFacade systemFacade;
    private boolean manager;

    public PlanDeliveryController() {
        planDeliveryView = new PlanDeliveryView();
        systemFacade = SystemFacade.getInstance();

        planDeliveryView.backButton.addActionListener(this);
        planDeliveryView.createDeliveryButton.addActionListener(this);
        planDeliveryView.cancelDeliveryButton.addActionListener(this);
        planDeliveryView.updateTruckButton.addActionListener(this);
        planDeliveryView.updateDriverButton.addActionListener(this);
        planDeliveryView.addStopButton.addActionListener(this);
        planDeliveryView.removeStopButton.addActionListener(this);
        planDeliveryView.editStopTimeButton.addActionListener(this);
        planDeliveryView.addStopItemButton.addActionListener(this);
        planDeliveryView.removeStopItemButton.addActionListener(this);
        planDeliveryView.showItemOrdersButton.addActionListener(this);
        planDeliveryView.userInputBackButton.addActionListener(this);
        planDeliveryView.createDeliveryConfirmButton.addActionListener(this);
        planDeliveryView.updateTruckConfirmButton.addActionListener(this);
        planDeliveryView.updateDriverConfirmButton.addActionListener(this);
        planDeliveryView.cancelDeliveryConfirmButton.addActionListener(this);
        planDeliveryView.addStopConfirmButton.addActionListener(this);
        planDeliveryView.removeStopConfirmButton.addActionListener(this);
        planDeliveryView.addItemConfirmButton.addActionListener(this);
        planDeliveryView.removeItemConfirmButton.addActionListener(this);
        planDeliveryView.editStopTimeConfirmButton.addActionListener(this);
    }

    public PlanDeliveryController(boolean manager) {
        this();
        this.manager = manager;
    }

    private void hideMainOptions() {
        planDeliveryView.mainMenuLabel.setVisible(false);
        planDeliveryView.selectActionLabel.setVisible(false);
        planDeliveryView.backButton.setVisible(false);
        planDeliveryView.createDeliveryButton.setVisible(false);
        planDeliveryView.cancelDeliveryButton.setVisible(false);
        planDeliveryView.updateTruckButton.setVisible(false);
        planDeliveryView.updateDriverButton.setVisible(false);
        planDeliveryView.addStopButton.setVisible(false);
        planDeliveryView.removeStopButton.setVisible(false);
        planDeliveryView.editStopTimeButton.setVisible(false);
        planDeliveryView.addStopItemButton.setVisible(false);
        planDeliveryView.removeStopItemButton.setVisible(false);
        planDeliveryView.showItemOrdersButton.setVisible(false);
    }

    private void showMainOptions() {
        planDeliveryView.mainMenuLabel.setVisible(true);
        planDeliveryView.selectActionLabel.setVisible(true);
        planDeliveryView.backButton.setVisible(true);
        planDeliveryView.createDeliveryButton.setVisible(true);
        planDeliveryView.cancelDeliveryButton.setVisible(true);
        planDeliveryView.updateTruckButton.setVisible(true);
        planDeliveryView.updateDriverButton.setVisible(true);
        planDeliveryView.addStopButton.setVisible(true);
        planDeliveryView.removeStopButton.setVisible(true);
        planDeliveryView.editStopTimeButton.setVisible(true);
        planDeliveryView.addStopItemButton.setVisible(true);
        planDeliveryView.removeStopItemButton.setVisible(true);
        planDeliveryView.showItemOrdersButton.setVisible(true);
    }

    private void hideUserInput() {
        planDeliveryView.userInput1Text.setVisible(false);
        planDeliveryView.userInput2Text.setVisible(false);
        planDeliveryView.userInput3Text.setVisible(false);
        planDeliveryView.userInput4Text.setVisible(false);
        planDeliveryView.userInput5Text.setVisible(false);
        planDeliveryView.userInput1Label.setVisible(false);
        planDeliveryView.userInput2Label.setVisible(false);
        planDeliveryView.userInput3Label.setVisible(false);
        planDeliveryView.userInput4Label.setVisible(false);
        planDeliveryView.userInput5Label.setVisible(false);
        planDeliveryView.userInputError1Label.setVisible(false);
        planDeliveryView.userInputError2Label.setVisible(false);
        planDeliveryView.userInputError3Label.setVisible(false);
        planDeliveryView.userInputError4Label.setVisible(false);
        planDeliveryView.userInputError5Label.setVisible(false);
        planDeliveryView.userInputBackButton.setVisible(false);
        planDeliveryView.userInputMainLabel.setVisible(false);
        planDeliveryView.userInputCheckBox.setVisible(false);
        planDeliveryView.userInputRadioButton1.setVisible(false);
        planDeliveryView.userInputRadioButton2.setVisible(false);
        planDeliveryView.userInput1Text.setText("");
        planDeliveryView.userInput2Text.setText("");
        planDeliveryView.userInput3Text.setText("");
        planDeliveryView.userInput4Text.setText("");
        planDeliveryView.userInput5Text.setText("");
        planDeliveryView.userInputCheckBox.setSelected(false);
        planDeliveryView.userInputCheckBox.setSelected(false);
        planDeliveryView.userInputRadioButton1.setSelected(false);
        planDeliveryView.userInputRadioButton2.setSelected(false);
        planDeliveryView.radioGroup.clearSelection();

        planDeliveryView.createDeliveryConfirmButton.setVisible(false);
        planDeliveryView.cancelDeliveryConfirmButton.setVisible(false);
        planDeliveryView.updateTruckConfirmButton.setVisible(false);
        planDeliveryView.updateDriverConfirmButton.setVisible(false);
        planDeliveryView.addStopConfirmButton.setVisible(false);
        planDeliveryView.removeStopConfirmButton.setVisible(false);
        planDeliveryView.addItemConfirmButton.setVisible(false);
        planDeliveryView.removeItemConfirmButton.setVisible(false);
        planDeliveryView.editStopTimeConfirmButton.setVisible(false);
    }

    private void createNewDelivery() {
        planDeliveryView.userInputMainLabel.setText("You are in the delivery creation menu, please insert the required information");
        planDeliveryView.userInputMainLabel.setBounds(85, 10, 550, 200);

        planDeliveryView.userInput1Label.setText("Input delivery date in the format YYYY/MM/DD:");
        planDeliveryView.userInput1Label.setBounds(50, 50, 350, 200);
        planDeliveryView.userInput2Label.setText("Input delivery time in the format HH:MM:");
        planDeliveryView.userInput2Label.setBounds(50, 100, 300, 200);
        planDeliveryView.userInput3Label.setText("Input delivery truck id:");
        planDeliveryView.userInput3Label.setBounds(50, 150, 300, 200);
        planDeliveryView.userInput4Label.setText("Input delivery driver id:");
        planDeliveryView.userInput4Label.setBounds(50, 200, 300, 200);
        planDeliveryView.userInput5Label.setText("Input delivery source site:");
        planDeliveryView.userInput5Label.setBounds(50, 250, 300, 200);

        planDeliveryView.userInputError1Label.setText("You must input a date");
        planDeliveryView.userInputError1Label.setBounds(525, 50, 300, 200);
        planDeliveryView.userInputError2Label.setText("You must input time");
        planDeliveryView.userInputError2Label.setBounds(525, 100, 300, 200);
        planDeliveryView.userInputError3Label.setText("You must input an id");
        planDeliveryView.userInputError3Label.setBounds(525, 150, 300, 200);
        planDeliveryView.userInputError4Label.setText("You must input an id");
        planDeliveryView.userInputError4Label.setBounds(525, 200, 300, 200);
        planDeliveryView.userInputError5Label.setText("You must input an address");
        planDeliveryView.userInputError5Label.setBounds(525, 250, 300, 200);

        planDeliveryView.userInput1Text.setBounds(375, 50, 125, 25);
        planDeliveryView.userInput2Text.setBounds(375, 100, 125, 25);
        planDeliveryView.userInput3Text.setBounds(375, 150, 125, 25);
        planDeliveryView.userInput4Text.setBounds(375, 200, 125, 25);
        planDeliveryView.userInput5Text.setBounds(375, 250, 125, 25);

        planDeliveryView.userInputBackButton.setBounds(150, 300, 200, 50);

        planDeliveryView.userInputMainLabel.setVisible(true);
        planDeliveryView.userInput1Label.setVisible(true);
        planDeliveryView.userInput2Label.setVisible(true);
        planDeliveryView.userInput3Label.setVisible(true);
        planDeliveryView.userInput4Label.setVisible(true);
        planDeliveryView.userInput5Label.setVisible(true);
        planDeliveryView.userInput1Text.setVisible(true);
        planDeliveryView.userInput2Text.setVisible(true);
        planDeliveryView.userInput3Text.setVisible(true);
        planDeliveryView.userInput4Text.setVisible(true);
        planDeliveryView.userInput5Text.setVisible(true);
        planDeliveryView.userInputBackButton.setVisible(true);
        planDeliveryView.createDeliveryConfirmButton.setVisible(true);
    }

    private void cancelDelivery() {
        planDeliveryView.userInputMainLabel.setText("You are in the delivery cancel menu, please insert the required information");
        planDeliveryView.userInputMainLabel.setBounds(90, 50, 550, 200);
        planDeliveryView.userInputError1Label.setText("You must input an id");
        planDeliveryView.userInputError1Label.setBounds(475, 100, 300, 200);
        planDeliveryView.userInput1Label.setText("Input delivery id:");
        planDeliveryView.userInput1Label.setBounds(150, 100, 350, 200);
        planDeliveryView.userInput1Text.setBounds(300, 100, 150, 25);
        planDeliveryView.cancelDeliveryConfirmButton.setBounds(350, 150, 200, 50);
        planDeliveryView.userInputBackButton.setBounds(150, 150, 200, 50);
        planDeliveryView.userInputMainLabel.setVisible(true);
        planDeliveryView.userInput1Label.setVisible(true);
        planDeliveryView.userInput1Text.setVisible(true);
        planDeliveryView.cancelDeliveryConfirmButton.setVisible(true);
        planDeliveryView.userInputBackButton.setVisible(true);
    }

    private void updateTruck() {
        planDeliveryView.userInputMainLabel.setText("You are in the delivery truck switch menu, please insert the required information");
        planDeliveryView.userInputMainLabel.setBounds(75, 50, 550, 200);
        planDeliveryView.userInputError1Label.setText("You must input an id");
        planDeliveryView.userInputError1Label.setBounds(475, 100, 300, 200);
        planDeliveryView.userInputError2Label.setText("You must input an id");
        planDeliveryView.userInputError2Label.setBounds(475, 150, 300, 200);
        planDeliveryView.userInput1Label.setText("Input delivery id:");
        planDeliveryView.userInput1Label.setBounds(150, 100, 350, 200);
        planDeliveryView.userInput2Label.setText("Input new truck id:");
        planDeliveryView.userInput2Label.setBounds(150, 150, 300, 200);
        planDeliveryView.userInput1Text.setBounds(300, 100, 150, 25);
        planDeliveryView.userInput2Text.setBounds(300, 150, 150, 25);
        planDeliveryView.updateTruckConfirmButton.setBounds(350, 200, 200, 50);
        planDeliveryView.userInputBackButton.setBounds(150, 200, 200, 50);
        planDeliveryView.userInputMainLabel.setVisible(true);
        planDeliveryView.userInput1Label.setVisible(true);
        planDeliveryView.userInput2Label.setVisible(true);
        planDeliveryView.userInput1Text.setVisible(true);
        planDeliveryView.userInput2Text.setVisible(true);
        planDeliveryView.updateTruckConfirmButton.setVisible(true);
        planDeliveryView.userInputBackButton.setVisible(true);
    }

    private void updateDriver() {
        planDeliveryView.userInputMainLabel.setText("You are in the delivery driver switch menu, please insert the required information");
        planDeliveryView.userInputMainLabel.setBounds(30, 50, 550, 200);
        planDeliveryView.userInputMainLabel.setBounds(75, 50, 550, 200);
        planDeliveryView.userInputError1Label.setText("You must input an id");
        planDeliveryView.userInputError1Label.setBounds(475, 100, 300, 200);
        planDeliveryView.userInputError2Label.setText("You must input an id");
        planDeliveryView.userInputError2Label.setBounds(475, 150, 300, 200);
        planDeliveryView.userInput1Label.setText("Input delivery id:");
        planDeliveryView.userInput1Label.setBounds(150, 100, 350, 200);
        planDeliveryView.userInput2Label.setText("Input new driver id:");
        planDeliveryView.userInput2Label.setBounds(150, 150, 300, 200);
        planDeliveryView.userInput1Text.setBounds(300, 100, 150, 25);
        planDeliveryView.userInput2Text.setBounds(300, 150, 150, 25);
        planDeliveryView.updateDriverConfirmButton.setBounds(350, 200, 200, 50);
        planDeliveryView.userInputBackButton.setBounds(150, 200, 200, 50);
        planDeliveryView.userInputMainLabel.setVisible(true);
        planDeliveryView.userInput1Label.setVisible(true);
        planDeliveryView.userInput2Label.setVisible(true);
        planDeliveryView.userInput1Text.setVisible(true);
        planDeliveryView.userInput2Text.setVisible(true);
        planDeliveryView.updateDriverConfirmButton.setVisible(true);
        planDeliveryView.userInputBackButton.setVisible(true);
    }

    private void addStop() {
        planDeliveryView.userInputMainLabel.setText("You are in the delivery add stop menu, please insert the required information");
        planDeliveryView.userInputMainLabel.setBounds(85, 50, 550, 200);
        planDeliveryView.userInputError1Label.setText("You must input an id");
        planDeliveryView.userInputError1Label.setBounds(475, 100, 300, 200);
        planDeliveryView.userInputError2Label.setText("You must input an address");
        planDeliveryView.userInputError2Label.setBounds(475, 150, 300, 200);
        planDeliveryView.userInput1Label.setText("Input delivery id:");
        planDeliveryView.userInput1Label.setBounds(150, 100, 350, 200);
        planDeliveryView.userInput2Label.setText("Input stop address:");
        planDeliveryView.userInput2Label.setBounds(150, 150, 300, 200);
        planDeliveryView.userInput1Text.setBounds(300, 100, 150, 25);
        planDeliveryView.userInput2Text.setBounds(300, 150, 150, 25);
        planDeliveryView.addStopConfirmButton.setBounds(350, 200, 200, 50);
        planDeliveryView.userInputBackButton.setBounds(150, 200, 200, 50);
        planDeliveryView.userInputMainLabel.setVisible(true);
        planDeliveryView.userInput1Label.setVisible(true);
        planDeliveryView.userInput2Label.setVisible(true);
        planDeliveryView.userInput1Text.setVisible(true);
        planDeliveryView.userInput2Text.setVisible(true);
        planDeliveryView.addStopConfirmButton.setVisible(true);
        planDeliveryView.userInputBackButton.setVisible(true);
    }

    private void removeStop() {
        planDeliveryView.userInputMainLabel.setText("You are in the delivery remove stop menu, please insert the required information");
        planDeliveryView.userInputMainLabel.setBounds(75, 50, 550, 200);
        planDeliveryView.userInputError1Label.setText("You must input an id");
        planDeliveryView.userInputError1Label.setBounds(475, 100, 300, 200);
        planDeliveryView.userInputError2Label.setText("You must input an address");
        planDeliveryView.userInputError2Label.setBounds(475, 150, 300, 200);
        planDeliveryView.userInput1Label.setText("Input delivery id:");
        planDeliveryView.userInput1Label.setBounds(150, 100, 350, 200);
        planDeliveryView.userInput2Label.setText("Input stop address:");
        planDeliveryView.userInput2Label.setBounds(150, 150, 300, 200);
        planDeliveryView.userInput1Text.setBounds(300, 100, 150, 25);
        planDeliveryView.userInput2Text.setBounds(300, 150, 150, 25);
        planDeliveryView.removeStopConfirmButton.setBounds(350, 200, 200, 50);
        planDeliveryView.userInputBackButton.setBounds(150, 200, 200, 50);
        planDeliveryView.userInputMainLabel.setVisible(true);
        planDeliveryView.userInput1Label.setVisible(true);
        planDeliveryView.userInput2Label.setVisible(true);
        planDeliveryView.userInput1Text.setVisible(true);
        planDeliveryView.userInput2Text.setVisible(true);
        planDeliveryView.removeStopConfirmButton.setVisible(true);
        planDeliveryView.userInputBackButton.setVisible(true);
    }

    private void addItem() {
        planDeliveryView.userInputMainLabel.setText("You are in the delivery item adding menu, please insert the required information");
        planDeliveryView.userInputMainLabel.setBounds(75, 15, 550, 200);
        planDeliveryView.userInputError1Label.setText("You must input an id");
        planDeliveryView.userInputError1Label.setBounds(485, 50, 300, 200);
        planDeliveryView.userInputError2Label.setText("You must input an id");
        planDeliveryView.userInputError2Label.setBounds(485, 100, 300, 200);
        planDeliveryView.userInputError3Label.setText("You must select an option");
        planDeliveryView.userInputError3Label.setBounds(485, 200, 300, 200);
        planDeliveryView.userInput1Label.setText("Input delivery id:");
        planDeliveryView.userInput1Label.setBounds(150, 50, 350, 200);
        planDeliveryView.userInput2Label.setText("Input item order id:");
        planDeliveryView.userInput2Label.setBounds(150, 100, 300, 200);
        planDeliveryView.userInput3Label.setText("Check delivery type:");
        planDeliveryView.userInput3Label.setBounds(150, 150, 300, 200);
        planDeliveryView.userInput4Label.setText("Select delivery stage:");
        planDeliveryView.userInput4Label.setBounds(150, 200, 300, 200);
        planDeliveryView.userInput1Text.setBounds(300, 50, 150, 25);
        planDeliveryView.userInput2Text.setBounds(300, 100, 150, 25);
        planDeliveryView.userInputCheckBox.setText("Direct delivery");
        planDeliveryView.userInputCheckBox.setBounds(300, 137, 200, 50);
        planDeliveryView.addItemConfirmButton.setBounds(350, 250, 200, 50);
        planDeliveryView.userInputBackButton.setBounds(150, 250, 200, 50);
        planDeliveryView.userInputRadioButton1.setText("Direct or to logic center");
        planDeliveryView.userInputRadioButton1.setBounds(300, 185, 185, 25);
        planDeliveryView.userInputRadioButton2.setText("From logic center");
        planDeliveryView.userInputRadioButton2.setBounds(300, 215, 150, 25);
        planDeliveryView.userInputMainLabel.setVisible(true);
        planDeliveryView.userInput1Label.setVisible(true);
        planDeliveryView.userInput2Label.setVisible(true);
        planDeliveryView.userInput3Label.setVisible(true);
        planDeliveryView.userInput4Label.setVisible(true);
        planDeliveryView.userInput1Text.setVisible(true);
        planDeliveryView.userInput2Text.setVisible(true);
        planDeliveryView.userInputCheckBox.setVisible(true);
        planDeliveryView.userInputRadioButton1.setVisible(true);
        planDeliveryView.userInputRadioButton2.setVisible(true);
        planDeliveryView.addItemConfirmButton.setVisible(true);
        planDeliveryView.userInputBackButton.setVisible(true);
    }

    private void removeItem() {
        planDeliveryView.userInputMainLabel.setText("You are in the delivery remove item menu, please insert the required information");
        planDeliveryView.userInputMainLabel.setBounds(75, 50, 550, 200);
        planDeliveryView.userInputError1Label.setText("You must input an id");
        planDeliveryView.userInputError1Label.setBounds(475, 100, 300, 200);
        planDeliveryView.userInputError2Label.setText("You must input an id");
        planDeliveryView.userInputError2Label.setBounds(475, 150, 300, 200);
        planDeliveryView.userInput1Label.setText("Input delivery id:");
        planDeliveryView.userInput1Label.setBounds(150, 100, 350, 200);
        planDeliveryView.userInput2Label.setText("Input item order id:");
        planDeliveryView.userInput2Label.setBounds(150, 150, 300, 200);
        planDeliveryView.userInput1Text.setBounds(300, 100, 150, 25);
        planDeliveryView.userInput2Text.setBounds(300, 150, 150, 25);
        planDeliveryView.removeItemConfirmButton.setBounds(350, 200, 200, 50);
        planDeliveryView.userInputBackButton.setBounds(150, 200, 200, 50);
        planDeliveryView.userInputMainLabel.setVisible(true);
        planDeliveryView.userInput1Label.setVisible(true);
        planDeliveryView.userInput2Label.setVisible(true);
        planDeliveryView.userInput1Text.setVisible(true);
        planDeliveryView.userInput2Text.setVisible(true);
        planDeliveryView.removeItemConfirmButton.setVisible(true);
        planDeliveryView.userInputBackButton.setVisible(true);
    }

    private void editStopTime() {
        planDeliveryView.userInputMainLabel.setText("You are in the edit stop arrive time menu, please insert the required information");
        planDeliveryView.userInputMainLabel.setBounds(80, 50, 550, 200);
        planDeliveryView.userInputError1Label.setText("You must input an id");
        planDeliveryView.userInputError1Label.setBounds(485, 100, 300, 200);
        planDeliveryView.userInputError2Label.setText("You must input an address");
        planDeliveryView.userInputError2Label.setBounds(485, 150, 300, 200);
        planDeliveryView.userInputError3Label.setText("You must input time");
        planDeliveryView.userInputError3Label.setBounds(485, 200, 300, 200);
        planDeliveryView.userInput1Label.setText("Input delivery id:");
        planDeliveryView.userInput1Label.setBounds(50, 100, 350, 200);
        planDeliveryView.userInput2Label.setText("Input stop address:");
        planDeliveryView.userInput2Label.setBounds(50, 150, 300, 200);
        planDeliveryView.userInput3Label.setText("Input new arrive time in the format HH:MM:");
        planDeliveryView.userInput3Label.setBounds(50, 200, 300, 200);
        planDeliveryView.userInput1Text.setBounds(350, 100, 125, 25);
        planDeliveryView.userInput2Text.setBounds(350, 150, 125, 25);
        planDeliveryView.userInput3Text.setBounds(350, 200, 125, 25);
        planDeliveryView.editStopTimeConfirmButton.setBounds(350, 250, 200, 50);
        planDeliveryView.userInputBackButton.setBounds(150, 250, 200, 50);
        planDeliveryView.userInputMainLabel.setVisible(true);
        planDeliveryView.userInput1Label.setVisible(true);
        planDeliveryView.userInput2Label.setVisible(true);
        planDeliveryView.userInput3Label.setVisible(true);
        planDeliveryView.userInput1Text.setVisible(true);
        planDeliveryView.userInput2Text.setVisible(true);
        planDeliveryView.userInput3Text.setVisible(true);
        planDeliveryView.editStopTimeConfirmButton.setVisible(true);
        planDeliveryView.userInputBackButton.setVisible(true);
    }

    private void showItemOrders() {
        new ShowAllItemOrdersController();
    }

    private void createDeliveryConfirm() {
        String dateString = planDeliveryView.userInput1Text.getText();
        String timeString = planDeliveryView.userInput2Text.getText();
        String truckIdString = planDeliveryView.userInput3Text.getText();
        String driverId = planDeliveryView.userInput4Text.getText();
        String address = planDeliveryView.userInput5Text.getText();

        planDeliveryView.userInputError1Label.setVisible(dateString.equals(""));
        planDeliveryView.userInputError2Label.setVisible(timeString.equals(""));
        planDeliveryView.userInputError3Label.setVisible(truckIdString.equals(""));
        planDeliveryView.userInputError4Label.setVisible(driverId.equals(""));
        planDeliveryView.userInputError5Label.setVisible(address.equals(""));
        if (dateString.equals("") || timeString.equals("") || truckIdString.equals("") || driverId.equals("") || address.equals(""))
            return;

        String[] date = dateString.split("/");
        if (date.length != 3) {
            JOptionPane.showMessageDialog(null, "Wrong date format", "Date error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] time = timeString.split(":");
        if (time.length != 2) {
            JOptionPane.showMessageDialog(null, "Wrong time format", "Time error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int truckId;
        try {
            truckId = Integer.parseInt(truckIdString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Truck id must be a non negative integer", "Wrong time", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String result = systemFacade.addDelivery(LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])), LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])), truckId, driverId, address);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelDeliveryConfirm() {
        try {
            String deliveryIdString = planDeliveryView.userInput1Text.getText();
            if (deliveryIdString.equals("")) {
                planDeliveryView.userInputError1Label.setVisible(true);
                return;
            } else
                planDeliveryView.setVisible(false);
            int deliveryId = Integer.parseInt(deliveryIdString);
            String result = systemFacade.cancelDelivery(deliveryId);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTruckConfirm() {
        String deliveryIdString = planDeliveryView.userInput1Text.getText();
        String truckIdString = planDeliveryView.userInput2Text.getText();
        planDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        planDeliveryView.userInputError2Label.setVisible(truckIdString.equals(""));
        if (deliveryIdString.equals("") || truckIdString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            int truckId = Integer.parseInt(truckIdString);
            String result = systemFacade.updateDeliveryTruck(deliveryId, truckId);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDriverConfirm() {
        String deliveryIdString = planDeliveryView.userInput1Text.getText();
        String driverIdString = planDeliveryView.userInput2Text.getText();
        planDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        planDeliveryView.userInputError2Label.setVisible(driverIdString.equals(""));
        if (deliveryIdString.equals("") || driverIdString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            int driverId = Integer.parseInt(driverIdString);
            String result = systemFacade.updateDeliveryTruck(deliveryId, driverId);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStopConfirm() {
        String deliveryIdString = planDeliveryView.userInput1Text.getText();
        String stopAddress = planDeliveryView.userInput2Text.getText();
        planDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        planDeliveryView.userInputError2Label.setVisible(stopAddress.equals(""));
        if (deliveryIdString.equals("") || stopAddress.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            String result = systemFacade.addStopToDelivery(deliveryId, stopAddress);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeStopConfirm() {
        String deliveryIdString = planDeliveryView.userInput1Text.getText();
        String stopAddress = planDeliveryView.userInput2Text.getText();
        planDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        planDeliveryView.userInputError2Label.setVisible(stopAddress.equals(""));
        if (deliveryIdString.equals("") || stopAddress.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            String result = systemFacade.removeStopFromDelivery(deliveryId, stopAddress);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addItemConfirm() {
        String deliveryIdString = planDeliveryView.userInput1Text.getText();
        String itemOrderString = planDeliveryView.userInput2Text.getText();
        planDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        planDeliveryView.userInputError2Label.setVisible(itemOrderString.equals(""));
        planDeliveryView.userInputError3Label.setVisible((!planDeliveryView.userInputRadioButton1.isSelected() && !planDeliveryView.userInputRadioButton2.isSelected()));
        if (deliveryIdString.equals("") || itemOrderString.equals("") || (!planDeliveryView.userInputRadioButton1.isSelected() && !planDeliveryView.userInputRadioButton2.isSelected()))
            return;
        int stage = 0;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            int itemOrderId = Integer.parseInt(itemOrderString);
            boolean direct = planDeliveryView.userInputCheckBox.isSelected();
            if (planDeliveryView.userInputRadioButton2.isSelected())
                stage = 1;
            String result = systemFacade.addItemToDelivery(deliveryId, itemOrderId, direct, stage);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeItemConfirm() {
        String deliveryIdString = planDeliveryView.userInput1Text.getText();
        String itemOrderString = planDeliveryView.userInput2Text.getText();
        planDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        planDeliveryView.userInputError2Label.setVisible(itemOrderString.equals(""));
        if (deliveryIdString.equals("") || itemOrderString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(deliveryIdString);
            int itemOrderId = Integer.parseInt(itemOrderString);
            String result = systemFacade.removeItemFromDelivery(deliveryId, itemOrderId);
            JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editStopTimeConfirm() {
        String deliveryIdString = planDeliveryView.userInput1Text.getText();
        String stopAddress = planDeliveryView.userInput2Text.getText();
        String timeString = planDeliveryView.userInput3Text.getText();
        planDeliveryView.userInputError1Label.setVisible(deliveryIdString.equals(""));
        planDeliveryView.userInputError2Label.setVisible(stopAddress.equals(""));
        planDeliveryView.userInputError3Label.setVisible(timeString.equals(""));
        if (deliveryIdString.equals("") || stopAddress.equals("") || timeString.equals(""))
            return;
        try {
            int deliveryId = Integer.parseInt(planDeliveryView.userInput1Text.getText());
            String[] time = timeString.split(":");
            if (time.length != 2) {
                JOptionPane.showMessageDialog(null, "Wrong time format", "Wrong time", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String result = systemFacade.updateArriveTimeToStop(deliveryId, stopAddress, LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
                JOptionPane.showMessageDialog(null, result, "", JOptionPane.INFORMATION_MESSAGE);
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Time error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == planDeliveryView.backButton) {
            if (manager)
                new DeliveryManagementController(true);
            else
                new DeliveryManagementController();
            planDeliveryView.dispose();
        } else if (e.getSource() == planDeliveryView.userInputBackButton) {
            hideUserInput();
            showMainOptions();
        } else if (e.getSource() == planDeliveryView.createDeliveryButton) {
            hideMainOptions();
            createNewDelivery();
        } else if (e.getSource() == planDeliveryView.cancelDeliveryButton) {
            hideMainOptions();
            cancelDelivery();
        } else if (e.getSource() == planDeliveryView.updateTruckButton) {
            hideMainOptions();
            updateTruck();
        } else if (e.getSource() == planDeliveryView.updateDriverButton) {
            hideMainOptions();
            updateDriver();
        } else if (e.getSource() == planDeliveryView.addStopButton) {
            hideMainOptions();
            addStop();
        } else if (e.getSource() == planDeliveryView.removeStopButton) {
            hideMainOptions();
            removeStop();
        } else if (e.getSource() == planDeliveryView.addStopItemButton) {
            hideMainOptions();
            addItem();
        } else if (e.getSource() == planDeliveryView.removeStopItemButton) {
            hideMainOptions();
            removeItem();
        } else if (e.getSource() == planDeliveryView.showItemOrdersButton) {
            showItemOrders();
        } else if (e.getSource() == planDeliveryView.editStopTimeButton) {
            hideMainOptions();
            editStopTime();
        } else if (e.getSource() == planDeliveryView.createDeliveryConfirmButton) {
            createDeliveryConfirm();
        } else if (e.getSource() == planDeliveryView.cancelDeliveryConfirmButton) {
            cancelDeliveryConfirm();
        } else if (e.getSource() == planDeliveryView.updateTruckConfirmButton) {
            updateTruckConfirm();
        } else if (e.getSource() == planDeliveryView.updateDriverConfirmButton) {
            updateDriverConfirm();
        } else if (e.getSource() == planDeliveryView.addStopConfirmButton) {
            addStopConfirm();
        } else if (e.getSource() == planDeliveryView.removeStopConfirmButton) {
            removeStopConfirm();
        } else if (e.getSource() == planDeliveryView.addItemConfirmButton) {
            addItemConfirm();
        } else if (e.getSource() == planDeliveryView.removeItemConfirmButton) {
            removeItemConfirm();
        } else if (e.getSource() == planDeliveryView.editStopTimeConfirmButton) {
            editStopTimeConfirm();
        }
    }
}
