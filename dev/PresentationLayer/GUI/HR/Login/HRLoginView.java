package PresentationLayer.GUI.HR.Login;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import java.awt.*;

public class HRLoginView extends BasicPage {
    private static HRLoginView instance;
    public JButton button;
    public JTextField idTB,passwordTB;
    public HRLoginView(){
        super("Login");
        createUIComponents();
        setVisible(true);
    }

    public static HRLoginView getInstance() {
        if (instance == null)
            instance = new HRLoginView();
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null)
            instance = null;
    }

    public JFrame getFrame(){
        return this;
    }



    private void createUIComponents(){
        JLabel label = new JLabel();
        label.setText("Login");
        label.setSize(500, 500);
        label.setFont(new Font("Tahoma" ,Font.BOLD, 40));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(50, 100, 500, 100);
        this.add(label);
        //ID
        JLabel id=new JLabel();
        id.setText("employee ID :");
        id.setFont(new Font("Tahoma",Font.PLAIN,18));
        id.setBounds(150,150,150,50);
        idTB=new JTextField(26);
        idTB.setPreferredSize(new Dimension(100,20));
        idTB.setFont(new Font("Tahoma",Font.PLAIN,16));
        idTB.setBounds(300,165,150,20);

        //Password
        JLabel password=new JLabel();
        password.setText("Password :");
        password.setFont(new Font("Tahoma",Font.PLAIN,18));
        password.setBounds(180,180,100,50);
        passwordTB=new JTextField(26);
        passwordTB.setPreferredSize(new Dimension(150,20));
        passwordTB.setFont(new Font("Tahoma",Font.PLAIN,16));
        passwordTB.setBounds(280,195,150,20);

        //Submit
        button=new JButton("Login");
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Tahoma",Font.PLAIN,16));
        button.setBounds(260,250,80,40);

        this.add(id);
        this.add(idTB);
        this.add(password);
        this.add(passwordTB);
        this.add(button);
    }

}
