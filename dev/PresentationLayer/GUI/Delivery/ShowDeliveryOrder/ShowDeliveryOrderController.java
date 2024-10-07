package PresentationLayer.GUI.Delivery.ShowDeliveryOrder;

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

public class ShowDeliveryOrderController implements ActionListener {

    int id;
    ShowDeliveryOrderModel showDeliveryOrderModel;
    ShowDeliveryOrderView showDeliveryOrderView;

    public ShowDeliveryOrderController(int id) throws IOException {
        String result = SystemFacade.getInstance().showDeliveryOrder(id);
        if (result.charAt(0) != '{') {
            JOptionPane.showMessageDialog(null, result, "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.id = id;
        showDeliveryOrderModel = JsonConverter.fromJson(result, ShowDeliveryOrderModel.class);
        showDeliveryOrderView = new ShowDeliveryOrderView();
        showDeliveryOrderView.refreshButton.addActionListener(this);
        showDeliveryOrderView.backButton.addActionListener(this);
        showDeliveryOrderView.idLabel.setText(showDeliveryOrderView.idLabel.getText() + " " + showDeliveryOrderModel.id);
        showDeliveryOrderView.destinationLabel.setText(showDeliveryOrderView.destinationLabel.getText() + " " + showDeliveryOrderModel.destination);
        showDeliveryOrderView.sourceLabel.setText(showDeliveryOrderView.sourceLabel.getText() + " " + showDeliveryOrderModel.source);
        showDeliveryOrderView.totalLoadWeightLabel.setText(showDeliveryOrderView.totalLoadWeightLabel.getText() + " " + showDeliveryOrderModel.totalLoadWeight);
        showDeliveryOrderView.statusLabel.setText(showDeliveryOrderView.statusLabel.getText() + " " + showDeliveryOrderModel.status);
        showDeliveryOrderView.itemsTable.setModel(new DefaultTableModel(showDeliveryOrderModel.data, showDeliveryOrderModel.columns));
        resizeColumnWidth(showDeliveryOrderView.itemsTable);
        showDeliveryOrderView.itemsTable.getColumnModel().getColumn(7).setMinWidth(70);
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 20;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
                Component comp = table.prepareRenderer(renderer, row, column);
                int headerWidth = headerRenderer.getTableCellRendererComponent(table, table.getColumnModel().getColumn(column).getHeaderValue(), false, false, 0, 0).getPreferredSize().width;
                width = Math.max(headerWidth, Math.max(comp.getPreferredSize().width + 1, width));
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showDeliveryOrderView.backButton)
            showDeliveryOrderView.dispose();
        else if (e.getSource() == showDeliveryOrderView.refreshButton) {
            try {
                new ShowDeliveryOrderController(id);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            showDeliveryOrderView.dispose();
        }
    }
}
