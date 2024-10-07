package PresentationLayer.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainMenuView extends JFrame {

    JButton deliveryButton;
    JButton hrButton;
    JButton deleteDataButton;
    JButton generateDataButton;

    public MainMenuView() {
        this.setTitle("SuperLi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 350);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));
        this.setVisible(true);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg")));
        this.setIconImage(imageIcon.getImage());

        JLabel mainMenuLabel = new JLabel();
        mainMenuLabel.setText("Welcome to SuperLi");
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setBounds(25, 0, 350, 200);
        mainMenuLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        JLabel selectActionLabel = new JLabel();
        selectActionLabel.setText("Please choose the desired module:");
        selectActionLabel.setVerticalAlignment(JLabel.TOP);
        selectActionLabel.setHorizontalAlignment(JLabel.CENTER);
        selectActionLabel.setBounds(50, 25, 300, 200);
        selectActionLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        JLabel debugLabel = new JLabel();
        debugLabel.setText("Debug options:");
        debugLabel.setVerticalAlignment(JLabel.TOP);
        debugLabel.setHorizontalAlignment(JLabel.CENTER);
        debugLabel.setBounds(50, 150, 300, 200);
        debugLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        deliveryButton = new JButton();
        deliveryButton.setText("Delivery module");
        deliveryButton.setFocusable(false);
        deliveryButton.setBounds(50, 50, 300, 50);

        hrButton = new JButton();
        hrButton.setText("HR module");
        hrButton.setFocusable(false);
        hrButton.setBounds(50, 100, 300, 50);

        deleteDataButton = new JButton();
        deleteDataButton.setText("Delete all data");
        deleteDataButton.setFocusable(false);
        deleteDataButton.setBounds(50, 175, 300, 50);

        generateDataButton = new JButton();
        generateDataButton.setText("Generate data");
        generateDataButton.setFocusable(false);
        generateDataButton.setBounds(50, 225, 300, 50);

        ImageIcon store = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/Store.png")));
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(400,65,200,200);
        imageLabel.setIcon(store);

        this.add(mainMenuLabel);
        this.add(selectActionLabel);
        this.add(debugLabel);
        this.add(imageLabel);
        this.add(deliveryButton);
        this.add(hrButton);
        this.add(deleteDataButton);
        this.add(generateDataButton);
    }

    public static void main(String[] args) {
        new MainMenuController();
    }
}
