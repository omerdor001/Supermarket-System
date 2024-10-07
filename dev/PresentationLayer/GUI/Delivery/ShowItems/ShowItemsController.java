package PresentationLayer.GUI.Delivery.ShowItems;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ShowItemsController implements ActionListener {

    ShowItemsView showItemsView;
    LinkedList<ItemDeliveryModel> items;

    public ShowItemsController(LinkedList<ItemDeliveryModel> items) {
        this.items = items;
        ShowItemsModel showItemsModel = new ShowItemsModel(items);
        showItemsView = new ShowItemsView(showItemsModel);
        resizeColumnWidth(showItemsView.deliveryOrdersTable);
        showItemsView.backButton.addActionListener(this);
        showItemsView.refreshButton.addActionListener(this);
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
        if (e.getSource() == showItemsView.backButton)
            showItemsView.dispose();
        else if (e.getSource() == showItemsView.refreshButton) {
            new ShowItemsController(items);
            showItemsView.dispose();
        }
    }
}
