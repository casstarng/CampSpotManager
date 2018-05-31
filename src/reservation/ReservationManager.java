package reservation;

import UTIL.GUIUtil;
import entity.Conf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.swing.plaf.basic.BasicToolTipUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cassidy Tarng on 5/4/2018.
 */
public class ReservationManager extends JFrame{
    private JSONParser parser = new JSONParser();

    private JSONObject reservations;
    private JSONArray reservationArray;
    private JTable table;
    private DefaultTableModel tableModel;

    private JButton editButton;
    private JButton deleteButton;

    private String[] columns = {"pricePerDay", "reserveTime", "startTime", "endTime",
                                "label", "tentSpace", "parkingSpace", "handicap", "c"};
    private Object[][] data;

    public ReservationManager(){
        reservations = initialJson();





        reservationArray =  (JSONArray) reservations.get(Conf.account);
        data = new Object[reservationArray.size()][9];
        for(int i = 0; i < reservationArray.size(); i++){
            JSONObject jsonObject = (JSONObject) reservationArray.get(i);
            JSONObject campObject = (JSONObject) jsonObject.get("campspot");
            data[i][0] = jsonObject.get("pricePerDay");
            data[i][1] = jsonObject.get("reserveTime");
            data[i][2] = jsonObject.get("startTime");
            data[i][3] = jsonObject.get("endTime");
            data[i][4] = campObject.get("label");
            data[i][5] = campObject.get("tentSpace");
            data[i][6] = campObject.get("parkingSpace");
            data[i][7] = campObject.get("handicap");
            data[i][8] = campObject.get("recommendedPeople");
        }

        tableModel = new DefaultTableModel(data, columns);
        table = new JTable(tableModel);


        JScrollPane scrollPane = new JScrollPane(table);
        setLayout(new BorderLayout());


        add(scrollPane, BorderLayout.CENTER);

        editButton = new JButton("Edit");
        deleteButton = new JButton("delete");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        buttonPanel.add(editButton, constraints);

        constraints.gridx = 1;
        buttonPanel.add(deleteButton, constraints);

        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Your Reservation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        GUIUtil.toCenter(this);
        pack();
        setVisible(true);

    }

    private JSONObject initialJson(){
        try {
            Object o = parser.parse(new FileReader("data/reservation.json"));
            JSONObject reservations = (JSONObject) o;
            return reservations;
        }catch (Exception e){

        }
        return null;
    }
}
