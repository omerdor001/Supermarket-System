package PresentationLayer.GUI;

import PresentationLayer.GUI.Delivery.DeliveryMainMenu.DeliveryMainController;
import PresentationLayer.GUI.HR.ChoosePage.ChooseRoleController;
import ServiceLayer.SystemFacade;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {
    MainMenuView mainMenuView;
    SystemFacade systemFacade;

    public MainMenuController() {
        mainMenuView = new MainMenuView();
        systemFacade = SystemFacade.getInstance();
        mainMenuView.deliveryButton.addActionListener(this);
        mainMenuView.hrButton.addActionListener(this);
        mainMenuView.deleteDataButton.addActionListener(this);
        mainMenuView.generateDataButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuView.deliveryButton) {
            new DeliveryMainController(true);
            mainMenuView.dispose();
        } else if (e.getSource() == mainMenuView.hrButton) {
            new ChooseRoleController();
            mainMenuView.dispose();
        } else if (e.getSource() == mainMenuView.deleteDataButton) {
            SystemFacade.deleteData();
            JOptionPane.showMessageDialog(null,"Data deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == mainMenuView.generateDataButton) {
            try {
                systemFacade.resetData();
                JOptionPane.showMessageDialog(null,"Data generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (JsonProcessingException j){
                JOptionPane.showMessageDialog(null,j.getMessage(),"Generate error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
