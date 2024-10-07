package PresentationLayer.GUI.Delivery.ShowAllItemOrders;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ShowAllItemOrdersView extends JFrame {

    ShowAllItemOrdersModel allItemOrdersModel;

    JButton backButton;
    JButton refreshButton;
    JTable itemOrdersTable;
    JScrollPane tablePane;

    public ShowAllItemOrdersView(ShowAllItemOrdersModel allDeliveryOrdersModel) {
        this.allItemOrdersModel = allDeliveryOrdersModel;
        this.setTitle("Show all item orders");
        this.setSize(800, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        JLabel mainTitleLabel = new JLabel();
        mainTitleLabel.setText("All item orders");
        mainTitleLabel.setVerticalAlignment(1);
        mainTitleLabel.setHorizontalAlignment(0);
        mainTitleLabel.setBounds(230, 0, 350, 200);
        mainTitleLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

        this.backButton = new JButton();
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBounds(200, 275, 200, 50);
        this.backButton.setBackground(new Color(255, 154, 162));

        this.refreshButton = new JButton();
        this.refreshButton.setText("Refresh");
        this.refreshButton.setFocusable(false);
        this.refreshButton.setBounds(400, 275, 200, 50);

        this.itemOrdersTable = new JTable();
        this.itemOrdersTable.setModel(allDeliveryOrdersModel);
        this.itemOrdersTable.setDefaultEditor(Object.class, null);
        this.itemOrdersTable.getTableHeader().setReorderingAllowed(false);
        this.itemOrdersTable.setRowSelectionAllowed(false);
        this.itemOrdersTable.setColumnSelectionAllowed(false);
        this.itemOrdersTable.setCellSelectionEnabled(false);
        this.itemOrdersTable.setBounds(50, 50, 500, 200);
        this.itemOrdersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.tablePane = new JScrollPane(this.itemOrdersTable);
        this.tablePane.setBounds(15, 50, 765, 225);
        this.tablePane.setVisible(true);
        this.tablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(mainTitleLabel);
        this.add(this.backButton);
        this.add(this.refreshButton);
        this.add(this.tablePane);
    }
}
