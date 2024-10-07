package PresentationLayer.GUI.Delivery.DeliveryManagement;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Objects;

public class DeliveryManagementView extends JFrame {

    // main options components
    JLabel mainMenuLabel;
    JLabel selectActionLabel;
    JButton backButton;
    JButton parseOrderButton;
    JButton planDeliveryButton;
    JButton progressDeliveryButton;
    JButton showDeliveryButton;
    JButton showDeliveryOrderButton;
    JButton showAllDeliveriesButton;
    JButton showAllDeliveryOrdersButton;

    // user input components
    JTextField userInput1Text;
    JLabel userInput1Label;
    JLabel userInputMainLabel;
    JLabel userInputErrorLabel;
    JButton userInputBackButton;
    JTextArea userInputTextArea;
    JPanel parseOrderPanel;

    // specific actions confirmation buttons
    JButton showDeliveryConfirmButton;
    JButton showDeliveryOrderConfirmButton;
    JButton parseOrderConfirmButton;

    public DeliveryManagementView() {
        this.setLocationRelativeTo(null);
        this.setTitle("Delivery management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 350);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        mainMenuLabel = new JLabel();
        mainMenuLabel.setText("Welcome to the delivery management menu");
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setBounds(125, 0, 350, 200);
        mainMenuLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

        selectActionLabel = new JLabel();
        selectActionLabel.setText("Please choose the desired action:");
        selectActionLabel.setVerticalAlignment(JLabel.TOP);
        selectActionLabel.setHorizontalAlignment(JLabel.CENTER);
        selectActionLabel.setBounds(150, 35, 300, 200);
        selectActionLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        backButton = new JButton();
        backButton.setText("Back");
        backButton.setFocusable(false);
        backButton.setBounds(100, 225, 200, 50);
        backButton.setBackground(new Color(255, 154, 162));

        parseOrderButton = new JButton();
        parseOrderButton.setText("Parse delivery order");
        parseOrderButton.setFocusable(false);
        parseOrderButton.setBounds(300, 225, 200, 50);

        planDeliveryButton = new JButton();
        planDeliveryButton.setText("Delivery planning");
        planDeliveryButton.setFocusable(false);
        planDeliveryButton.setBounds(100, 75, 200, 50);

        progressDeliveryButton = new JButton();
        progressDeliveryButton.setText("Delivery progression");
        progressDeliveryButton.setFocusable(false);
        progressDeliveryButton.setBounds(300, 75, 200, 50);

        showDeliveryButton = new JButton();
        showDeliveryButton.setText("Show delivery information");
        showDeliveryButton.setFocusable(false);
        showDeliveryButton.setBounds(100, 125, 200, 50);

        showDeliveryOrderButton = new JButton();
        showDeliveryOrderButton.setText("Show delivery order");
        showDeliveryOrderButton.setFocusable(false);
        showDeliveryOrderButton.setBounds(100, 175, 200, 50);

        showAllDeliveriesButton = new JButton();
        showAllDeliveriesButton.setText("Show all deliveries");
        showAllDeliveriesButton.setFocusable(false);
        showAllDeliveriesButton.setBounds(300, 125, 200, 50);

        showAllDeliveryOrdersButton = new JButton();
        showAllDeliveryOrdersButton.setText("Show all delivery orders");
        showAllDeliveryOrdersButton.setFocusable(false);
        showAllDeliveryOrdersButton.setBounds(300, 175, 200, 50);

        userInput1Text = new JTextField();
        userInput1Text.setVisible(false);

        userInput1Label = new JLabel();
        userInput1Label.setVerticalAlignment(JLabel.TOP);
        userInput1Label.setHorizontalAlignment(JLabel.LEFT);
        userInput1Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInput1Label.setVisible(false);

        userInputMainLabel = new JLabel();
        userInputMainLabel.setVerticalAlignment(JLabel.TOP);
        userInputMainLabel.setHorizontalAlignment(JLabel.LEFT);
        userInputMainLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInputMainLabel.setVisible(false);

        userInputErrorLabel = new JLabel();
        userInputErrorLabel.setVerticalAlignment(JLabel.TOP);
        userInputErrorLabel.setHorizontalAlignment(JLabel.LEFT);
        userInputErrorLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInputErrorLabel.setForeground(Color.red);
        userInputErrorLabel.setVisible(false);

        userInputBackButton = new JButton();
        userInputBackButton.setText("Back");
        userInputBackButton.setFocusable(false);
        userInputBackButton.setBackground(new Color(255, 154, 162));
        userInputBackButton.setVisible(false);

        userInputTextArea = new JTextArea();
        userInputTextArea.setEditable(true);
        userInputTextArea.setColumns(46);
        userInputTextArea.setRows(10);

        showDeliveryConfirmButton = new JButton();
        showDeliveryConfirmButton.setText("Confirm");
        showDeliveryConfirmButton.setFocusable(false);
        showDeliveryConfirmButton.setBounds(300, 150, 200, 50);
        showDeliveryConfirmButton.setVisible(false);

        showDeliveryOrderConfirmButton = new JButton();
        showDeliveryOrderConfirmButton.setText("Confirm");
        showDeliveryOrderConfirmButton.setFocusable(false);
        showDeliveryOrderConfirmButton.setBounds(300, 150, 200, 50);
        showDeliveryOrderConfirmButton.setVisible(false);

        parseOrderConfirmButton = new JButton();
        parseOrderConfirmButton.setText("Confirm");
        parseOrderConfirmButton.setFocusable(false);
        parseOrderConfirmButton.setBounds(300, 150, 200, 50);
        parseOrderConfirmButton.setVisible(false);

        parseOrderPanel = new JPanel();
        parseOrderPanel.setBounds(50, 50, 500, 200);
        parseOrderPanel.setBorder(new TitledBorder(new EtchedBorder(), "Input delivery order in JSON format"));
        JScrollPane scroll = new JScrollPane(userInputTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        parseOrderPanel.add(scroll);
        parseOrderPanel.setVisible(false);

        this.add(mainMenuLabel);
        this.add(selectActionLabel);
        this.add(backButton);
        this.add(parseOrderButton);
        this.add(planDeliveryButton);
        this.add(progressDeliveryButton);
        this.add(showDeliveryButton);
        this.add(showDeliveryOrderButton);
        this.add(showAllDeliveryOrdersButton);
        this.add(showAllDeliveriesButton);

        this.add(userInput1Text);
        this.add(userInput1Label);
        this.add(userInputMainLabel);
        this.add(userInputBackButton);
        this.add(userInputErrorLabel);

        this.add(showDeliveryConfirmButton);
        this.add(showDeliveryOrderConfirmButton);
        this.add(parseOrderConfirmButton);

        this.add(parseOrderPanel);
    }
}
