package PresentationLayer.GUI.Delivery.ResourceManagement;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ResourceManagementView extends JFrame {
    JLabel mainMenuLabel;
    JLabel selectActionLabel;
    JButton backButton;
    JButton showDriverButton;
    JButton showAllDriversButton;
    JButton showTruckButton;
    JButton showAllTrucksButton;
    JButton showSiteButton;
    JButton showAllSitesButton;
    JButton truckManagementButton;
    JButton siteManagementButton;

    JTextField userInput1Text;
    JLabel userInput1Label;
    JLabel userInputMainLabel;
    JLabel userInputError1Label;
    JButton userInputBackButton;

    JButton showDriverConfirmButton;
    JButton showTruckConfirmButton;
    JButton showSiteConfirmButton;

    public ResourceManagementView() {
        this.setLocationRelativeTo(null);
        this.setTitle("Resource management");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 350);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        this.mainMenuLabel = new JLabel();
        this.mainMenuLabel.setText("Welcome to the resource management menu");
        this.mainMenuLabel.setVerticalAlignment(1);
        this.mainMenuLabel.setHorizontalAlignment(0);
        this.mainMenuLabel.setBounds(100, 0, 400, 200);
        this.mainMenuLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

        this.selectActionLabel = new JLabel();
        this.selectActionLabel.setText("Please choose the desired action:");
        this.selectActionLabel.setVerticalAlignment(1);
        this.selectActionLabel.setHorizontalAlignment(0);
        this.selectActionLabel.setBounds(100, 25, 400, 200);
        this.selectActionLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        this.backButton = new JButton();
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBounds(200, 250, 200, 50);
        this.backButton.setBackground(new Color(255, 154, 162));

        this.showDriverButton = new JButton();
        this.showDriverButton.setText("Show driver information");
        this.showDriverButton.setFocusable(false);
        this.showDriverButton.setBounds(100, 50, 200, 50);

        this.showAllDriversButton = new JButton();
        this.showAllDriversButton.setText("Show all drivers information");
        this.showAllDriversButton.setFocusable(false);
        this.showAllDriversButton.setBounds(300, 50, 200, 50);

        this.showTruckButton = new JButton();
        this.showTruckButton.setText("Show truck information");
        this.showTruckButton.setFocusable(false);
        this.showTruckButton.setBounds(100, 100, 200, 50);

        this.showAllTrucksButton = new JButton();
        this.showAllTrucksButton.setText("Show all trucks information");
        this.showAllTrucksButton.setFocusable(false);
        this.showAllTrucksButton.setBounds(300, 100, 200, 50);

        this.showSiteButton = new JButton();
        this.showSiteButton.setText("Show site information");
        this.showSiteButton.setFocusable(false);
        this.showSiteButton.setBounds(100, 150, 200, 50);

        this.showAllSitesButton = new JButton();
        this.showAllSitesButton.setText("Show all sites information");
        this.showAllSitesButton.setFocusable(false);
        this.showAllSitesButton.setBounds(300, 150, 200, 50);

        this.truckManagementButton = new JButton();
        this.truckManagementButton.setText("Manage trucks");
        this.truckManagementButton.setFocusable(false);
        this.truckManagementButton.setBounds(100, 200, 200, 50);

        this.siteManagementButton = new JButton();
        this.siteManagementButton.setText("Manage sites");
        this.siteManagementButton.setFocusable(false);
        this.siteManagementButton.setBounds(300, 200, 200, 50);

        this.userInput1Text = new JTextField();
        this.userInput1Text.setVisible(false);

        this.userInput1Label = new JLabel();
        this.userInput1Label.setVerticalAlignment(1);
        this.userInput1Label.setHorizontalAlignment(2);
        this.userInput1Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput1Label.setVisible(false);

        this.userInputMainLabel = new JLabel();
        this.userInputMainLabel.setVerticalAlignment(1);
        this.userInputMainLabel.setHorizontalAlignment(2);
        this.userInputMainLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInputMainLabel.setVisible(false);

        this.userInputError1Label = new JLabel();
        this.userInputError1Label.setVerticalAlignment(1);
        this.userInputError1Label.setHorizontalAlignment(2);
        this.userInputError1Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError1Label.setForeground(new Color(204, 0, 0));
        this.userInputError1Label.setVisible(false);

        this.userInputBackButton = new JButton();
        this.userInputBackButton.setText("Back");
        this.userInputBackButton.setFocusable(false);
        this.userInputBackButton.setBackground(new Color(255, 154, 162));
        this.userInputBackButton.setVisible(false);

        this.showDriverConfirmButton = new JButton();
        this.showDriverConfirmButton.setText("Confirm");
        this.showDriverConfirmButton.setFocusable(false);
        this.showDriverConfirmButton.setBounds(300, 250, 200, 50);
        this.showDriverConfirmButton.setVisible(false);

        this.showSiteConfirmButton = new JButton();
        this.showSiteConfirmButton.setText("Confirm");
        this.showSiteConfirmButton.setFocusable(false);
        this.showSiteConfirmButton.setBounds(300, 150, 200, 50);
        this.showSiteConfirmButton.setVisible(false);

        this.showTruckConfirmButton = new JButton();
        this.showTruckConfirmButton.setText("Confirm");
        this.showTruckConfirmButton.setFocusable(false);
        this.showTruckConfirmButton.setBounds(300, 150, 200, 50);
        this.showTruckConfirmButton.setVisible(false);

        this.add(this.mainMenuLabel);
        this.add(this.selectActionLabel);
        this.add(this.backButton);
        this.add(this.showDriverButton);
        this.add(this.showAllDriversButton);
        this.add(this.showTruckButton);
        this.add(this.showAllTrucksButton);
        this.add(this.showSiteButton);
        this.add(this.showAllSitesButton);
        this.add(this.truckManagementButton);
        this.add(this.siteManagementButton);

        this.add(this.userInput1Text);
        this.add(this.userInput1Label);
        this.add(this.userInputMainLabel);
        this.add(this.userInputError1Label);
        this.add(this.userInputBackButton);

        this.add(this.showDriverConfirmButton);
        this.add(this.showTruckConfirmButton);
        this.add(this.showSiteConfirmButton);
    }
}
