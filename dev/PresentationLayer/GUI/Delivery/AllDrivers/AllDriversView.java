package PresentationLayer.GUI.Delivery.AllDrivers;

import PresentationLayer.GUI.HR.EmployeeInformationHR.DriverModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class AllDriversView extends JFrame {

    AllDriversModel allDriversModel;

    JLabel siteManagementLabel;
    JButton backButton;
    JButton infoButton;
    JButton refreshButton;
    JTable driversTable;
    JScrollPane tablePane;

    JLabel userInputError1Label;
    JComboBox<String> userInputComboBox1;

    public AllDriversView(AllDriversModel allDriversModel, HashMap<String, DriverModel> drivers) {
        this.allDriversModel = allDriversModel;
        this.setTitle("Show Drivers");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));
        this.setVisible(true);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        this.siteManagementLabel = new JLabel();
        this.siteManagementLabel.setText("Show Drivers");
        this.siteManagementLabel.setVerticalAlignment(1);
        this.siteManagementLabel.setHorizontalAlignment(0);
        this.siteManagementLabel.setBounds(125, 0, 350, 200);
        this.siteManagementLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

        this.backButton = new JButton();
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBounds(100, 275, 200, 50);

        this.backButton.setBackground(new Color(255, 154, 162));
        this.infoButton = new JButton();
        this.infoButton.setText("show all information");
        this.infoButton.setFocusable(false);
        this.infoButton.setBounds(205, 225, 200, 25);

        this.refreshButton = new JButton();
        this.refreshButton.setText("refresh");
        this.refreshButton.setFocusable(false);
        this.refreshButton.setBounds(300, 275, 200, 50);

        this.userInputError1Label = new JLabel();
        this.userInputError1Label.setVerticalAlignment(1);
        this.userInputError1Label.setHorizontalAlignment(2);
        this.userInputError1Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError1Label.setForeground(new Color(204, 0, 0));
        this.userInputError1Label.setBounds(410, 225, 150, 50);
        this.userInputError1Label.setText("you must choose one option");
        this.userInputError1Label.setVisible(false);

        this.driversTable = new JTable();
        this.driversTable.setModel(allDriversModel);
        this.driversTable.setDefaultEditor(Object.class, null);
        this.driversTable.getTableHeader().setReorderingAllowed(false);
        this.driversTable.setBounds(50, 50, 450, 200);
        this.driversTable.setRowSelectionAllowed(false);
        this.driversTable.setColumnSelectionAllowed(false);
        this.driversTable.setCellSelectionEnabled(false);
        this.driversTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.tablePane = new JScrollPane(driversTable);
        this.tablePane.setBounds(50, 50, 450, 125);
        this.tablePane.setVisible(true);
        this.tablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.userInputComboBox1 = new JComboBox<>();
        this.userInputComboBox1.setBounds(100, 225, 100, 25);
        this.userInputComboBox1.addItem("-");
        for (String i : drivers.keySet())
            userInputComboBox1.addItem(i);

        this.add(this.siteManagementLabel);
        this.add(this.backButton);
        this.add(this.refreshButton);
        this.add(this.infoButton);
        this.add(this.tablePane);

        this.add(this.userInputError1Label);
        this.add(this.userInputComboBox1);
    }
}
