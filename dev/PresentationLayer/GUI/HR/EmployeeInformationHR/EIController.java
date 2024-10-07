package PresentationLayer.GUI.HR.EmployeeInformationHR;

import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class EIController implements ActionListener {
    public EIView eiView;
    SystemFacade systemFacade;
    DefaultTableModel model2;
    public EIController(String id){
        eiView=new EIView();
        systemFacade=SystemFacade.getInstance();
        eiView.createMenu(id);
        eiView.getFrame().setVisible(true);
        model2 = new DefaultTableModel();
        insertIds();
        eiView.comboBox.addActionListener(this);
    }

    public void insertIds(){
        Set<String> ids=systemFacade.getEmployeeIds();
        for(String id:ids){
            eiView.comboBox.addItem(id);
        }
    }

    public void buildConstraintsTable(){
        String[] constraintDetails={"ConstraintId","ShiftId","Description"};
        model2.setColumnIdentifiers(constraintDetails);
        eiView.constraints.setModel(model2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = (String) eiView.comboBox.getSelectedItem();
        if(systemFacade.isBranchEmployeeV(s).equals("true")){
            String resultB=systemFacade.showEmployee(s);
            BranchEmployeeModel branchEmployeeModel;
            try {
                branchEmployeeModel = JsonConverter.fromJson(resultB, BranchEmployeeModel.class);
            }
            catch (IOException e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            eiView.employeeId.setText(branchEmployeeModel.employeeId);
            eiView.firstName.setText(branchEmployeeModel.firstName);
            eiView.lastName.setText(branchEmployeeModel.lastName);
            eiView.password.setText(branchEmployeeModel.password);
            eiView.accountNumber.setText(Integer.toString(branchEmployeeModel.accountNumber));
            eiView.branchBankNumber.setText(Integer.toString(branchEmployeeModel.branchBankNumber));
            eiView.salary.setText(Integer.toString(branchEmployeeModel.salary));
            eiView.termsOfEmployment.setText(branchEmployeeModel.termsOfEmployment);
            eiView.statusOfEmployee.setText(branchEmployeeModel.statusOfEmployee);
            eiView.phoneNumber.setText(branchEmployeeModel.phoneNumber);
            eiView.type.setText("Branch Employee");
            eiView.hireDate.setText(branchEmployeeModel.hireDate.toString());
            eiView.management.setText(Boolean.toString(branchEmployeeModel.management));
            eiView.cancellation.setText(Boolean.toString(branchEmployeeModel.cancellations));

            StringBuilder stringBuilder= new StringBuilder();
            for (String l: branchEmployeeModel.roles){
                stringBuilder.append(l).append("\n");
            }
            eiView.displayTextArea3.setText(stringBuilder.toString());
            eiView.displayPanelBranchEmployees.setVisible(true);
            eiView.displayPanelDrivers.setVisible(false);

            cleanTableConstraints();
            buildConstraintsTable();
            for(EmployeeConstraintModel employeeConstraintModel:branchEmployeeModel.constraints){
                String [] dataEmployeeConstraint=new String[3];
                dataEmployeeConstraint[0]=Integer.toString(employeeConstraintModel.constraintId);
                dataEmployeeConstraint[1]=Integer.toString(employeeConstraintModel.shiftId);
                dataEmployeeConstraint[2]=employeeConstraintModel.description;
                model2.addRow(dataEmployeeConstraint);
            }

        }
        else if (systemFacade.isDriverV(s).equals("true")){
            String resultD=systemFacade.showEmployee(s);
            DriverModel driverModel;
            try {
                driverModel = JsonConverter.fromJson(resultD, DriverModel.class);
            }
            catch (IOException e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            eiView.employeeId.setText(driverModel.employeeId);
            eiView.firstName.setText(driverModel.firstName);
            eiView.lastName.setText(driverModel.lastName);
            eiView.password.setText(driverModel.password);
            eiView.accountNumber.setText(Integer.toString(driverModel.accountNumber));
            eiView.branchBankNumber.setText(Integer.toString(driverModel.branchBankNumber));
            eiView.salary.setText(Integer.toString(driverModel.salary));
            eiView.termsOfEmployment.setText(driverModel.termsOfEmployment);
            eiView.statusOfEmployee.setText(driverModel.statusOfEmployee);
            eiView.phoneNumber.setText(driverModel.phoneNumber);
            eiView.type.setText("Driver");
            eiView.hireDate.setText(driverModel.hireDate.toString());
            eiView.licences.setText(Integer.toString(driverModel.licenses));

            StringBuilder stringBuilder= new StringBuilder();
            for (LocalDate l: driverModel.deliveryDates){
                stringBuilder.append(l).append("\n");
            }
            eiView.displayTextArea1.setText(stringBuilder.toString());

            StringBuilder stringBuilder1= new StringBuilder();
            for (String l: driverModel.qualifications){
                stringBuilder1.append(l).append("\n");
            }
            eiView.displayTextArea2.setText(stringBuilder1.toString());
            eiView.displayPanelDrivers.setVisible(true);
            eiView.displayPanelBranchEmployees.setVisible(false);

            cleanTableConstraints();
            buildConstraintsTable();
            for(EmployeeConstraintModel employeeConstraintModel:driverModel.constraints){
                String [] dataEmployeeConstraint=new String[3];
                dataEmployeeConstraint[0]=Integer.toString(employeeConstraintModel.constraintId);
                dataEmployeeConstraint[1]=Integer.toString(employeeConstraintModel.shiftId);
                dataEmployeeConstraint[2]=employeeConstraintModel.description;
                model2.addRow(dataEmployeeConstraint);
            }
        }
        else{
            String result=systemFacade.showEmployee(s);
            EmployeeModel employeeModel;
            try {
                employeeModel = JsonConverter.fromJson(result, EmployeeModel.class);
            }
            catch (IOException e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            eiView.employeeId.setText(employeeModel.employeeId);
            eiView.firstName.setText(employeeModel.firstName);
            eiView.lastName.setText(employeeModel.lastName);
            eiView.password.setText(employeeModel.password);
            eiView.accountNumber.setText(Integer.toString(employeeModel.accountNumber));
            eiView.branchBankNumber.setText(Integer.toString(employeeModel.branchBankNumber));
            eiView.salary.setText(Integer.toString(employeeModel.salary));
            eiView.termsOfEmployment.setText(employeeModel.termsOfEmployment);
            eiView.statusOfEmployee.setText(employeeModel.statusOfEmployee);
            eiView.phoneNumber.setText(employeeModel.phoneNumber);
            if(employeeModel.type.ordinal()==0){
                eiView.type.setText("Branch Employee");
            }
            else if(employeeModel.type.ordinal()==1){
                eiView.type.setText("Driver");
            }
            else eiView.type.setText("HR Manager");
            eiView.hireDate.setText(employeeModel.hireDate.toString());
            cleanTableConstraints();
            buildConstraintsTable();
            for(EmployeeConstraintModel employeeConstraintModel:employeeModel.constraints){
                String [] dataEmployeeConstraint=new String[3];
                dataEmployeeConstraint[0]=Integer.toString(employeeConstraintModel.constraintId);
                dataEmployeeConstraint[1]=Integer.toString(employeeConstraintModel.shiftId);
                dataEmployeeConstraint[2]=employeeConstraintModel.description;
                model2.addRow(dataEmployeeConstraint);
            }
        }
        eiView.displayConstraints.setVisible(true);
    }

    private void cleanTableConstraints(){
        if(model2.getRowCount()>0){
            for (int r=model2.getRowCount()-1;r>-1;r--){
                model2.removeRow(r);
            }
        }
    }



}
