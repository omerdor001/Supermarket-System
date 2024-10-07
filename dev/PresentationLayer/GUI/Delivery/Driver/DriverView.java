package PresentationLayer.GUI.Delivery.Driver;

import PresentationLayer.GUI.HR.EmployeeInformationHR.DriverModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;

public class DriverView extends JFrame {
    DriverModel driverModel;

    JLabel driverManagementLabel;
    JButton backButton;
    JButton refreshButton;
    JPanel displayPanel1;
    JPanel displayPanel2;

    JLabel userInput1Label;
    JLabel userInput2Label;
    JLabel userInput3Label;
    JLabel userInput4Label;
    JLabel userInput5Label;
    JLabel userInput6Label;
    JLabel userInput7Label;
    JLabel userInput8Label;

    public DriverView(DriverModel driverModel) {
        this.driverModel = driverModel;
        this.setTitle("Show Driver");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        this.driverManagementLabel = new JLabel();
        this.driverManagementLabel.setText("Show Driver");
        this.driverManagementLabel.setVerticalAlignment(1);
        this.driverManagementLabel.setHorizontalAlignment(0);
        this.driverManagementLabel.setBounds(125, 0, 350, 200);
        this.driverManagementLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

        this.backButton = new JButton();
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBounds(100, 275, 200, 50);
        this.backButton.setBackground(new Color(255, 154, 162));

        this.refreshButton = new JButton();
        this.refreshButton.setText("Refresh");
        this.refreshButton.setFocusable(false);
        this.refreshButton.setBounds(300, 275, 200, 50);

        this.userInput1Label = new JLabel();
        this.userInput1Label.setVerticalAlignment(1);
        this.userInput1Label.setHorizontalAlignment(2);
        this.userInput1Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput1Label.setVisible(true);

        this.userInput2Label = new JLabel();
        this.userInput2Label.setVerticalAlignment(1);
        this.userInput2Label.setHorizontalAlignment(2);
        this.userInput2Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput2Label.setVisible(true);

        this.userInput3Label = new JLabel();
        this.userInput3Label.setVerticalAlignment(1);
        this.userInput3Label.setHorizontalAlignment(2);
        this.userInput3Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput3Label.setVisible(true);

        this.userInput4Label = new JLabel();
        this.userInput4Label.setVerticalAlignment(1);
        this.userInput4Label.setHorizontalAlignment(2);
        this.userInput4Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput4Label.setVisible(true);

        this.userInput5Label = new JLabel();
        this.userInput5Label.setVerticalAlignment(1);
        this.userInput5Label.setHorizontalAlignment(2);
        this.userInput5Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput5Label.setVisible(true);

        this.userInput6Label = new JLabel();
        this.userInput6Label.setVerticalAlignment(1);
        this.userInput6Label.setHorizontalAlignment(2);
        this.userInput6Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput6Label.setVisible(true);

        this.userInput7Label = new JLabel();
        this.userInput7Label.setVerticalAlignment(1);
        this.userInput7Label.setHorizontalAlignment(2);
        this.userInput7Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput7Label.setVisible(true);

        this.userInput8Label = new JLabel();
        this.userInput8Label.setVerticalAlignment(1);
        this.userInput8Label.setHorizontalAlignment(2);
        this.userInput8Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput8Label.setVisible(true);

        this.userInput1Label.setText("id: " + driverModel.employeeId);
        this.userInput1Label.setBounds(200, 50, 350, 200);

        this.userInput2Label.setText("Name: " + driverModel.firstName + " " + driverModel.lastName);
        this.userInput2Label.setBounds(200, 75, 300, 200);

        this.userInput3Label.setText("salary: " + driverModel.salary);
        this.userInput3Label.setBounds(200, 100, 300, 200);

        this.userInput4Label.setText("phone number: " + driverModel.phoneNumber);
        this.userInput4Label.setBounds(200, 125, 300, 200);

        this.userInput5Label.setText("hire date: " + driverModel.hireDate.toLocalDate());
        this.userInput5Label.setBounds(200, 150, 300, 200);

        this.userInput6Label.setText("licenses: " + driverModel.licenses + "Kg");
        this.userInput6Label.setBounds(200, 175, 300, 200);

        this.userInput7Label.setText("delivery date: ");
        this.userInput7Label.setBounds(50, 200, 100, 200);

        this.userInput8Label.setText("Qualifications: ");
        this.userInput8Label.setBounds(300, 200, 100, 200);

        this.displayPanel1 = new JPanel();
        JTextArea displayTextArea1 = new JTextArea(4, 10);
        displayTextArea1.setEditable(false);
        JScrollPane displayPane1 = new JScrollPane(displayTextArea1);
        displayPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        StringBuilder s = new StringBuilder();
        for (LocalDate l : driverModel.deliveryDates)
            s.append(l).append("\n");
        displayTextArea1.setText(s.toString());
        this.displayPanel1.add(displayPane1);
        this.displayPanel1.setBounds(140, 200, 125, 120);
        this.displayPanel1.setVisible(true);

        this.displayPanel2 = new JPanel();
        JTextArea displayTextArea2 = new JTextArea(4, 10);
        displayTextArea2.setEditable(false);
        JScrollPane displayPane2 = new JScrollPane(displayTextArea2);
        displayPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        StringBuilder s2 = new StringBuilder();
        for (String l : driverModel.qualifications)
            s2.append(l).append("\n");
        displayTextArea2.setText(s2.toString());
        this.displayPanel2.add(displayPane2);
        this.displayPanel2.setBounds(400, 200, 125, 120);
        this.displayPanel2.setVisible(true);

        this.add(this.driverManagementLabel);
        this.add(this.backButton);
        this.add(this.refreshButton);
        this.add(displayPanel1);
        this.add(displayPanel2);

        this.add(this.userInput1Label);
        this.add(this.userInput2Label);
        this.add(this.userInput3Label);
        this.add(this.userInput4Label);
        this.add(this.userInput5Label);
        this.add(this.userInput6Label);
        this.add(this.userInput7Label);
        this.add(this.userInput8Label);
    }
}
