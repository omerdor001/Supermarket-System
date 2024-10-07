package PresentationLayer.GUI.Delivery.SiteManagement;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SiteManagementView extends JFrame {
    JLabel siteManagementLabel;
    JLabel selectActionLabel;
    JButton backButton;
    JButton addSiteButton;
    JButton removeSiteButton;
    JButton editContactButton;
    JButton editPhoneNumberButton;

    JTextField userInput1Text;
    JTextField userInput2Text;
    JTextField userInput3Text;
    JTextField userInput4Text;
    JTextField userInput5Text;
    JLabel userInput1Label;
    JLabel userInput2Label;
    JLabel userInput3Label;
    JLabel userInput4Label;
    JLabel userInput5Label;
    JLabel userInput6Label;
    JLabel userInput7Label;
    JLabel userInputMainLabel;
    JLabel userInputError1Label;
    JLabel userInputError2Label;
    JLabel userInputError3Label;
    JLabel userInputError4Label;
    JLabel userInputError5Label;
    JLabel userInputError6Label;
    JLabel userInputError7Label;
    JButton userInputBackButton;
    JComboBox<String> userInputComboBox1;
    JComboBox<String> userInputComboBox2;

    JButton addSiteConfirmButton;
    JButton removeSiteConfirmButton;
    JButton editContactConfirmButton;
    JButton editPhoneNumberConfirmButton;

    public SiteManagementView() {
        this.setTitle("Site management menu");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(121, 198, 252));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"))); //avoidIt
        this.setIconImage(imageIcon.getImage());

        this.siteManagementLabel = new JLabel();
        this.siteManagementLabel.setText("Welcome to the site management menu");
        this.siteManagementLabel.setVerticalAlignment(1);
        this.siteManagementLabel.setHorizontalAlignment(0);
        this.siteManagementLabel.setBounds(125, 0, 350, 200);
        this.siteManagementLabel.setFont(new Font("Sans_serif", Font.PLAIN, 18));

        this.selectActionLabel = new JLabel();
        this.selectActionLabel.setText("Please choose the desired action:");
        this.selectActionLabel.setVerticalAlignment(1);
        this.selectActionLabel.setHorizontalAlignment(0);
        this.selectActionLabel.setBounds(125, 25, 350, 200);
        this.selectActionLabel.setFont(new Font("Sans_serif", Font.PLAIN, 16));

        this.backButton = new JButton();
        this.backButton.setText("Back");
        this.backButton.setFocusable(false);
        this.backButton.setBounds(200, 275, 200, 50);
        this.backButton.setBackground(new Color(255, 154, 162));

        this.addSiteButton = new JButton();
        this.addSiteButton.setText("Add site");
        this.addSiteButton.setFocusable(false);
        this.addSiteButton.setBounds(200, 75, 200, 50);

        this.removeSiteButton = new JButton();
        this.removeSiteButton.setText("Remove site");
        this.removeSiteButton.setFocusable(false);
        this.removeSiteButton.setBounds(200, 125, 200, 50);

        this.editContactButton = new JButton();
        this.editContactButton.setText("Edit site contact name");
        this.editContactButton.setFocusable(false);
        this.editContactButton.setBounds(200, 175, 200, 50);

        this.editPhoneNumberButton = new JButton();
        this.editPhoneNumberButton.setText("Edit site phone number");
        this.editPhoneNumberButton.setFocusable(false);
        this.editPhoneNumberButton.setBounds(200, 225, 200, 50);

        this.userInput1Text = new JTextField();
        this.userInput1Text.setVisible(false);

        this.userInput2Text = new JTextField();
        this.userInput2Text.setVisible(false);

        this.userInput3Text = new JTextField();
        this.userInput3Text.setVisible(false);

        this.userInput4Text = new JTextField();
        this.userInput4Text.setVisible(false);

        this.userInput5Text = new JTextField();
        this.userInput5Text.setVisible(false);

        this.userInput1Label = new JLabel();
        this.userInput1Label.setVerticalAlignment(1);
        this.userInput1Label.setHorizontalAlignment(2);
        this.userInput1Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput1Label.setVisible(false);

        this.userInput2Label = new JLabel();
        this.userInput2Label.setVerticalAlignment(1);
        this.userInput2Label.setHorizontalAlignment(2);
        this.userInput2Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput2Label.setVisible(false);

        this.userInput3Label = new JLabel();
        this.userInput3Label.setVerticalAlignment(1);
        this.userInput3Label.setHorizontalAlignment(2);
        this.userInput3Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput3Label.setVisible(false);

        this.userInput4Label = new JLabel();
        this.userInput4Label.setVerticalAlignment(1);
        this.userInput4Label.setHorizontalAlignment(2);
        this.userInput4Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput4Label.setVisible(false);

        this.userInput5Label = new JLabel();
        this.userInput5Label.setVerticalAlignment(1);
        this.userInput5Label.setHorizontalAlignment(2);
        this.userInput5Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput5Label.setVisible(false);

        this.userInput6Label = new JLabel();
        this.userInput6Label.setVerticalAlignment(1);
        this.userInput6Label.setHorizontalAlignment(2);
        this.userInput6Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput6Label.setVisible(false);

        this.userInput7Label = new JLabel();
        this.userInput7Label.setVerticalAlignment(1);
        this.userInput7Label.setHorizontalAlignment(2);
        this.userInput7Label.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInput7Label.setVisible(false);

        this.userInputMainLabel = new JLabel();
        this.userInputMainLabel.setVerticalAlignment(1);
        this.userInputMainLabel.setHorizontalAlignment(2);
        this.userInputMainLabel.setFont(new Font("Sans_serif", Font.PLAIN, 15));
        this.userInputMainLabel.setVisible(false);

        this.userInputError1Label = new JLabel();
        this.userInputError1Label.setVerticalAlignment(1);
        this.userInputError1Label.setHorizontalAlignment(2);
        this.userInputError1Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError1Label.setForeground(new Color(204, 0, 0));
        this.userInputError1Label.setVisible(false);

        this.userInputError2Label = new JLabel();
        this.userInputError2Label.setVerticalAlignment(1);
        this.userInputError2Label.setHorizontalAlignment(2);
        this.userInputError2Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError2Label.setForeground(new Color(204, 0, 0));
        this.userInputError2Label.setVisible(false);

        this.userInputError3Label = new JLabel();
        this.userInputError3Label.setVerticalAlignment(1);
        this.userInputError3Label.setHorizontalAlignment(2);
        this.userInputError3Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError3Label.setForeground(new Color(204, 0, 0));
        this.userInputError3Label.setVisible(false);

        this.userInputError4Label = new JLabel();
        this.userInputError4Label.setVerticalAlignment(1);
        this.userInputError4Label.setHorizontalAlignment(2);
        this.userInputError4Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError4Label.setForeground(new Color(204, 0, 0));
        this.userInputError4Label.setVisible(false);

        this.userInputError5Label = new JLabel();
        this.userInputError5Label.setVerticalAlignment(1);
        this.userInputError5Label.setHorizontalAlignment(2);
        this.userInputError5Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError5Label.setForeground(new Color(204, 0, 0));
        this.userInputError5Label.setVisible(false);

        this.userInputError6Label = new JLabel();
        this.userInputError6Label.setVerticalAlignment(1);
        this.userInputError6Label.setHorizontalAlignment(2);
        this.userInputError6Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError6Label.setForeground(new Color(204, 0, 0));
        this.userInputError6Label.setVisible(false);

        this.userInputError7Label = new JLabel();
        this.userInputError7Label.setVerticalAlignment(1);
        this.userInputError7Label.setHorizontalAlignment(2);
        this.userInputError7Label.setFont(new Font("serif", Font.PLAIN, 12));
        this.userInputError7Label.setForeground(new Color(204, 0, 0));
        this.userInputError7Label.setVisible(false);

        this.userInputBackButton = new JButton();
        this.userInputBackButton.setText("Back");
        this.userInputBackButton.setFocusable(false);
        this.userInputBackButton.setBackground(new Color(255, 154, 162));
        this.userInputBackButton.setVisible(false);

        this.addSiteConfirmButton = new JButton();
        this.addSiteConfirmButton.setText("Confirm");
        this.addSiteConfirmButton.setFocusable(false);
        this.addSiteConfirmButton.setBounds(300, 250, 200, 50);
        this.addSiteConfirmButton.setVisible(false);

        this.editContactConfirmButton = new JButton();
        this.editContactConfirmButton.setText("Confirm");
        this.editContactConfirmButton.setFocusable(false);
        this.editContactConfirmButton.setBounds(300, 150, 200, 50);
        this.editContactConfirmButton.setVisible(false);

        this.editPhoneNumberConfirmButton = new JButton();
        this.editPhoneNumberConfirmButton.setText("Confirm");
        this.editPhoneNumberConfirmButton.setFocusable(false);
        this.editPhoneNumberConfirmButton.setBounds(300, 150, 200, 50);
        this.editPhoneNumberConfirmButton.setVisible(false);

        String[] s1 = new String[]{"-", "north", "south", "center", "general"};
        this.userInputComboBox1 = new JComboBox<>(s1);

        String[] s2 = new String[]{"-", "store", "supplier", "center"};
        this.userInputComboBox2 = new JComboBox<>(s2);

        this.removeSiteConfirmButton = new JButton();
        this.removeSiteConfirmButton.setText("Confirm");
        this.removeSiteConfirmButton.setFocusable(false);
        this.removeSiteConfirmButton.setBounds(300, 150, 200, 50);
        this.removeSiteConfirmButton.setVisible(false);

        this.add(this.siteManagementLabel);
        this.add(this.selectActionLabel);
        this.add(this.backButton);
        this.add(this.addSiteButton);
        this.add(this.removeSiteButton);
        this.add(this.editContactButton);
        this.add(this.editPhoneNumberButton);

        this.add(this.userInput1Text);
        this.add(this.userInput5Text);
        this.add(this.userInput3Text);
        this.add(this.userInput4Text);
        this.add(this.userInput2Text);
        this.add(this.userInput4Label);
        this.add(this.userInput1Label);
        this.add(this.userInput2Label);
        this.add(this.userInput3Label);
        this.add(this.userInput5Label);
        this.add(this.userInput6Label);
        this.add(this.userInput7Label);
        this.add(this.userInputMainLabel);
        this.add(this.userInputError1Label);
        this.add(this.userInputError2Label);
        this.add(this.userInputError3Label);
        this.add(this.userInputError4Label);
        this.add(this.userInputError5Label);
        this.add(this.userInputError6Label);
        this.add(this.userInputError7Label);
        this.add(this.userInputBackButton);
        this.add(this.userInputComboBox1);
        this.add(this.userInputComboBox2);

        this.add(this.addSiteConfirmButton);
        this.add(this.removeSiteConfirmButton);
        this.add(this.editContactConfirmButton);
        this.add(this.editPhoneNumberConfirmButton);
    }
}
