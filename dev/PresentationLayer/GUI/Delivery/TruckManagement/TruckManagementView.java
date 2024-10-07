package PresentationLayer.GUI.Delivery.TruckManagement;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TruckManagementView extends JFrame {

    JLabel truckManagementLabel;
    JLabel selectActionLabel;
    JButton backButton;
    JButton editStatusButton;
    JButton addDeliveryDateButton;
    JButton removeDeliveryDateButton;
    JButton addTruckButton;
    JButton removeTruckButton;

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
    JRadioButton userInputRadioButton1;
    JRadioButton userInputRadioButton2;
    ButtonGroup radioGroup;

    JButton addTruckConfirmButton;
    JButton removeTruckConfirmButton;
    JButton editStatusConfirmButton;
    JButton addDeliveryDateConfirmButton;
    JButton removeDeliveryDateConfirmButton;

    public TruckManagementView() {
        this.setTitle("Truck management menu");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        this.truckManagementLabel = new JLabel();
        this.truckManagementLabel.setText("Welcome to the truck management menu");
        this.truckManagementLabel.setVerticalAlignment(1);
        this.truckManagementLabel.setHorizontalAlignment(0);
        this.truckManagementLabel.setBounds(125, 0, 350, 200);
        this.truckManagementLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

        this.selectActionLabel = new JLabel();
        this.selectActionLabel.setText("Please choose the desired action:");
        this.selectActionLabel.setVerticalAlignment(1);
        this.selectActionLabel.setHorizontalAlignment(0);
        this.selectActionLabel.setBounds(150, 25, 300, 200);
        this.selectActionLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        this.addTruckButton = new JButton();
        this.addTruckButton.setText("Add truck");
        this.addTruckButton.setFocusable(false);
        this.addTruckButton.setBounds(100, 100, 200, 50);

        this.removeTruckButton = new JButton();
        this.removeTruckButton.setText("Remove truck");
        this.removeTruckButton.setFocusable(false);
        this.removeTruckButton.setBounds(300, 100, 200, 50);

        this.backButton = new JButton();
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBounds(100, 200, 200, 50);
        this.backButton.setBackground(new Color(255, 154, 162));

        this.editStatusButton = new JButton();
        this.editStatusButton.setText("Edit truck status");
        this.editStatusButton.setFocusable(false);
        this.editStatusButton.setBounds(100, 150, 200, 50);

        this.addDeliveryDateButton = new JButton();
        this.addDeliveryDateButton.setText("Add truck delivery date");
        this.addDeliveryDateButton.setFocusable(false);
        this.addDeliveryDateButton.setBounds(300, 150, 200, 50);

        this.removeDeliveryDateButton = new JButton();
        this.removeDeliveryDateButton.setText("Remove truck delivery date");
        this.removeDeliveryDateButton.setFocusable(false);
        this.removeDeliveryDateButton.setBounds(300, 200, 200, 50);

        this.userInput1Text = new JTextField();
        this.userInput1Text.setVisible(false);

        this.userInput2Text = new JTextField();
        this.userInput2Text.setVisible(false);

        this.userInput3Text = new JTextField();
        this.userInput3Text.setVisible(false);

        this.userInput4Text = new JTextField();
        this.userInput4Text.setVisible(false);

        this.userInput5Text = new JTextField();
        this.userInput5Text.setVisible(false);

        this.userInput1Label = new JLabel();
        this.userInput1Label.setVerticalAlignment(1);
        this.userInput1Label.setHorizontalAlignment(2);
        this.userInput1Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput1Label.setVisible(false);

        this.userInput2Label = new JLabel();
        this.userInput2Label.setVerticalAlignment(1);
        this.userInput2Label.setHorizontalAlignment(2);
        this.userInput2Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput2Label.setVisible(false);

        this.userInput3Label = new JLabel();
        this.userInput3Label.setVerticalAlignment(1);
        this.userInput3Label.setHorizontalAlignment(2);
        this.userInput3Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput3Label.setVisible(false);

        this.userInput4Label = new JLabel();
        this.userInput4Label.setVerticalAlignment(1);
        this.userInput4Label.setHorizontalAlignment(2);
        this.userInput4Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput4Label.setVisible(false);

        this.userInput5Label = new JLabel();
        this.userInput5Label.setVerticalAlignment(1);
        this.userInput5Label.setHorizontalAlignment(2);
        this.userInput5Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput5Label.setVisible(false);

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

        this.userInputError2Label = new JLabel();
        this.userInputError2Label.setVerticalAlignment(1);
        this.userInputError2Label.setHorizontalAlignment(2);
        this.userInputError2Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError2Label.setForeground(new Color(204, 0, 0));
        this.userInputError2Label.setVisible(false);

        this.userInputError3Label = new JLabel();
        this.userInputError3Label.setVerticalAlignment(1);
        this.userInputError3Label.setHorizontalAlignment(2);
        this.userInputError3Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError3Label.setForeground(new Color(204, 0, 0));
        this.userInputError3Label.setVisible(false);

        this.userInputError4Label = new JLabel();
        this.userInputError4Label.setVerticalAlignment(1);
        this.userInputError4Label.setHorizontalAlignment(2);
        this.userInputError4Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError4Label.setForeground(new Color(204, 0, 0));
        this.userInputError4Label.setVisible(false);

        this.userInputError5Label = new JLabel();
        this.userInputError5Label.setVerticalAlignment(1);
        this.userInputError5Label.setHorizontalAlignment(2);
        this.userInputError5Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError5Label.setForeground(new Color(204, 0, 0));
        this.userInputError5Label.setVisible(false);

        this.userInputBackButton = new JButton();
        this.userInputBackButton.setText("Back");
        this.userInputBackButton.setFocusable(false);
        this.userInputBackButton.setBackground(new Color(255, 154, 162));
        this.userInputBackButton.setVisible(false);

        this.addTruckConfirmButton = new JButton();
        this.addTruckConfirmButton.setText("Confirm");
        this.addTruckConfirmButton.setFocusable(false);
        this.addTruckConfirmButton.setBounds(300, 300, 200, 50);
        this.addTruckConfirmButton.setVisible(false);

        this.editStatusConfirmButton = new JButton();
        this.editStatusConfirmButton.setText("Confirm");
        this.editStatusConfirmButton.setFocusable(false);
        this.editStatusConfirmButton.setBounds(300, 150, 200, 50);
        this.editStatusConfirmButton.setVisible(false);

        this.addDeliveryDateConfirmButton = new JButton();
        this.addDeliveryDateConfirmButton.setText("Confirm");
        this.addDeliveryDateConfirmButton.setFocusable(false);
        this.addDeliveryDateConfirmButton.setBounds(300, 150, 200, 50);
        this.addDeliveryDateConfirmButton.setVisible(false);

        this.removeTruckConfirmButton = new JButton();
        this.removeTruckConfirmButton.setText("Confirm");
        this.removeTruckConfirmButton.setFocusable(false);
        this.removeTruckConfirmButton.setBounds(300, 150, 200, 50);
        this.removeTruckConfirmButton.setVisible(false);

        this.removeDeliveryDateConfirmButton = new JButton();
        this.removeDeliveryDateConfirmButton.setText("Confirm");
        this.removeDeliveryDateConfirmButton.setFocusable(false);
        this.removeDeliveryDateConfirmButton.setBounds(300, 150, 200, 50);
        this.removeDeliveryDateConfirmButton.setVisible(false);

        this.userInputRadioButton1 = new JRadioButton();
        this.userInputRadioButton1.setFocusable(false);
        this.userInputRadioButton1.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInputRadioButton1.setVisible(false);
        this.userInputRadioButton1.setBackground(new Color(121, 198, 252));

        this.userInputRadioButton2 = new JRadioButton();
        this.userInputRadioButton2.setFocusable(false);
        this.userInputRadioButton2.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInputRadioButton2.setVisible(false);
        this.userInputRadioButton2.setBackground(new Color(121, 198, 252));

        this.radioGroup = new ButtonGroup();
        this.radioGroup.add(this.userInputRadioButton1);
        this.radioGroup.add(this.userInputRadioButton2);

        this.add(this.truckManagementLabel);
        this.add(this.selectActionLabel);
        this.add(this.backButton);
        this.add(this.editStatusButton);
        this.add(this.addDeliveryDateButton);
        this.add(this.removeDeliveryDateButton);
        this.add(this.addTruckButton);
        this.add(this.removeTruckButton);

        this.add(this.userInput1Text);
        this.add(this.userInput5Text);
        this.add(this.userInput3Text);
        this.add(this.userInput4Text);
        this.add(this.userInput2Text);
        this.add(this.userInput4Label);
        this.add(this.userInput1Label);
        this.add(this.userInput2Label);
        this.add(this.userInput3Label);
        this.add(this.userInput5Label);
        this.add(this.userInputMainLabel);
        this.add(this.userInputError1Label);
        this.add(this.userInputError2Label);
        this.add(this.userInputError3Label);
        this.add(this.userInputError4Label);
        this.add(this.userInputError5Label);
        this.add(this.userInputBackButton);
        this.add(this.userInputRadioButton1);
        this.add(this.userInputRadioButton2);

        this.add(this.addTruckConfirmButton);
        this.add(this.removeTruckConfirmButton);
        this.add(this.editStatusConfirmButton);
        this.add(this.addDeliveryDateConfirmButton);
        this.add(this.removeDeliveryDateConfirmButton);
    }
}
