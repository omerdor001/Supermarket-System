package PresentationLayer.GUI.HR.BranchesInformation;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BIView extends BasicPage {
    public JComboBox comboBox;
    public JPanel p,displayPanel;
    public JLabel region,address,phoneNumber,contactNumber,latitude,longitude,morningShiftStartHour,
            eveningShiftStartHour,morningShiftEndHour,eveningShiftEndHour;
    public JTextArea displayTextArea;
    public BIView(){
        super("Branches information");
        createUIComponents();
    }

    private void createUIComponents(){
        p=new JPanel();
        JLabel label=new JLabel();
        label.setText("Enter Branch Name:");
        label.setSize(1000,100);
        label.setFont(new Font("Tahoma", Font.BOLD,18));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(100,30,500,100);
        this.add(label);
        region=new JLabel();
        address=new JLabel();
        contactNumber=new JLabel();
        latitude=new JLabel();
        longitude=new JLabel();
        morningShiftStartHour=new JLabel();
        eveningShiftStartHour=new JLabel();
        morningShiftEndHour=new JLabel();
        eveningShiftEndHour=new JLabel();
        String[] names={};
        comboBox=new JComboBox<>(names);
        comboBox.setSize(150,50);
        comboBox.setBounds(190,60,150,30);

        p.setLayout(null);
        p.setBackground(new Color(121, 198, 252));
        p.setBounds(250,120,300,200);
        p.setBorder(new LineBorder(Color.BLACK));

        JLabel regionT=new JLabel("Region : ");
        regionT.setBounds(10,0,100,30);
        regionT.setFont(new Font("Tahoma", Font.BOLD,14));
        region.setBounds(80,0,200,30);
        region.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(regionT);
        p.add(region);

        JLabel addressT=new JLabel("Address : ");
        addressT.setBounds(10,20,100,30);
        addressT.setFont(new Font("Tahoma", Font.BOLD,14));
        address.setBounds(80,20,200,30);
        address.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(addressT);
        p.add(address);

        JLabel contactNumberT=new JLabel("Contact Number : ");
        contactNumberT.setBounds(10,40,160,30);
        contactNumberT.setFont(new Font("Tahoma", Font.BOLD,14));
        contactNumber.setBounds(150,40,100,30);
        contactNumber.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(contactNumberT);
        p.add(contactNumber);

        JLabel latitudeT=new JLabel("Latitude : ");
        latitudeT.setBounds(10,60,100,30);
        latitudeT.setFont(new Font("Tahoma", Font.BOLD,14));
        latitude.setBounds(90,60,200,30);
        latitude.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(latitudeT);
        p.add(latitude);

        JLabel longitudeT=new JLabel("Longitude : ");
        longitudeT.setBounds(10,80,100,30);
        longitudeT.setFont(new Font("Tahoma", Font.BOLD,14));
        longitude.setBounds(100,80,200,30);
        longitude.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(longitudeT);
        p.add(longitude);

        JLabel morningShiftStartHourT=new JLabel("Morning Shift Start Hour : ");
        morningShiftStartHourT.setBounds(10,100,250,30);
        morningShiftStartHourT.setFont(new Font("Tahoma", Font.BOLD,14));
        morningShiftStartHour.setBounds(200,100,200,30);
        morningShiftStartHour.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(morningShiftStartHourT);
        p.add(morningShiftStartHour);

        JLabel eveningShiftStartHourT=new JLabel("Evening Shift Start Hour : ");
        eveningShiftStartHourT.setBounds(10,120,250,30);
        eveningShiftStartHourT.setFont(new Font("Tahoma", Font.BOLD,14));
        eveningShiftStartHour.setBounds(200,120,200,30);
        eveningShiftStartHour.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(eveningShiftStartHourT);
        p.add(eveningShiftStartHour);

        JLabel morningShiftEndHourT=new JLabel("Morning Shift End Hour : ");
        morningShiftEndHourT.setBounds(10,140,250,30);
        morningShiftEndHourT.setFont(new Font("Tahoma", Font.BOLD,14));
        morningShiftEndHour.setBounds(200,140,200,30);
        morningShiftEndHour.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(morningShiftEndHourT);
        p.add(morningShiftEndHour);

        JLabel eveningShiftEndHourT=new JLabel("Evening Shift End Hour : ");
        eveningShiftEndHourT.setBounds(10,160,250,30);
        eveningShiftEndHourT.setFont(new Font("Tahoma", Font.BOLD,14));
        eveningShiftEndHour.setBounds(200,160,200,30);
        eveningShiftEndHour.setFont(new Font("Tahoma", Font.PLAIN,14));
        p.add(eveningShiftEndHourT);
        p.add(eveningShiftEndHour);

        displayPanel = new JPanel();
        JLabel timesOfShiftsT=new JLabel("Times Of Shifts : ");
        timesOfShiftsT.setBounds(10,10,250,30);
        timesOfShiftsT.setFont(new Font("Tahoma", Font.BOLD,14));
        displayTextArea = new JTextArea(4, 10);
        displayTextArea.setEditable(false);
        JScrollPane displayPane = new JScrollPane(displayTextArea);
        displayPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        displayPane.setBounds(10,45,100,100);
        displayPanel.setLayout(null);
        displayPanel.add(timesOfShiftsT);
        displayPanel.add(displayPane);
        displayPanel.setBounds(550 , 120, 260, 200);
        displayPanel.setBackground(new Color(121, 198, 252));
        displayPanel.setBorder(new LineBorder(Color.BLACK));
        displayPanel.setVisible(true);

        this.add(displayPanel);
        this.add(comboBox);
        this.add(p);

    }

    public JFrame getFrame(){
        return this;
    }

}
