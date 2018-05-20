import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cassidy Tarng on 5/4/2018.
 */
public class CampSpotManager {

    CampSpotManager(){
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

        String[] lblFir1 = { "FA1", "FA2", "FA3", "FA4","FA5", "FA6", "FA7", "FA8",
                "FB1", "FB2", "FB3", "FB4","FB5", "FB6", "FB7", "FB8",
                "GA1", "GA2", "GA3", "GA4","GA5", "GA6", "GA7", "GA8",
                "GB1", "GB2", "GB3", "GB4","GB5", "GB6", "GB7", "GB8",
                "HA1", "HA2", "HA3", "HA4","HA5", "HA6", "HA7", "HA8",
                "HB1", "HB2", "HB3", "HB4","HB5", "HB6", "HB7", "HB8"};
        JButton[] seats = new JButton[lblFir1.length];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = new JButton(lblFir1[i]);
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
}
