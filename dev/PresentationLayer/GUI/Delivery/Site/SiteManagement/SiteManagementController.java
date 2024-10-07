package PresentationLayer.GUI.Delivery.SiteManagement;

import PresentationLayer.GUI.Delivery.ResourceManagement.ResourceManagementController;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SiteManagementController implements ActionListener {

    SiteManagementView siteManagementView;
    SystemFacade systemFacade;
    boolean manager;

    public SiteManagementController() {
        siteManagementView = new SiteManagementView();
        systemFacade = SystemFacade.getInstance();
        siteManagementView.backButton.addActionListener(this);
        siteManagementView.addSiteButton.addActionListener(this);
        siteManagementView.removeSiteButton.addActionListener(this);
        siteManagementView.editContactButton.addActionListener(this);
        siteManagementView.editPhoneNumberButton.addActionListener(this);
        siteManagementView.userInputBackButton.addActionListener(this);
        siteManagementView.addSiteConfirmButton.addActionListener(this);
        siteManagementView.removeSiteConfirmButton.addActionListener(this);
        siteManagementView.editContactConfirmButton.addActionListener(this);
        siteManagementView.editPhoneNumberConfirmButton.addActionListener(this);
    }

    public SiteManagementController(boolean manager) {
        this();
        this.manager = manager;
    }

    public void hideMainOptions() {
        siteManagementView.siteManagementLabel.setVisible(false);
        siteManagementView.selectActionLabel.setVisible(false);
        siteManagementView.backButton.setVisible(false);
        siteManagementView.addSiteButton.setVisible(false);
        siteManagementView.removeSiteButton.setVisible(false);
        siteManagementView.editContactButton.setVisible(false);
        siteManagementView.editPhoneNumberButton.setVisible(false);
    }

    public void showMainOptions() {
        siteManagementView.siteManagementLabel.setVisible(true);
        siteManagementView.selectActionLabel.setVisible(true);
        siteManagementView.backButton.setVisible(true);
        siteManagementView.addSiteButton.setVisible(true);
        siteManagementView.removeSiteButton.setVisible(true);
        siteManagementView.editContactButton.setVisible(true);
        siteManagementView.editPhoneNumberButton.setVisible(true);
    }

    public void hideUserInput() {
        siteManagementView.userInput1Text.setVisible(false);
        siteManagementView.userInput2Text.setVisible(false);
        siteManagementView.userInput3Text.setVisible(false);
        siteManagementView.userInput4Text.setVisible(false);
        siteManagementView.userInput5Text.setVisible(false);
        siteManagementView.userInput1Label.setVisible(false);
        siteManagementView.userInput2Label.setVisible(false);
        siteManagementView.userInput3Label.setVisible(false);
        siteManagementView.userInput4Label.setVisible(false);
        siteManagementView.userInput5Label.setVisible(false);
        siteManagementView.userInput6Label.setVisible(false);
        siteManagementView.userInput7Label.setVisible(false);
        siteManagementView.userInputBackButton.setVisible(false);
        siteManagementView.userInputMainLabel.setVisible(false);
        siteManagementView.userInputError1Label.setVisible(false);
        siteManagementView.userInputError2Label.setVisible(false);
        siteManagementView.userInputError3Label.setVisible(false);
        siteManagementView.userInputError4Label.setVisible(false);
        siteManagementView.userInputError5Label.setVisible(false);
        siteManagementView.userInputError6Label.setVisible(false);
        siteManagementView.userInputError7Label.setVisible(false);
        siteManagementView.userInputComboBox1.setVisible(false);
        siteManagementView.userInputComboBox2.setVisible(false);
        siteManagementView.userInput1Text.setText("");
        siteManagementView.userInput2Text.setText("");
        siteManagementView.userInput3Text.setText("");
        siteManagementView.userInput4Text.setText("");
        siteManagementView.userInput5Text.setText("");
        siteManagementView.userInputComboBox1.setSelectedIndex(0);
        siteManagementView.userInputComboBox2.setSelectedIndex(0);
        siteManagementView.addSiteConfirmButton.setVisible(false);
        siteManagementView.removeSiteConfirmButton.setVisible(false);
        siteManagementView.editContactConfirmButton.setVisible(false);
        siteManagementView.editPhoneNumberConfirmButton.setVisible(false);
    }

    public void hideErrors() {
        siteManagementView.userInputError1Label.setVisible(false);
        siteManagementView.userInputError2Label.setVisible(false);
        siteManagementView.userInputError3Label.setVisible(false);
        siteManagementView.userInputError4Label.setVisible(false);
        siteManagementView.userInputError5Label.setVisible(false);
        siteManagementView.userInputError6Label.setVisible(false);
        siteManagementView.userInputError7Label.setVisible(false);
    }

    public void addNewSite() {
        siteManagementView.userInputMainLabel.setText("You are in the site addition menu, please insert the required information");
        siteManagementView.userInputMainLabel.setBounds(35, 10, 550, 200);
        siteManagementView.userInput1Label.setText("Input address:");
        siteManagementView.userInput1Label.setBounds(100, 50, 350, 200);
        siteManagementView.userInput2Label.setText("Input phone number:");
        siteManagementView.userInput2Label.setBounds(100, 75, 300, 200);
        siteManagementView.userInput3Label.setText("Input contact name:");
        siteManagementView.userInput3Label.setBounds(100, 100, 300, 200);
        siteManagementView.userInput4Label.setText("Input region:");
        siteManagementView.userInput4Label.setBounds(100, 125, 300, 200);
        siteManagementView.userInput5Label.setText("Input type:");
        siteManagementView.userInput5Label.setBounds(100, 150, 300, 200);
        siteManagementView.userInput6Label.setText("Input longitude:");
        siteManagementView.userInput6Label.setBounds(100, 175, 300, 200);
        siteManagementView.userInput7Label.setText("Input latitude:");
        siteManagementView.userInput7Label.setBounds(100, 200, 300, 200);
        siteManagementView.userInput1Text.setBounds(250, 50, 150, 25);
        siteManagementView.userInput2Text.setBounds(250, 75, 150, 25);
        siteManagementView.userInput3Text.setBounds(250, 100, 150, 25);
        siteManagementView.userInputComboBox1.setBounds(250, 125, 150, 25);
        siteManagementView.userInputComboBox2.setBounds(250, 150, 150, 25);
        siteManagementView.userInput4Text.setBounds(250, 175, 150, 25);
        siteManagementView.userInput5Text.setBounds(250, 200, 150, 25);
        siteManagementView.userInputBackButton.setBounds(100, 250, 200, 50);
        siteManagementView.userInputMainLabel.setVisible(true);
        siteManagementView.userInput1Label.setVisible(true);
        siteManagementView.userInput2Label.setVisible(true);
        siteManagementView.userInput3Label.setVisible(true);
        siteManagementView.userInput4Label.setVisible(true);
        siteManagementView.userInput5Label.setVisible(true);
        siteManagementView.userInput6Label.setVisible(true);
        siteManagementView.userInput7Label.setVisible(true);
        siteManagementView.userInput1Text.setVisible(true);
        siteManagementView.userInput2Text.setVisible(true);
        siteManagementView.userInput3Text.setVisible(true);
        siteManagementView.userInput4Text.setVisible(true);
        siteManagementView.userInput5Text.setVisible(true);
        siteManagementView.userInputComboBox1.setVisible(true);
        siteManagementView.userInputComboBox2.setVisible(true);
        siteManagementView.userInputBackButton.setVisible(true);
        siteManagementView.addSiteConfirmButton.setVisible(true);
    }

    public void removeSite() {
        siteManagementView.userInputMainLabel.setText("You are in the remove site menu, please insert the required information");
        siteManagementView.userInputMainLabel.setBounds(40, 50, 550, 200);
        siteManagementView.userInput1Label.setText("Input site address:");
        siteManagementView.userInput1Label.setBounds(125, 150, 350, 200);
        siteManagementView.userInput1Text.setBounds(275, 150, 150, 25);
        siteManagementView.removeSiteConfirmButton.setBounds(300, 250, 200, 50);
        siteManagementView.userInputBackButton.setBounds(100, 250, 200, 50);
        siteManagementView.userInputMainLabel.setVisible(true);
        siteManagementView.userInput1Label.setVisible(true);
        siteManagementView.userInput1Text.setVisible(true);
        siteManagementView.removeSiteConfirmButton.setVisible(true);
        siteManagementView.userInputBackButton.setVisible(true);
    }

    public void editPhoneNumber() {
        siteManagementView.userInputMainLabel.setText("You are in the phone number edition menu, please insert the required information");
        siteManagementView.userInputMainLabel.setBounds(30, 50, 550, 200);
        siteManagementView.userInput1Label.setText("Input site address:");
        siteManagementView.userInput1Label.setBounds(100, 100, 300, 200);
        siteManagementView.userInput2Label.setText("Input phone number:");
        siteManagementView.userInput2Label.setBounds(100, 150, 350, 200);
        siteManagementView.userInput1Text.setBounds(250, 100, 150, 25);
        siteManagementView.userInput2Text.setBounds(250, 150, 150, 25);
        siteManagementView.editPhoneNumberConfirmButton.setBounds(300, 200, 200, 50);
        siteManagementView.userInputBackButton.setBounds(100, 200, 200, 50);
        siteManagementView.userInputMainLabel.setVisible(true);
        siteManagementView.userInput1Label.setVisible(true);
        siteManagementView.userInput2Label.setVisible(true);
        siteManagementView.userInput1Text.setVisible(true);
        siteManagementView.userInput2Text.setVisible(true);
        siteManagementView.editPhoneNumberConfirmButton.setVisible(true);
        siteManagementView.userInputBackButton.setVisible(true);
    }

    public void editContact() {
        siteManagementView.userInputMainLabel.setText("You are in the contact edition menu, please insert the required information");
        siteManagementView.userInputMainLabel.setBounds(30, 50, 550, 200);
        siteManagementView.userInput1Label.setText("Input site address:");
        siteManagementView.userInput1Label.setBounds(100, 100, 300, 200);
        siteManagementView.userInput2Label.setText("Input contact:");
        siteManagementView.userInput2Label.setBounds(100, 150, 350, 200);
        siteManagementView.userInput1Text.setBounds(250, 100, 150, 25);
        siteManagementView.userInput2Text.setBounds(250, 150, 150, 25);
        siteManagementView.editContactConfirmButton.setBounds(300, 200, 200, 50);
        siteManagementView.userInputBackButton.setBounds(100, 200, 200, 50);
        siteManagementView.userInputMainLabel.setVisible(true);
        siteManagementView.userInput1Label.setVisible(true);
        siteManagementView.userInput2Label.setVisible(true);
        siteManagementView.userInput1Text.setVisible(true);
        siteManagementView.userInput2Text.setVisible(true);
        siteManagementView.editContactConfirmButton.setVisible(true);
        siteManagementView.userInputBackButton.setVisible(true);
    }

    public void editPhoneNumberConfirm() {
        boolean error = false;
        String address = siteManagementView.userInput1Text.getText();
        String phoneString = siteManagementView.userInput2Text.getText();
        String pattern = "\\d{10}";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(phoneString);
        if (address.equals("")) {
            siteManagementView.userInputError1Label.setText("you must fill the address");
            siteManagementView.userInputError1Label.setBounds(405, 100, 300, 200);
            siteManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else
            siteManagementView.userInputError1Label.setVisible(false);
        if (phoneString.equals("")) {
            siteManagementView.userInputError2Label.setText("you must fill the phone number");
            siteManagementView.userInputError2Label.setBounds(405, 150, 300, 200);
            siteManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else if (!matcher.matches()) {
            siteManagementView.userInputError2Label.setText("phone format must be xxxxxxxxxx");
            siteManagementView.userInputError2Label.setBounds(405, 150, 300, 200);
            siteManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else
            siteManagementView.userInputError2Label.setVisible(false);
        if (error)
            return;
        hideErrors();
        try {
            String result = systemFacade.updatePhoneNumber(address, phoneString);
            if (result.equals("Phone number changed successfully"))
                JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editContactConfirm() {
        boolean error = false;
        String address = siteManagementView.userInput1Text.getText();
        String contactString = siteManagementView.userInput2Text.getText();
        if (address.equals("")) {
            siteManagementView.userInputError1Label.setText("you must fill the address");
            siteManagementView.userInputError1Label.setBounds(405, 100, 300, 200);
            siteManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else
            siteManagementView.userInputError1Label.setVisible(false);
        if (contactString.equals("")) {
            siteManagementView.userInputError2Label.setText("you must fill the contact");
            siteManagementView.userInputError2Label.setBounds(405, 150, 300, 200);
            siteManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else
            siteManagementView.userInputError2Label.setVisible(false);
        if (error)
            return;
        hideErrors();
        try {
            String result = systemFacade.updateContactName(address, contactString);
            if (result.equals("Contact name changed successfully"))
                JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addNewSiteConfirm() {
        boolean error = false;
        String address = siteManagementView.userInput1Text.getText();
        String phoneNumber = siteManagementView.userInput2Text.getText();
        String contactName = siteManagementView.userInput3Text.getText();
        int region = siteManagementView.userInputComboBox1.getSelectedIndex();
        int type = siteManagementView.userInputComboBox2.getSelectedIndex();
        String longitude = siteManagementView.userInput4Text.getText();
        String latitude = siteManagementView.userInput5Text.getText();
        String pattern = "\\d{10}";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(phoneNumber);
        double parseLongitude = 0;
        double parseLatitude = 0;
        if (address.equals("")) {
            siteManagementView.userInputError1Label.setText("you must fill the address");
            siteManagementView.userInputError1Label.setBounds(405, 50, 300, 200);
            siteManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else
            siteManagementView.userInputError1Label.setVisible(false);
        if (phoneNumber.equals("")) {
            siteManagementView.userInputError2Label.setText("you must fill the phone number");
            siteManagementView.userInputError2Label.setBounds(405, 75, 300, 200);
            siteManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else if (!matcher.matches()) {
            siteManagementView.userInputError2Label.setText("phone format must be xxxxxxxxxx");
            siteManagementView.userInputError2Label.setBounds(405, 75, 300, 200);
            siteManagementView.userInputError2Label.setVisible(true);
            error = true;
        } else
            siteManagementView.userInputError2Label.setVisible(false);
        if (contactName.equals("")) {
            siteManagementView.userInputError3Label.setText("you must fill the contact");
            siteManagementView.userInputError3Label.setBounds(405, 100, 300, 200);
            siteManagementView.userInputError3Label.setVisible(true);
            error = true;
        } else
            siteManagementView.userInputError3Label.setVisible(false);
        if (region == 0) {
            siteManagementView.userInputError4Label.setText("you must choose one option");
            siteManagementView.userInputError4Label.setBounds(405, 125, 300, 200);
            siteManagementView.userInputError4Label.setVisible(true);
            error = true;
        } else
            siteManagementView.userInputError4Label.setVisible(false);
        if (type == 0) {
            siteManagementView.userInputError5Label.setText("you must choose one option");
            siteManagementView.userInputError5Label.setBounds(405, 150, 300, 200);
            siteManagementView.userInputError5Label.setVisible(true);
            error = true;
        } else
            siteManagementView.userInputError5Label.setVisible(false);
        if (longitude.equals("")) {
            siteManagementView.userInputError6Label.setText("you must fill the longitude");
            siteManagementView.userInputError6Label.setBounds(405, 175, 300, 200);
            siteManagementView.userInputError6Label.setVisible(true);
            error = true;
        } else {
            try {
                parseLongitude = Double.parseDouble(longitude);
                siteManagementView.userInputError6Label.setVisible(false);
            } catch (NumberFormatException e) {
                siteManagementView.userInputError6Label.setText("longitude must be double");
                siteManagementView.userInputError6Label.setBounds(405, 175, 300, 200);
                siteManagementView.userInputError6Label.setVisible(true);
                error = true;
            }
        }
        if (latitude.equals("")) {
            siteManagementView.userInputError7Label.setText("you must fill the latitude");
            siteManagementView.userInputError7Label.setBounds(405, 200, 300, 200);
            siteManagementView.userInputError7Label.setVisible(true);
            error = true;
        } else {
            try {
                parseLatitude = Double.parseDouble(latitude);
                siteManagementView.userInputError7Label.setVisible(false);
            } catch (NumberFormatException e) {
                siteManagementView.userInputError7Label.setText("latitude must be double");
                siteManagementView.userInputError7Label.setBounds(405, 200, 300, 200);
                siteManagementView.userInputError7Label.setVisible(true);
                error = true;
            }
        }

        if (error)
            return;
        hideErrors();
        try {
            String result = systemFacade.addSite(address, phoneNumber, contactName, region - 1, type - 1, parseLongitude, parseLatitude);
            if (result.equals("Site created successfully"))
                JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeSiteConfirm() {
        boolean error = false;
        String address = siteManagementView.userInput1Text.getText();
        if (address.equals("")) {
            siteManagementView.userInputError1Label.setText("you must fill the address");
            siteManagementView.userInputError1Label.setBounds(430, 150, 300, 200);
            siteManagementView.userInputError1Label.setVisible(true);
            error = true;
        }
        if (error)
            return;
        hideErrors();
        try {
            String result = systemFacade.removeSite(address);
            if (result.equals("Site removed successfully"))
                JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == siteManagementView.backButton) {
            if (manager)
                new ResourceManagementController(true);
            else
                new ResourceManagementController();
            siteManagementView.dispose();
        } else if (e.getSource() == siteManagementView.userInputBackButton) {
            this.hideUserInput();
            this.showMainOptions();
        } else if (e.getSource() == siteManagementView.addSiteButton) {
            this.hideMainOptions();
            this.addNewSite();
        } else if (e.getSource() == siteManagementView.removeSiteButton) {
            this.hideMainOptions();
            this.removeSite();
        } else if (e.getSource() == siteManagementView.editContactButton) {
            this.hideMainOptions();
            this.editContact();
        } else if (e.getSource() == siteManagementView.editPhoneNumberButton) {
            this.hideMainOptions();
            this.editPhoneNumber();
        } else if (e.getSource() == siteManagementView.editPhoneNumberConfirmButton) {
            this.editPhoneNumberConfirm();
        } else if (e.getSource() == siteManagementView.editContactConfirmButton) {
            this.editContactConfirm();
        } else if (e.getSource() == siteManagementView.removeSiteConfirmButton) {
            this.removeSiteConfirm();
        } else if (e.getSource() == siteManagementView.addSiteConfirmButton) {
            this.addNewSiteConfirm();
        }
    }
}
