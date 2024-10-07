package PresentationLayer.GUI.Delivery.DeliveryMainMenu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DeliveryMainView extends JFrame {

    JButton deliveryManagementButton;
    JButton resourceManagementButton;
    JButton backButton;

    public DeliveryMainView() {
        this.setTitle("Delivery module");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        JLabel mainMenuLabel = new JLabel();
        mainMenuLabel.setText("Welcome to the delivery module main menu");
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setBounds(25, 25, 350, 200);
        mainMenuLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        JLabel selectActionLabel = new JLabel();
        selectActionLabel.setText("Please choose the desired action:");
        selectActionLabel.setVerticalAlignment(JLabel.TOP);
        selectActionLabel.setHorizontalAlignment(JLabel.CENTER);
        selectActionLabel.setBounds(50, 75, 300, 200);
        selectActionLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        deliveryManagementButton = new JButton();
        deliveryManagementButton.setText("Delivery management");
        deliveryManagementButton.setFocusable(false);
        deliveryManagementButton.setBounds(50, 125, 300, 50);

        resourceManagementButton = new JButton();
        resourceManagementButton.setText("Resource management");
        resourceManagementButton.setFocusable(false);
        resourceManagementButton.setBounds(50, 175, 300, 50);

        backButton = new JButton();
        backButton.setText("Back");
        backButton.setFocusable(false);
        backButton.setVisible(false);
        backButton.setBackground(new Color(255, 154, 162));
        backButton.setBounds(50, 225, 300, 50);

        ImageIcon store = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/Delivery.png")));
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(360, 0, 512, 412);
        imageLabel.setIcon(store);

        this.add(mainMenuLabel);
        this.add(selectActionLabel);
        this.add(imageLabel);
        this.add(deliveryManagementButton);
        this.add(resourceManagementButton);
        this.add(backButton);
    }
}
