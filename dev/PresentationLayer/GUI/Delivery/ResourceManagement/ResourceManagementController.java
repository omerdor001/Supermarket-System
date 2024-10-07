package PresentationLayer.GUI.Delivery.ResourceManagement;

import PresentationLayer.GUI.Delivery.AllDrivers.AllDriversController;
import PresentationLayer.GUI.Delivery.AllSites.AllSitesController;
import PresentationLayer.GUI.Delivery.AllTrucks.AllTrucksController;
import PresentationLayer.GUI.Delivery.DeliveryMainMenu.DeliveryMainController;
import PresentationLayer.GUI.Delivery.Driver.DriverController;
import PresentationLayer.GUI.Delivery.Site.SiteController;
import PresentationLayer.GUI.Delivery.SiteManagement.SiteManagementController;
import PresentationLayer.GUI.Delivery.Truck.TruckController;
import PresentationLayer.GUI.Delivery.TruckManagement.TruckManagementController;
import ServiceLayer.SystemFacade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourceManagementController implements ActionListener {
    ResourceManagementView resourceManagementView;
    SystemFacade systemFacade;
    boolean manager;

    public ResourceManagementController() {
        systemFacade = SystemFacade.getInstance();
        resourceManagementView = new ResourceManagementView();
        resourceManagementView.backButton.addActionListener(this);
        resourceManagementView.showDriverButton.addActionListener(this);
        resourceManagementView.showAllDriversButton.addActionListener(this);
        resourceManagementView.showTruckButton.addActionListener(this);
        resourceManagementView.showAllTrucksButton.addActionListener(this);
        resourceManagementView.showSiteButton.addActionListener(this);
        resourceManagementView.showAllSitesButton.addActionListener(this);
        resourceManagementView.truckManagementButton.addActionListener(this);
        resourceManagementView.siteManagementButton.addActionListener(this);
        resourceManagementView.userInputBackButton.addActionListener(this);
        resourceManagementView.showDriverConfirmButton.addActionListener(this);
        resourceManagementView.showTruckConfirmButton.addActionListener(this);
        resourceManagementView.showSiteConfirmButton.addActionListener(this);
    }

    public ResourceManagementController(boolean manager) {
        this();
        this.manager = manager;
    }

    public void hideMainOptions() {
        resourceManagementView.mainMenuLabel.setVisible(false);
        resourceManagementView.selectActionLabel.setVisible(false);
        resourceManagementView.backButton.setVisible(false);
        resourceManagementView.showDriverButton.setVisible(false);
        resourceManagementView.showAllDriversButton.setVisible(false);
        resourceManagementView.showTruckButton.setVisible(false);
        resourceManagementView.showAllTrucksButton.setVisible(false);
        resourceManagementView.showSiteButton.setVisible(false);
        resourceManagementView.showAllSitesButton.setVisible(false);
        resourceManagementView.truckManagementButton.setVisible(false);
        resourceManagementView.siteManagementButton.setVisible(false);
    }

    public void showMainOptions() {
        resourceManagementView.mainMenuLabel.setVisible(true);
        resourceManagementView.selectActionLabel.setVisible(true);
        resourceManagementView.backButton.setVisible(true);
        resourceManagementView.showDriverButton.setVisible(true);
        resourceManagementView.showAllDriversButton.setVisible(true);
        resourceManagementView.showTruckButton.setVisible(true);
        resourceManagementView.showAllTrucksButton.setVisible(true);
        resourceManagementView.showSiteButton.setVisible(true);
        resourceManagementView.showAllSitesButton.setVisible(true);
        resourceManagementView.truckManagementButton.setVisible(true);
        resourceManagementView.siteManagementButton.setVisible(true);
    }

    public void hideUserInput() {
        resourceManagementView.userInput1Text.setVisible(false);
        resourceManagementView.userInput1Label.setVisible(false);
        resourceManagementView.userInputBackButton.setVisible(false);
        resourceManagementView.userInputMainLabel.setVisible(false);
        resourceManagementView.userInputError1Label.setVisible(false);
        resourceManagementView.userInput1Text.setText("");
        resourceManagementView.showDriverConfirmButton.setVisible(false);
        resourceManagementView.showTruckConfirmButton.setVisible(false);
        resourceManagementView.showSiteConfirmButton.setVisible(false);
    }

    public void hideErrors() {
        resourceManagementView.userInputError1Label.setVisible(false);
    }

    public void showDriver() {
        resourceManagementView.userInputMainLabel.setText("You are in the show driver menu, please insert the required information");
        resourceManagementView.userInputMainLabel.setBounds(40, 50, 550, 200);
        resourceManagementView.userInput1Label.setText("Input driver id:");
        resourceManagementView.userInput1Label.setBounds(100, 150, 350, 200);
        resourceManagementView.userInput1Text.setBounds(250, 150, 150, 25);
        resourceManagementView.showDriverConfirmButton.setBounds(300, 250, 200, 50);
        resourceManagementView.userInputBackButton.setBounds(100, 250, 200, 50);
        resourceManagementView.userInputMainLabel.setVisible(true);
        resourceManagementView.userInput1Label.setVisible(true);
        resourceManagementView.userInput1Text.setVisible(true);
        resourceManagementView.showDriverConfirmButton.setVisible(true);
        resourceManagementView.userInputBackButton.setVisible(true);
    }

    public void showSite() {
        resourceManagementView.userInputMainLabel.setText("You are in the show site menu, please insert the required information");
        resourceManagementView.userInputMainLabel.setBounds(40, 50, 550, 200);
        resourceManagementView.userInput1Label.setText("Input site address:");
        resourceManagementView.userInput1Label.setBounds(100, 150, 350, 200);
        resourceManagementView.userInput1Text.setBounds(250, 150, 150, 25);
        resourceManagementView.showSiteConfirmButton.setBounds(300, 250, 200, 50);
        resourceManagementView.userInputBackButton.setBounds(100, 250, 200, 50);
        resourceManagementView.userInputMainLabel.setVisible(true);
        resourceManagementView.userInput1Label.setVisible(true);
        resourceManagementView.userInput1Text.setVisible(true);
        resourceManagementView.showSiteConfirmButton.setVisible(true);
        resourceManagementView.userInputBackButton.setVisible(true);
    }

    public void showTruck() {
        resourceManagementView.userInputMainLabel.setText("You are in the show truck menu, please insert the required information");
        resourceManagementView.userInputMainLabel.setBounds(40, 50, 550, 200);
        resourceManagementView.userInput1Label.setText("Input truck id:");
        resourceManagementView.userInput1Label.setBounds(100, 150, 350, 200);
        resourceManagementView.userInput1Text.setBounds(250, 150, 150, 25);
        resourceManagementView.showTruckConfirmButton.setBounds(300, 250, 200, 50);
        resourceManagementView.userInputBackButton.setBounds(100, 250, 200, 50);
        resourceManagementView.userInputMainLabel.setVisible(true);
        resourceManagementView.userInput1Label.setVisible(true);
        resourceManagementView.userInput1Text.setVisible(true);
        resourceManagementView.showTruckConfirmButton.setVisible(true);
        resourceManagementView.userInputBackButton.setVisible(true);
    }

    public void showDriverConfirm() {
        boolean error = false;
        String driverId = resourceManagementView.userInput1Text.getText();
        if (driverId.equals("")) {
            resourceManagementView.userInputError1Label.setText("you must fill the driverId");
            resourceManagementView.userInputError1Label.setBounds(405, 150, 300, 200);
            resourceManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else {
            resourceManagementView.userInputError1Label.setVisible(false);
        }
        if (error)
            return;
        hideErrors();
        new DriverController(driverId);
    }

    public void showTruckConfirm() {
        boolean error = false;
        String truckId = resourceManagementView.userInput1Text.getText();
        int parseTruckId = 0;
        if (truckId.equals("")) {
            resourceManagementView.userInputError1Label.setText("you must fill the truckId");
            resourceManagementView.userInputError1Label.setBounds(405, 150, 300, 200);
            resourceManagementView.userInputError1Label.setVisible(true);
            error = true;
        } else {
            try {
                parseTruckId = Integer.parseInt(truckId);
                resourceManagementView.userInputError1Label.setVisible(false);
            } catch (NumberFormatException e) {
                resourceManagementView.userInputError1Label.setText("truckId must be int");
                resourceManagementView.userInputError1Label.setBounds(405, 150, 300, 200);
                resourceManagementView.userInputError1Label.setVisible(true);
                error = true;
            }
        }
        if (error)
            return;
        hideErrors();
        new TruckController(parseTruckId);
    }

    public void showSiteConfirm() {
        boolean error = false;
        String address = resourceManagementView.userInput1Text.getText();
        if (address.equals("")) {
            resourceManagementView.userInputError1Label.setText("you must fill the address");
            resourceManagementView.userInputError1Label.setBounds(405, 150, 300, 200);
            resourceManagementView.userInputError1Label.setVisible(true);
            error = true;
        }
        if (error)
            return;
        hideErrors();
        new SiteController(address);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resourceManagementView.backButton) {
            if (manager)
                new DeliveryMainController(true);
            else
                new DeliveryMainController();
            resourceManagementView.dispose();
        } else if (e.getSource() == resourceManagementView.userInputBackButton) {
            this.hideUserInput();
            this.showMainOptions();
        } else if (e.getSource() == resourceManagementView.showDriverButton) {
            this.hideMainOptions();
            this.showDriver();
        } else if (e.getSource() == resourceManagementView.showTruckButton) {
            this.hideMainOptions();
            this.showTruck();
        } else if (e.getSource() == resourceManagementView.showSiteButton) {
            this.hideMainOptions();
            this.showSite();
        } else if (e.getSource() == resourceManagementView.truckManagementButton) {
            if (manager)
                new TruckManagementController(true);
            else
                new TruckManagementController();
            resourceManagementView.dispose();
        } else if (e.getSource() == resourceManagementView.siteManagementButton) {
            if (manager)
                new SiteManagementController(true);
            else
                new SiteManagementController();
            resourceManagementView.dispose();
        } else if (e.getSource() == resourceManagementView.showSiteConfirmButton) {
            showSiteConfirm();
        } else if (e.getSource() == resourceManagementView.showDriverConfirmButton) {
            showDriverConfirm();
        } else if (e.getSource() == resourceManagementView.showTruckConfirmButton) {
            showTruckConfirm();
        } else if (e.getSource() == resourceManagementView.showAllTrucksButton) {
            new AllTrucksController();
        } else if (e.getSource() == resourceManagementView.showAllDriversButton) {
            new AllDriversController();
        } else if (e.getSource() == resourceManagementView.showAllSitesButton) {
            new AllSitesController();
        }
    }
}
