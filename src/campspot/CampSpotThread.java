package campspot;

import UTIL.GUIUtil;
import entity.CampSpot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Cassidy Tarng on 5/31/2018.
 */
public class CampSpotThread extends Thread {

    JFrame frame = new JFrame();
    JSONParser parser = new JSONParser();
    ArrayList<CampSpot> campSpots = new ArrayList<>();
    JButton[] seats;

    public CampSpotThread(int x, int y){
        frame.setLayout(new GridLayout(6, 8, 5, 50));
        frame.setTitle("Camp Spot Manager");
        frame.setSize(425, 850);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeCamp();

        frame.setLocation(x,y);

    }

    public void run(){
        try{
            Color firColor = Color.GREEN;

            seats = new JButton[campSpots.size()];

            for (int i = 0; i < seats.length; i++) {
                CampSpot spot = campSpots.get(i);
                seats[i] = new JButton(spot.getLabel());

                seats[i].setOpaque(true);
                seats[i].setBorder(null);
                seats[i].setBorderPainted(false);
                seats[i].setBackground(firColor);
                seats[i].setPreferredSize(new Dimension(40, 40));
                seats[i].setVisible(false);
                frame.add(seats[i]);
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        finally {
            startHide();
        }
    }

    public void startHide(){

        SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                for (int i = 0; i < seats.length; i++) {
                    Thread.sleep(500);
                    publish(i);
                }
                return true;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int latestChunk = chunks.get(chunks.size()-1);
                seats[latestChunk].setVisible(true);
            }

        };

        worker.execute();
    }

    public void initializeCamp(){
        try{
            Object obj = parser.parse(new FileReader("data/CampSpotManager.json"));
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.size(); i++){
                JSONObject object = (JSONObject) jsonArray.get(i);
                String label = object.get("label").toString();
                int parking = Integer.parseInt(object.get("parkingSpace").toString());
                int people = Integer.parseInt(object.get("recommendedPeople").toString());
                int tent = Integer.parseInt(object.get("tentSpace").toString());
                Double price = Double.parseDouble(object.get("price").toString());
                boolean handicap = (Boolean) object.get("handicap");
                boolean available = (Boolean) object.get("available");

                campSpots.add(new CampSpot(label, parking, people, tent, price, handicap, available));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public JPanel drawCampSpots(){
        JPanel campSpotPanel = new JPanel();
        campSpotPanel.setPreferredSize(new Dimension(300, 300));
        campSpotPanel.setLayout(new GridLayout(6, 8, 5, 50));

        Color firColor = Color.GREEN;

        JButton[] seats = new JButton[campSpots.size()];

        for (int i = 0; i < seats.length; i++) {
            CampSpot spot = campSpots.get(i);
            seats[i] = new JButton(spot.getLabel());

            seats[i].setOpaque(true);
            seats[i].setBorder(null);
            seats[i].setBorderPainted(false);
            seats[i].setBackground(firColor);
            seats[i].setPreferredSize(new Dimension(40, 40));
            frame.add(seats[i]);

        }

        return campSpotPanel;
    }
}
