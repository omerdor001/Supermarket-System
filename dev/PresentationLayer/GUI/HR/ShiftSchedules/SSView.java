package PresentationLayer.GUI.HR.ShiftSchedules;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SSView extends BasicPage {
    public JButton drivers,toRoles,assign,s1,s2,s3;
    public JTextField driverId,branchEmployeeId,branchEmployeeIdCopy;
    public JComboBox comboBoxDS,comboBoxBS,comboBoxBSCopy,comboBoxRoles;
    private JPanel p1,p2,p3;
    public SSView(){
        super("Scheduling shift");
        createUIComponents();
    }

    public void createUIComponents(){
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();

        p1.setBackground(new Color(121, 198, 252));
        p1.setBounds(180,170,150,150);
        p2.setBackground(new Color(121, 198, 252));
        p2.setBounds(180,170,150,150);
        p3.setBackground(new Color(121, 198, 252));
        p3.setBounds(180,170,150,300);

        //Schedule Drivers
        //drivers
        JLabel driver=new JLabel("Choose driver :");
        driver.setBounds(10,170,150,30);
        driver.setFont(new Font("Tahoma", Font.PLAIN,12));
        driverId =new JTextField();
        driverId.setPreferredSize(new Dimension(100,20));
        driverId.setBounds(160,170,100,20);

        //shifts
        JLabel driverShift=new JLabel("Choose shift :");
        driverShift.setBounds(10,190,150,30);
        driverShift.setFont(new Font("Tahoma", Font.PLAIN,12));
        String[] driverShifts={};
        comboBoxDS=new JComboBox<>(driverShifts);
        comboBoxDS.setSize(150,30);
        comboBoxDS.setBounds(110,190,150,30);

        s1=new JButton("Submit");
        s1.setBackground(Color.WHITE);
        s1.setBounds(170,230,140,40);

        p1.add(driver);
        p1.add(driverId);
        p1.add(driverShift);
        p1.add(comboBoxDS);
        p1.add(s1);
        //

        //Add schedule of branch employee
        //branchEmployees
        JLabel branchEmployee=new JLabel("Choose branch employee :");
        branchEmployee.setBounds(10,170,200,30);
        branchEmployee.setFont(new Font("Tahoma", Font.PLAIN,12));
        branchEmployeeId =new JTextField();
        branchEmployeeId.setPreferredSize(new Dimension(100,20));
        branchEmployeeId.setBounds(160,170,100,20);

        //shifts
        JLabel branchShift=new JLabel("Choose shift :");
        branchShift.setFont(new Font("Tahoma", Font.PLAIN,12));
        branchShift.setBounds(10,190,150,30);
        String[] branchShifts={};
        comboBoxBS=new JComboBox<>(branchShifts);
        comboBoxBS.setSize(150,30);
        comboBoxBS.setBounds(110,190,150,30);

        s2=new JButton("Submit");
        s2.setBackground(Color.WHITE);
        s2.setBounds(170,230,140,40);

        p2.add(branchEmployee);
        p2.add(branchEmployeeId);
        p2.add(branchShift);
        p2.add(comboBoxBS);
        p2.add(s2);
        //

        //Schedule branch employee to role
        //branchEmployees
        JLabel branchEmployeeS=new JLabel("Choose branch employee :");
        branchEmployeeS.setFont(new Font("Tahoma", Font.PLAIN,12));
        branchEmployeeS.setBounds(10,170,150,30);
        branchEmployeeIdCopy=new JTextField();
        branchEmployeeIdCopy.setPreferredSize(new Dimension(100,20));
        branchEmployeeIdCopy.setBounds(160,170,100,20);

        //shifts
        JLabel branchShiftCopy=new JLabel("Choose shift :");
        branchShiftCopy.setBounds(10,190,150,30);
        branchShiftCopy.setFont(new Font("Tahoma", Font.PLAIN,12));
        String[] branchShiftsCopy={};
        comboBoxBSCopy=new JComboBox<>(branchShiftsCopy);
        comboBoxBSCopy.setSize(150,30);
        comboBoxBSCopy.setBounds(160,190,150,30);

        //role
        JLabel role=new JLabel("Choose role :");
        role.setBounds(0,170,150,30);
        role.setFont(new Font("Tahoma", Font.PLAIN,12));
        String[] roles={"Shift Manager","Store Keeper","Cashier","General Employee","Guard","Cleaner","Merchandiser"};
        comboBoxRoles=new JComboBox<>(roles);
        comboBoxRoles.setSize(150,30);
        comboBoxRoles.setBounds(130,170,150,30);

        s3=new JButton("Submit");
        s3.setBackground(Color.WHITE);
        s3.setBounds(170,230,140,40);

        p3.add(branchShiftCopy);
        p3.add(comboBoxBSCopy);
        p3.add(branchEmployeeS);
        p3.add(branchEmployeeIdCopy);
        p3.add(role);
        p3.add(comboBoxRoles);
        p3.add(s3);
        //


        this.add(p1);
        this.add(p2);
        this.add(p3);

        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);


        //drivers
        drivers=new JButton("Schedule Drivers");
        drivers.setBackground(Color.WHITE);
        drivers.setBounds(200,100,140,40);
        drivers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p1.setVisible(true);
                p2.setVisible(false);
                p3.setVisible(false);
            }
        });
        this.add(drivers);

        //assign
        assign=new JButton("Add Branch-Employee To Shift");
        assign.setBackground(Color.WHITE);
        assign.setBounds(350,100,220,40);
        assign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p1.setVisible(false);
                p2.setVisible(true);
                p3.setVisible(false);
            }
        });
        this.add(assign);

        //toRoles
        toRoles=new JButton("Schedule Branch-Employee To Shift");
        toRoles.setBackground(Color.WHITE);
        toRoles.setBounds(585,100,250,40);
        toRoles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p1.setVisible(false);
                p2.setVisible(false);
                p3.setVisible(true);
            }
        });
        this.add(toRoles);


    }

    public JFrame getFrame(){
        return this;
    }

}
