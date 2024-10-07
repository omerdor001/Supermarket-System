package PresentationLayer.GUI.HR.CreateShift;

import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class CSController implements ActionListener {
    public CSView csView;
    SystemFacade systemFacade;
    public CSController(String id){
        csView=new CSView();
        systemFacade=SystemFacade.getInstance();
        csView.createMenu(id);
        csView.getFrame().setVisible(true);
        csView.button1.addActionListener(this);
        csView.button2.addActionListener(this);
        insertBranches();
    }

    private void insertBranches(){
        List<String> branches=systemFacade.getBranchesList();
        for(String name:branches){
            csView.selectBranch.addItem(name);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==csView.button2){
            try{
                int year=Integer.parseInt(csView.year.getText());
                int month=Integer.parseInt(csView.month.getText());
                int day=Integer.parseInt(csView.day.getText());
                LocalDateTime date=LocalDateTime.of(year,month,day,1,1);
                int shiftManagerCount=Integer.parseInt(csView.shiftManagerCountTB.getText());
                int storeKeeperCount=Integer.parseInt(csView.storeKeeperCountTB.getText());
                int cashierCount=Integer.parseInt(csView.cashierCountTB.getText());
                int generalEmployeeCount=Integer.parseInt(csView.generalEmployeeCountTB.getText());
                int guardCount=Integer.parseInt(csView.guardCountTB.getText());
                int cleanerCount=Integer.parseInt(csView.cleanerCountTB.getText());
                int merchandiserCount=Integer.parseInt(csView.merchandiserCountTB.getText());
                String branch=(String) csView.selectBranch.getSelectedItem();
                String type=(String) csView.selectType.getSelectedItem();
                String message=systemFacade.addBranchShift(date,storeKeeperCount,cashierCount,shiftManagerCount,generalEmployeeCount,guardCount,cleanerCount,merchandiserCount,branch,type);
                if(message.equals("success")){
                    JOptionPane.showMessageDialog(null,"Branch Shift was created successfully","CreateShiftSuccess",JOptionPane.PLAIN_MESSAGE);
                }
                else JOptionPane.showMessageDialog(null,message,"CreateShiftError",JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"CreateShiftError",JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource()==csView.button1){
            try{
                int year=Integer.parseInt(csView.year.getText());
                int month=Integer.parseInt(csView.month.getText());
                int day=Integer.parseInt(csView.day.getText());
                LocalDateTime date=LocalDateTime.of(year,month,day,1,1);
                int count=Integer.parseInt(csView.driverCountTB.getText());
                String message=systemFacade.addDriverShift(date,count);
                if(message.equals("Driver shift was added successfully")){
                    JOptionPane.showMessageDialog(null,"Driver Shift was created successfully","CreateShiftSuccess",JOptionPane.PLAIN_MESSAGE);
                }
                else JOptionPane.showMessageDialog(null,message,"CreateShiftError",JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"CreateShiftError",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
