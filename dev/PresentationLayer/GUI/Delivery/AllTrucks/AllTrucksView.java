package PresentationLayer.GUI.Delivery.AllTrucks;

import PresentationLayer.GUI.Delivery.Truck.TruckModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class AllTrucksView extends JFrame {

    AllTrucksModel allTrucksModel;

    JLabel siteManagementLabel;
    JLabel userInputError1Label;
    JButton backButton;
    JButton infoButton;
    JButton refreshButton;
    JTable trucksTable;
    JScrollPane tablePane;
    JComboBox<Object> userInputComboBox1;


    public AllTrucksView(AllTrucksModel allTrucksModel, HashMap<Integer, TruckModel> trucks) {
        this.allTrucksModel = allTrucksModel;
        this.setTitle("Show Trucks");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        this.siteManagementLabel = new JLabel();
        this.siteManagementLabel.setText("Show Trucks");
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

        this.trucksTable = new JTable();
        this.trucksTable.setModel(allTrucksModel);
        this.trucksTable.setDefaultEditor(Object.class, null);
        this.trucksTable.getTableHeader().setReorderingAllowed(false);
        this.trucksTable.setBounds(50, 50, 450, 200);
        this.trucksTable.setRowSelectionAllowed(false);
        this.trucksTable.setColumnSelectionAllowed(false);
        this.trucksTable.setCellSelectionEnabled(false);
        this.trucksTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.tablePane = new JScrollPane(trucksTable);
        this.tablePane.setBounds(50, 50, 450, 125);
        this.tablePane.setVisible(true);
        this.tablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.userInputComboBox1 = new JComboBox<>();
        this.userInputComboBox1.setBounds(100, 225, 100, 25);
        this.userInputComboBox1.addItem("-");
        for (Integer i : trucks.keySet())
            this.userInputComboBox1.addItem(i);


        this.add(this.siteManagementLabel);
        this.add(this.backButton);
        this.add(this.refreshButton);
        this.add(this.infoButton);
        this.add(this.tablePane);

        this.add(this.userInputError1Label);
        this.add(this.userInputComboBox1);
    }
}
