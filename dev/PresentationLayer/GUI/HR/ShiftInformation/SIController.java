package PresentationLayer.GUI.HR.ShiftInformation;

import PresentationLayer.GUI.HR.BranchesInformation.BranchModel;
import PresentationLayer.GUI.HR.EmployeeInformationHR.EmployeeConstraintModel;
import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class SIController implements ActionListener {
    public SIView siView;
    SystemFacade systemFacade;
    public DefaultTableModel schedulesModel,schedulesStatusModel,cancellationsModel;
    public SIController(String id){
        siView=new SIView();
        systemFacade=SystemFacade.getInstance();
        siView.createMenu(id);
        siView.getFrame().setVisible(true);
        insertIds();
        schedulesModel = new DefaultTableModel();
        schedulesStatusModel = new DefaultTableModel();
        cancellationsModel = new DefaultTableModel();
        siView.comboBox.addActionListener(this);
    }

    public void insertIds(){
        List<String> ids=systemFacade.getShiftIds();
        for(String id:ids){
            siView.comboBox.addItem(id);
        }
    }

    private void buildSchedulesTable(){
        String[] schedulesDetails={"Employee ID","Role"};
        schedulesModel.setColumnIdentifiers(schedulesDetails);
        siView.schedules.setModel(schedulesModel);
    }

    private void buildSchedulesStatusTable(){
        String[] schedulesStatusDetails={"Role","Needed","Scheduled"};
        schedulesStatusModel.setColumnIdentifiers(schedulesStatusDetails);
        siView.schedulesStatus.setModel(schedulesStatusModel);
    }

    private void buildCancellationTable(){
        String[] cancellationsDetails={"Product ID","Employee ID"};
        cancellationsModel.setColumnIdentifiers(cancellationsDetails);
        siView.cancellations.setModel(cancellationsModel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = (String) siView.comboBox.getSelectedItem();
        if(systemFacade.isBranchShift(s).equals("true")){
            String resultBS=systemFacade.showBranchShift(Integer.parseInt(s));
            BranchShiftModel branchShiftModel;
            try {
                branchShiftModel = JsonConverter.fromJson(resultBS, BranchShiftModel.class);
            }
            catch (IOException e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            cleanSchedulesTable();
            buildSchedulesTable();
            cleanSchedulesStatusTable();
            buildSchedulesStatusTable();
            cleanCancellationsTable();
            buildCancellationTable();

            siView.id.setText(Integer.toString(branchShiftModel.shiftId));
            siView.date.setText(branchShiftModel.date.toString());
            siView.p1.setVisible(true);
            siView.p2.setVisible(true);
            siView.p3.setVisible(false);
            siView.branch.setText(branchShiftModel.branch);

            Object [] values = new Object[2];
            for(String id:branchShiftModel.schedules.keySet()){
                values[0]=id;
                values[1]=branchShiftModel.schedules.get(id);
                schedulesModel.addRow(values);
            }

            Object [] valuesSS = new Object[3];
            for(String role:branchShiftModel.roleCounts.keySet()){
                valuesSS[0]=role;
                valuesSS[1]=branchShiftModel.roleCounts.get(role);
                valuesSS[2]=branchShiftModel.scheduleToRoleCount.get(role);
                schedulesStatusModel.addRow(valuesSS);
            }

            for(ProductCancellationModel productCancellationModel:branchShiftModel.cancellations){
                String [] dataProductCancellation=new String[2];
                dataProductCancellation[0]= productCancellationModel.productId;
                dataProductCancellation[1]= productCancellationModel.employeeId;
                cancellationsModel.addRow(dataProductCancellation);
            }

            resizeColumnWidthSchedules(siView.schedules);
            resizeColumnWidthSchedulesStatus(siView.schedulesStatus);
            resizeColumnWidthCancellations(siView.cancellations);
        }
        else{
            String resultDS=systemFacade.showDriverShift(Integer.parseInt(s));
            DriverShiftModel driverShiftModel;
            try {
                driverShiftModel = JsonConverter.fromJson(resultDS, DriverShiftModel.class);
            }
            catch (IOException e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            siView.id.setText(Integer.toString(driverShiftModel.shiftId));
            siView.date.setText(driverShiftModel.date.toString());
            siView.p1.setVisible(true);
            siView.p2.setVisible(false);
            siView.p3.setVisible(true);
            siView.driversCount.setText(Integer.toString(driverShiftModel.driversCount));
            StringBuilder stringBuilder= new StringBuilder();
            for (String l: driverShiftModel.scheduleDrivers){
                stringBuilder.append(l).append("\n");
            }
            siView.displayTextAreaS.setText(stringBuilder.toString());
        }
    }

    public void resizeColumnWidthSchedules(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 20;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
                Component comp = table.prepareRenderer(renderer, row, column);
                int headerWidth = headerRenderer.getTableCellRendererComponent(table, siView.schedules.getColumnModel().getColumn(column).getHeaderValue(), false, false, 0, 0).getPreferredSize().width;
                width = Math.max(headerWidth,Math.max(comp.getPreferredSize().width +1 , width));
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public void resizeColumnWidthSchedulesStatus(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 20;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
                Component comp = table.prepareRenderer(renderer, row, column);
                int headerWidth = headerRenderer.getTableCellRendererComponent(table, siView.schedulesStatus.getColumnModel().getColumn(column).getHeaderValue(), false, false, 0, 0).getPreferredSize().width;
                width = Math.max(headerWidth,Math.max(comp.getPreferredSize().width +1 , width));
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public void resizeColumnWidthCancellations(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 20;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
                Component comp = table.prepareRenderer(renderer, row, column);
                int headerWidth = headerRenderer.getTableCellRendererComponent(table, siView.cancellations.getColumnModel().getColumn(column).getHeaderValue(), false, false, 0, 0).getPreferredSize().width;
                width = Math.max(headerWidth,Math.max(comp.getPreferredSize().width +1 , width));
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private void cleanSchedulesTable(){
        if(schedulesModel.getRowCount()>0){
            for (int r=schedulesModel.getRowCount()-1;r>-1;r--){
                schedulesModel.removeRow(r);
            }
        }
    }

    private void cleanSchedulesStatusTable(){
        if(schedulesStatusModel.getRowCount()>0){
            for (int r=schedulesStatusModel.getRowCount()-1;r>-1;r--){
                schedulesStatusModel.removeRow(r);
            }
        }
    }

    private void cleanCancellationsTable(){
        if(cancellationsModel.getRowCount()>0){
            for (int r=cancellationsModel.getRowCount()-1;r>-1;r--){
                cancellationsModel.removeRow(r);
            }
        }
    }
}
