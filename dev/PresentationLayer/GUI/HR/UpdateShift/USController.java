package PresentationLayer.GUI.HR.UpdateShift;

import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class USController implements ActionListener {
    public USView usView;
    SystemFacade systemFacade;
    public USController(String id){
        usView=new USView();
        systemFacade=SystemFacade.getInstance();
        usView.createMenu(id);
        usView.getFrame().setVisible(true);
        insertIds();
    }

    public void insertIds(){
        List<String> ids=systemFacade.getShiftIds();
        for(String id:ids){
            usView.sComboBox.addItem(id);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==usView.submitButton){
            try{
                int shiftId=Integer.parseInt(usView.sComboBox.getSelectedItem().toString());
                String s=(String) usView.comboBox.getSelectedItem();
                switch (s){
                    case "Branch Shift":
                        if(systemFacade.isBranchShift(Integer.toString(shiftId)).equals("false")){
                            JOptionPane.showMessageDialog(null,"Not a branch shift","UpdateShiftError",JOptionPane.ERROR_MESSAGE);
                        }
                        String detail=(String) usView.uComboBoxBS.getSelectedItem();
                        if(detail.equals("Date")){
                            LocalDateTime date=LocalDateTime.of(Integer.parseInt(usView.year.getText()),Integer.parseInt(usView.month.getText()),Integer.parseInt(usView.day.getText()),1,1);
                            String message=systemFacade.setDate(shiftId,date);
                            if(message.equals("Date was changed successfully"))
                                JOptionPane.showMessageDialog(null,"Date was changed successfully","SetDateSuccess",JOptionPane.PLAIN_MESSAGE);
                            else JOptionPane.showMessageDialog(null,message,"SetDateError",JOptionPane.ERROR_MESSAGE);
                        }
                        if(detail.equals("Branch")){
                            String message=systemFacade.setBranch(shiftId,usView.newInfo.getText());
                            if(message.equals("Branch was changed successfully"))
                                JOptionPane.showMessageDialog(null,"Branch was changed successfully","SetBranchSuccess",JOptionPane.PLAIN_MESSAGE);
                            else JOptionPane.showMessageDialog(null,message,"SetBranchError",JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "Driver Shift":
                        if(systemFacade.isBranchShift(Integer.toString(shiftId)).equals("true")){
                            JOptionPane.showMessageDialog(null,"Not a driver shift","UpdateShiftError",JOptionPane.ERROR_MESSAGE);
                        }
                        String detailD=(String) usView.uComboBoxDS.getSelectedItem();
                        if(detailD.equals("Date")){
                            LocalDateTime date=LocalDateTime.of(Integer.parseInt(usView.year.getText()),Integer.parseInt(usView.month.getText()),Integer.parseInt(usView.day.getText()),1,1);
                            String message=systemFacade.setDate(shiftId,date);
                            if(message.equals("Date was changed successfully"))
                                JOptionPane.showMessageDialog(null,"Date was changed successfully","SetDateSuccess",JOptionPane.PLAIN_MESSAGE);
                            else JOptionPane.showMessageDialog(null,message,"SetDateError",JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                }
            }
            catch (Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"UpdateShiftError",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
