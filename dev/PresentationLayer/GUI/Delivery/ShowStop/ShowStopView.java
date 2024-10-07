package PresentationLayer.GUI.Delivery.ShowStop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ShowStopView extends JFrame {

    JLabel idLabel;
    JLabel deliveryIdLabel;
    JLabel destinationLabel;
    JLabel statusLabel;
    JLabel arriveTimeLabel;

    JButton loadItemsButton;
    JButton unloadItemsButton;
    JButton refreshButton;
    JButton backButton;

    public ShowStopView() {
        this.setTitle("Stop information");
        this.setSize(400, 350);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Stop details:");
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(25, 25, 350, 200);
        titleLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        this.idLabel = new JLabel();
        this.idLabel.setBounds(100, 50, 550, 200);
        this.idLabel.setVerticalAlignment(JLabel.TOP);
        this.idLabel.setHorizontalAlignment(JLabel.LEFT);
        this.idLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.idLabel.setText("Stop id:");

        this.deliveryIdLabel = new JLabel();
        this.deliveryIdLabel.setBounds(100, 75, 350, 200);
        this.deliveryIdLabel.setVerticalAlignment(JLabel.TOP);
        this.deliveryIdLabel.setHorizontalAlignment(JLabel.LEFT);
        this.deliveryIdLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.deliveryIdLabel.setText("Stop delivery id:");

        this.destinationLabel = new JLabel();
        this.destinationLabel.setBounds(100, 100, 300, 200);
        this.destinationLabel.setVerticalAlignment(JLabel.TOP);
        this.destinationLabel.setHorizontalAlignment(JLabel.LEFT);
        this.destinationLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.destinationLabel.setText("Stop address:");

        this.statusLabel = new JLabel();
        this.statusLabel.setBounds(100, 125, 300, 200);
        this.statusLabel.setVerticalAlignment(JLabel.TOP);
        this.statusLabel.setHorizontalAlignment(JLabel.LEFT);
        this.statusLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.statusLabel.setText("Stop status:");

        this.arriveTimeLabel = new JLabel();
        this.arriveTimeLabel.setBounds(100, 150, 300, 200);
        this.arriveTimeLabel.setVerticalAlignment(JLabel.TOP);
        this.arriveTimeLabel.setHorizontalAlignment(JLabel.LEFT);
        this.arriveTimeLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.arriveTimeLabel.setText("Stop arrive time:");

        this.refreshButton = new JButton();
        this.refreshButton.setText("Refresh");
        this.refreshButton.setFocusable(false);
        this.refreshButton.setBounds(200, 250, 150, 50);

        this.loadItemsButton = new JButton();
        this.loadItemsButton.setText("Show loading items");
        this.loadItemsButton.setFocusable(false);
        this.loadItemsButton.setBounds(50, 200, 150, 50);

        this.unloadItemsButton = new JButton();
        this.unloadItemsButton.setText("Show unload items");
        this.unloadItemsButton.setFocusable(false);
        this.unloadItemsButton.setBounds(200, 200, 150, 50);

        this.backButton = new JButton();
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBackground(new Color(255, 154, 162));
        this.backButton.setBounds(50, 250, 150, 50);

        this.add(titleLabel);
        this.add(idLabel);
        this.add(deliveryIdLabel);
        this.add(destinationLabel);
        this.add(statusLabel);
        this.add(arriveTimeLabel);
        this.add(loadItemsButton);
        this.add(unloadItemsButton);
        this.add(refreshButton);
        this.add(backButton);
    }
}
