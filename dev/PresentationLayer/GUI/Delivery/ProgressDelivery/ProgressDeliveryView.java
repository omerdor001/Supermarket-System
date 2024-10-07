package PresentationLayer.GUI.Delivery.ProgressDelivery;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ProgressDeliveryView extends JFrame {

    // main options components
    JLabel mainMenuLabel;
    JLabel selectActionLabel;
    JLabel overweightLabel;
    JButton backButton;
    JButton approveDeliveryButton;
    JButton visitStopButton;
    JButton switchTruckButton;
    JButton switchTruckDriverButton;
    JButton skipStopButton;
    JButton leaveItemButton;

    // user input components
    JTextField userInput1Text;
    JTextField userInput2Text;
    JTextField userInput3Text;
    JTextField userInput4Text;
    JLabel userInput1Label;
    JLabel userInput2Label;
    JLabel userInput3Label;
    JLabel userInput4Label;
    JLabel userInputMainLabel;
    JLabel userInputError1Label;
    JLabel userInputError2Label;
    JLabel userInputError3Label;
    JLabel userInputError4Label;
    JButton userInputBackButton;
    JCheckBox userInputCheckBox;

    // specific actions confirmation buttons
    JButton approveDeliveryConfirmButton;
    JButton visitStopConfirmButton;
    JButton switchTruckConfirmButton;
    JButton switchTruckDriverConfirmButton;
    JButton skipStopConfirmButton;
    JButton leaveItemConfirmButton;

    public ProgressDeliveryView() {
        this.setLocationRelativeTo(null);
        this.setTitle("Delivery progression");
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
        mainMenuLabel.setText("Welcome to the delivery progression menu");
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setBounds(150, 0, 300, 200);
        mainMenuLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        selectActionLabel = new JLabel();
        selectActionLabel.setText("Please choose the desired action:");
        selectActionLabel.setVerticalAlignment(JLabel.TOP);
        selectActionLabel.setHorizontalAlignment(JLabel.CENTER);
        selectActionLabel.setBounds(150, 25, 300, 200);
        selectActionLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        overweightLabel = new JLabel();
        overweightLabel.setText("Handle overweight delivery options:");
        overweightLabel.setVerticalAlignment(JLabel.TOP);
        overweightLabel.setHorizontalAlignment(JLabel.CENTER);
        overweightLabel.setBounds(150, 115, 300, 200);
        overweightLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        backButton = new JButton();
        backButton.setText("Back");
        backButton.setFocusable(false);
        backButton.setBounds(200, 250, 200, 50);
        backButton.setBackground(new Color(255, 154, 162));

        approveDeliveryButton = new JButton();
        approveDeliveryButton.setText("Approve delivery");
        approveDeliveryButton.setFocusable(false);
        approveDeliveryButton.setBounds(100, 50, 200, 50);

        visitStopButton = new JButton();
        visitStopButton.setText("Visit stop");
        visitStopButton.setFocusable(false);
        visitStopButton.setBounds(300, 50, 200, 50);

        switchTruckButton = new JButton();
        switchTruckButton.setText("Switch truck");
        switchTruckButton.setFocusable(false);
        switchTruckButton.setBounds(100, 150, 200, 50);

        switchTruckDriverButton = new JButton();
        switchTruckDriverButton.setText("Switch truck and driver");
        switchTruckDriverButton.setFocusable(false);
        switchTruckDriverButton.setBounds(300, 150, 200, 50);

        skipStopButton = new JButton();
        skipStopButton.setText("Skip stop");
        skipStopButton.setFocusable(false);
        skipStopButton.setBounds(100, 200, 200, 50);

        leaveItemButton = new JButton();
        leaveItemButton.setText("Leave item at stop");
        leaveItemButton.setFocusable(false);
        leaveItemButton.setBounds(300, 200, 200, 50);

        userInput1Text = new JTextField();
        userInput1Text.setVisible(false);

        userInput2Text = new JTextField();
        userInput2Text.setVisible(false);

        userInput3Text = new JTextField();
        userInput3Text.setVisible(false);

        userInput4Text = new JTextField();
        userInput4Text.setVisible(false);

        userInput1Label = new JLabel();
        userInput1Label.setVerticalAlignment(JLabel.TOP);
        userInput1Label.setHorizontalAlignment(JLabel.LEFT);
        userInput1Label.setFont(new Font("Sans_serif", Font.PLAIN, 16));
        userInput1Label.setVisible(false);

        userInput2Label = new JLabel();
        userInput2Label.setVerticalAlignment(JLabel.TOP);
        userInput2Label.setHorizontalAlignment(JLabel.LEFT);
        userInput2Label.setFont(new Font("Sans_serif", Font.PLAIN, 16));
        userInput2Label.setVisible(false);

        userInput3Label = new JLabel();
        userInput3Label.setVerticalAlignment(JLabel.TOP);
        userInput3Label.setHorizontalAlignment(JLabel.LEFT);
        userInput3Label.setFont(new Font("Sans_serif", Font.PLAIN, 16));
        userInput3Label.setVisible(false);

        userInput4Label = new JLabel();
        userInput4Label.setVerticalAlignment(JLabel.TOP);
        userInput4Label.setHorizontalAlignment(JLabel.LEFT);
        userInput4Label.setFont(new Font("Sans_serif", Font.PLAIN, 16));
        userInput4Label.setVisible(false);

        userInputMainLabel = new JLabel();
        userInputMainLabel.setVerticalAlignment(JLabel.TOP);
        userInputMainLabel.setHorizontalAlignment(JLabel.LEFT);
        userInputMainLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));
        userInputMainLabel.setVisible(false);

        userInputError1Label = new JLabel();
        userInputError1Label.setVerticalAlignment(JLabel.TOP);
        userInputError1Label.setHorizontalAlignment(JLabel.LEFT);
        userInputError1Label.setFont(new Font("Sans_serif", Font.PLAIN, 13));
        userInputError1Label.setForeground(Color.red);
        userInputError1Label.setVisible(false);

        userInputError2Label = new JLabel();
        userInputError2Label.setVerticalAlignment(JLabel.TOP);
        userInputError2Label.setHorizontalAlignment(JLabel.LEFT);
        userInputError2Label.setFont(new Font("Sans_serif", Font.PLAIN, 13));
        userInputError2Label.setForeground(Color.red);
        userInputError2Label.setVisible(false);

        userInputError3Label = new JLabel();
        userInputError3Label.setVerticalAlignment(JLabel.TOP);
        userInputError3Label.setHorizontalAlignment(JLabel.LEFT);
        userInputError3Label.setFont(new Font("Sans_serif", Font.PLAIN, 13));
        userInputError3Label.setForeground(Color.red);
        userInputError3Label.setVisible(false);

        userInputError4Label = new JLabel();
        userInputError4Label.setVerticalAlignment(JLabel.TOP);
        userInputError4Label.setHorizontalAlignment(JLabel.LEFT);
        userInputError4Label.setFont(new Font("Sans_serif", Font.PLAIN, 13));
        userInputError4Label.setForeground(Color.red);
        userInputError4Label.setVisible(false);

        userInputBackButton = new JButton();
        userInputBackButton.setText("Back");
        userInputBackButton.setFocusable(false);
        userInputBackButton.setBackground(new Color(255, 154, 162));
        userInputBackButton.setVisible(false);

        userInputCheckBox = new JCheckBox();
        userInputCheckBox.setFocusable(false);
        userInputCheckBox.setFont(new Font("Sans_serif", Font.PLAIN, 16));
        userInputCheckBox.setVisible(false);

        approveDeliveryConfirmButton = new JButton();
        approveDeliveryConfirmButton.setText("Confirm");
        approveDeliveryConfirmButton.setFocusable(false);
        approveDeliveryConfirmButton.setVisible(false);

        visitStopConfirmButton = new JButton();
        visitStopConfirmButton.setText("Confirm");
        visitStopConfirmButton.setFocusable(false);
        visitStopConfirmButton.setVisible(false);

        switchTruckConfirmButton = new JButton();
        switchTruckConfirmButton.setText("Confirm");
        switchTruckConfirmButton.setFocusable(false);
        switchTruckConfirmButton.setVisible(false);

        switchTruckDriverConfirmButton = new JButton();
        switchTruckDriverConfirmButton.setText("Confirm");
        switchTruckDriverConfirmButton.setFocusable(false);
        switchTruckDriverConfirmButton.setVisible(false);

        skipStopConfirmButton = new JButton();
        skipStopConfirmButton.setText("Confirm");
        skipStopConfirmButton.setFocusable(false);
        skipStopConfirmButton.setVisible(false);

        leaveItemConfirmButton = new JButton();
        leaveItemConfirmButton.setText("Confirm");
        leaveItemConfirmButton.setFocusable(false);
        leaveItemConfirmButton.setVisible(false);

        this.add(mainMenuLabel);
        this.add(selectActionLabel);
        this.add(overweightLabel);
        this.add(backButton);
        this.add(approveDeliveryButton);
        this.add(visitStopButton);
        this.add(switchTruckButton);
        this.add(switchTruckDriverButton);
        this.add(skipStopButton);
        this.add(leaveItemButton);

        this.add(userInput1Text);
        this.add(userInput2Text);
        this.add(userInput3Text);
        this.add(userInput4Text);
        this.add(userInput1Label);
        this.add(userInput2Label);
        this.add(userInput3Label);
        this.add(userInput4Label);
        this.add(userInputError1Label);
        this.add(userInputError2Label);
        this.add(userInputError3Label);
        this.add(userInputError4Label);
        this.add(userInputMainLabel);
        this.add(userInputBackButton);
        this.add(userInputCheckBox);

        this.add(approveDeliveryConfirmButton);
        this.add(visitStopConfirmButton);
        this.add(switchTruckConfirmButton);
        this.add(switchTruckDriverConfirmButton);
        this.add(skipStopConfirmButton);
        this.add(leaveItemConfirmButton);
    }
}
