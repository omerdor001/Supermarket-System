package PresentationLayer.GUI.Delivery.AllSites;


import PresentationLayer.GUI.Delivery.Site.SiteModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class AllSitesView extends JFrame {

    AllSitesModel allSitesModel;

    JLabel siteManagementLabel;
    JLabel userInputError1Label;
    JButton backButton;
    JButton infoButton;
    JButton refreshButton;
    JTable sitesTable;
    JScrollPane tablePane;
    JComboBox<Object> userInputComboBox1;

    public AllSitesView(AllSitesModel allSitesModel, HashMap<String, SiteModel> sites) {
        this.allSitesModel = allSitesModel;
        this.setTitle("Show Sites");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        this.siteManagementLabel = new JLabel();
        this.siteManagementLabel.setText("Show Sites");
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

        this.sitesTable = new JTable();
        this.sitesTable.setModel(allSitesModel);
        this.sitesTable.setDefaultEditor(Object.class, null);
        this.sitesTable.getTableHeader().setReorderingAllowed(false);
        this.sitesTable.setBounds(25, 50, 525, 200);
        this.sitesTable.setRowSelectionAllowed(false);
        this.sitesTable.setColumnSelectionAllowed(false);
        this.sitesTable.setCellSelectionEnabled(false);
        this.sitesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.tablePane = new JScrollPane(sitesTable);
        this.tablePane.setBounds(25, 50, 525, 125);
        this.tablePane.setVisible(true);
        this.tablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.userInputComboBox1 = new JComboBox<>();
        this.userInputComboBox1.setBounds(100, 225, 100, 25);
        this.userInputComboBox1.addItem("-");
        for (String i : sites.keySet())
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

