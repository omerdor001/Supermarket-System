package PresentationLayer.GUI.Delivery.ShowAllDeliveries;

import PresentationLayer.GUI.Delivery.ShowDelivery.ShowDeliveryController;
import PresentationLayer.GUI.Delivery.ShowDelivery.ShowDeliveryModel;
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

public class ShowAllDeliveriesController implements ActionListener {

    ShowAllDeliveriesView showAllDeliveriesView;
    SystemFacade systemFacade;

    public ShowAllDeliveriesController() {
        systemFacade = SystemFacade.getInstance();
        String result = systemFacade.showAllDeliveries();
        TypeReference<HashMap<Integer, ShowDeliveryModel>> typeReference = new TypeReference<>() {
        };
        ShowAllDeliveriesModel showAllDeliveriesModel;
        HashMap<Integer, ShowDeliveryModel> deliveries;
        try {
            deliveries = JsonConverter.fromJson(result, typeReference);
            showAllDeliveriesModel = new ShowAllDeliveriesModel(deliveries);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        showAllDeliveriesView = new ShowAllDeliveriesView(showAllDeliveriesModel, deliveries);
        resizeColumnWidth(showAllDeliveriesView.deliveriesTable);
        showAllDeliveriesView.backButton.addActionListener(this);
        showAllDeliveriesView.infoButton.addActionListener(this);
        showAllDeliveriesView.refreshButton.addActionListener(this);
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
        if (e.getSource() == showAllDeliveriesView.backButton)
            showAllDeliveriesView.dispose();
        else if (e.getSource() == showAllDeliveriesView.refreshButton) {
            new ShowAllDeliveriesController();
            showAllDeliveriesView.dispose();
        } else if (e.getSource() == showAllDeliveriesView.infoButton) {
            Object item = showAllDeliveriesView.userInputComboBox.getSelectedItem();
            if (item == null || item.equals("-"))
                showAllDeliveriesView.userInputErrorLabel.setVisible(true);
            else {
                showAllDeliveriesView.userInputErrorLabel.setVisible(false);
                try {
                    new ShowDeliveryController((int) item);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
