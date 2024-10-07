package PresentationLayer.GUI.HR.BranchesInformation;

import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class BIController implements ActionListener {
    public BIView biView;
    SystemFacade systemFacade;
    DefaultTableModel model;
    public BIController(String id){
        biView=new BIView();
        systemFacade=SystemFacade.getInstance();
        biView.createMenu(id);
        biView.getFrame().setVisible(true);
        model = new DefaultTableModel();
        insertNames();
        biView.comboBox.addActionListener(this);
    }

    public void insertNames(){
        List<String> names=systemFacade.getBranchesList();
        for(String name:names){
            biView.comboBox.addItem(name);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = (String) biView.comboBox.getSelectedItem();
        String resultB=systemFacade.showBranch(s);
        BranchModel branchModel;
        try {
            branchModel = JsonConverter.fromJson(resultB, BranchModel.class);
        }
        catch (IOException e1){
            JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        biView.region.setText(branchModel.region.toString());
        biView.address.setText(branchModel.address);
        biView.contactNumber.setText(branchModel.contactName);
        biView.latitude.setText(Double.toString(branchModel.coordinateModel.latitude));
        biView.longitude.setText(Double.toString(branchModel.coordinateModel.longitude));
        biView.morningShiftStartHour.setText(branchModel.morningShiftStartHour.toString());
        biView.eveningShiftStartHour.setText(branchModel.eveningShiftStartHour.toString());
        biView.morningShiftEndHour.setText(branchModel.morningShiftEndHour.toString());
        biView.eveningShiftEndHour.setText(branchModel.morningShiftEndHour.toString());

        StringBuilder stringBuilder= new StringBuilder();
        for (String l: branchModel.timeOfShifts){
            stringBuilder.append(l).append("\n");
        }
        biView.displayTextArea.setText(stringBuilder.toString());
    }
}
