package PresentationLayer.GUI.HR.EmployeesInformation;

import PresentationLayer.GUI.HR.EmployeeInformationHR.BranchEmployeeModel;
import PresentationLayer.GUI.HR.EmployeeInformationHR.DriverModel;
import PresentationLayer.GUI.HR.EmployeeInformationHR.EmployeeConstraintModel;
import PresentationLayer.GUI.HR.EmployeeInformationHR.EmployeeModel;
import PresentationLayer.GUI.HR.ShiftInformation.BranchShiftModel;
import PresentationLayer.GUI.HR.ShiftInformation.DriverShiftModel;
import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class EmployeeInformationController implements ActionListener {
    public EmployeeInformationView employeeInformationView;
    SystemFacade systemFacade;
    String employeeId;
    public DefaultTableModel constraintsModel,shiftsModel;
    public EmployeeInformationController(String id){
        employeeInformationView=new EmployeeInformationView();
        systemFacade=SystemFacade.getInstance();
        employeeInformationView.createMenuEmployee(id);
        employeeInformationView.getFrame().setVisible(true);
        constraintsModel=new DefaultTableModel();
        shiftsModel=new DefaultTableModel();
        employeeId=id;
        employeeInformationView.comboBox.addActionListener(this);
    }

    private void buildConstraintsTable(){
        String[] constraintDetails={"ConstraintId","ShiftId","Description"};
        constraintsModel.setColumnIdentifiers(constraintDetails);
        employeeInformationView.constraints.setModel(constraintsModel);
    }

    private void buildBranchShiftsTable(){
        String[] branchShiftsDetails={"Date","Branch"};
        shiftsModel.setColumnIdentifiers(branchShiftsDetails);
        employeeInformationView.shifts.setModel(shiftsModel);
    }

    private void buildDriverShiftsTable(){
        String[] driverShiftsDetails={"Date"};
        shiftsModel.setColumnIdentifiers(driverShiftsDetails);
        employeeInformationView.shifts.setModel(shiftsModel);
    }

    private BranchEmployeeModel getBranchEmployeeModel(){
        String resultB=systemFacade.showEmployee(employeeId);
        BranchEmployeeModel branchEmployeeModel=null;
        try {
            branchEmployeeModel = JsonConverter.fromJson(resultB, BranchEmployeeModel.class);
        }
        catch (IOException e1){
            JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return branchEmployeeModel;
    }

    private DriverModel getDriverModel(){
        String resultD = systemFacade.showEmployee(employeeId);
        DriverModel driverModel=null;
        try {
            driverModel = JsonConverter.fromJson(resultD, DriverModel.class);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return driverModel;
    }

    private EmployeeModel getEmployeeModel(){
        String result=systemFacade.showEmployee(employeeId);
        EmployeeModel employeeModel=null;
        try {
            employeeModel = JsonConverter.fromJson(result, EmployeeModel.class);
        }
        catch (IOException e1){
            JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return employeeModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = (String) employeeInformationView.comboBox.getSelectedItem();
        boolean isBranchEmployee=systemFacade.isBranchEmployeeV(employeeId).equals("true");
        boolean isDriver=systemFacade.isDriverV(employeeId).equals("true");
        switch (s) {
            case "Personal":
                if(isBranchEmployee){
                    BranchEmployeeModel branchEmployeeModel=getBranchEmployeeModel();
                    employeeInformationView.employeeId.setText(branchEmployeeModel.employeeId);
                    employeeInformationView.firstName.setText(branchEmployeeModel.firstName);
                    employeeInformationView.lastName.setText(branchEmployeeModel.lastName);
                    employeeInformationView.password.setText(branchEmployeeModel.password);
                    employeeInformationView.accountNumber.setText(Integer.toString(branchEmployeeModel.accountNumber));
                    employeeInformationView.branchBankNumber.setText(Integer.toString(branchEmployeeModel.branchBankNumber));
                    employeeInformationView.salary.setText(Integer.toString(branchEmployeeModel.salary));
                    employeeInformationView.termsOfEmployment.setText(branchEmployeeModel.termsOfEmployment);
                    employeeInformationView.statusOfEmployee.setText(branchEmployeeModel.statusOfEmployee);
                    employeeInformationView.phoneNumber.setText(branchEmployeeModel.phoneNumber);
                    employeeInformationView.type.setText("Branch Employee");
                    employeeInformationView.hireDate.setText(branchEmployeeModel.hireDate.toString());
                    employeeInformationView.management.setText(Boolean.toString(branchEmployeeModel.management));
                    employeeInformationView.cancellation.setText(Boolean.toString(branchEmployeeModel.cancellations));

                    StringBuilder stringBuilder= new StringBuilder();
                    for (String l: branchEmployeeModel.roles){
                        stringBuilder.append(l).append("\n");
                    }

                    employeeInformationView.displayTextArea3.setText(stringBuilder.toString());
                    employeeInformationView.pEmployee.setVisible(true);
                    employeeInformationView.displayPanelBranchEmployees.setVisible(true);
                    employeeInformationView.displayPanelDrivers.setVisible(false);

                }
                else if (isDriver) {
                    DriverModel driverModel=getDriverModel();
                    employeeInformationView.employeeId.setText(driverModel.employeeId);
                    employeeInformationView.firstName.setText(driverModel.firstName);
                    employeeInformationView.lastName.setText(driverModel.lastName);
                    employeeInformationView.password.setText(driverModel.password);
                    employeeInformationView.accountNumber.setText(Integer.toString(driverModel.accountNumber));
                    employeeInformationView.branchBankNumber.setText(Integer.toString(driverModel.branchBankNumber));
                    employeeInformationView.salary.setText(Integer.toString(driverModel.salary));
                    employeeInformationView.termsOfEmployment.setText(driverModel.termsOfEmployment);
                    employeeInformationView.statusOfEmployee.setText(driverModel.statusOfEmployee);
                    employeeInformationView.phoneNumber.setText(driverModel.phoneNumber);
                    employeeInformationView.type.setText("Driver");
                    employeeInformationView.hireDate.setText(driverModel.hireDate.toString());
                    employeeInformationView.licences.setText(Integer.toString(driverModel.licenses));

                    StringBuilder stringBuilder= new StringBuilder();
                    for (LocalDate l: driverModel.deliveryDates){
                        stringBuilder.append(l).append("\n");
                    }
                    employeeInformationView.displayTextArea1.setText(stringBuilder.toString());

                    StringBuilder stringBuilder1= new StringBuilder();
                    for (String l: driverModel.qualifications){
                        stringBuilder1.append(l).append("\n");
                    }
                    employeeInformationView.displayTextArea2.setText(stringBuilder1.toString());
                    employeeInformationView.pEmployee.setVisible(true);
                    employeeInformationView.displayPanelDrivers.setVisible(true);
                    employeeInformationView.displayPanelBranchEmployees.setVisible(false);
                }
                else {
                    EmployeeModel employeeModel=getEmployeeModel();
                    employeeInformationView.employeeId.setText(employeeModel.employeeId);
                    employeeInformationView.firstName.setText(employeeModel.firstName);
                    employeeInformationView.lastName.setText(employeeModel.lastName);
                    employeeInformationView.password.setText(employeeModel.password);
                    employeeInformationView.accountNumber.setText(Integer.toString(employeeModel.accountNumber));
                    employeeInformationView.branchBankNumber.setText(Integer.toString(employeeModel.branchBankNumber));
                    employeeInformationView.salary.setText(Integer.toString(employeeModel.salary));
                    employeeInformationView.termsOfEmployment.setText(employeeModel.termsOfEmployment);
                    employeeInformationView.statusOfEmployee.setText(employeeModel.statusOfEmployee);
                    employeeInformationView.phoneNumber.setText(employeeModel.phoneNumber);
                    if(employeeModel.typeOrdinal==0){
                        employeeInformationView.type.setText("Branch Employee");
                    }
                    else if(employeeModel.typeOrdinal==1){
                        employeeInformationView.type.setText("Driver");
                    }
                    else employeeInformationView.type.setText("HR Manager");
                    employeeInformationView.hireDate.setText(employeeModel.hireDate.toString());
                    employeeInformationView.pEmployee.setVisible(true);
                }

                employeeInformationView.p1.setVisible(true);
                employeeInformationView.p2.setVisible(false);
                employeeInformationView.p3.setVisible(false);
                break;
            case "Shifts":
                cleanShiftsTable();
                for(String shift:systemFacade.showEmployeeHisShiftsList(employeeId)){
                    String[] splitted=shift.split(" ");
                    String shiftId=splitted[0];
                    if(systemFacade.isBranchShift(shiftId).equals("true")) {
                        String resultBS = systemFacade.showBranchShift(Integer.parseInt(shiftId));
                        BranchShiftModel branchShiftModel;
                        try {
                            branchShiftModel = JsonConverter.fromJson(resultBS, BranchShiftModel.class);
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        buildBranchShiftsTable();
                        Object[] details=new Object[2];
                        details[0]=branchShiftModel.date;
                        details[1]=branchShiftModel.branch;
                        shiftsModel.addRow(details);
                    }
                    else{
                        String resultDS=systemFacade.showDriverShift(Integer.parseInt(shiftId));
                        DriverShiftModel driverShiftModel;
                        try {
                            driverShiftModel = JsonConverter.fromJson(resultDS, DriverShiftModel.class);
                        }
                        catch (IOException e1){
                            JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        buildDriverShiftsTable();
                        Object[] details=new Object[1];
                        details[0]=driverShiftModel.date;
                        shiftsModel.addRow(details);
                    }
                }
                employeeInformationView.p1.setVisible(false);
                employeeInformationView.p2.setVisible(true);
                employeeInformationView.p3.setVisible(false);
                break;
            case "Constraints":
                cleanConstraintsTable();
                buildConstraintsTable();
                if(isBranchEmployee){
                    BranchEmployeeModel branchEmployeeModel=getBranchEmployeeModel();
                    for(EmployeeConstraintModel employeeConstraintModel:branchEmployeeModel.constraints){
                        String [] dataEmployeeConstraint=new String[3];
                        dataEmployeeConstraint[0]=Integer.toString(employeeConstraintModel.constraintId);
                        dataEmployeeConstraint[1]=Integer.toString(employeeConstraintModel.shiftId);
                        dataEmployeeConstraint[2]=employeeConstraintModel.description;
                        constraintsModel.addRow(dataEmployeeConstraint);
                    }
                }
                else if (isDriver) {
                    DriverModel driverModel=getDriverModel();
                    for(EmployeeConstraintModel employeeConstraintModel:driverModel.constraints){
                        String [] dataEmployeeConstraint=new String[3];
                        dataEmployeeConstraint[0]=Integer.toString(employeeConstraintModel.constraintId);
                        dataEmployeeConstraint[1]=Integer.toString(employeeConstraintModel.shiftId);
                        dataEmployeeConstraint[2]=employeeConstraintModel.description;
                        constraintsModel.addRow(dataEmployeeConstraint);
                    }
                }
                else {
                    EmployeeModel employeeModel=getEmployeeModel();
                    for(EmployeeConstraintModel employeeConstraintModel:employeeModel.constraints){
                        String [] dataEmployeeConstraint=new String[3];
                        dataEmployeeConstraint[0]=Integer.toString(employeeConstraintModel.constraintId);
                        dataEmployeeConstraint[1]=Integer.toString(employeeConstraintModel.shiftId);
                        dataEmployeeConstraint[2]=employeeConstraintModel.description;
                        constraintsModel.addRow(dataEmployeeConstraint);
                    }
                }
                employeeInformationView.p1.setVisible(false);
                employeeInformationView.p2.setVisible(false);
                employeeInformationView.p3.setVisible(true);
                break;
        }
    }

    private void cleanShiftsTable(){
        if(shiftsModel.getRowCount()>0){
            for (int r=shiftsModel.getRowCount()-1;r>-1;r--){
                shiftsModel.removeRow(r);
            }
        }
    }

    private void cleanConstraintsTable(){
        if(constraintsModel.getRowCount()>0){
            for (int r=constraintsModel.getRowCount()-1;r>-1;r--){
                constraintsModel.removeRow(r);
            }
        }
    }
}

