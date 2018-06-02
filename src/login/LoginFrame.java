package login;

import UTIL.FileOpen;
import UTIL.GUIUtil;
import entity.Conf;
import home.HomeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private Icon welcomeIcon=new ImageIcon("data/AR-CAMP.png");
    private JLabel lbWelcome=new JLabel(welcomeIcon);
    private JLabel lbAccount=new JLabel("UserID");
    private JTextField tfAccount=new JTextField(10);
    private JLabel lbPassword=new JLabel("Password");
    private JPasswordField pfPassword=new JPasswordField(10);
    private JButton btLogin=new JButton("Login");
    private JButton btRegister=new JButton("Register");
    private JButton btExit=new JButton("Exit");
    public LoginFrame() {
        super("WELCOME");
        this.setLayout(new FlowLayout());
        this.add(lbWelcome);
        this.add(lbAccount);
        this.add(tfAccount);
        this.add(lbPassword);
        this.add(pfPassword);
        this.add(btLogin);
        this.add(btRegister);
        this.add(btExit);
        this.setSize(200,230);
        GUIUtil.toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        /*****************************add ActionListeners************************/
        btLogin.addActionListener(this);
        btRegister.addActionListener(this);
        btExit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btLogin) {
            String account=tfAccount.getText();
            String password=new String(pfPassword.getPassword());
            FileOpen.getInfoByAccount(account);
            if(Conf.account==null||!Conf.password.equals(password)) {
                JOptionPane.showMessageDialog(this,"Login failed");
                return;
            }
            JOptionPane.showMessageDialog(this,"Login successfully");
            this.dispose();
            new HomeScreen();
        }
        else if(e.getSource()==btRegister) {
            this.dispose();
            new RegisterFrame();
        }
        else {
            JOptionPane.showMessageDialog(this,"Thanks and see you soon");
            System.exit(0);
        }
    }
}