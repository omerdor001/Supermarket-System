package PresentationLayer.GUI.HR.EmployeeInformationHR;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EIView extends BasicPage {
    public JComboBox comboBox;
    public JPanel p,displayPanelDrivers,displayPanelBranchEmployees,displayConstraints;
    public JTable constraints;
    public JLabel employeeId,firstName,lastName,password,accountNumber,branchBankNumber,salary,termsOfEmployment,
    statusOfEmployee,phoneNumber,type,hireDate,management,cancellation,licences;
    public JTextArea displayTextArea1,displayTextArea2,displayTextArea3;
    public JLabel showDeliveryDates,qualifications,roles;
    public EIView(){
        super("Employees information");
        createUIComponents();
    }

    private void createUIComponents(){
        p=new JPanel();
        constraints=new JTable();
        p.setBounds(180,100,380,250);
        p.setBackground(new Color(121, 198, 252));
        p.setBorder(new LineBorder(Color.BLACK));
        JLabel label=new JLabel();
        label.setText("Enter employee id:");
        label.setSize(1000,100);
        label.setFont(new Font("Tahoma", Font.BOLD,20));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(100,30,500,100);
        this.add(label);
        String[] ids={};
        comboBox=new JComboBox<>(ids);
        comboBox.setSize(150,50);
        comboBox.setBounds(200,60,150,30);

        employeeId=new JLabel();
        firstName=new JLabel();
        lastName=new JLabel();
        password=new JLabel();
        accountNumber=new JLabel();
        branchBankNumber=new JLabel();
        salary=new JLabel();
        termsOfEmployment=new JLabel();
        statusOfEmployee=new JLabel();
        phoneNumber=new JLabel();
        type=new JLabel();
        hireDate=new JLabel();
        management=new JLabel();
        cancellation=new JLabel();
        licences=new JLabel();

        JLabel employeeIdT=new JLabel("Employee ID : ");
        employeeIdT.setBounds(10,0,200,30);
        employeeIdT.setFont(new Font("Tahoma", Font.BOLD,14));
        employeeId.setBounds(180,0,200,30);
        employeeId.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(employeeIdT);
        p.add(employeeId);

        JLabel firstNameT=new JLabel("First Name : ");
        firstNameT.setBounds(10,20,200,30);
        firstNameT.setFont(new Font("Tahoma", Font.BOLD,14));
        firstName.setBounds(180,20,200,30);
        firstName.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(firstNameT);
        p.add(firstName);

        JLabel lastNameT=new JLabel("Last Name : ");
        lastNameT.setBounds(10,40,200,30);
        lastNameT.setFont(new Font("Tahoma", Font.BOLD,14));
        lastName.setBounds(180,40,200,30);
        lastName.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(lastNameT);
        p.add(lastName);

        JLabel passwordT=new JLabel("Password : ");
        passwordT.setBounds(10,60,200,30);
        passwordT.setFont(new Font("Tahoma", Font.BOLD,14));
        password.setBounds(180,60,200,30);
        password.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(passwordT);
        p.add(password);

        JLabel accountNumberT=new JLabel("Account Number : ");
        accountNumberT.setBounds(10,80,200,30);
        accountNumberT.setFont(new Font("Tahoma", Font.BOLD,14));
        accountNumber.setBounds(180,80,200,30);
        accountNumber.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(accountNumberT);
        p.add(accountNumber);

        JLabel branchBankNumberT=new JLabel("Branch Bank Number : ");
        branchBankNumberT.setBounds(10,100,200,30);
        branchBankNumberT.setFont(new Font("Tahoma", Font.BOLD,14));
        branchBankNumber.setBounds(180,100,200,30);
        branchBankNumber.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(branchBankNumberT);
        p.add(branchBankNumber);

        JLabel salaryT=new JLabel("Salary : ");
        salaryT.setBounds(10,120,200,30);
        salaryT.setFont(new Font("Tahoma", Font.BOLD,14));
        salary.setBounds(180,120,200,30);
        salary.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(salaryT);
        p.add(salary);

        JLabel termsOfEmploymentT=new JLabel("Terms Of Employment : ");
        termsOfEmploymentT.setBounds(10,140,200,30);
        termsOfEmploymentT.setFont(new Font("Tahoma", Font.BOLD,14));
        termsOfEmployment.setBounds(180,140,200,30);
        termsOfEmployment.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(termsOfEmploymentT);
        p.add(termsOfEmployment);

        JLabel statusOfEmployeeT=new JLabel("Status Of Employee : ");
        statusOfEmployeeT.setBounds(10,160,200,30);
        statusOfEmployeeT.setFont(new Font("Tahoma", Font.BOLD,14));
        statusOfEmployee.setBounds(180,160,200,30);
        statusOfEmployee.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(statusOfEmployeeT);
        p.add(statusOfEmployee);

        JLabel phoneNumberT=new JLabel("Phone Number : ");
        phoneNumberT.setBounds(10,180,200,30);
        phoneNumberT.setFont(new Font("Tahoma", Font.BOLD,14));
        phoneNumber.setBounds(180,180,200,30);
        phoneNumber.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(phoneNumberT);
        p.add(phoneNumber);

        JLabel typeT=new JLabel("Type : ");
        typeT.setBounds(10,200,200,30);
        typeT.setFont(new Font("Tahoma", Font.BOLD,14));
        type.setBounds(180,200,200,30);
        type.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(typeT);
        p.add(type);

        JLabel hireDateT=new JLabel("Hire Date : ");
        hireDateT.setBounds(10,220,200,30);
        hireDateT.setFont(new Font("Tahoma", Font.BOLD,14));
        hireDate.setBounds(180,220,200,30);
        hireDate.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(hireDateT);
        p.add(hireDate);

        displayConstraints=new JPanel();
        displayConstraints.setBounds(580,100,450,250);
        displayConstraints.setVisible(false);
        constraints.setDefaultEditor(Object.class,null);
        constraints.getTableHeader().setReorderingAllowed(false);
        constraints.setBounds(0,0,250,250);
        constraints.setRowSelectionAllowed(false);
        constraints.setColumnSelectionAllowed(false);
        constraints.setCellSelectionEnabled(false);
        JScrollPane spConstraints = new JScrollPane(constraints);
        constraints.getTableHeader().setBackground(Color.DARK_GRAY);
        constraints.getTableHeader().setForeground(Color.WHITE);
        constraints.setSelectionBackground(Color.CYAN);
        constraints.setSelectionForeground(Color.BLACK);
        constraints.setFont(new Font("Tahoma", Font.PLAIN,12));
        spConstraints.setBounds(0,-10,250,250);
        spConstraints.setFont(new Font("Tahoma", Font.BOLD,14));
        displayConstraints.add(spConstraints);

        displayPanelDrivers = new JPanel();
        displayPanelDrivers.setBounds(180,350,380,250);
        displayPanelDrivers.setBorder(new LineBorder(Color.BLACK));
        displayPanelDrivers.setBackground(new Color(121, 198, 252));

        JLabel licencesT=new JLabel("Licences : ");
        licencesT.setBounds(10,10,200,30);
        licencesT.setFont(new Font("Tahoma", Font.BOLD,14));
        licences.setBounds(180,10,200,30);
        licences.setFont(new Font("Tahoma", Font.PLAIN,14));
        displayPanelDrivers.add(licencesT);
        displayPanelDrivers.add(licences);

        showDeliveryDates=new JLabel("Delivery Dates : ");
        showDeliveryDates.setFont(new Font("Tahoma",Font.BOLD,14));
        showDeliveryDates.setBounds(10,30,150,30);
        displayTextArea1 = new JTextArea(4, 10);
        displayTextArea1.setEditable(false);
        JScrollPane displayPane1 = new JScrollPane(displayTextArea1);
        displayPane1.setBounds(10,60,90,100);
        displayPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        qualifications=new JLabel("Qualifications : ");
        qualifications.setFont(new Font("Tahoma",Font.BOLD,14));
        qualifications.setBounds(200,30,150,30);
        displayTextArea2 = new JTextArea(4, 10);
        displayTextArea2.setEditable(false);
        JScrollPane displayPane2 = new JScrollPane(displayTextArea2);
        displayPane2.setBounds(200,60,90,100);
        displayPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        displayPanelBranchEmployees = new JPanel();
        displayPanelBranchEmployees.setBounds(180,350,380,250);
        displayPanelBranchEmployees.setBorder(new LineBorder(Color.BLACK));
        displayPanelBranchEmployees.setBackground(new Color(121, 198, 252));

        JLabel managementT=new JLabel("Management : ");
        managementT.setBounds(10,10,200,30);
        managementT.setFont(new Font("Tahoma", Font.BOLD,14));
        management.setBounds(180,10,200,30);
        management.setFont(new Font("Tahoma", Font.PLAIN,14));
        displayPanelBranchEmployees.add(managementT);
        displayPanelBranchEmployees.add(management);

        JLabel cancellationsT=new JLabel("Cancellations : ");
        cancellationsT.setBounds(10,30,200,30);
        cancellationsT.setFont(new Font("Tahoma", Font.BOLD,14));
        cancellation.setBounds(180,30,200,30);
        cancellation.setFont(new Font("Tahoma", Font.PLAIN,14));
        displayPanelBranchEmployees.add(cancellationsT);
        displayPanelBranchEmployees.add(cancellation);

        roles=new JLabel("Roles :");
        roles.setFont(new Font("Tahoma",Font.BOLD,14));
        roles.setBounds(10,50,150,30);
        displayTextArea3 = new JTextArea(4, 10);
        displayTextArea3.setEditable(false);
        JScrollPane displayPane3 = new JScrollPane(displayTextArea3);
        displayPane3.setBounds(10,90,90,100);
        displayPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        displayPanelDrivers.add(showDeliveryDates);
        displayPanelDrivers.add(displayPane1);
        displayPanelDrivers.add(qualifications);
        displayPanelDrivers.add(displayPane2);
        displayPanelBranchEmployees.add(roles);
        displayPanelBranchEmployees.add(displayPane3);

        displayPanelDrivers.setVisible(false);
        displayPanelBranchEmployees.setVisible(false);

        this.add(displayConstraints);
        displayPanelDrivers.setLayout(null);
        this.add(displayPanelDrivers);
        displayPanelBranchEmployees.setLayout(null);
        this.add(displayPanelBranchEmployees);
        this.add(comboBox);
        p.setLayout(null);
        this.add(p);

    }

    public JFrame getFrame(){
        return this;
    }




}
