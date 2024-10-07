package PresentationLayer.GUI.HR.ConstraintManagement;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CMView extends BasicPage {
    public JComboBox comboBox,comboBoxShifts,constraintComboBox,constraintUpdateComboBox,comboBoxUpdateShifts,constraintUpdateComboBoxU;
    public JTextField descriptionTB,descriptionUpdateTB;
    public JButton buttonInsert,buttonRemove,buttonUpdateShift,buttonUpdateDescription;
    private JPanel p1,p2,p3,p4,p6,p7;
    public CMView(){
        super("Constraints management");
        createUIComponents();
    }

    private void createUIComponents(){
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        p4=new JPanel();
        p6=new JPanel();
        p7=new JPanel();
        JLabel label=new JLabel();
        label.setText("Choose an option :");
        label.setSize(1000,100);
        label.setFont(new Font("Tahoma", Font.BOLD,20));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(100,30,500,100);
        this.add(label);

        p1.setBackground(new Color(121, 198, 252));
        p1.setBounds(200,100,500,30);
        p4.setBackground(new Color(121, 198, 252));
        p4.setBounds(200,130,500,200);
        p2.setBackground(new Color(121, 198, 252));
        p2.setBounds(200,100,500,200);
        p3.setBackground(new Color(121, 198, 252));
        p3.setBounds(200,100,500,70);
        p6.setBackground(new Color(121, 198, 252));
        p6.setBounds(200,170,500,200);
        p7.setBackground(new Color(121, 198, 252));
        p7.setBounds(200,170,500,200);

        //Shift
        JLabel shift=new JLabel();
        shift.setText("Choose shift :");
        shift.setFont(new Font("Tahoma",Font.PLAIN,14));
        shift.setBounds(0,0,200,20);
        String[] shiftsDetails={};
        comboBoxShifts=new JComboBox<>(shiftsDetails);
        comboBoxShifts.setSize(70,50);
        comboBoxShifts.setBounds(100,0,300,20);
        comboBoxShifts.setVisible(true);

        //Description
        JLabel description=new JLabel();
        description.setText("Description :");
        description.setFont(new Font("Tahoma",Font.PLAIN,14));
        description.setHorizontalAlignment(JLabel.LEFT);
        descriptionTB=new JTextField();
        descriptionTB.setPreferredSize(new Dimension(150,20));
        descriptionTB.setHorizontalAlignment(JLabel.RIGHT);

        p1.add(shift);
        p1.add(comboBoxShifts);
        p4.add(description);
        p4.add(descriptionTB);

        //Insert button
        buttonInsert=new JButton("Insert");
        buttonInsert.setBackground(Color.WHITE);
        buttonInsert.setBounds(500,500,80,40);

        p4.add(buttonInsert);

        //Constraint
        JLabel constraint=new JLabel();
        constraint.setText("Choose constraint :");
        constraint.setFont(new Font("Tahoma",Font.PLAIN,14));
        constraint.setBounds(0,0,200,20);
        String[] constraintDetails={};
        constraintComboBox=new JComboBox<>(constraintDetails);
        constraintComboBox.setBounds(100,0,150,20);

        //Remove button
        buttonRemove=new JButton("Remove");
        buttonRemove.setBackground(Color.WHITE);
        buttonRemove.setBounds(0,0,80,40);

        p2.add(constraint);
        p2.add(constraintComboBox);
        p2.add(buttonRemove);

        //Update shift button
        buttonUpdateShift=new JButton("Update Shift");
        buttonUpdateShift.setBackground(Color.WHITE);
        buttonUpdateShift.setBounds(0,70,150,30);
        buttonUpdateShift.setVisible(false);

        //Shift
        JLabel shiftUpdate=new JLabel();
        shiftUpdate.setText("Choose shift :");
        shiftUpdate.setFont(new Font("Tahoma",Font.PLAIN,14));
        shiftUpdate.setBounds(0,30,200,20);
        comboBoxUpdateShifts=new JComboBox<>(shiftsDetails);
        comboBoxUpdateShifts.setSize(70,50);
        comboBoxUpdateShifts.setBounds(100,30,200,20);
        comboBoxUpdateShifts.setVisible(false);

        JLabel constraint1=new JLabel();
        constraint1.setText("Choose constraint :");
        constraint1.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        constraint1.setBounds(0,0,200,20);

        //Description
        JLabel descriptionUpdate=new JLabel();
        descriptionUpdate.setText("Description :");
        descriptionUpdate.setFont(new Font("Tahoma",Font.PLAIN,14));
        descriptionUpdate.setBounds(0,20,150,50);
        descriptionUpdateTB=new JTextField();
        descriptionUpdateTB.setPreferredSize(new Dimension(150,20));
        descriptionUpdateTB.setBounds(130,30,150,20);

        String[] constraintDetailsU={};
        constraintUpdateComboBox=new JComboBox<>(constraintDetailsU);
        constraintUpdateComboBox.setBounds(130,0,150,20);

        String[] constraintDetailsUU={};
        constraintUpdateComboBoxU=new JComboBox<>(constraintDetailsUU);
        constraintUpdateComboBoxU.setBounds(130,0,150,20);

        //Update description button
        buttonUpdateDescription=new JButton("Update description");
        buttonUpdateDescription.setBackground(Color.WHITE);
        buttonUpdateDescription.setBounds(0,70,200,30);

        p6.setLayout(null);
        p6.add(constraint);
        p6.add(constraintUpdateComboBoxU);
        p6.add(shiftUpdate);
        p6.add(comboBoxUpdateShifts);
        p6.add(buttonUpdateShift);

        p7.setLayout(null);
        p7.add(constraint1);
        p7.add(constraintUpdateComboBox);
        p7.add(descriptionUpdate);
        p7.add(descriptionUpdateTB);
        p7.add(buttonUpdateDescription);

        p6.setVisible(false);
        p7.setVisible(false);



        //Update
        JLabel update=new JLabel();
        update.setText("Choose what to update :");
        update.setFont(new Font("Tahoma",Font.PLAIN,14));
        update.setBounds(0,0,200,20);
        String[] updates={"Shift","Description"};
        JComboBox comboBoxUpdates=new JComboBox<>(updates);
        comboBoxUpdates.setSize(70,50);
        comboBoxUpdates.setBounds(100,0,300,20);
        comboBoxUpdates.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBoxUpdates.getSelectedItem();
                switch (s) {
                    case "Shift":
                        p6.setVisible(true);
                        p7.setVisible(false);
                        comboBoxUpdateShifts.setVisible(true);
                        buttonUpdateShift.setVisible(true);
                        break;
                    case "Description":
                        p6.setVisible(false);
                        p7.setVisible(true);
                        buttonUpdateDescription.setVisible(true);
                        break;
                }
            }
        });

        p3.add(update);
        p3.add(comboBoxUpdates);

        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
        p4.setVisible(false);

        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p6);
        this.add(p7);

        String[] types={"Insert constraint","Remove constraint","Update constraint"};
        comboBox=new JComboBox<>(types);
        comboBox.setSize(70,50);
        comboBox.setBounds(300,60,120,35);
        comboBox.setVisible(true);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox.getSelectedItem();
                switch (s) {
                    case "Insert constraint":
                        p1.setVisible(true);
                        p4.setVisible(true);
                        p2.setVisible(false);
                        p3.setVisible(false);
                        buttonUpdateDescription.setVisible(false);
                        break;
                    case "Remove constraint":
                        p1.setVisible(false);
                        p4.setVisible(false);
                        p2.setVisible(true);
                        p3.setVisible(false);
                        buttonUpdateDescription.setVisible(false);
                        break;
                    case "Update constraint":
                        p1.setVisible(false);
                        p4.setVisible(false);
                        p2.setVisible(false);
                        p3.setVisible(true);
                        break;
                }
            }
        });

        this.add(comboBox);
        this.setLayout(null);

    }

    public JFrame getFrame(){
        return this;
    }

}
