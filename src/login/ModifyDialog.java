package login;

import UTIL.FileOpen;
import UTIL.GUIUtil;
import entity.Conf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyDialog extends  JDialog implements ActionListener {
    private JLabel lbMsg=new JLabel("Your ID：");
    private JLabel lbAccount=new JLabel(Conf.account);
    private JLabel lbPassword=new JLabel("Password");
    private JPasswordField pfPassword=new JPasswordField(Conf.password,10);
    private JLabel lbPassword2=new JLabel("Password again");
    private JPasswordField pfPassword2=new JPasswordField(Conf.password,10);
    private JLabel lbName=new JLabel("Please modify your name");
    private JTextField tfName=new JTextField(Conf.name,10);
    private JLabel lbPhone=new JLabel("Please modify your phone number");
    private JTextField tfPhone=new JTextField(Conf.name,10);
    private JButton btModify=new JButton("Confirm");
    private JButton btExit=new JButton("Exit");
    public ModifyDialog(JFrame frm) {
        super(frm,true);
        this.setLayout(new GridLayout(6,2));
        this.add(lbMsg);
        this.add(lbAccount);
        this.add(lbPassword);
        this.add(pfPassword);
        this.add(lbPassword2);
        this.add(pfPassword2);
        this.add(lbName);
        this.add(tfName);
        this.add(lbPhone);
        this.add(tfPhone);
        this.add(btModify);
        this.add(btExit);
        this.setSize(450,200);
        GUIUtil.toCenter(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btModify.addActionListener(this);
        btExit.addActionListener(this);
        this.setResizable(false);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btModify) {
            String password1=new String(pfPassword.getPassword());
            String password2=new String(pfPassword2.getPassword());
            if(!password1.equals(password2)) {
                JOptionPane.showMessageDialog(this,"Inconsistent passwords",
                	    "Inane warning",
                	    JOptionPane.WARNING_MESSAGE);
                return;
            }
            String name=tfName.getText();
            String phone=tfPhone.getText();
            Conf.password=password1;
            Conf.name=name;
            Conf.phone=phone;
            FileOpen.updateCustomer(Conf.account,password1,name,phone);
            JOptionPane.showMessageDialog(this,"Modified successfully");
            this.dispose();
        }
        else {
            this.dispose();
        }
    }
}