package campspot;

import entity.CampSpot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cassidy Tarng on 5/31/2018.
 */
public class CampSpotCompare extends Thread {

    JFrame frame = new JFrame();
    JSONParser parser = new JSONParser();
    ArrayList<CampSpot> campSpots = new ArrayList<>();
    JButton[] seats;
    String date;

    public CampSpotCompare(int x, int y, String date){
        this.date = date;
        frame.setLayout(new GridLayout(6, 8, 5, 50));
        frame.setTitle(date);
        frame.setSize(425, 850);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeCamp();

        frame.setLocation(x,y);

    }

    public void run(){
        try{
            // Initializes the CampSpot grid
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
                if (Arrays.asList(spot.getDatesReserved()).contains(date)){
                    seats[i].setEnabled(false);
                    seats[i].setBackground(Color.LIGHT_GRAY);
                }
                seats[i].setVisible(false);
                frame.add(seats[i]);
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        finally {
            startLoad();
        }
    }

    /**
     * Simulates a load using threads
     */
    public void startLoad(){

        SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
            /**
             * Adds a sleep to the thread
             */
            @Override
            protected Boolean doInBackground() throws Exception {
                for (int i = 0; i < seats.length; i++) {
                    Thread.sleep(100);
                    publish(i);
                }
                return true;
            }

            /**
             * Updates GUI
             */
            @Override
            protected void process(List<Integer> chunks) {
                int latestChunk = chunks.get(chunks.size()-1);
                seats[latestChunk].setVisible(true);
            }

        };

        worker.execute();
    }

    /**
     * Gets information from CampSpotManager.json to initialize
     */
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
                JSONArray datesReserved = (JSONArray) object.get("reservations");
                //System.out.println("number of dates this camp, " + i + "is booked for " + datesReserved.size());
                String[] datesReservedCamp = new String[datesReserved.size()];
                for(int j = 0; j < datesReserved.size(); j++) {
                    String strDateFromJson = (String) datesReserved.get(j);
                    datesReservedCamp[j] = strDateFromJson;
                }
                campSpots.add(new CampSpot(label, parking, people, tent, price, handicap, datesReservedCamp));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
