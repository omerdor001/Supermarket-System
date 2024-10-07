package PresentationLayer.GUI.Delivery.TruckManagement;

import PresentationLayer.GUI.Delivery.ResourceManagement.ResourceManagementController;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TruckManagementController implements ActionListener {

    SystemFacade systemFacade;
    TruckManagementView truckManagementView;
    boolean manager;

    public TruckManagementController() {
        systemFacade = SystemFacade.getInstance();
        truckManagementView = new TruckManagementView();
        truckManagementView.backButton.addActionListener(this);
        truckManagementView.editStatusButton.addActionListener(this);
        truckManagementView.addDeliveryDateButton.addActionListener(this);
        truckManagementView.removeDeliveryDateButton.addActionListener(this);
        truckManagementView.addTruckButton.addActionListener(this);
        truckManagementView.removeTruckButton.addActionListener(this);
        truckManagementView.userInputBackButton.addActionListener(this);
        truckManagementView.addTruckConfirmButton.addActionListener(this);
        truckManagementView.removeTruckConfirmButton.addActionListener(this);
        truckManagementView.editStatusConfirmButton.addActionListener(this);
        truckManagementView.addDeliveryDateConfirmButton.addActionListener(this);
        truckManagementView.removeDeliveryDateConfirmButton.addActionListener(this);
    }

    public TruckManagementController(boolean manager) {
        this();
        this.manager = manager;
    }

    public void hideMainOptions() {
        truckManagementView.truckManagementLabel.setVisible(false);
        truckManagementView.selectActionLabel.setVisible(false);
        truckManagementView.backButton.setVisible(false);
        truckManagementView.editStatusButton.setVisible(false);
        truckManagementView.addDeliveryDateButton.setVisible(false);
        truckManagementView.removeDeliveryDateButton.setVisible(false);
        truckManagementView.addTruckButton.setVisible(false);
        truckManagementView.removeTruckButton.setVisible(false);
    }

    public void showMainOptions() {
        truckManagementView.truckManagementLabel.setVisible(true);
        truckManagementView.selectActionLabel.setVisible(true);
        truckManagementView.backButton.setVisible(true);
        truckManagementView.editStatusButton.setVisible(true);
        truckManagementView.addDeliveryDateButton.setVisible(true);
        truckManagementView.removeDeliveryDateButton.setVisible(true);
        truckManagementView.addTruckButton.setVisible(true);
        truckManagementView.removeTruckButton.setVisible(true);
    }

    public void hideUserInput() {
        truckManagementView.userInput1Text.setVisible(false);
        truckManagementView.userInput2Text.setVisible(false);
        truckManagementView.userInput3Text.setVisible(false);
        truckManagementView.userInput4Text.setVisible(false);
        truckManagementView.userInput5Text.setVisible(false);
        truckManagementView.userInput1Label.setVisible(false);
        truckManagementView.userInput2Label.setVisible(false);
        truckManagementView.userInput3Label.setVisible(false);
        truckManagementView.userInput4Label.setVisible(false);
        truckManagementView.userInput5Label.setVisible(false);
        truckManagementView.userInputBackButton.setVisible(false);
        truckManagementView.userInputMainLabel.setVisible(false);
        truckManagementView.userInputError1Label.setVisible(false);
        truckManagementView.userInputError2Label.setVisible(false);
        truckManagementView.userInputError3Label.setVisible(false);
        truckManagementView.userInputError4Label.setVisible(false);
        truckManagementView.userInputError5Label.setVisible(false);
        truckManagementView.userInputRadioButton1.setVisible(false);
        truckManagementView.userInputRadioButton2.setVisible(false);
        truckManagementView.radioGroup.clearSelection();
        truckManagementView.userInput1Text.setText("");
        truckManagementView.userInput2Text.setText("");
        truckManagementView.userInput3Text.setText("");
        truckManagementView.userInput4Text.setText("");
        truckManagementView.userInput5Text.setText("");
        truckManagementView.addTruckConfirmButton.setVisible(false);
        truckManagementView.removeTruckConfirmButton.setVisible(false);
        truckManagementView.editStatusConfirmButton.setVisible(false);
        truckManagementView.addDeliveryDateConfirmButton.setVisible(false);
        truckManagementView.removeDeliveryDateConfirmButton.setVisible(false);
    }

    public void hideErrors() {
        truckManagementView.userInputError1Label.setVisible(false);
        truckManagementView.userInputError2Label.setVisible(false);
        truckManagementView.userInputError3Label.setVisible(false);
        truckManagementView.userInputError4Label.setVisible(false);
        truckManagementView.userInputError5Label.setVisible(false);
    }

    public void addNewTruck() {
        truckManagementView.userInputMainLabel.setText("You are in the truck addition menu, please insert the required information");
        truckManagementView.userInputMainLabel.setBounds(35, 10, 550, 200);
        truckManagementView.userInput1Label.setText("Input id:");
        truckManagementView.userInput1Label.setBounds(100, 50, 350, 200);
        truckManagementView.userInput2Label.setText("Input type:");
        truckManagementView.userInput2Label.setBounds(100, 100, 300, 200);
        truckManagementView.userInput3Label.setText("Input model:");
        truckManagementView.userInput3Label.setBounds(100, 150, 300, 200);
        truckManagementView.userInput4Label.setText("Input net weight:");
        truckManagementView.userInput4Label.setBounds(100, 200, 300, 200);
        truckManagementView.userInput5Label.setText("Input max weight:");
        truckManagementView.userInput5Label.setBounds(100, 250, 300, 200);
        truckManagementView.userInput1Text.setBounds(250, 50, 150, 25);
        truckManagementView.userInput2Text.setBounds(250, 100, 150, 25);
        truckManagementView.userInput3Text.setBounds(250, 150, 150, 25);
        truckManagementView.userInput4Text.setBounds(250, 200, 150, 25);
        truckManagementView.userInput5Text.setBounds(250, 250, 150, 25);
        truckManagementView.userInputBackButton.setBounds(100, 300, 200, 50);
        truckManagementView.userInputMainLabel.setVisible(true);
        truckManagementView.userInput1Label.setVisible(true);
        truckManagementView.userInput2Label.setVisible(true);
        truckManagementView.userInput3Label.setVisible(true);
        truckManagementView.userInput4Label.setVisible(true);
        truckManagementView.userInput5Label.setVisible(true);
        truckManagementView.userInput1Text.setVisible(true);
        truckManagementView.userInput2Text.setVisible(true);
        truckManagementView.userInput3Text.setVisible(true);
        truckManagementView.userInput4Text.setVisible(true);
        truckManagementView.userInput5Text.setVisible(true);
        truckManagementView.userInputBackButton.setVisible(true);
        truckManagementView.addTruckConfirmButton.setVisible(true);
    }

    public void removeTruck() {
        truckManagementView.userInputMainLabel.setText("You are in the remove truck menu, please insert the required information");
        truckManagementView.userInputMainLabel.setBounds(40, 50, 550, 200);
        truckManagementView.userInput1Label.setText("Input truck id:");
        truckManagementView.userInput1Label.setBounds(100, 150, 350, 200);
        truckManagementView.userInput1Text.setBounds(250, 150, 150, 25);
        truckManagementView.removeTruckConfirmButton.setBounds(300, 250, 200, 50);
        truckManagementView.userInputBackButton.setBounds(100, 250, 200, 50);
        truckManagementView.userInputMainLabel.setVisible(true);
        truckManagementView.userInput1Label.setVisible(true);
        truckManagementView.userInput1Text.setVisible(true);
        truckManagementView.removeTruckConfirmButton.setVisible(true);
        truckManagementView.userInputBackButton.setVisible(true);
    }

    public void addDeliveryDate() {
        truckManagementView.userInputMainLabel.setText("You are in the add delivery date menu, please insert the required information");
        truckManagementView.userInputMainLabel.setBounds(30, 50, 550, 200);
        truckManagementView.userInput1Label.setText("Input truck id:");
        truckManagementView.userInput1Label.setBounds(100, 100, 300, 200);
        truckManagementView.userInput2Label.setText("Input delivery date:");
        truckManagementView.userInput2Label.setBounds(100, 150, 350, 200);
        truckManagementView.userInput1Text.setBounds(250, 100, 150, 25);
        truckManagementView.userInput2Text.setBounds(250, 150, 150, 25);
        truckManagementView.addDeliveryDateConfirmButton.setBounds(300, 200, 200, 50);
        truckManagementView.userInputBackButton.setBounds(100, 200, 200, 50);
        truckManagementView.userInputMainLabel.setVisible(true);
        truckManagementView.userInput1Label.setVisible(true);
        truckManagementView.userInput2Label.setVisible(true);
        truckManagementView.userInput1Text.setVisible(true);
        truckManagementView.userInput2Text.setVisible(true);
        truckManagementView.addDeliveryDateConfirmButton.setVisible(true);
        truckManagementView.userInputBackButton.setVisible(true);
    }

    public void removeDeliveryDate() {
        truckManagementView.userInputMainLabel.setText("You are in the remove delivery date menu, please insert the required information");
        truckManagementView.userInputMainLabel.setBounds(30, 50, 550, 200);
        truckManagementView.userInput1Label.setText("Input truck id:");
        truckManagementView.userInput1Label.setBounds(100, 100, 300, 200);
        truckManagementView.userInput2Label.setText("Input delivery date:");
        truckManagementView.userInput2Label.setBounds(100, 150, 350, 200);
        truckManagementView.userInput1Text.setBounds(250, 100, 150, 25);
        truckManagementView.userInput2Text.setBounds(250, 150, 150, 25);
        truckManagementView.removeDeliveryDateConfirmButton.setBounds(300, 200, 200, 50);
        truckManagementView.userInputBackButton.setBounds(100, 200, 200, 50);
        truckManagementView.userInputMainLabel.setVisible(true);
        truckManagementView.userInput1Label.setVisible(true);
        truckManagementView.userInput2Label.setVisible(true);
        truckManagementView.userInput1Text.setVisible(true);
        truckManagementView.userInput2Text.setVisible(true);
        truckManagementView.removeDeliveryDateConfirmButton.setVisible(true);
        truckManagementView.userInputBackButton.setVisible(true);
    }

    public void editStatus() {
        truckManagementView.userInputMainLabel.setText("You are in the edit truck status menu, please insert the required information");
        truckManagementView.userInputMainLabel.setBounds(30, 15, 550, 200);
        truckManagementView.userInput1Label.setText("Input truck id:");
        truckManagementView.userInput1Label.setBounds(100, 100, 350, 200);
        truckManagementView.userInput4Label.setText("Select status:");
        truckManagementView.userInput4Label.setBounds(100, 150, 300, 200);
        truckManagementView.userInput1Text.setBounds(250, 100, 150, 25);
        truckManagementView.userInputBackButton.setBounds(100, 250, 200, 50);
        truckManagementView.editStatusConfirmButton.setBounds(300, 250, 200, 50);
        truckManagementView.userInputRadioButton1.setText("Available");
        truckManagementView.userInputRadioButton1.setBounds(250, 135, 150, 25);
        truckManagementView.userInputRadioButton2.setText("Unavailable");
        truckManagementView.userInputRadioButton2.setBounds(250, 165, 150, 25);
        truckManagementView.userInputMainLabel.setVisible(true);
        truckManagementView.userInput1Label.setVisible(true);
        truckManagementView.userInput4Label.setVisible(true);
        truckManagementView.userInput1Text.setVisible(true);
        truckManagementView.editStatusConfirmButton.setVisible(true);
        truckManagementView.userInputRadioButton1.setVisible(true);
        truckManagementView.userInputRadioButton2.setVisible(true);
        truckManagementView.editStatusConfirmButton.setVisible(true);
        truckManagementView.userInputBackButton.setVisible(true);
    }

    public void addDeliveryDateConfirm() {
        boolean error = false;
        String truckId = truckManagementView.userInput1Text.getText();
        int parseTruckId = 0;
        String date = truckManagementView.userInput2Text.getText();
        String pattern = "\\d{4}/\\d{2}/\\d{2}";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(date);
        if (truckId.equals("")) {
            truckManagementView.userInputError1Label.setText("you must fill the truckId");
            truckManagementView.userInputError1Label.setBounds(405, 100, 300, 200);
            truckManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else {
            try {
                parseTruckId = Integer.parseInt(truckId);
                truckManagementView.userInputError1Label.setVisible(false);
            } catch (NumberFormatException e) {
                truckManagementView.userInputError1Label.setText("truckId must be int");
                truckManagementView.userInputError1Label.setBounds(405, 100, 300, 200);
                truckManagementView.userInputError1Label.setVisible(true);
                error = true;
            }
        }
        if (date.equals("")) {
            truckManagementView.userInputError2Label.setText("you must fill the delivery Date");
            truckManagementView.userInputError2Label.setBounds(405, 150, 300, 200);
            truckManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else if (!matcher.matches()) {
            truckManagementView.userInputError2Label.setText("date format must be yyyy/mm/dd");
            truckManagementView.userInputError2Label.setBounds(405, 150, 300, 200);
            truckManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else
            truckManagementView.userInputError2Label.setVisible(false);
        if (error)
            return;
        hideErrors();
        try {
            String[] dateSplit = date.split("/");
            String result = systemFacade.addTruckDeliveryDate(parseTruckId, LocalDate.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2])));
            if (result.equals("Truck delivery date added successfully"))
                JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeDeliveryDateConfirm() {
        boolean error = false;
        String truckId = truckManagementView.userInput1Text.getText();
        int parseTruckId = 0;
        String date = truckManagementView.userInput2Text.getText();
        String pattern = "\\d{4}/\\d{2}/\\d{2}";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(date);
        if (truckId.equals("")) {
            truckManagementView.userInputError1Label.setText("you must fill the truckId");
            truckManagementView.userInputError1Label.setBounds(405, 100, 300, 200);
            truckManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else {
            try {
                parseTruckId = Integer.parseInt(truckId);
                truckManagementView.userInputError1Label.setVisible(false);
            } catch (NumberFormatException e) {
                truckManagementView.userInputError1Label.setText("truckId must be int");
                truckManagementView.userInputError1Label.setBounds(405, 100, 300, 200);
                truckManagementView.userInputError1Label.setVisible(true);
                error = true;
            }
        }
        if (date.equals("")) {
            truckManagementView.userInputError2Label.setText("you must fill the delivery Date");
            truckManagementView.userInputError2Label.setBounds(405, 150, 300, 200);
            truckManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else if (!matcher.matches()) {
            truckManagementView.userInputError2Label.setText("date format must be yyyy/mm/dd");
            truckManagementView.userInputError2Label.setBounds(405, 150, 300, 200);
            truckManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else
            truckManagementView.userInputError2Label.setVisible(false);
        if (error)
            return;
        hideErrors();
        try {
            String[] dateSplit = date.split("/");
            String result = systemFacade.removeTruckDeliveryDate(parseTruckId, LocalDate.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2])));
            if (result.equals("Truck delivery date removed successfully"))
                JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editStatusConfirm() {
        boolean error = false;
        String truckId = truckManagementView.userInput1Text.getText();
        int parseTruckId = 0;
        int status = -1;
        if (truckId.equals("")) {
            truckManagementView.userInputError1Label.setText("you must fill the truckId");
            truckManagementView.userInputError1Label.setBounds(405, 100, 300, 200);
            truckManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else {
            try {
                parseTruckId = Integer.parseInt(truckId);
                truckManagementView.userInputError1Label.setVisible(false);
            } catch (NumberFormatException e) {
                truckManagementView.userInputError1Label.setText("truckId must be int");
                truckManagementView.userInputError1Label.setBounds(405, 100, 300, 200);
                truckManagementView.userInputError1Label.setVisible(true);
                error = true;
            }
        }
        if (truckManagementView.radioGroup.getSelection() == null) {
            truckManagementView.userInputError2Label.setText("you must choose one option");
            truckManagementView.userInputError2Label.setBounds(405, 150, 300, 200);
            truckManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else if (truckManagementView.userInputRadioButton1.isSelected()) {
            status = 1;
            truckManagementView.userInputError2Label.setVisible(false);
        } else if (truckManagementView.userInputRadioButton2.isSelected()) {
            status = 0;
            truckManagementView.userInputError2Label.setVisible(false);
        }
        if (error)
            return;
        hideErrors();
        try {
            String result = systemFacade.updateTruckStatus(parseTruckId, status);
            if (result.equals("Truck status updated successfully"))
                JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addNewTruckConfirm() {
        boolean error = false;
        String truckId = truckManagementView.userInput1Text.getText();
        String type = truckManagementView.userInput2Text.getText();
        String model = truckManagementView.userInput3Text.getText();
        String netWeight = truckManagementView.userInput4Text.getText();
        String maxWeight = truckManagementView.userInput5Text.getText();
        int parseTruckId = 0;
        int parseNetWeight = 0;
        int parseMaxWeight = 0;
        if (truckId.equals("")) {
            truckManagementView.userInputError1Label.setText("you must fill the truckId");
            truckManagementView.userInputError1Label.setBounds(405, 50, 300, 200);
            truckManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else {
            try {
                parseTruckId = Integer.parseInt(truckId);
                truckManagementView.userInputError1Label.setVisible(false);
            } catch (NumberFormatException e) {
                truckManagementView.userInputError1Label.setText("truckId must be int");
                truckManagementView.userInputError1Label.setBounds(405, 50, 300, 200);
                truckManagementView.userInputError1Label.setVisible(true);
                error = true;
            }
        }
        if (type.equals("")) {
            truckManagementView.userInputError2Label.setText("you must fill the type");
            truckManagementView.userInputError2Label.setBounds(405, 100, 300, 200);
            truckManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else {
            truckManagementView.userInputError2Label.setVisible(false);
        }
        if (model.equals("")) {
            truckManagementView.userInputError3Label.setText("you must fill the model");
            truckManagementView.userInputError3Label.setBounds(405, 150, 300, 200);
            truckManagementView.userInputError3Label.setVisible(true);
            error = true;
        } else
            truckManagementView.userInputError3Label.setVisible(false);
        if (netWeight.equals("")) {
            truckManagementView.userInputError4Label.setText("you must fill the netWeight");
            truckManagementView.userInputError4Label.setBounds(405, 200, 300, 200);
            truckManagementView.userInputError4Label.setVisible(true);
            error = true;
        } else {
            try {
                parseNetWeight = Integer.parseInt(netWeight);
                truckManagementView.userInputError4Label.setVisible(false);
            } catch (NumberFormatException e) {
                truckManagementView.userInputError4Label.setText("netWeight must be int");
                truckManagementView.userInputError4Label.setBounds(405, 200, 300, 200);
                truckManagementView.userInputError4Label.setVisible(true);
                error = true;
            }
        }
        if (maxWeight.equals("")) {
            truckManagementView.userInputError5Label.setText("you must fill the maxWeight");
            truckManagementView.userInputError5Label.setBounds(405, 250, 300, 200);
            truckManagementView.userInputError5Label.setVisible(true);
            error = true;
        } else {
            try {
                parseMaxWeight = Integer.parseInt(maxWeight);
                truckManagementView.userInputError5Label.setVisible(false);
            } catch (NumberFormatException e) {
                truckManagementView.userInputError5Label.setText("maxWeight must be int");
                truckManagementView.userInputError5Label.setBounds(405, 250, 300, 200);
                truckManagementView.userInputError5Label.setVisible(true);
                error = true;
            }
        }
        if (error)
            return;
        hideErrors();
        try {
            String result = systemFacade.addTruck(parseTruckId, type, model, parseNetWeight, parseMaxWeight);
            if (result.equals("Truck added successfully"))
                JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeTruckConfirm() {
        boolean error = false;
        String truckId = truckManagementView.userInput1Text.getText();
        int parseTruckId = 0;
        if (truckId.equals("")) {
            truckManagementView.userInputError1Label.setText("you must fill the truckId");
            truckManagementView.userInputError1Label.setBounds(405, 150, 300, 200);
            truckManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else {
            try {
                parseTruckId = Integer.parseInt(truckId);
                truckManagementView.userInputError1Label.setVisible(false);
            } catch (NumberFormatException e) {
                truckManagementView.userInputError1Label.setText("truckId must be int");
                truckManagementView.userInputError1Label.setBounds(405, 150, 300, 200);
                truckManagementView.userInputError1Label.setVisible(true);
                error = true;
            }
        }
        if (error)
            return;
        hideErrors();
        try {
            String result = systemFacade.removeTruck(parseTruckId);
            if (result.equals("Truck removed successfully"))
                JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == truckManagementView.backButton) {
            if (manager)
                new ResourceManagementController(true);
            else
                new ResourceManagementController();
            truckManagementView.dispose();
        } else if (e.getSource() == truckManagementView.userInputBackButton) {
            this.hideUserInput();
            this.showMainOptions();
        } else if (e.getSource() == truckManagementView.editStatusButton) {
            this.hideMainOptions();
            this.editStatus();
        } else if (e.getSource() == truckManagementView.addDeliveryDateButton) {
            this.hideMainOptions();
            this.addDeliveryDate();
        } else if (e.getSource() == truckManagementView.removeDeliveryDateButton) {
            this.hideMainOptions();
            this.removeDeliveryDate();
        } else if (e.getSource() == truckManagementView.addTruckButton) {
            this.hideMainOptions();
            this.addNewTruck();
        } else if (e.getSource() == truckManagementView.removeTruckButton) {
            this.hideMainOptions();
            this.removeTruck();
        } else if (e.getSource() == truckManagementView.addDeliveryDateConfirmButton) {
            this.addDeliveryDateConfirm();
        } else if (e.getSource() == truckManagementView.removeDeliveryDateConfirmButton) {
            this.removeDeliveryDateConfirm();
        } else if (e.getSource() == truckManagementView.editStatusConfirmButton) {
            this.editStatusConfirm();
        } else if (e.getSource() == truckManagementView.addTruckConfirmButton) {
            this.addNewTruckConfirm();
        } else if (e.getSource() == truckManagementView.removeTruckConfirmButton) {
            this.removeTruckConfirm();
        }
    }
}
