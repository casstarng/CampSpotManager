package reservation;

import UTIL.GUIUtil;
import entity.Conf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by Cassidy Tarng on 5/4/2018.
 */
public class ReservationManager extends JFrame implements ActionListener{
    private JSONParser parser = new JSONParser();

    private JSONObject reservations;
    private JSONArray reservationArray;
    private JTable table;
    private DefaultTableModel tableModel;

    //private JButton editButton;
    private JButton deleteButton;

    private String[] columns = {"pricePerDay", "reserveTime", "startTime", "endTime",
                                "label", "tentSpace", "parkingSpace", "handicap", "recommendedPeople"};
    private Object[][] data;

    public ReservationManager(){
        reservations = initialJson();





        reservationArray =  (JSONArray) reservations.get(Conf.account);
        if (reservationArray == null){
            data = new Object[0][9];
        }else{
            data = new Object[reservationArray.size()][9];
            for(int i = 0; i < reservationArray.size(); i++){
                JSONObject jsonObject = (JSONObject) reservationArray.get(i);
                JSONObject campObject = (JSONObject) jsonObject.get("campSpot");
                data[i][0] = jsonObject.get("pricePerDay");
                data[i][1] = jsonObject.get("reserveTime");
                data[i][2] = jsonObject.get("startTime");
                data[i][3] = jsonObject.get("endTime");
                data[i][4] = campObject.get("label");
                data[i][5] = campObject.get("tentSpace");
                data[i][6] = campObject.get("parkingSpace");
                data[i][7] = !(boolean) campObject.get("handicap") ? "No" : "Yes";
                data[i][8] = campObject.get("recommendedPeople");
            }
        }


        tableModel = new DefaultTableModel(data, columns);
        table = new JTable(tableModel);


        JScrollPane scrollPane = new JScrollPane(table);
        setLayout(new BorderLayout());


        add(scrollPane, BorderLayout.CENTER);

        //editButton = new JButton("Edit");
        deleteButton = new JButton("delete");
        //editButton.addActionListener(this);
        deleteButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        //buttonPanel.add(editButton, constraints);
        buttonPanel.add(deleteButton, constraints);
        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Your Reservation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 500);
        GUIUtil.toCenter(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton){
            delete();
        }
    }

    private void edit(){
        int row = table.getSelectedRow();
        System.out.println("Select row : " + row);
    }

    private void delete(){
        int row = table.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(this,"Please select one record!");
            return;
        }
        reservationArray.remove(row);

        reservations.put(Conf.account, reservationArray);

        try {
            FileWriter reservationFile = new FileWriter("data/reservation.json", false);
            reservationFile.write(reservations.toJSONString());
            reservationFile.flush();
            reservationFile.close();

            tableModel.removeRow(row);
            tableModel.fireTableDataChanged();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void updateData(){

    }
}
