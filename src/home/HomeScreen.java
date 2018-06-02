package home;

import UTIL.GUIUtil;
import campspot.CampSpotManager;
import entity.Conf;
import login.ModifyDialog;
import reservation.ReservationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame implements ActionListener{

    private String welcomeString = "Welcome! " + Conf.name;
    private JLabel welcomeLabel = new JLabel(welcomeString);

    private JButton reserveButton;
    private JButton viewReservationButton;
    private JButton modifyButton;
    private JButton exitButton;




    public HomeScreen(){
        reserveButton = new JButton("Reserve");
        viewReservationButton = new JButton("View Reservation");
        modifyButton = new JButton("Modify personal info");
        exitButton = new JButton("Exit");

        reserveButton.addActionListener(this);
        viewReservationButton.addActionListener(this);
        modifyButton.addActionListener(this);
        exitButton.addActionListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(welcomeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(50, 0, 0, 0);
        add(reserveButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(viewReservationButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(modifyButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(exitButton, gbc);

        this.setSize(300, 300);
        GUIUtil.toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reserveButton){
            new CampSpotManager();
        }else if(e.getSource() == viewReservationButton){
            new ReservationManager();
        }else if(e.getSource() == modifyButton){
            new ModifyDialog(this);
        }else if(e.getSource() == exitButton){
            JOptionPane.showMessageDialog(this,"Thanks and see you soon");
            System.exit(0);
        }
    }


    public static void main(String[] args){
        new HomeScreen();
    }


}
