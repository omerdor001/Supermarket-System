package PresentationLayer.GUI.Delivery.ShowAllItemOrders;

import PresentationLayer.GUI.Delivery.ShowItems.ItemOrderModel;
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

public class ShowAllItemOrdersController implements ActionListener {

    ShowAllItemOrdersView showAllItemOrdersView;
    SystemFacade systemFacade;

    public ShowAllItemOrdersController() {
        systemFacade = SystemFacade.getInstance();
        String result = systemFacade.showAllItemOrders();
        TypeReference<HashMap<Integer, ItemOrderModel>> typeReference = new TypeReference<>() {
        };
        ShowAllItemOrdersModel allItemOrdersModel;
        HashMap<Integer, ItemOrderModel> itemOrders;
        try {
            itemOrders = JsonConverter.fromJson(result, typeReference);
            allItemOrdersModel = new ShowAllItemOrdersModel(itemOrders);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        showAllItemOrdersView = new ShowAllItemOrdersView(allItemOrdersModel);
        resizeColumnWidth(showAllItemOrdersView.itemOrdersTable);
        showAllItemOrdersView.backButton.addActionListener(this);
        showAllItemOrdersView.refreshButton.addActionListener(this);
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
        if (e.getSource() == showAllItemOrdersView.backButton)
            showAllItemOrdersView.dispose();
        else if (e.getSource() == showAllItemOrdersView.refreshButton) {
            new ShowAllItemOrdersController();
            showAllItemOrdersView.dispose();
        }
    }
}
