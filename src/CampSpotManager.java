import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Cassidy Tarng on 5/4/2018.
 */
public class CampSpotManager {

    ArrayList<CampSpot> campSpots = new ArrayList<>();
    String previousClickedCampLabel;
    JLabel label = new JLabel();
    JLabel parkingSpace = new JLabel();
    JLabel recommendedPeople = new JLabel();
    JLabel tentSpace = new JLabel();
    JLabel price = new JLabel();
    JLabel handicap = new JLabel();

    CampSpotManager(){
        initializeCamp();
        drawScreen();
    }

    public void drawScreen(){
        JPanel filterPanel = new JPanel();
        JButton nextButton = new JButton("Next");
        JButton previousButton = new JButton("Previous");
        filterPanel.add(nextButton);
        filterPanel.add(previousButton);
        JPanel infoPanel = drawCampSpotInfo();
        filterPanel.add(infoPanel);

        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(0, 2));
        frame.setTitle("Camp Spot Manager");
        frame.setSize(850, 850);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawCampSpots());

        frame.add(filterPanel);

    }

    public JPanel drawCampSpots(){
        JPanel campSpotPanel = new JPanel();
        campSpotPanel.setPreferredSize(new Dimension(300, 300));
        campSpotPanel.setLayout(new GridLayout(6, 8, 5, 50));

        Color firColor = Color.GREEN;

        JButton[] seats = new JButton[campSpots.size()];

        for (int i = 0; i < seats.length; i++) {
            seats[i] = new JButton(campSpots.get(i).getLabel());
            seats[i].setBackground(firColor);
            seats[i].setOpaque(true);
            seats[i].setBorder(null);
            seats[i].setBorderPainted(false);
            seats[i].setPreferredSize(new Dimension(40, 40));
            campSpotPanel.add(seats[i]);
            seats[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // Select
                    if (((JButton) e.getSource()).getBackground() == Color.GREEN){
                        // Makes previous selection green
                        if (previousClickedCampLabel != null){
                            int previousClickedIndex = getCampSpotIndex(previousClickedCampLabel);
                            seats[previousClickedIndex].setBackground(Color.GREEN);
                        }
                        // Makes current selection blue
                        String labelName = ((JButton) e.getSource()).getText();
                        previousClickedCampLabel = labelName;
                        CampSpot currentSpot = getCampSpot(labelName);
                        ((JButton) e.getSource()).setBackground(Color.decode("#80bfff"));

                        // Set Camp Spot Info
                        label.setText("Label: " + currentSpot.getLabel());
                        parkingSpace.setText("Parking Spaces: " + currentSpot.getParkingSpace());
                        recommendedPeople.setText("Recommended People: " + currentSpot.getRecommendedPeople());
                        tentSpace.setText("Tent Spaces: " + currentSpot.getTentSpace());
                        price.setText("Price: " + currentSpot.getPrice());
                        handicap.setText("Handicap: " + currentSpot.isHandicap());
                    }
                }
            });
        }

        return campSpotPanel;
    }

    public JPanel drawCampSpotInfo(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(6, 1));
        label.setText("Label: ---");
        jPanel.add(label);
        parkingSpace.setText("Parking Spaces: ---");
        jPanel.add(parkingSpace);
        recommendedPeople.setText("Recommended People: ---");
        jPanel.add(recommendedPeople);
        tentSpace.setText("Tent Spaces: ---");
        jPanel.add(tentSpace);
        price.setText("Price: ---");
        jPanel.add(price);
        handicap.setText("Handicap: ---");
        jPanel.add(handicap);
        return jPanel;
    }

    public void initializeCamp(){
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("FA" + i, 1, 4, 1, 30, false));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("FB" + i, 1, 4, 1, 30, false));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("GA" + i, 2, 8, 2, 50, false));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("GB" + i, 2, 8, 3, 50, false));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("HA" + i, 3, 12, 4, 65, true));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("HB" + i, 3, 12, 5, 65, true));
        }
    }

    public CampSpot getCampSpot(String label){
        for (int i = 0; i < campSpots.size(); i++){
            if (campSpots.get(i).getLabel().equals(label)) return campSpots.get(i);
        }
        return null;
    }

    public int getCampSpotIndex(String label){
        for (int i = 0; i < campSpots.size(); i++){
            if (campSpots.get(i).getLabel().equals(label)) return i;
        }
        return 0;
    }
}
