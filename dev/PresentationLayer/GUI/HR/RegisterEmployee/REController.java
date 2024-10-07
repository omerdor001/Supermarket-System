package PresentationLayer.GUI.HR.RegisterEmployee;

import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;

public class REController implements ActionListener, KeyListener {
    public REView reView;
    SystemFacade systemFacade;
    public REController(String id){
        reView=new REView();
        systemFacade=SystemFacade.getInstance();
        reView.createMenu(id);
        reView.getFrame().setVisible(true);
        reView.button1.addActionListener(this);
        reView.button2.addActionListener(this);
        reView.idTB.addKeyListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String id=reView.idTB.getText();
            String firstName=reView.firstNameTB.getText();
            String lastName=reView.lastNameTB.getText();
            String password=reView.passwordTB.getText();
            int accountNumber=Integer.parseInt(reView.accountNumberTB.getText());
            int branchBankAccount=Integer.parseInt(reView.branchBankAccountTB.getText());
            int salary=Integer.parseInt(reView.salaryTB.getText());
            String termsOfEmployment=reView.termsOfEmploymentTB.getText();
            String statusOfEmployee=reView.statusOfEmployeeTB.getText();
            int year=Integer.parseInt(reView.year.getText());
            int month=Integer.parseInt(reView.month.getText());
            int day=Integer.parseInt(reView.day.getText());
            String phoneNumber=reView.phoneNumberTB.getText();
            if(e.getSource()==reView.button1){
                boolean management=reView.managementCB.isSelected();
                boolean cancellation=reView.cancellationCB.isSelected();
                String message=systemFacade.registerBranchEmployee(id,firstName,lastName,password,accountNumber,branchBankAccount,salary,termsOfEmployment,
                        statusOfEmployee, LocalDateTime.of(year,month,day,1,1),phoneNumber,management,cancellation);
                if(message.equals("success"))
                    JOptionPane.showMessageDialog(null,"Branch Employee was registered successfully","RegisterBranchEmployeeSuccess",JOptionPane.PLAIN_MESSAGE);
                else JOptionPane.showMessageDialog(null,message,"RegisterBranchEmployeeError",JOptionPane.ERROR_MESSAGE);
            }
            if(e.getSource()==reView.button2){
                int licenses=Integer.parseInt(reView.licensesTB.getText());
                String message=systemFacade.registerDriver(id,firstName,lastName,password,accountNumber,branchBankAccount,salary,termsOfEmployment,
                        statusOfEmployee, LocalDateTime.of(year,month,day,1,1),phoneNumber,licenses);
                if(message.equals("success"))
                    JOptionPane.showMessageDialog(null,"Driver was registered successfully","RegisterDriverSuccess",JOptionPane.PLAIN_MESSAGE);
                else JOptionPane.showMessageDialog(null,message,"RegisterDriverError",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e1){
            String s=e1.getMessage();
            if(e1.getMessage().contains("input string")) s="You have an empty field";
            JOptionPane.showMessageDialog(null,s,"RegisterDriverError",JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
            reView.idTB.setEditable(true);
        } else {
            reView.idTB.setEditable(false);
            reView.idTB.setText("");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
