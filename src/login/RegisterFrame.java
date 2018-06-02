package login;

import UTIL.FileOpen;
import UTIL.GUIUtil;
import entity.Conf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    /**********************components********************************/
    private JLabel lbAccount=new JLabel("UserID");
    private JTextField tfAccount=new JTextField(10);
    private JLabel lbPassword=new JLabel("Password");
    private JPasswordField pfPassword=new JPasswordField(10);
    private JLabel lbPassword2=new JLabel("Password again");
    private JPasswordField pfPassword2=new JPasswordField(10);
    private JLabel lbName=new JLabel("Your name");
    private JTextField tfName=new JTextField(10);
    private JLabel lbPhone=new JLabel("Phone");
    private JTextField tfPhone=new JTextField(10);
    private JButton btRegister=new JButton("Register");
    private JButton btLogin=new JButton("Login");
    private JButton btExit=new JButton("Exit");
    public RegisterFrame() {
        /******************init********************/
        super("Register");
        this.setLayout(new FlowLayout());
        this.add(lbAccount);
        this.add(tfAccount);
        this.add(lbPassword);
        this.add(pfPassword);
        this.add(lbPassword2);
        this.add(pfPassword2);
        this.add(lbName);
        this.add(tfName);
        this.add(lbPhone);
        this.add(tfPhone);
        this.add(btRegister);
        this.add(btLogin);
        this.add(btExit);
        this.setSize(240,250);
        GUIUtil.toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        /****************************Add listeners***********************/
        btLogin.addActionListener(this);
        btRegister.addActionListener(this);
        btExit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btRegister) {
            String password1=new String(pfPassword.getPassword());
            String password2=new String(pfPassword2.getPassword());
            if(!password1.equals(password2)) {
                JOptionPane.showMessageDialog(this,"Inconsistent passwords",
                	    "Inane warning",
                	    JOptionPane.WARNING_MESSAGE);
                return;
            }
            String account=tfAccount.getText();
            FileOpen.getInfoByAccount(account);
            if(Conf.account!=null) {
                JOptionPane.showMessageDialog(this,"You have already registered",
                	    "Inane warning",
                	    JOptionPane.WARNING_MESSAGE);
                return;
            }
            String name=tfName.getText();
            String phone=tfPhone.getText();
            FileOpen.updateCustomer(account,password1,name,phone);
            JOptionPane.showMessageDialog(this,"Register Successfully");
        }
        else if(e.getSource()==btLogin) {
            this.dispose();
            new LoginFrame();
        }
        else {
            JOptionPane.showMessageDialog(this,"Thanks and see you soon");
            System.exit(0);
        }
    }
}