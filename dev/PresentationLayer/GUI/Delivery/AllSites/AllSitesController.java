package PresentationLayer.GUI.Delivery.AllSites;

import PresentationLayer.GUI.Delivery.Site.SiteController;
import PresentationLayer.GUI.Delivery.Site.SiteModel;
import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class AllSitesController implements ActionListener {
    AllSitesView allSitesView;
    SystemFacade systemFacade;

    public AllSitesController() {
        systemFacade = SystemFacade.getInstance();
        String result = systemFacade.showAllSites();
        TypeReference<HashMap<String, SiteModel>> typeReference = new TypeReference<>() {};
        AllSitesModel allSitesModel;
        HashMap<String, SiteModel> sites;
        try {
            sites = JsonConverter.fromJson(result, typeReference);
            allSitesModel = new AllSitesModel(sites);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        allSitesView = new AllSitesView(allSitesModel, sites);
        allSitesView.backButton.addActionListener(this);
        allSitesView.infoButton.addActionListener(this);
        allSitesView.refreshButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == allSitesView.backButton)
            allSitesView.dispose();
        else if (e.getSource() == allSitesView.refreshButton) {
            new AllSitesController();
            allSitesView.dispose();
        } else if (e.getSource() == allSitesView.infoButton) {
            Object item = allSitesView.userInputComboBox1.getSelectedItem();
            if (item == null || item.equals("-"))
                allSitesView.userInputError1Label.setVisible(true);
            else {
                allSitesView.userInputError1Label.setVisible(false);
                new SiteController((String) item);
            }
        }
    }
}
