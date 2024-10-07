package PresentationLayer.GUI.Delivery.ShowItems;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ShowItemsView extends JFrame {

    ShowItemsModel itemsModel;

    JButton backButton;
    JButton refreshButton;
    JTable deliveryOrdersTable;
    JScrollPane tablePane;

    public ShowItemsView(ShowItemsModel itemsModel) {
        this.itemsModel = itemsModel;
        this.setTitle("Item information");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        JLabel mainTitleLabel = new JLabel();
        mainTitleLabel.setText("All items");
        mainTitleLabel.setVerticalAlignment(1);
        mainTitleLabel.setHorizontalAlignment(0);
        mainTitleLabel.setBounds(125, 0, 350, 200);
        mainTitleLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

        this.backButton = new JButton();
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBounds(100, 300, 200, 50);
        this.backButton.setBackground(new Color(255, 154, 162));

        this.refreshButton = new JButton();
        this.refreshButton.setText("Refresh");
        this.refreshButton.setFocusable(false);
        this.refreshButton.setBounds(300, 300, 200, 50);

        this.deliveryOrdersTable = new JTable();
        this.deliveryOrdersTable.setModel(itemsModel);
        this.deliveryOrdersTable.setDefaultEditor(Object.class, null);
        this.deliveryOrdersTable.getTableHeader().setReorderingAllowed(false);
        this.deliveryOrdersTable.setBounds(50, 50, 500, 200);
        this.deliveryOrdersTable.setRowSelectionAllowed(false);
        this.deliveryOrdersTable.setColumnSelectionAllowed(false);
        this.deliveryOrdersTable.setCellSelectionEnabled(false);
        this.deliveryOrdersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.tablePane = new JScrollPane(this.deliveryOrdersTable);
        this.tablePane.setBounds(50, 50, 500, 250);
        this.tablePane.setVisible(true);
        this.tablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(mainTitleLabel);
        this.add(this.backButton);
        this.add(this.refreshButton);
        this.add(this.tablePane);
    }
}
