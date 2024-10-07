package PresentationLayer.GUI.HR;

import PresentationLayer.GUI.HR.BranchesInformation.BIController;
import PresentationLayer.GUI.HR.ChoosePage.ChooseRoleController;
import PresentationLayer.GUI.HR.ConstraintManagement.CMController;
import PresentationLayer.GUI.HR.CreateShift.CSController;
import PresentationLayer.GUI.HR.EmployeeInformationHR.EIController;
import PresentationLayer.GUI.HR.EmployeesInformation.EmployeeInformationController;
import PresentationLayer.GUI.HR.RegisterEmployee.REController;
import PresentationLayer.GUI.HR.ShiftInformation.SIController;
import PresentationLayer.GUI.HR.ShiftSchedules.SSController;
import PresentationLayer.GUI.HR.UpdateEmployeeDetails.UEController;
import PresentationLayer.GUI.HR.UpdateShift.USController;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicPage extends JFrame implements ActionListener {
    public JButton reButton,ueButton,csButton,ssButton,siButton,biButton,logoutButton,cmButton,eipButton,eiButton,usButton;
    SystemFacade systemFacade;
    public String employeeId;

    public BasicPage(String name){
        createBasicPage(name);
        systemFacade=SystemFacade.getInstance();
    }

    private void createBasicPage(String name){
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/PresentationLayer/GUI/Images/shopping-cart-icon-illustration-free-vector.jpg"));
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(new Color(121, 198, 252));
        this.setSize(1000, 1000);
        this.setLayout(null);
    }

    public void createMenu(String id){
        reButton=new JButton("Register Employee");
        reButton.setBounds(0,100,180,30);
        reButton.setBackground(Color.WHITE);
        this.add(reButton);
        ueButton=new JButton("Update details employee");
        ueButton.setBounds(0,130,180,30);
        ueButton.setBackground(Color.WHITE);
        this.add(ueButton);
        csButton=new JButton("Create new shift");
        csButton.setBounds(0,160,180,30);
        csButton.setBackground(Color.WHITE);
        this.add(csButton);
        usButton=new JButton("Update a shift");
        usButton.setBounds(0,190,180,30);
        usButton.setBackground(Color.WHITE);
        this.add(usButton);
        ssButton=new JButton("Schedule to shift");
        ssButton.setBounds(0,220,180,30);
        ssButton.setBackground(Color.WHITE);
        this.add(ssButton);
        eiButton=new JButton("Employees information");
        eiButton.setBounds(0,250,180,30);
        eiButton.setBackground(Color.WHITE);
        this.add(eiButton);
        siButton=new JButton("Shifts information");
        siButton.setBounds(0,280,180,30);
        siButton.setBackground(Color.WHITE);
        this.add(siButton);
        biButton=new JButton("Branches information");
        biButton.setBounds(0,310,180,30);
        biButton.setBackground(Color.WHITE);
        this.add(biButton);
        logoutButton=new JButton("Logout");
        logoutButton.setBounds(0,340,180,30);
        logoutButton.setBackground(Color.WHITE);
        this.add(logoutButton);
        reButton.addActionListener(this);
        ueButton.addActionListener(this);
        csButton.addActionListener(this);
        ssButton.addActionListener(this);
        siButton.addActionListener(this);
        biButton.addActionListener(this);
        eiButton.addActionListener(this);
        usButton.addActionListener(this);
        logoutButton.addActionListener(this);
        employeeId=id;
    }

    public void createMenuEmployee(String id){
        cmButton=new JButton("Constraints page");
        cmButton.setBounds(0,100,180,30);
        cmButton.setBackground(Color.WHITE);
        this.add(cmButton);
        eipButton=new JButton("Employee Information");
        eipButton.setBounds(0,130,180,30);
        eipButton.setBackground(Color.WHITE);
        this.add(eipButton);
        logoutButton=new JButton("Logout");
        logoutButton.setBounds(0,160,180,30);
        logoutButton.setBackground(Color.WHITE);
        this.add(logoutButton);
        cmButton.addActionListener(this);
        eipButton.addActionListener(this);
        logoutButton.addActionListener(this);
        employeeId=id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==reButton){
            new REController(employeeId);
            dispose();
        }
        if(e.getSource()==ueButton){
            new UEController(employeeId);
            dispose();
        }
        if(e.getSource()==csButton){
            new CSController(employeeId);
            dispose();
        }
        if(e.getSource()==ssButton){
            new SSController(employeeId);
            dispose();
        }
        if(e.getSource()==eiButton){
            new EIController(employeeId);
            dispose();
        }
        if(e.getSource()==siButton){
            new SIController(employeeId);
            dispose();
        }
        if(e.getSource()==biButton){
            new BIController(employeeId);
            dispose();
        }
        if(e.getSource()==logoutButton){
            if (systemFacade.isEmployeeExists(employeeId).equals("false")) {
                new ChooseRoleController();
            } else systemFacade.logout(employeeId);
            dispose();
        }
        if(e.getSource()==cmButton){
            new CMController(employeeId);
            dispose();
        }
        if(e.getSource()==eipButton){
            new EmployeeInformationController(employeeId);
            dispose();
        }
        if(e.getSource()==usButton){
            new USController(employeeId);
            dispose();
        }

    }








}
