package PresentationLayer.GUI.HR.ChoosePage;

import javax.swing.*;
import java.awt.*;

public class ChooseRoleView extends JFrame {
    JButton hRManager,employee,back;
    JTextField idTB;
    public ChooseRoleView(){
        createUIComponents();
        setVisible(true);
    }

    private void createUIComponents(){
        JLabel id = new JLabel();
        id.setText("Enter ID :");
        id.setSize(500, 500);
        id.setFont(new Font("Tahoma", Font.BOLD, 26));
        id.setForeground(Color.BLACK);
        id.setHorizontalAlignment(JLabel.CENTER);
        id.setVerticalAlignment(JLabel.TOP);
        id.setBounds(250, 50, 500, 100);
        idTB=new JTextField();
        idTB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        idTB.setPreferredSize(new Dimension(100,20));
        idTB.setBounds(380,100,200,30);
        JLabel label = new JLabel();
        label.setText("Choose page :");
        label.setSize(500, 500);
        label.setFont(new Font("Tahoma", Font.BOLD, 26));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(250, 150, 500, 100);
        this.add(id);
        this.add(idTB);
        this.add(label);
        hRManager=new JButton("HR Manager Page");
        employee=new JButton("Employee Page");
        hRManager.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        employee.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        hRManager.setBounds(310,200,150,40);
        employee.setBounds(510,200,150,40);
        back=new JButton("Back");
        back.setBackground(new Color(255, 154, 162));
        back.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        back.setBounds(700,400,80,40);
        this.setTitle("ChoosePage");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"));
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(new Color(121, 198, 252));
        this.setSize(1000, 1000);
        this.add(hRManager);
        this.add(employee);
        this.add(back);
        this.setLayout(null);
    }
}
