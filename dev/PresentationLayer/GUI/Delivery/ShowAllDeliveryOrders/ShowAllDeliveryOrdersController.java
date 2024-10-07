package PresentationLayer.GUI.Delivery.ShowAllDeliveryOrders;

import PresentationLayer.GUI.Delivery.ShowDeliveryOrder.ShowDeliveryOrderController;
import PresentationLayer.GUI.Delivery.ShowDeliveryOrder.ShowDeliveryOrderModel;

import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class ShowAllDeliveryOrdersController implements ActionListener {

    ShowAllDeliveryOrdersView showAllDeliveryOrdersView;
    SystemFacade systemFacade;

    public ShowAllDeliveryOrdersController() {
        systemFacade = SystemFacade.getInstance();
        String result = systemFacade.showAllDeliveryOrders();
        TypeReference<HashMap<Integer, ShowDeliveryOrderModel>> typeReference = new TypeReference<>() {
        };
        ShowAllDeliveryOrdersModel allDeliveryOrdersModel;
        HashMap<Integer, ShowDeliveryOrderModel> deliveryOrders;
        try {
            deliveryOrders = JsonConverter.fromJson(result, typeReference);
            allDeliveryOrdersModel = new ShowAllDeliveryOrdersModel(deliveryOrders);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        showAllDeliveryOrdersView = new ShowAllDeliveryOrdersView(allDeliveryOrdersModel, deliveryOrders);
        resizeColumnWidth(showAllDeliveryOrdersView.deliveryOrdersTable);
        showAllDeliveryOrdersView.backButton.addActionListener(this);
        showAllDeliveryOrdersView.infoButton.addActionListener(this);
        showAllDeliveryOrdersView.refreshButton.addActionListener(this);
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
            columnModel.getColumn(column).setMinWidth(width);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showAllDeliveryOrdersView.backButton)
            showAllDeliveryOrdersView.dispose();
        else if (e.getSource() == showAllDeliveryOrdersView.refreshButton) {
            new ShowAllDeliveryOrdersController();
            showAllDeliveryOrdersView.dispose();
        } else if (e.getSource() == showAllDeliveryOrdersView.infoButton) {
            Object item = showAllDeliveryOrdersView.userInputComboBox.getSelectedItem();
            if (item == null || item.equals("-"))
                showAllDeliveryOrdersView.userInputErrorLabel.setVisible(true);
            else {
                showAllDeliveryOrdersView.userInputErrorLabel.setVisible(false);
                try {
                    new ShowDeliveryOrderController((int) item);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
