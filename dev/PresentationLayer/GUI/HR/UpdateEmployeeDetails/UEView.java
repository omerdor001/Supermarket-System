package PresentationLayer.GUI.HR.UpdateEmployeeDetails;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UEView extends BasicPage {
    public JComboBox comboBoxEmployeeTypes, comboBoxBranchEmployee, comboBoxHRManager, comboBoxDriver, comboBoxRoles, comboBoxAddRemove;
    public JTextField idTB, newInfoTB;
    public JButton submitChanges;
    private JPanel idPanel, employeeBranchPanel, driverPanel, hrManagerPanel, updateTextDetailPanel, submitPanel, rolesPanel, addRemovePanel;

    public UEView() {
        super("Update Details Employee");
        createUIComponents();
        setVisible(true);
    }

    private void createUIComponents() {
        idPanel = new JPanel();
        employeeBranchPanel = new JPanel();
        driverPanel = new JPanel();
        hrManagerPanel = new JPanel();
        updateTextDetailPanel = new JPanel();
        rolesPanel = new JPanel();
        addRemovePanel = new JPanel();
        submitPanel = new JPanel();
        JLabel label = new JLabel();
        label.setText("Choose employee type :");
        label.setSize(1000, 100);
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(100, 30, 500, 100);
        this.add(label);

        idPanel.setBackground(new Color(121, 198, 252));
        idPanel.setBounds(200, 150, 150, 20);
        employeeBranchPanel.setBackground(new Color(121, 198, 252));
        employeeBranchPanel.setBounds(200, 170, 150, 50);
        driverPanel.setBackground(new Color(121, 198, 252));
        driverPanel.setBounds(200, 170, 150, 50);
        hrManagerPanel.setBackground(new Color(121, 198, 252));
        hrManagerPanel.setBounds(200, 170, 150, 50);
        updateTextDetailPanel.setBackground(new Color(121, 198, 252));
        updateTextDetailPanel.setBounds(200, 250, 300, 50);
        rolesPanel.setBackground(new Color(121, 198, 252));
        rolesPanel.setBounds(200, 250, 300, 50);
        addRemovePanel.setBackground(new Color(121, 198, 252));
        addRemovePanel.setBounds(200, 250, 300, 50);
        submitPanel.setBackground(new Color(121, 198, 252));
        submitPanel.setBounds(300, 400, 150, 50);

        String[] employeeTypes = {"Branch Employee", "Driver", "HR Manager"};
        comboBoxEmployeeTypes = new JComboBox<>(employeeTypes);
        comboBoxEmployeeTypes.setSize(70, 50);
        comboBoxEmployeeTypes.setBounds(300, 60, 105, 35);
        comboBoxEmployeeTypes.setVisible(true);
        comboBoxEmployeeTypes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBoxEmployeeTypes.getSelectedItem();
                switch (s) {
                    case "Branch Employee":
                        idPanel.setVisible(true);
                        employeeBranchPanel.setVisible(true);
                        driverPanel.setVisible(false);
                        updateTextDetailPanel.setVisible(false);
                        rolesPanel.setVisible(false);
                        submitPanel.setVisible(false);
                        idTB.setText("");
                        newInfoTB.setText("");
                        break;
                    case "Driver":
                        idPanel.setVisible(true);
                        driverPanel.setVisible(true);
                        employeeBranchPanel.setVisible(false);
                        updateTextDetailPanel.setVisible(false);
                        rolesPanel.setVisible(false);
                        submitPanel.setVisible(false);
                        idTB.setText("");
                        newInfoTB.setText("");
                        break;
                    case "HR Manager":
                        idPanel.setVisible(true);
                        hrManagerPanel.setVisible(true);
                        driverPanel.setVisible(false);
                        employeeBranchPanel.setVisible(false);
                        updateTextDetailPanel.setVisible(false);
                        rolesPanel.setVisible(false);
                        submitPanel.setVisible(false);
                        idTB.setText("");
                        newInfoTB.setText("");
                        break;
                }
            }
        });
        this.add(comboBoxEmployeeTypes);

        //Submit changes
        submitChanges = new JButton();
        submitChanges.setText("Submit changes");
        submitChanges.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));

        //ID
        JLabel id = new JLabel();
        id.setText("ID:");
        id.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        id.setBounds(0, -10, 50, 50);
        idTB = new JTextField();
        idTB.setPreferredSize(new Dimension(100, 20));
        idTB.setBounds(30, 7, 100, 20);

        //Text detail
        JLabel newInfo = new JLabel();
        newInfo.setText("Enter change:");
        newInfo.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        newInfo.setBounds(0, -10, 50, 50);
        newInfoTB = new JTextField();
        newInfoTB.setPreferredSize(new Dimension(100, 20));
        newInfoTB.setBounds(30, 7, 100, 20);

        //Branch Employee ComboBox
        String[] typesBranchEmployee = {"First name", "Last name", "Password", "Account number", "Branch bank account",
                "Salary", "Terms of employment", "Status of employee", "Phone number", "HR Manager", "Cancellation option",
                "Management option", "Insert role", "Remove role",};
        comboBoxBranchEmployee = new JComboBox<>(typesBranchEmployee);
        comboBoxBranchEmployee.setSize(70, 50);
        comboBoxBranchEmployee.setBounds(300, 150, 20, 20);
        comboBoxBranchEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBoxBranchEmployee.getSelectedItem();
                switch (s) {
                    case "First name":
                    case "Last name":
                    case "Password":
                    case "Account number":
                    case "Branch bank account":
                    case "Salary":
                    case "Terms of employment":
                    case "Status of employee":
                    case "Phone number":
                        updateTextDetailPanel.setVisible(true);
                        rolesPanel.setVisible(false);
                        addRemovePanel.setVisible(false);
                        submitPanel.setVisible(true);
                        break;
                    case "HR Manager":
                        rolesPanel.setVisible(false);
                        updateTextDetailPanel.setVisible(false);
                        addRemovePanel.setVisible(false);
                        submitPanel.setVisible(true);
                        break;
                    case "Cancellation option":
                    case "Management option":
                        addRemovePanel.setVisible(true);
                        rolesPanel.setVisible(false);
                        updateTextDetailPanel.setVisible(false);
                        submitPanel.setVisible(false);
                        break;
                    case "Insert role":
                    case "Remove role":
                        rolesPanel.setVisible(true);
                        addRemovePanel.setVisible(false);
                        updateTextDetailPanel.setVisible(false);
                        submitPanel.setVisible(false);
                        break;
                }
            }
        });

        //HRManager ComboBox
        String[] typesHRManager = {"First name", "Last name", "Password", "Account number", "Branch bank account",
                "Salary", "Terms of employment", "Status of employee", "Phone number"};
        comboBoxHRManager = new JComboBox<>(typesHRManager);
        comboBoxHRManager.setSize(70, 50);
        comboBoxHRManager.setBounds(300, 150, 20, 20);
        comboBoxHRManager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTextDetailPanel.setVisible(true);
                rolesPanel.setVisible(false);
                addRemovePanel.setVisible(false);
                submitPanel.setVisible(true);
            }
        });

        //Driver ComboBox
        String[] typesDriverEmployee = {"First name", "Last name", "Password", "Account number", "Branch bank account",
                "Salary", "Terms of employment", "Status of employee","Phone number", "HR Manager", "Set licenses", "Add qualification", "Remove qualification"};
        comboBoxDriver = new JComboBox<>(typesDriverEmployee);
        comboBoxDriver.setSize(70, 50);
        comboBoxDriver.setBounds(300, 150, 20, 20);

        comboBoxDriver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBoxDriver.getSelectedItem();
                switch (s) {
                    case "First name":
                    case "Last name":
                    case "Password":
                    case "Account number":
                    case "Branch bank account":
                    case "Salary":
                    case "Terms of employment":
                    case "Status of employee":
                    case "Phone number":
                    case "Set licenses":
                    case "Add qualification":
                    case "Remove qualification":
                        updateTextDetailPanel.setVisible(true);
                        rolesPanel.setVisible(false);
                        addRemovePanel.setVisible(false);
                        submitPanel.setVisible(true);
                        break;
                    case "HR Manager":
                        rolesPanel.setVisible(false);
                        updateTextDetailPanel.setVisible(false);
                        addRemovePanel.setVisible(false);
                        submitPanel.setVisible(true);
                        break;
                }
            }
        });


        //Roles ComboBox
        String[] typesRoles = {"Shift Manager", "Store Keeper", "Cashier", "General Employee",
                "Guard", "Cleaner", "Merchandiser"};
        comboBoxRoles = new JComboBox<>(typesRoles);
        comboBoxRoles.setSize(70, 50);
        comboBoxRoles.setBounds(300, 150, 20, 20);
        comboBoxRoles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitPanel.setVisible(true);
            }
        });

        //Add Remove comboBox
        String[] typesAddRemove = {"Add", "Remove"};
        comboBoxAddRemove = new JComboBox<>(typesAddRemove);
        comboBoxAddRemove.setSize(70, 50);
        comboBoxAddRemove.setBounds(300, 150, 20, 20);

        comboBoxAddRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitPanel.setVisible(true);
            }
        });


        //Id Panel
        idPanel.setLayout(new GridLayout(1, 1));
        idPanel.add(id);
        idPanel.add(idTB);
        idPanel.setVisible(false);
        this.add(idPanel);

        //Branch Employee Panel
        employeeBranchPanel.setLayout(new GridLayout(2, 2));
        employeeBranchPanel.add(comboBoxBranchEmployee);
        employeeBranchPanel.setVisible(false);
        this.add(employeeBranchPanel);

        //Driver Panel
        driverPanel.setLayout(new GridLayout(2, 2));
        driverPanel.add(comboBoxDriver);
        driverPanel.setVisible(false);
        this.add(driverPanel);

        //HRManager Panel
        hrManagerPanel.setLayout(new GridLayout(2, 2));
        hrManagerPanel.add(comboBoxHRManager);
        hrManagerPanel.setVisible(false);
        this.add(hrManagerPanel);

        //New Text Info Panel
        updateTextDetailPanel.setLayout(new GridLayout(2, 2));
        updateTextDetailPanel.add(newInfo);
        updateTextDetailPanel.add(newInfoTB);
        updateTextDetailPanel.setVisible(false);
        this.add(updateTextDetailPanel);

        //Roles Panel
        rolesPanel.add(comboBoxRoles);
        rolesPanel.setVisible(false);
        this.add(rolesPanel);

        //Add Remove Panel
        addRemovePanel.add(comboBoxAddRemove);
        addRemovePanel.setVisible(false);
        this.add(addRemovePanel);

        //Submit Panel
        submitPanel.setLayout(new BorderLayout());
        submitPanel.add(submitChanges);
        submitPanel.setVisible(false);
        this.add(submitPanel);

    }

    public JFrame getFrame() {
        return this;
    }


}
