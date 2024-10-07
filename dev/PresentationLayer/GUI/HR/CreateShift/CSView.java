package PresentationLayer.GUI.HR.CreateShift;

import PresentationLayer.GUI.HR.BasicPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CSView extends BasicPage {
    private JComboBox comboBox;
    private JPanel p1,p2,p3;
    public JTextField year,month,day,shiftManagerCountTB,storeKeeperCountTB,cashierCountTB,generalEmployeeCountTB,guardCountTB,cleanerCountTB,
            merchandiserCountTB,driverCountTB;
    public JComboBox selectBranch,selectType;
    public JButton button1,button2;

    public CSView() {
        super("Creating Shift");
        createUIComponents();
    }

    private void createUIComponents(){
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        JLabel label=new JLabel();
        label.setText("Choose shift type :");
        label.setSize(1000,100);
        label.setFont(new Font("Tahoma", Font.BOLD,20));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(100,30,500,100);
        this.add(label);

        p1.setBackground(new Color(121, 198, 252));
        p1.setBounds(180,100,400,70);
        p2.setBackground(new Color(121, 198, 252));
        p2.setBounds(200,170,280,250);
        p3.setBackground(new Color(121, 198, 252));
        p3.setBounds(200,170,200,80);

        //Date
        JLabel date=new JLabel();
        date.setText("Date of shift :");
        date.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        date.setBounds(-10,-10,50,50);

        JLabel yearLabel=new JLabel();
        yearLabel.setText("Year :");
        yearLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        yearLabel.setBounds(-10,0,50,50);

        year=new JTextField();
        year.setPreferredSize(new Dimension(30,20));
        year.setBounds(40,0,100,20);

        JLabel monthLabel=new JLabel();
        monthLabel.setText("Month :");
        monthLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        monthLabel.setBounds(70,0,50,50);

        month=new JTextField();
        month.setPreferredSize(new Dimension(30,20));
        month.setBounds(120,0,30,20);

        JLabel dayLabel=new JLabel();
        dayLabel.setText("Day :");
        dayLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        dayLabel.setBounds(150,0,50,50);

        day=new JTextField();
        day.setPreferredSize(new Dimension(30,20));
        day.setBounds(200,0,30,20);

        p1.add(date);
        p1.add(yearLabel);
        p1.add(year);
        p1.add(monthLabel);
        p1.add(month);
        p1.add(dayLabel);
        p1.add(day);

        //Shift manager count
        JLabel shiftManagerCount=new JLabel();
        shiftManagerCount.setText("Shift Manager Count :");
        shiftManagerCount.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        shiftManagerCount.setBounds(0,-10,150,50);
        shiftManagerCountTB=new JTextField();
        shiftManagerCountTB.setPreferredSize(new Dimension(100,20));
        shiftManagerCountTB.setBounds(150,5,100,20);

        //Storekeeper count
        JLabel storeKeeperCount=new JLabel();
        storeKeeperCount.setText("Store Keeper Count :");
        storeKeeperCount.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        storeKeeperCount.setBounds(0,10,150,50);
        storeKeeperCountTB=new JTextField();
        storeKeeperCountTB.setPreferredSize(new Dimension(100,20));
        storeKeeperCountTB.setBounds(150,25,100,20);

        //Cashier count
        JLabel cashierCount=new JLabel();
        cashierCount.setText("Cashier Count :");
        cashierCount.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        cashierCount.setBounds(0,30,150,50);
        cashierCountTB=new JTextField();
        cashierCountTB.setPreferredSize(new Dimension(100,20));
        cashierCountTB.setBounds(150,45,100,20);

        //General Employee count
        JLabel generalEmployeeCount=new JLabel();
        generalEmployeeCount.setText("General Employee Count :");
        generalEmployeeCount.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        generalEmployeeCount.setBounds(0,50,200,50);
        generalEmployeeCountTB=new JTextField();
        generalEmployeeCountTB.setPreferredSize(new Dimension(100,20));
        generalEmployeeCountTB.setBounds(180,65,100,20);

        //Guard count
        JLabel guardCount=new JLabel();
        guardCount.setText("Guard Count :");
        guardCount.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        guardCount.setBounds(0,70,100,50);
        guardCountTB=new JTextField();
        guardCountTB.setPreferredSize(new Dimension(100,20));
        guardCountTB.setBounds(110,85,100,20);

        //Cleaner count
        JLabel cleanerCount=new JLabel();
        cleanerCount.setText("Cleaner Count :");
        cleanerCount.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        cleanerCount.setBounds(0,90,100,50);
        cleanerCountTB=new JTextField();
        cleanerCountTB.setPreferredSize(new Dimension(100,20));
        cleanerCountTB.setBounds(110,105,100,20);

        //Merchandiser count
        JLabel merchandiserCount=new JLabel();
        merchandiserCount.setText("Merchandiser Count :");
        merchandiserCount.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        merchandiserCount.setBounds(0,110,150,50);
        merchandiserCountTB=new JTextField();
        merchandiserCountTB.setPreferredSize(new Dimension(100,20));
        merchandiserCountTB.setBounds(140,125,100,20);

        //branch
        JLabel branch=new JLabel();
        branch.setText("Branch :");
        branch.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        branch.setBounds(0,130,150,50);
        String[] namesOfBranch={};
        selectBranch=new JComboBox(namesOfBranch);
        selectBranch.setSize(145,35);
        selectBranch.setBounds(140,145,145,35);

        //type
        JLabel type=new JLabel();
        type.setText("Type :");
        type.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        type.setBounds(0,130,150,50);
        String[] typesME={"M","E"};
        selectType=new JComboBox(typesME);
        selectType.setSize(145,35);
        selectType.setBounds(140,165,145,35);

        p2.add(shiftManagerCount);
        p2.add(shiftManagerCountTB);
        p2.add(storeKeeperCount);
        p2.add(storeKeeperCountTB);
        p2.add(cashierCount);
        p2.add(cashierCountTB);
        p2.add(generalEmployeeCount);
        p2.add(generalEmployeeCountTB);
        p2.add(guardCount);
        p2.add(guardCountTB);
        p2.add(cleanerCount);
        p2.add(cleanerCountTB);
        p2.add(merchandiserCount);
        p2.add(merchandiserCountTB);
        p2.add(branch);
        p2.add(selectBranch);
        p2.add(type);
        p2.add(selectType,BorderLayout.AFTER_LINE_ENDS);

        //submit button
        button2=new JButton("Submit");
        button2.setBackground(Color.WHITE);
        button2.setBounds(500,500,80,40);

        p2.add(button2);

        this.add(p1);
        this.add(p2);

        //Driver count
        JLabel driverCount=new JLabel();
        driverCount.setText("Driver count :");
        driverCount.setFont(new Font(Font.DIALOG,Font.PLAIN,14));
        driverCount.setBounds(0,0,70,20);
        driverCountTB=new JTextField();
        driverCountTB.setPreferredSize(new Dimension(100,20));
        driverCountTB.setBounds(0,20,100,20);
        driverCountTB.setVisible(false);

        //submit button
        button1=new JButton("Submit");
        button1.setBackground(Color.WHITE);
        button1.setBounds(500,500,80,40);

        p3.add(driverCount);
        p3.add(driverCountTB);
        p3.add(button1);
        this.add(p3);

        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);

        String[] types={"Branch Shift","Driver Shift"};
        comboBox=new JComboBox<>(types);
        comboBox.setSize(70,50);
        comboBox.setBounds(300,60,105,35);
        comboBox.setVisible(true);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox.getSelectedItem();
                switch (s) {
                    case "Branch Shift":
                        p1.setVisible(true);
                        p2.setVisible(true);
                        p3.setVisible(false);
                        button1.setVisible(false);
                        button2.setVisible(true);
                        break;
                    case "Driver Shift":
                        p1.setVisible(true);
                        p2.setVisible(false);
                        p3.setVisible(true);
                        driverCountTB.setVisible(true);
                        button1.setVisible(true);
                        button2.setVisible(false);
                        break;
                }
            }
        });

        this.add(comboBox);
    }

    public JFrame getFrame(){
        return this;
    }


}
