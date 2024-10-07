package PresentationLayer.GUI.Delivery.ShowDelivery;

import PresentationLayer.GUI.Delivery.ShowStop.ShowStopModel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;

public class ShowDeliveryView extends JFrame {

    JLabel idLabel;
    JLabel dateLabel;
    JLabel timeLabel;
    JLabel sourceLabel;
    JLabel truckIdLabel;
    JLabel driverIdLabel;
    JLabel startingWeightLabel;
    JLabel statusLabel;
    JLabel maxWeightLabel;
    JLabel destinationsLabel;
    JLabel selectStopLabel;
    JLabel userInputErrorLabel;
    JPanel displayPanel;
    JButton showStopButton;
    JButton refreshButton;
    JButton backButton;
    JComboBox<Object> userInputComboBox;

    public ShowDeliveryView(ShowDeliveryModel showDeliveryModel, LinkedList<ShowStopModel> destinations) {
        this.setTitle("Delivery information");
        this.setSize(400, 525);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Delivery details:");
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(25, 25, 350, 200);
        titleLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        this.idLabel = new JLabel();
        this.idLabel.setBounds(100, 50, 550, 200);
        this.idLabel.setVerticalAlignment(JLabel.TOP);
        this.idLabel.setHorizontalAlignment(JLabel.LEFT);
        this.idLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.idLabel.setText("Delivery id:");

        this.dateLabel = new JLabel();
        this.dateLabel.setBounds(100, 75, 350, 200);
        this.dateLabel.setVerticalAlignment(JLabel.TOP);
        this.dateLabel.setHorizontalAlignment(JLabel.LEFT);
        this.dateLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.dateLabel.setText("Delivery depart date:");

        this.timeLabel = new JLabel();
        this.timeLabel.setBounds(100, 100, 300, 200);
        this.timeLabel.setVerticalAlignment(JLabel.TOP);
        this.timeLabel.setHorizontalAlignment(JLabel.LEFT);
        this.timeLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.timeLabel.setText("Delivery depart time:");

        this.sourceLabel = new JLabel();
        this.sourceLabel.setBounds(100, 125, 300, 200);
        this.sourceLabel.setVerticalAlignment(JLabel.TOP);
        this.sourceLabel.setHorizontalAlignment(JLabel.LEFT);
        this.sourceLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.sourceLabel.setText("Delivery source site:");

        this.truckIdLabel = new JLabel();
        this.truckIdLabel.setBounds(100, 150, 300, 200);
        this.truckIdLabel.setVerticalAlignment(JLabel.TOP);
        this.truckIdLabel.setHorizontalAlignment(JLabel.LEFT);
        this.truckIdLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.truckIdLabel.setText("Delivery truck id:");

        this.driverIdLabel = new JLabel();
        this.driverIdLabel.setBounds(100, 175, 300, 200);
        this.driverIdLabel.setVerticalAlignment(JLabel.TOP);
        this.driverIdLabel.setHorizontalAlignment(JLabel.LEFT);
        this.driverIdLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.driverIdLabel.setText("Delivery driver id:");

        this.startingWeightLabel = new JLabel();
        this.startingWeightLabel.setBounds(100, 200, 300, 200);
        this.startingWeightLabel.setVerticalAlignment(JLabel.TOP);
        this.startingWeightLabel.setHorizontalAlignment(JLabel.LEFT);
        this.startingWeightLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.startingWeightLabel.setText("Delivery starting weight:");

        this.statusLabel = new JLabel();
        this.statusLabel.setBounds(100, 225, 300, 200);
        this.statusLabel.setVerticalAlignment(JLabel.TOP);
        this.statusLabel.setHorizontalAlignment(JLabel.LEFT);
        this.statusLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.statusLabel.setText("Delivery status:");

        this.maxWeightLabel = new JLabel();
        this.maxWeightLabel.setBounds(100, 250, 300, 200);
        this.maxWeightLabel.setVerticalAlignment(JLabel.TOP);
        this.maxWeightLabel.setHorizontalAlignment(JLabel.LEFT);
        this.maxWeightLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.maxWeightLabel.setText("Delivery max weight:");

        this.destinationsLabel = new JLabel();
        this.destinationsLabel.setBounds(100, 275, 300, 200);
        this.destinationsLabel.setVerticalAlignment(JLabel.TOP);
        this.destinationsLabel.setHorizontalAlignment(JLabel.LEFT);
        this.destinationsLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.destinationsLabel.setText("Delivery destinations:");

        this.selectStopLabel = new JLabel();
        this.selectStopLabel.setBounds(100, 300, 300, 200);
        this.selectStopLabel.setVerticalAlignment(JLabel.TOP);
        this.selectStopLabel.setHorizontalAlignment(JLabel.LEFT);
        this.selectStopLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.selectStopLabel.setText("Select stop:");

        this.userInputErrorLabel = new JLabel();
        this.userInputErrorLabel.setBounds(50, 350, 300, 200);
        this.userInputErrorLabel.setVerticalAlignment(JLabel.TOP);
        this.userInputErrorLabel.setHorizontalAlignment(JLabel.LEFT);
        this.userInputErrorLabel.setFont(new Font("Sans_serif", Font.PLAIN, 14));
        this.userInputErrorLabel.setVisible(false);
        this.userInputErrorLabel.setForeground(Color.RED);
        this.userInputErrorLabel.setText("You must select an option");

        this.refreshButton = new JButton();
        this.refreshButton.setText("Refresh");
        this.refreshButton.setFocusable(false);
        this.refreshButton.setBounds(200, 375, 150, 50);

        this.showStopButton = new JButton();
        this.showStopButton.setText("Stop information");
        this.showStopButton.setFocusable(false);
        this.showStopButton.setBounds(50, 375, 150, 50);

        this.backButton = new JButton();
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBackground(new Color(255, 154, 162));
        this.backButton.setBounds(125, 425, 150, 50);

        this.displayPanel = new JPanel();
        JTextArea displayTextArea = new JTextArea(4, 10);
        displayTextArea.setEditable(false);
        JScrollPane displayPane = new JScrollPane(displayTextArea);
        displayPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        StringBuilder s = new StringBuilder();
        for (ShowStopModel sm : showDeliveryModel.destinations) {
            s.append(sm.destination.address).append("\n");
        }
        displayTextArea.setText(s.toString());
        this.displayPanel.add(displayPane);
        this.displayPanel.setBounds(250, 275, 125, 75);
        this.displayPanel.setVisible(true);

        this.userInputComboBox = new JComboBox<>();
        userInputComboBox.setBounds(100, 325, 100, 25);
        userInputComboBox.addItem("-");
        for (ShowStopModel stop : destinations)
            userInputComboBox.addItem(stop.destination.address);

        this.add(titleLabel);
        this.add(idLabel);
        this.add(dateLabel);
        this.add(timeLabel);
        this.add(sourceLabel);
        this.add(truckIdLabel);
        this.add(driverIdLabel);
        this.add(startingWeightLabel);
        this.add(statusLabel);
        this.add(maxWeightLabel);
        this.add(destinationsLabel);
        this.add(selectStopLabel);
        this.add(userInputErrorLabel);
        this.add(displayPanel);
        this.add(userInputComboBox);
        this.add(refreshButton);
        this.add(showStopButton);
        this.add(backButton);
    }
}
