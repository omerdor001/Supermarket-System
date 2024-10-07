package PresentationLayer.GUI.Delivery.ShowDeliveryOrder;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ShowDeliveryOrderView extends JFrame {

    JLabel idLabel;
    JLabel destinationLabel;
    JLabel sourceLabel;
    JLabel totalLoadWeightLabel;
    JLabel itemsLabel;
    JLabel statusLabel;
    JButton backButton;
    JButton refreshButton;
    JScrollPane tablePane;
    JTable itemsTable;

    public ShowDeliveryOrderView() {
        this.setLocationRelativeTo(null);
        this.setTitle("Delivery order information");
        this.setSize(650, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        JLabel titleLabel = new JLabel();
        titleLabel.setBounds(215, 25, 550, 200);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        titleLabel.setFont(new Font("Sans_serif", Font.BOLD, 16));
        titleLabel.setText("Delivery order information");

        this.idLabel = new JLabel();
        this.idLabel.setBounds(100, 50, 550, 200);
        this.idLabel.setVerticalAlignment(JLabel.TOP);
        this.idLabel.setHorizontalAlignment(JLabel.LEFT);
        this.idLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.idLabel.setText("Delivery order id:");

        this.destinationLabel = new JLabel();
        this.destinationLabel.setBounds(100, 75, 350, 200);
        this.destinationLabel.setVerticalAlignment(JLabel.TOP);
        this.destinationLabel.setHorizontalAlignment(JLabel.LEFT);
        this.destinationLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.destinationLabel.setText("Delivery destination:");

        this.sourceLabel = new JLabel();
        this.sourceLabel.setBounds(100, 100, 300, 200);
        this.sourceLabel.setVerticalAlignment(JLabel.TOP);
        this.sourceLabel.setHorizontalAlignment(JLabel.LEFT);
        this.sourceLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.sourceLabel.setText("Delivery source:");

        this.totalLoadWeightLabel = new JLabel();
        this.totalLoadWeightLabel.setBounds(100, 125, 300, 200);
        this.totalLoadWeightLabel.setVerticalAlignment(JLabel.TOP);
        this.totalLoadWeightLabel.setHorizontalAlignment(JLabel.LEFT);
        this.totalLoadWeightLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.totalLoadWeightLabel.setText("Delivery load weight:");

        this.statusLabel = new JLabel();
        this.statusLabel.setBounds(100, 150, 300, 200);
        this.statusLabel.setVerticalAlignment(JLabel.TOP);
        this.statusLabel.setHorizontalAlignment(JLabel.LEFT);
        this.statusLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.statusLabel.setText("Delivery order status:");

        this.itemsLabel = new JLabel();
        this.itemsLabel.setBounds(100, 175, 300, 200);
        this.itemsLabel.setVerticalAlignment(JLabel.TOP);
        this.itemsLabel.setHorizontalAlignment(JLabel.LEFT);
        this.itemsLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.itemsLabel.setText("Delivery items:");

        this.refreshButton = new JButton();
        this.refreshButton.setText("Refresh");
        this.refreshButton.setFocusable(false);
        this.refreshButton.setBounds(325, 500, 200, 50);

        this.backButton = new JButton();
        this.backButton.setBounds(125, 500, 200, 50);
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBackground(new Color(255, 154, 162));

        this.itemsTable = new JTable();
        this.itemsTable.setDefaultEditor(Object.class, null);
        this.itemsTable.getTableHeader().setReorderingAllowed(false);
        this.itemsTable.setBounds(100, 160, 400, 200);
        this.itemsTable.setRowSelectionAllowed(false);
        this.itemsTable.setColumnSelectionAllowed(false);
        this.itemsTable.setCellSelectionEnabled(false);
        this.itemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.tablePane = new JScrollPane(itemsTable);
        this.tablePane.setBounds(25, 200, 585, 300);
        this.tablePane.setVisible(true);
        this.tablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(titleLabel);
        this.add(idLabel);
        this.add(destinationLabel);
        this.add(sourceLabel);
        this.add(totalLoadWeightLabel);
        this.add(itemsLabel);
        this.add(statusLabel);
        this.add(refreshButton);
        this.add(backButton);
        this.add(tablePane);
    }
}
