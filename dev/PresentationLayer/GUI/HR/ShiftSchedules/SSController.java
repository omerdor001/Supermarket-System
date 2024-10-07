package PresentationLayer.GUI.HR.ShiftSchedules;

import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SSController implements ActionListener {
    public SSView ssView;
    SystemFacade systemFacade;
    public SSController(String id){
        ssView=new SSView();
        systemFacade=SystemFacade.getInstance();
        ssView.createMenu(id);
        ssView.getFrame().setVisible(true);
        insertDriverShifts();
        insertBranchShifts();
        ssView.s1.addActionListener(this);
        ssView.s2.addActionListener(this);
        ssView.s3.addActionListener(this);
    }

    private void insertDriverShifts(){
        List<String> shifts=systemFacade.getDriverShifts();
        for(String shift:shifts){
            ssView.comboBoxDS.addItem(shift);
        }
    }

    private void insertBranchShifts(){
        List<String> shifts=systemFacade.getBranchShifts();
        for(String shift:shifts){
            ssView.comboBoxBS.addItem(shift);
        }
        for(String shift:shifts){
            ssView.comboBoxBSCopy.addItem(shift);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ssView.s1){
            try{
                String driverId=ssView.driverId.getText();
                String shift= (String) ssView.comboBoxDS.getSelectedItem();
                String[] splitted=shift.split(" ");
                int shiftId=Integer.parseInt(splitted[0]);
                String message=systemFacade.addDriverSchedule(driverId,shiftId);
                if(message.equals("Driver was scheduled successfully"))
                    JOptionPane.showMessageDialog(null,"Driver was scheduled successfully","ScheduleDriverSuccess",JOptionPane.PLAIN_MESSAGE);
                else JOptionPane.showMessageDialog(null,message,"ScheduleDriverError",JOptionPane.ERROR_MESSAGE);
                ssView.driverId.setText("");
            }
            catch (Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"ScheduleDriverError",JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource()==ssView.s2){
            try{
                String branchEmployeeId=ssView.branchEmployeeId.getText();
                String shift= (String) ssView.comboBoxBS.getSelectedItem();
                String[] splitted=shift.split(" ");
                int shiftId=Integer.parseInt(splitted[0]);
                String message=systemFacade.addEmployeeToSchedule(branchEmployeeId,shiftId);
                if(message.equals("Employee was added to shift successfully"))
                    JOptionPane.showMessageDialog(null,"Employee was added to shift successfully","AddBranchEmployeeToScheduleSuccess",JOptionPane.PLAIN_MESSAGE);
                else JOptionPane.showMessageDialog(null,message,"AddBranchEmployeeToScheduleError",JOptionPane.ERROR_MESSAGE);
                ssView.branchEmployeeId.setText("");
            }
            catch (Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"AddBranchEmployeeToScheduleError",JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource()==ssView.s3){
            try {
                String branchEmployeeId=ssView.branchEmployeeIdCopy.getText();
                String shift= (String) ssView.comboBoxBSCopy.getSelectedItem();
                String[] splitted=shift.split(" ");
                int shiftId=Integer.parseInt(splitted[0]);
                String role=(String) ssView.comboBoxRoles.getSelectedItem();
                String message=systemFacade.scheduleEmployeeToRole(branchEmployeeId,shiftId,role);
                if(message.equals("Schedule employee to role successfully"))
                    JOptionPane.showMessageDialog(null,"Schedule branch employee to role successfully","ScheduleBranchEmployeeToRoleSuccess",JOptionPane.PLAIN_MESSAGE);
                else JOptionPane.showMessageDialog(null,message,"ScheduleBranchEmployeeToRoleError",JOptionPane.ERROR_MESSAGE);
                ssView.branchEmployeeIdCopy.setText("");
            }
            catch (Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"ScheduleBranchEmployeeToRoleError",JOptionPane.ERROR_MESSAGE);
            }

        }

    }
}
