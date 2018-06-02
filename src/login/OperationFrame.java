package login;

import UTIL.GUIUtil;
import entity.Conf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationFrame extends JFrame implements ActionListener {
    private String welcomMsg="Plese Select:";
    private JLabel lblWelcome=new JLabel(welcomMsg);
    private JButton btQuery=new JButton("Your reservations");
    private JButton btModify=new JButton("Modify personal information");
    private JButton btExit=new JButton("Exit");
    public OperationFrame() {
        super("Current login: "+ Conf.account);
        this.setLayout(new GridLayout(4,1));
        this.add(lblWelcome);
        this.add(btQuery);
        this.add(btModify);
        this.add(btExit);
        this.setSize(300,250);
        GUIUtil.toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        /**********************Add listeners*******************************/
        btQuery.addActionListener(this);
        btModify.addActionListener(this);
        btExit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btQuery) {
        	//TODO use entity.Reservation object
            String message="Your reservations:\n";
            message+="UserID："+Conf.account+"\n";
            message+="Name："+Conf.name+"\n";
            message+="Phone："+Conf.phone+"\n";
            JOptionPane.showMessageDialog(this,message);
        }
        else if(e.getSource()==btModify) {
            new ModifyDialog(this);
        }
        else {
            JOptionPane.showMessageDialog(this,"Thanks and see you soon");
            System.exit(0);
        }
    }
}