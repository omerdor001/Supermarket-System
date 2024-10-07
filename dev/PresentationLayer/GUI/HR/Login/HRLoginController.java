package PresentationLayer.GUI.HR.Login;

import PresentationLayer.GUI.HR.HRMain;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HRLoginController implements ActionListener, KeyListener {
    HRLoginView loginView;
    HRMain hrMain;
    SystemFacade systemFacade;
    String employeeId;
    public HRLoginController(){
        loginView=new HRLoginView();
        systemFacade=SystemFacade.getInstance();
        loginView.button.addActionListener(this);
        loginView.idTB.addKeyListener(this);
        employeeId="";
    }

    public String getEmployeeId(){ return employeeId; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginView.button){
            employeeId=loginView.idTB.getText();
            String message=systemFacade.login(employeeId,loginView.passwordTB.getText());
            if(message.equals("success")){
                if(systemFacade.isHRManager(employeeId).equals("true")){
                    hrMain=new HRMain();
                    hrMain.createMenu(employeeId);
                    hrMain.setVisible(true);
                    loginView.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Employee Must Not Be HR","LoginError",JOptionPane.ERROR_MESSAGE);
                    systemFacade.logout(employeeId);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,message,"LoginError",JOptionPane.ERROR_MESSAGE);
                systemFacade.logout(employeeId);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
            loginView.idTB.setEditable(true);
        } else {
            loginView.idTB.setEditable(false);
            loginView.idTB.setText("");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
