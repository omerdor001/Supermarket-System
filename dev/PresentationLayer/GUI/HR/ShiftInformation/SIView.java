package PresentationLayer.GUI.HR.ShiftInformation;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SIView extends BasicPage {
    public JPanel p1,p2,p3;
    public JLabel id,date,driversCount,branch;
    public JComboBox comboBox;
    public JTable schedules,schedulesStatus,cancellations;
    public JTextArea displayTextAreaS;
    public SIView(){
        super("Shifts Information");
        createUIComponents();
    }

    public void createUIComponents(){
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        schedules=new JTable();
        schedulesStatus=new JTable();
        cancellations=new JTable();

        JLabel label=new JLabel();
        label.setText("Enter Shift ID:");
        label.setSize(1000,100);
        label.setFont(new Font("Tahoma", Font.BOLD,18));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(100,30,500,100);
        this.add(label);
        String[] ids={};
        comboBox=new JComboBox<>(ids);
        comboBox.setSize(150,50);
        comboBox.setBounds(200,60,150,30);

        p1.setLayout(null);
        p1.setBackground(new Color(121, 198, 252));
        p1.setBounds(250,120,500,50);
        p1.setBorder(new LineBorder(Color.BLACK));
        p1.setVisible(false);
        p2.setLayout(null);
        p2.setBackground(new Color(121, 198, 252));
        p2.setBounds(250,170,500,400);
        p2.setBorder(new LineBorder(Color.BLACK));
        p2.setVisible(false);
        p3.setLayout(null);
        p3.setBackground(new Color(121, 198, 252));
        p3.setBounds(250,170,500,250);
        p3.setBorder(new LineBorder(Color.BLACK));
        p3.setVisible(false);

        id=new JLabel();
        date=new JLabel();
        branch=new JLabel();
        driversCount=new JLabel();

        JLabel idT=new JLabel("ID: ");
        idT.setBounds(10,0,100,30);
        idT.setFont(new Font("Tahoma", Font.BOLD,14));
        id.setBounds(80,0,200,30);
        id.setFont(new Font("Tahoma", Font.PLAIN,14));
        p1.add(idT);
        p1.add(id);

        JLabel dateT=new JLabel("Date: ");
        dateT.setBounds(10,20,100,30);
        dateT.setFont(new Font("Tahoma", Font.BOLD,14));
        date.setBounds(80,20,200,30);
        date.setFont(new Font("Tahoma", Font.PLAIN,14));
        p1.add(dateT);
        p1.add(date);

        JLabel branchT=new JLabel("Branch: ");
        branchT.setBounds(10,0,100,30);
        branchT.setFont(new Font("Tahoma", Font.BOLD,14));
        branch.setBounds(80,0,200,30);
        branch.setFont(new Font("Tahoma", Font.PLAIN,14));
        p2.add(branchT);
        p2.add(branch);

        JLabel schedulesT=new JLabel("Schedules: ");
        schedulesT.setBounds(10,200,100,30);
        schedulesT.setFont(new Font("Tahoma", Font.BOLD,14));

        schedules.setDefaultEditor(Object.class,null);
        schedules.getTableHeader().setReorderingAllowed(false);
        schedules.setBounds(10,230,150,150);
        schedules.setRowSelectionAllowed(false);
        schedules.setColumnSelectionAllowed(false);
        schedules.setCellSelectionEnabled(false);
        JScrollPane spSchedules = new JScrollPane(schedules);
        schedules.getTableHeader().setBackground(Color.DARK_GRAY);
        schedules.getTableHeader().setForeground(Color.WHITE);
        schedules.setSelectionBackground(Color.CYAN);
        schedules.setSelectionForeground(Color.BLACK);
        schedules.setFont(new Font("Tahoma", Font.PLAIN,12));
        spSchedules.setBounds(10,230,150,150);
        spSchedules.setFont(new Font("Tahoma", Font.BOLD,14));

        JLabel schedulesStatusT=new JLabel("Schedules Status: ");
        schedulesStatusT.setBounds(10,20,200,30);
        schedulesStatusT.setFont(new Font("Tahoma", Font.BOLD,14));

        schedulesStatus.setDefaultEditor(Object.class,null);
        schedulesStatus.getTableHeader().setReorderingAllowed(false);
        schedulesStatus.setBounds(10,30,320,140);
        schedulesStatus.setRowSelectionAllowed(false);
        schedulesStatus.setColumnSelectionAllowed(false);
        schedulesStatus.setCellSelectionEnabled(false);
        JScrollPane spSchedulesStatus = new JScrollPane(schedulesStatus);
        schedulesStatus.getTableHeader().setBackground(Color.DARK_GRAY);
        schedulesStatus.getTableHeader().setForeground(Color.WHITE);
        schedulesStatus.setSelectionBackground(Color.CYAN);
        schedulesStatus.setSelectionForeground(Color.BLACK);
        schedulesStatus.setFont(new Font("Tahoma", Font.PLAIN,12));
        spSchedulesStatus.setBounds(10,50,320,140);
        spSchedulesStatus.setFont(new Font("Tahoma", Font.BOLD,14));

        JLabel cancellationsT=new JLabel("Cancellations: ");
        cancellationsT.setBounds(200,200,200,30);
        cancellationsT.setFont(new Font("Tahoma", Font.BOLD,14));

        cancellations.setDefaultEditor(Object.class,null);
        cancellations.getTableHeader().setReorderingAllowed(false);
        cancellations.setBounds(200,230,180,150);
        cancellations.setRowSelectionAllowed(false);
        cancellations.setColumnSelectionAllowed(false);
        cancellations.setCellSelectionEnabled(false);
        JScrollPane spCancellations = new JScrollPane(cancellations);
        cancellations.getTableHeader().setBackground(Color.DARK_GRAY);
        cancellations.getTableHeader().setForeground(Color.WHITE);
        cancellations.setSelectionBackground(Color.CYAN);
        cancellations.setSelectionForeground(Color.BLACK);
        cancellations.setFont(new Font("Tahoma", Font.PLAIN,12));
        spCancellations.setBounds(200,230,180,150);
        spCancellations.setFont(new Font("Tahoma", Font.BOLD,14));

        p2.add(schedulesStatusT);
        p2.add(spSchedulesStatus);

        p2.add(schedulesT);
        p2.add(spSchedules);

        p2.add(cancellationsT);
        p2.add(spCancellations);

        JLabel driversCountT=new JLabel("Drivers Count: ");
        driversCountT.setBounds(10,0,200,30);
        driversCountT.setFont(new Font("Tahoma", Font.BOLD,14));
        driversCount.setBounds(180,0,200,30);
        driversCount.setFont(new Font("Tahoma", Font.PLAIN,14));
        p3.add(driversCountT);
        p3.add(driversCount);

        JLabel scheduleDriversT=new JLabel("Schedules IDs : ");
        scheduleDriversT.setBounds(10,20,250,30);
        scheduleDriversT.setFont(new Font("Tahoma", Font.BOLD,14));
        p3.add(scheduleDriversT);
        displayTextAreaS = new JTextArea(4, 10);
        displayTextAreaS.setEditable(false);
        JScrollPane displayPaneS = new JScrollPane(displayTextAreaS);
        displayPaneS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        displayPaneS.setBounds(10,55,100,100);
        p3.add(displayPaneS);

        this.add(comboBox);
        this.add(p1);
        this.add(p2);
        this.add(p3);
    }


    public JFrame getFrame(){
        return this;
    }


}
