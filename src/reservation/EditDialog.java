package reservation;

import org.json.simple.JSONArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDialog extends JDialog implements ActionListener {

    private JSONArray reservationArray;
    public EditDialog(JSONArray reservationArray){
        this.reservationArray = reservationArray;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
