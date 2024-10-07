package PresentationLayer.GUI.HR.ChoosePage;

import PresentationLayer.GUI.HR.HRMain;
import PresentationLayer.GUI.MainMenuController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChooseRoleController implements ActionListener, KeyListener {
    ChooseRoleView chooseRoleView;
    HRMain hrMain;
    public ChooseRoleController(){
        chooseRoleView=new ChooseRoleView();
        chooseRoleView.hRManager.addActionListener(this);
        chooseRoleView.employee.addActionListener(this);
        chooseRoleView.back.addActionListener(this);
        chooseRoleView.idTB.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==chooseRoleView.hRManager){
            hrMain=new HRMain();
            hrMain.createMenu(chooseRoleView.idTB.getText());
            hrMain.getFrame().setVisible(true);
            chooseRoleView.dispose();
        }
        if(e.getSource()==chooseRoleView.employee){
            if(chooseRoleView.idTB.getText().isEmpty())
                JOptionPane.showMessageDialog(null,"Id cannot can be empty","CreateShiftError",JOptionPane.ERROR_MESSAGE);
            else{
                hrMain=new HRMain();
                hrMain.createMenuEmployee(chooseRoleView.idTB.getText());
                hrMain.getFrame().setVisible(true);
                chooseRoleView.dispose();
            }
        }
        if(e.getSource()==chooseRoleView.back){
            new MainMenuController();
            chooseRoleView.dispose();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
            chooseRoleView.idTB.setEditable(true);
        } else {
            chooseRoleView.idTB.setEditable(false);
            chooseRoleView.idTB.setText("");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
