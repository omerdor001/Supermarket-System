package PresentationLayer.GUI.HR.RegisterEmployee;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class REView extends BasicPage{
    private JComboBox comboBox;
    private JPanel p1,p2,p3;
    public JTextField idTB,firstNameTB,lastNameTB,passwordTB,accountNumberTB,branchBankAccountTB,salaryTB,
            termsOfEmploymentTB,statusOfEmployeeTB,phoneNumberTB,year,month,day,licensesTB;
    public JCheckBox managementCB,cancellationCB;
    public JButton button1,button2;
    public REView(){
        super("Register Employee");
        createUIComponents();
    }

    private void createUIComponents() {
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        JLabel label=new JLabel();
        label.setText("Choose employee type :");
        label.setSize(1000,100);
        label.setFont(new Font("Tahoma", Font.BOLD,20));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(100,30,500,100);
        this.add(label);

        p1.setBackground(new Color(121, 198, 252));
        p1.setBounds(200,100,200,450);
        p2.setBackground(new Color(121, 198, 252));
        p2.setBounds(200,550,300,70);
        p3.setBackground(new Color(121, 198, 252));
        p3.setBounds(200,550,170,85);

        //ID
        JLabel id=new JLabel();
        id.setText("ID :");
        id.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        id.setBounds(0,-10,200,50);
        idTB=new JTextField();
        idTB.setPreferredSize(new Dimension(100,20));
        idTB.setBounds(200,7,100,20);


        //First Name
        JLabel firstName=new JLabel();
        firstName.setText("First Name :");
        firstName.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        firstName.setBounds(0,10,200,50);
        firstNameTB=new JTextField();
        firstNameTB.setPreferredSize(new Dimension(100,20));
        firstNameTB.setBounds(200,30,100,20);

        //Last Name
        JLabel lastName=new JLabel();
        lastName.setText("Last Name :");
        lastName.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        lastName.setBounds(0,30,200,50);
        lastNameTB=new JTextField();
        lastNameTB.setPreferredSize(new Dimension(100,20));
        lastNameTB.setBounds(200,50,100,20);

        //Password
        JLabel password=new JLabel();
        password.setText("Password :");
        password.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        password.setBounds(0,50,200,50);
        passwordTB=new JTextField();
        passwordTB.setPreferredSize(new Dimension(100,20));
        passwordTB.setBounds(200,70,100,20);

        //Account Number
        JLabel accountNumber=new JLabel();
        accountNumber.setText("Account Number :");
        accountNumber.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        accountNumber.setBounds(0,70,200,50);
        accountNumberTB=new JTextField();
        accountNumberTB.setPreferredSize(new Dimension(100,20));
        accountNumberTB.setBounds(200,90,100,20);

        //Branch Bank Account Number
        JLabel branchBankAccount=new JLabel();
        branchBankAccount.setText("Branch Bank Account :");
        branchBankAccount.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        branchBankAccount.setBounds(0,90,200,50);
        branchBankAccountTB=new JTextField();
        branchBankAccountTB.setPreferredSize(new Dimension(200,20));
        branchBankAccountTB.setBounds(200,110,200,20);

        //Salary
        JLabel salary=new JLabel();
        salary.setText("Salary :");
        salary.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        salary.setBounds(0,110,300,50);
        salaryTB=new JTextField();
        salaryTB.setPreferredSize(new Dimension(100,20));
        salaryTB.setBounds(200,130,100,20);

        //Terms of employment
        JLabel termsOfEmployment=new JLabel();
        termsOfEmployment.setText("Terms Of Employment :");
        termsOfEmployment.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        termsOfEmployment.setBounds(0,130,200,50);
        termsOfEmploymentTB=new JTextField();
        termsOfEmploymentTB.setPreferredSize(new Dimension(100,20));
        termsOfEmploymentTB.setBounds(200,150,100,20);

        //Status of employee
        JLabel statusOfEmployee=new JLabel();
        statusOfEmployee.setText("Status Of Employee :");
        statusOfEmployee.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        statusOfEmployee.setBounds(0,150,200,50);
        statusOfEmployeeTB=new JTextField();
        statusOfEmployeeTB.setPreferredSize(new Dimension(100,20));
        statusOfEmployeeTB.setBounds(200,170,100,20);

        //Phone number
        JLabel phoneNumber=new JLabel();
        phoneNumber.setText("Phone Number :");
        phoneNumber.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        phoneNumber.setBounds(0,170,200,50);
        phoneNumberTB=new JTextField();
        phoneNumberTB.setPreferredSize(new Dimension(200,20));
        phoneNumberTB.setBounds(200,190,200,20);

        //Hire Date
        JLabel hireDate=new JLabel();
        hireDate.setText("Hire Date :");
        hireDate.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        hireDate.setBounds(0,270,400,50);

        JLabel yearLabel=new JLabel();
        yearLabel.setText("Year :");
        yearLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));

        year=new JTextField();
        year.setPreferredSize(new Dimension(30,20));
        year.setBounds(40,210,150,20);


        JLabel monthLabel=new JLabel();
        monthLabel.setText("Month :");
        monthLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));

        month=new JTextField();
        month.setPreferredSize(new Dimension(30,20));
        month.setBounds(120,210,150,20);

        JLabel dayLabel=new JLabel();
        dayLabel.setText("Day :");
        dayLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));

        day=new JTextField();
        day.setPreferredSize(new Dimension(30,20));
        day.setBounds(200,210,150,20);

        p1.add(id);
        p1.add(idTB);
        p1.add(firstName);
        p1.add(firstNameTB);
        p1.add(lastName);
        p1.add(lastNameTB);
        p1.add(password);
        p1.add(passwordTB);
        p1.add(accountNumber);
        p1.add(accountNumberTB);
        p1.add(branchBankAccount);
        p1.add(branchBankAccountTB);
        p1.add(salary);
        p1.add(salaryTB);
        p1.add(termsOfEmployment);
        p1.add(termsOfEmploymentTB);
        p1.add(statusOfEmployee);
        p1.add(statusOfEmployeeTB);
        p1.add(phoneNumber);
        p1.add(phoneNumberTB);
        p1.add(hireDate);
        p1.add(yearLabel);
        p1.add(year);
        p1.add(monthLabel);
        p1.add(month);
        p1.add(dayLabel);
        p1.add(day);

        //Management
        JLabel management=new JLabel();
        management.setText("Management Option :");
        management.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        managementCB=new JCheckBox();
        managementCB.setBackground(new Color(121, 198, 252));

        //Cancellation
        JLabel cancellation=new JLabel();
        cancellation.setText("Cancellations Option :");
        cancellation.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        cancellationCB=new JCheckBox();
        cancellationCB.setBackground(new Color(121, 198, 252));

        p2.add(management);
        p2.add(managementCB);
        p2.add(cancellation);
        p2.add(cancellationCB);

        //Licenses
        JLabel licenses=new JLabel();
        licenses.setText("Licenses :");
        licenses.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        licenses.setBounds(0,0,40,20);
        licensesTB=new JTextField();
        licensesTB.setPreferredSize(new Dimension(100,20));

        p3.add(licenses);
        p3.add(licensesTB);

        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);

        this.add(p1);
        this.add(p2);
        this.add(p3);

        button1=new JButton("Submit");
        button1.setBackground(Color.WHITE);
        button1.setBounds(285,30,25,30);
        p2.add(button1);

        button2=new JButton("Submit");
        button2.setBackground(Color.WHITE);
        button2.setHorizontalAlignment(JButton.RIGHT);
        button2.setVerticalAlignment(JButton.BOTTOM);
        p3.add(button2);

        String[] types={"Branch Employee","Driver"};
        comboBox=new JComboBox<>(types);
        comboBox.setSize(70,50);
        comboBox.setBounds(300,60,105,35);
        comboBox.setVisible(true);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox.getSelectedItem();
                switch (s) {
                    case "Branch Employee":
                        p1.setVisible(true);
                        p2.setVisible(true);
                        p3.setVisible(false);
                        break;
                    case "Driver":
                        p1.setVisible(true);
                        p2.setVisible(false);
                        p3.setVisible(true);
                        break;
                }
            }
        });
        this.add(comboBox);

    }

    public JFrame getFrame(){
        return this;
    }


    }

