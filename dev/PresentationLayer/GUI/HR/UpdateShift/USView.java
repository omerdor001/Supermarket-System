package PresentationLayer.GUI.HR.UpdateShift;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class USView extends BasicPage {
    public JComboBox comboBox,sComboBox,uComboBoxBS,uComboBoxDS;
    public JTextField year,month,day,newInfo;
    public JPanel p1,p2,p3,p4;
    public JButton submitButton;
    public USView(){
        super("Update Shift");
        createUIComponents();
    }
    private void createUIComponents(){
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        p4=new JPanel();
        submitButton=new JButton("Submit");
        JLabel label=new JLabel();
        label.setText("Choose shift type :");
        label.setSize(1000,100);
        label.setFont(new Font("Tahoma", Font.BOLD,20));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(100,30,500,100);
        this.add(label);
        JLabel sLabel=new JLabel();
        sLabel.setText("Choose shift :");
        sLabel.setSize(1000,100);
        sLabel.setFont(new Font("Tahoma", Font.BOLD,20));
        sLabel.setForeground(Color.BLACK);
        sLabel.setHorizontalAlignment(JLabel.CENTER);
        sLabel.setVerticalAlignment(JLabel.TOP);
        sLabel.setBounds(100,100,500,100);
        this.add(sLabel);

        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        p4.setLayout(null);
        p1.setBackground(new Color(121, 198, 252));
        p1.setBounds(200,200,300,100);
        p2.setBackground(new Color(121, 198, 252));
        p2.setBounds(200,200,300,100);
        p3.setBackground(new Color(121, 198, 252));
        p3.setBounds(200,300,400,200);
        p4.setBackground(new Color(121, 198, 252));
        p4.setBounds(200,300,400,200);

        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
        p4.setVisible(false);
        submitButton.setVisible(false);

        JLabel bs=new JLabel("Choose Detail :");
        bs.setFont(new Font("Tahoma", Font.PLAIN,14));
        bs.setBounds(0,0,150,30);
        JLabel ds=new JLabel("Choose Detail :");
        ds.setFont(new Font("Tahoma", Font.PLAIN,14));
        ds.setBounds(0,0,150,30);
        String[] detailsBS={"Date","Branch"};
        uComboBoxBS=new JComboBox<>(detailsBS);
        String[] detailsDS={"Date"};
        uComboBoxDS=new JComboBox<>(detailsDS);
        uComboBoxBS.setBounds(0,30,150,30);
        uComboBoxBS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) uComboBoxBS.getSelectedItem();
                if(s.equals("Date")){
                    p3.setVisible(true);
                    p4.setVisible(false);
                    submitButton.setVisible(true);
                }
                else{
                    p4.setVisible(true);
                    p3.setVisible(false);
                    submitButton.setVisible(true);
                }
            }
        });

        uComboBoxDS.setBounds(0,30,150,30);
        uComboBoxDS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) uComboBoxDS.getSelectedItem();
                if(s.equals("Date")){
                    p3.setVisible(true);
                    p4.setVisible(false);
                    submitButton.setVisible(true);
                }
            }
        });

        p1.add(bs);
        p1.add(uComboBoxBS);
        p2.add(ds);
        p2.add(uComboBoxDS);

        JLabel date=new JLabel();
        date.setText("Date of shift :");
        date.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        date.setBounds(0,0,150,50);

        JLabel yearLabel=new JLabel();
        yearLabel.setText("Year :");
        yearLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        yearLabel.setBounds(0,30,70,50);

        year=new JTextField();
        year.setPreferredSize(new Dimension(30,20));
        year.setBounds(50,45,30,20);

        JLabel monthLabel=new JLabel();
        monthLabel.setText("Month :");
        monthLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        monthLabel.setBounds(100,30,70,50);

        month=new JTextField();
        month.setPreferredSize(new Dimension(30,20));
        month.setBounds(150,45,50,20);

        JLabel dayLabel=new JLabel();
        dayLabel.setText("Day :");
        dayLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        dayLabel.setBounds(210,30,70,50);

        day=new JTextField();
        day.setPreferredSize(new Dimension(30,20));
        day.setBounds(260,45,50,20);

        p3.add(date);
        p3.add(yearLabel);
        p3.add(year);
        p3.add(monthLabel);
        p3.add(month);
        p3.add(dayLabel);
        p3.add(day);

        JLabel infoLabel=new JLabel();
        infoLabel.setText("New Info :");
        infoLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        infoLabel.setBounds(0,0,150,50);

        newInfo=new JTextField();
        newInfo.setPreferredSize(new Dimension(150,20));
        newInfo.setBounds(0,40,150,20);

        submitButton.setBackground(Color.WHITE);
        submitButton.setBounds(500,500,80,40);

        p4.add(infoLabel);
        p4.add(newInfo);

        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
        p4.setVisible(false);
        submitButton.setVisible(false);

        String[] types={"Branch Shift","Driver Shift"};
        comboBox=new JComboBox<>(types);
        comboBox.setSize(70,50);
        comboBox.setBounds(300,60,105,35);
        comboBox.setVisible(true);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox.getSelectedItem();
                switch (s) {
                    case "Branch Shift":
                        p1.setVisible(true);
                        p2.setVisible(false);
                        p3.setVisible(false);
                        submitButton.setVisible(false);
                        break;
                    case "Driver Shift":
                        p1.setVisible(false);
                        p2.setVisible(true);
                        p3.setVisible(false);
                        submitButton.setVisible(false);
                        break;
                }
            }
        });

        String[] shifts={};
        sComboBox=new JComboBox<>(shifts);
        sComboBox.setSize(70,50);
        sComboBox.setBounds(300,130,105,35);
        sComboBox.setVisible(true);

        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(submitButton);
        this.add(comboBox);
        this.add(sComboBox);

    }

    public JFrame getFrame(){
        return this;
    }
}
