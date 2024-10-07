package PresentationLayer.GUI.Delivery.Truck;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;

public class TruckView extends JFrame {

    TruckModel truckModel;

    JLabel siteManagementLabel;
    JButton backButton;
    JButton refreshButton;
    JPanel displayPanel;

    JLabel userInput1Label;
    JLabel userInput2Label;
    JLabel userInput3Label;
    JLabel userInput4Label;
    JLabel userInput5Label;
    JLabel userInput6Label;
    JLabel userInput7Label;

    public TruckView(TruckModel truckModel) {
        this.truckModel = truckModel;
        this.setTitle("Show Truck");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        this.siteManagementLabel = new JLabel();
        this.siteManagementLabel.setText("Show Truck");
        this.siteManagementLabel.setVerticalAlignment(1);
        this.siteManagementLabel.setHorizontalAlignment(0);
        this.siteManagementLabel.setBounds(125, 0, 350, 200);
        this.siteManagementLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

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

        this.userInput1Label.setText("id: " + truckModel.id);
        this.userInput1Label.setBounds(200, 50, 350, 200);
        this.userInput2Label.setText("type: " + truckModel.type);
        this.userInput2Label.setBounds(200, 75, 300, 200);
        this.userInput3Label.setText("model: " + truckModel.model);
        this.userInput3Label.setBounds(200, 100, 300, 200);
        this.userInput4Label.setText("netWeight: " + truckModel.netWeight);
        this.userInput4Label.setBounds(200, 125, 300, 200);
        this.userInput5Label.setText("maxWeight: " + truckModel.maxWeight);
        this.userInput5Label.setBounds(200, 150, 300, 200);
        this.userInput6Label.setText("status: " + truckModel.status);
        this.userInput6Label.setBounds(200, 175, 300, 200);
        this.userInput7Label.setText("delivery date: ");
        this.userInput7Label.setBounds(200, 200, 100, 200);

        this.displayPanel = new JPanel();
        JTextArea displayTextArea = new JTextArea(4, 10);
        displayTextArea.setEditable(false);
        JScrollPane displayPane = new JScrollPane(displayTextArea);
        displayPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        StringBuilder s = new StringBuilder();
        for (LocalDate l : truckModel.deliveryDates) {
            s.append(l).append("\n");
        }
        displayTextArea.setText(s.toString());
        this.displayPanel.add(displayPane);
        this.displayPanel.setBounds(290, 200, 125, 120);
        this.displayPanel.setVisible(true);

        this.add(this.siteManagementLabel);
        this.add(this.backButton);
        this.add(this.refreshButton);
        this.add(displayPanel);

        this.add(this.userInput1Label);
        this.add(this.userInput2Label);
        this.add(this.userInput3Label);
        this.add(this.userInput4Label);
        this.add(this.userInput5Label);
        this.add(this.userInput6Label);
        this.add(this.userInput7Label);
    }

}
