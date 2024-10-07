package PresentationLayer.GUI.HR;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HRMain extends BasicPage{

    public HRMain() {
        super("Main Page");
        createUIComponents();
        setVisible(true);
    }

    private void createUIComponents() {
        JLabel label = new JLabel();
        label.setText("Welcome To Human Resources Of SuperLee !");
        label.setSize(500, 500);
        label.setFont(new Font("Tahoma", Font.BOLD, 30));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(48, 100, 1000, 100);
        ImageIcon imageIcon=new ImageIcon(getClass().getResource("/PresentationLayer/GUI/Images/HR.png"));
        JLabel photo=new JLabel(imageIcon);
        photo.setBounds(320,150,400,400);
        this.add(label);
        this.add(photo);

    }

    public JFrame getFrame(){
        return this;
    }

}









