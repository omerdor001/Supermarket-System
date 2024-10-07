package PresentationLayer.GUI.HR.ConstraintManagement;

import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CMController implements ActionListener {
    public CMView cmView;
    SystemFacade systemFacade;
    String employeeId;
    public CMController(String _employeeId){
        cmView=new CMView();
        systemFacade=SystemFacade.getInstance();
        cmView.createMenuEmployee(employeeId);
        cmView.getFrame().setVisible(true);
        employeeId=_employeeId;
        insertShifts();
        insertConstraints();
        cmView.buttonInsert.addActionListener(this);
        cmView.buttonRemove.addActionListener(this);
        cmView.buttonUpdateDescription.addActionListener(this);
        cmView.buttonUpdateShift.addActionListener(this);
    }

    private void insertShifts(){
        List<String> shifts=systemFacade.showEmployeeHisFutureShiftsList(employeeId);
        for(String shift:shifts){
            cmView.comboBoxShifts.addItem(shift);
        }
        for(String shift:shifts){
            cmView.comboBoxUpdateShifts.addItem(shift);
        }
    }

    private void insertConstraints(){
        List<String> constraints=systemFacade.showAllConstraintToEmployeeList(employeeId);
        for(String constraint:constraints){
            cmView.constraintComboBox.addItem(constraint);
        }
        for(String constraint:constraints){
            cmView.constraintUpdateComboBox.addItem(constraint);
        }
    }

    private void clearConstraints(){
        for (int i=0;i<cmView.constraintComboBox.getItemCount();i++){
            cmView.constraintComboBox.remove(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cmView.buttonInsert){
            try {
                String shift=cmView.comboBoxShifts.getSelectedItem().toString();
                String[] splitted=shift.split(" ");
                int shiftId=Integer.parseInt(splitted[0]);
                String description=cmView.descriptionTB.getText();
                String message=systemFacade.addConstraint(employeeId,shiftId,description);
                if(message.equals("Constraint added successfully"))
                    JOptionPane.showMessageDialog(null,"Constraint was added successfully","AddConstraintSuccess",JOptionPane.PLAIN_MESSAGE);
                else JOptionPane.showMessageDialog(null,message,"AddConstraintError",JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"AddConstraintError",JOptionPane.ERROR_MESSAGE);
            }
            clearConstraints();
            insertConstraints();
        }
        if(e.getSource()==cmView.buttonRemove){
            try {
                String constraint=cmView.constraintComboBox.getSelectedItem().toString();
                String[] splitted=constraint.split(" ");
                int constraintId=Integer.parseInt(splitted[0]);
                String message=systemFacade.removeConstraint(employeeId,constraintId);
                if(message.equals("Constraint was removed successfully"))
                    JOptionPane.showMessageDialog(null,"Constraint was removed successfully","RemoveConstraintSuccess",JOptionPane.PLAIN_MESSAGE);
                else JOptionPane.showMessageDialog(null,message,"RemoveConstraintError",JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"RemoveConstraintError",JOptionPane.ERROR_MESSAGE);
            }
            clearConstraints();
            insertConstraints();
        }
        if(e.getSource()==cmView.buttonUpdateDescription){
            String constraint=cmView.constraintUpdateComboBox.getSelectedItem().toString();
            String[] splitted=constraint.split(" ");
            int constraintId=Integer.parseInt(splitted[0]);
            String description=cmView.descriptionUpdateTB.getText();
            String message=systemFacade.editConstraintDescription(employeeId,constraintId,description);
            if(message.equals("Constraint has been edited successfully"))
                JOptionPane.showMessageDialog(null,"Constraint has been edited successfully","UpdateConstraintSuccess",JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(null,message,"UpdateConstraintError",JOptionPane.ERROR_MESSAGE);
        }
        if(e.getSource()==cmView.buttonUpdateShift){
            String constraint=cmView.constraintUpdateComboBoxU.getSelectedItem().toString();
            String[] splitted=constraint.split(" ");
            int constraintId=Integer.parseInt(splitted[0]);
            String shift=cmView.comboBoxUpdateShifts.getSelectedItem().toString();
            String[] splittedS=constraint.split(" ");
            int shiftId=Integer.parseInt(splittedS[0]);
            String message=systemFacade.editConstraintShift(employeeId,constraintId,shiftId);
            if(message.equals("Constraint has been edited successfully"))
                JOptionPane.showMessageDialog(null,"Constraint has been edited successfully","UpdateConstraintSuccess",JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(null,message,"UpdateConstraintError",JOptionPane.ERROR_MESSAGE);
        }
    }
}
