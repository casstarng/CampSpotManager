package home;

import UTIL.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame implements ActionListener{
    


    public HomeScreen(){
        this.setSize(850, 850);
        GUIUtil.toCenter(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
