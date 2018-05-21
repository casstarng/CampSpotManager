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

        Color firColor = new Color(12, 255, 23);

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
                    ((JButton) e.getSource()).setBackground(Color.BLACK);
                    String text = ((JButton) e.getSource()).getText();
                    System.out.println(text);
                }
            });
        }

        return campSpotPanel;
    }

    public void initializeCamp(){
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("FA" + i, 1, 4, 1, 30, false));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("FB" + i, 1, 4, 1, 30, false));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("GA" + i, 1, 4, 1, 30, false));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("GB" + i, 1, 4, 1, 30, false));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("HA" + i, 1, 4, 1, 30, false));
        }
        for(int i = 1; i < 9; i++){
            campSpots.add(new CampSpot("HB" + i, 1, 4, 1, 30, false));
        }
    }
}
