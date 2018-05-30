package reservation;

import entity.Conf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public ReservationManager(){
        reservations = initialJson();
        setTitle("Your Reservation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        reservationArray =  (JSONArray) reservations.get(Conf.account);
        if (reservationArray != null){
            List<String> columnList = new ArrayList<>();
            JSONObject oneObject = (JSONObject) reservationArray.get(0);
            Iterator<?> it = oneObject.keySet().iterator();
            while (it.hasNext()){
                columnList.add(it.next().toString());
            }
        }


    }

    private JSONObject initialJson(){
        try {
            Object o = parser.parse(new FileReader("data\\reservation.json"));
            JSONObject reservations = (JSONObject) o;
            return reservations;
        }catch (Exception e){

        }
        return null;
    }
}
