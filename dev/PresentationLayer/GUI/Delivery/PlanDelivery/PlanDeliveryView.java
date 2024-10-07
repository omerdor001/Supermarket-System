package PresentationLayer.GUI.Delivery.PlanDelivery;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PlanDeliveryView extends JFrame {

    // main options components
    JLabel mainMenuLabel;
    JLabel selectActionLabel;
    JButton backButton;
    JButton createDeliveryButton;
    JButton cancelDeliveryButton;
    JButton updateTruckButton;
    JButton updateDriverButton;
    JButton addStopButton;
    JButton removeStopButton;
    JButton editStopTimeButton;
    JButton addStopItemButton;
    JButton removeStopItemButton;
    JButton showItemOrdersButton;

    // user input components
    JTextField userInput1Text;
    JTextField userInput2Text;
    JTextField userInput3Text;
    JTextField userInput4Text;
    JTextField userInput5Text;
    JLabel userInput1Label;
    JLabel userInput2Label;
    JLabel userInput3Label;
    JLabel userInput4Label;
    JLabel userInput5Label;
    JLabel userInputMainLabel;
    JLabel userInputError1Label;
    JLabel userInputError2Label;
    JLabel userInputError3Label;
    JLabel userInputError4Label;
    JLabel userInputError5Label;
    JButton userInputBackButton;
    JCheckBox userInputCheckBox;
    JRadioButton userInputRadioButton1;
    JRadioButton userInputRadioButton2;
    ButtonGroup radioGroup;

    // specific actions confirmation buttons
    JButton createDeliveryConfirmButton;
    JButton cancelDeliveryConfirmButton;
    JButton updateTruckConfirmButton;
    JButton updateDriverConfirmButton;
    JButton addStopConfirmButton;
    JButton removeStopConfirmButton;
    JButton addItemConfirmButton;
    JButton removeItemConfirmButton;
    JButton editStopTimeConfirmButton;

    public PlanDeliveryView() {
        this.setLocationRelativeTo(null);
        this.setTitle("Delivery planning");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        mainMenuLabel = new JLabel();
        mainMenuLabel.setText("Welcome to the delivery planning menu");
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setBounds(175, 0, 350, 200);
        mainMenuLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

        selectActionLabel = new JLabel();
        selectActionLabel.setText("Please choose the desired action:");
        selectActionLabel.setVerticalAlignment(JLabel.TOP);
        selectActionLabel.setHorizontalAlignment(JLabel.CENTER);
        selectActionLabel.setBounds(200, 25, 300, 200);
        selectActionLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        backButton = new JButton();
        backButton.setText("Back");
        backButton.setFocusable(false);
        backButton.setBounds(250, 300, 200, 50);
        backButton.setBackground(new Color(255, 154, 162));

        createDeliveryButton = new JButton();
        createDeliveryButton.setText("Create delivery");
        createDeliveryButton.setFocusable(false);
        createDeliveryButton.setBounds(150, 50, 200, 50);

        cancelDeliveryButton = new JButton();
        cancelDeliveryButton.setText("Cancel delivery");
        cancelDeliveryButton.setFocusable(false);
        cancelDeliveryButton.setBounds(350, 50, 200, 50);

        updateTruckButton = new JButton();
        updateTruckButton.setText("Update delivery truck");
        updateTruckButton.setFocusable(false);
        updateTruckButton.setBounds(150, 100, 200, 50);

        updateDriverButton = new JButton();
        updateDriverButton.setText("Update delivery driver");
        updateDriverButton.setFocusable(false);
        updateDriverButton.setBounds(350, 100, 200, 50);

        addStopButton = new JButton();
        addStopButton.setText("Add delivery stop");
        addStopButton.setFocusable(false);
        addStopButton.setBounds(150, 150, 200, 50);

        removeStopButton = new JButton();
        removeStopButton.setText("Remove delivery stop");
        removeStopButton.setFocusable(false);
        removeStopButton.setBounds(350, 150, 200, 50);

        editStopTimeButton = new JButton();
        editStopTimeButton.setText("Edit stop arrive time");
        editStopTimeButton.setFocusable(false);
        editStopTimeButton.setBounds(350, 250, 200, 50);

        addStopItemButton = new JButton();
        addStopItemButton.setText("Add stop pickup item");
        addStopItemButton.setFocusable(false);
        addStopItemButton.setBounds(150, 200, 200, 50);

        removeStopItemButton = new JButton();
        removeStopItemButton.setText("Remove stop pickup item");
        removeStopItemButton.setFocusable(false);
        removeStopItemButton.setBounds(350, 200, 200, 50);

        showItemOrdersButton = new JButton();
        showItemOrdersButton.setText("Show item orders");
        showItemOrdersButton.setFocusable(false);
        showItemOrdersButton.setBounds(150, 250, 200, 50);

        userInput1Text = new JTextField();
        userInput1Text.setVisible(false);

        userInput2Text = new JTextField();
        userInput2Text.setVisible(false);

        userInput3Text = new JTextField();
        userInput3Text.setVisible(false);

        userInput4Text = new JTextField();
        userInput4Text.setVisible(false);

        userInput5Text = new JTextField();
        userInput5Text.setVisible(false);

        userInput1Label = new JLabel();
        userInput1Label.setVerticalAlignment(JLabel.TOP);
        userInput1Label.setHorizontalAlignment(JLabel.LEFT);
        userInput1Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInput1Label.setVisible(false);

        userInput2Label = new JLabel();
        userInput2Label.setVerticalAlignment(JLabel.TOP);
        userInput2Label.setHorizontalAlignment(JLabel.LEFT);
        userInput2Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInput2Label.setVisible(false);

        userInput3Label = new JLabel();
        userInput3Label.setVerticalAlignment(JLabel.TOP);
        userInput3Label.setHorizontalAlignment(JLabel.LEFT);
        userInput3Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInput3Label.setVisible(false);

        userInput4Label = new JLabel();
        userInput4Label.setVerticalAlignment(JLabel.TOP);
        userInput4Label.setHorizontalAlignment(JLabel.LEFT);
        userInput4Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInput4Label.setVisible(false);

        userInput5Label = new JLabel();
        userInput5Label.setVerticalAlignment(JLabel.TOP);
        userInput5Label.setHorizontalAlignment(JLabel.LEFT);
        userInput5Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInput5Label.setVisible(false);

        userInputMainLabel = new JLabel();
        userInputMainLabel.setVerticalAlignment(JLabel.TOP);
        userInputMainLabel.setHorizontalAlignment(JLabel.LEFT);
        userInputMainLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
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

        userInputError5Label = new JLabel();
        userInputError5Label.setVerticalAlignment(JLabel.TOP);
        userInputError5Label.setHorizontalAlignment(JLabel.LEFT);
        userInputError5Label.setFont(new Font("Sans_serif", Font.PLAIN, 13));
        userInputError5Label.setForeground(Color.red);
        userInputError5Label.setVisible(false);

        userInputBackButton = new JButton();
        userInputBackButton.setText("Back");
        userInputBackButton.setFocusable(false);
        userInputBackButton.setBackground(new Color(255, 154, 162));
        userInputBackButton.setVisible(false);

        userInputCheckBox = new JCheckBox();
        userInputCheckBox.setFocusable(false);
        userInputCheckBox.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInputCheckBox.setVisible(false);
        userInputCheckBox.setBackground(new Color(121, 198, 252));

        userInputRadioButton1 = new JRadioButton();
        userInputRadioButton1.setFocusable(false);
        userInputRadioButton1.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInputRadioButton1.setVisible(false);
        userInputRadioButton1.setBackground(new Color(121, 198, 252));

        userInputRadioButton2 = new JRadioButton();
        userInputRadioButton2.setFocusable(false);
        userInputRadioButton2.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        userInputRadioButton2.setVisible(false);
        userInputRadioButton2.setBackground(new Color(121, 198, 252));

        radioGroup = new ButtonGroup();
        radioGroup.add(userInputRadioButton1);
        radioGroup.add(userInputRadioButton2);

        createDeliveryConfirmButton = new JButton();
        createDeliveryConfirmButton.setText("Confirm");
        createDeliveryConfirmButton.setFocusable(false);
        createDeliveryConfirmButton.setBounds(350, 300, 200, 50);
        createDeliveryConfirmButton.setVisible(false);

        updateTruckConfirmButton = new JButton();
        updateTruckConfirmButton.setText("Confirm");
        updateTruckConfirmButton.setFocusable(false);
        updateTruckConfirmButton.setBounds(300, 150, 200, 50);
        updateTruckConfirmButton.setVisible(false);

        updateDriverConfirmButton = new JButton();
        updateDriverConfirmButton.setText("Confirm");
        updateDriverConfirmButton.setFocusable(false);
        updateDriverConfirmButton.setBounds(300, 150, 200, 50);
        updateDriverConfirmButton.setVisible(false);

        cancelDeliveryConfirmButton = new JButton();
        cancelDeliveryConfirmButton.setText("Confirm");
        cancelDeliveryConfirmButton.setFocusable(false);
        cancelDeliveryConfirmButton.setBounds(300, 150, 200, 50);
        cancelDeliveryConfirmButton.setVisible(false);

        addStopConfirmButton = new JButton();
        addStopConfirmButton.setText("Confirm");
        addStopConfirmButton.setFocusable(false);
        addStopConfirmButton.setBounds(300, 150, 200, 50);
        addStopConfirmButton.setVisible(false);

        removeStopConfirmButton = new JButton();
        removeStopConfirmButton.setText("Confirm");
        removeStopConfirmButton.setFocusable(false);
        removeStopConfirmButton.setBounds(300, 150, 200, 50);
        removeStopConfirmButton.setVisible(false);

        addItemConfirmButton = new JButton();
        addItemConfirmButton.setText("Confirm");
        addItemConfirmButton.setFocusable(false);
        addItemConfirmButton.setBounds(300, 300, 200, 50);
        addItemConfirmButton.setVisible(false);

        removeItemConfirmButton = new JButton();
        removeItemConfirmButton.setText("Confirm");
        removeItemConfirmButton.setFocusable(false);
        removeItemConfirmButton.setBounds(300, 150, 200, 50);
        removeItemConfirmButton.setVisible(false);

        editStopTimeConfirmButton = new JButton();
        editStopTimeConfirmButton.setText("Confirm");
        editStopTimeConfirmButton.setFocusable(false);
        editStopTimeConfirmButton.setBounds(300, 250, 200, 50);
        editStopTimeConfirmButton.setVisible(false);

        this.add(mainMenuLabel);
        this.add(selectActionLabel);
        this.add(backButton);
        this.add(createDeliveryButton);
        this.add(cancelDeliveryButton);
        this.add(updateTruckButton);
        this.add(updateDriverButton);
        this.add(addStopButton);
        this.add(removeStopButton);
        this.add(editStopTimeButton);
        this.add(addStopItemButton);
        this.add(removeStopItemButton);
        this.add(showItemOrdersButton);

        this.add(userInput1Text);
        this.add(userInput5Text);
        this.add(userInput3Text);
        this.add(userInput4Text);
        this.add(userInput2Text);
        this.add(userInput4Label);
        this.add(userInput1Label);
        this.add(userInput2Label);
        this.add(userInput3Label);
        this.add(userInput5Label);
        this.add(userInputMainLabel);
        this.add(userInputError1Label);
        this.add(userInputError2Label);
        this.add(userInputError3Label);
        this.add(userInputError4Label);
        this.add(userInputError5Label);
        this.add(userInputBackButton);
        this.add(userInputCheckBox);
        this.add(userInputRadioButton1);
        this.add(userInputRadioButton2);

        this.add(createDeliveryConfirmButton);
        this.add(cancelDeliveryConfirmButton);
        this.add(updateTruckConfirmButton);
        this.add(updateDriverConfirmButton);
        this.add(addStopConfirmButton);
        this.add(removeStopConfirmButton);
        this.add(addItemConfirmButton);
        this.add(removeItemConfirmButton);
        this.add(editStopTimeConfirmButton);
    }
}
