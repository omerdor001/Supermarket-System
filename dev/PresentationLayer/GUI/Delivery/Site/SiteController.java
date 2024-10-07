package PresentationLayer.GUI.Delivery.Site;

import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SiteController implements ActionListener {
    SiteView siteView;
    SystemFacade systemFacade;
    String address;

    public SiteController(String address) {
        this.address = address;
        systemFacade = SystemFacade.getInstance();
        String result = systemFacade.showSite(address);
        SiteModel siteModel;
        try {
            siteModel = JsonConverter.fromJson(result, SiteModel.class);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        siteView = new SiteView(siteModel);
        siteView.backButton.addActionListener(this);
        siteView.refreshButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == siteView.backButton)
            siteView.dispose();
        else if (e.getSource() == siteView.refreshButton) {
            siteView.dispose();
            new SiteController(address);
        }
    }
}
