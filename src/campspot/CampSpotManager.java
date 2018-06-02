package campspot;

import UTIL.GUIUtil;
import entity.CampSpot;
import entity.Conf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    JPanel drawCampSpot = drawCampSpots();
    JFrame frame = new JFrame();
    JPanel sidePanel = new JPanel();

    String filterPeople = " ";
    String filterParking = " ";
    String filterTent = " ";
    Double filterPrice = 0.0;
    String filterHandicap = " ";
    JSONParser parser = new JSONParser();
    CampSpot currentSpot;
    DateFormat acceptedDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date startDate = new Date(); // store today's date on startDate if user doesn't specify.
    Date endDate = null;
    JFormattedTextField date1Compare, date2Compare;


    public CampSpotManager(){
        initializeCamp();
        drawScreen();
    }

    public void drawScreen(){
        sidePanel = getSidePanel(" ", " ", " ", 0.0, " ");

        frame.setLayout(new GridLayout(0, 2));
        frame.setTitle("Camp Spot Manager");
        frame.setSize(850, 850);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawCampSpot = drawCampSpots();

        frame.add(drawCampSpot);

        frame.add(sidePanel);

        GUIUtil.toCenter(frame);

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
            // Disable if spot is unavailable
            if (spot.getDatesReserved().length > 0) {
                for (String date: spot.getDatesReserved()){
                    if (acceptedDateFormat.format(startDate).equals(date)) {
                        seats[i].setBackground(Color.LIGHT_GRAY);

                        System.out.println("Reservations present");
                        seats[i].setEnabled(false);
                        break;
                    } else {
                        seats[i].setBackground(firColor);
                    }
                }
            }
            // filter recommended people
            else if ((!filterPeople.equals(" ") && spot.getRecommendedPeople() < Integer.parseInt(filterPeople)) ||
                    // filter parking space
                    (!filterParking.equals(" ") && spot.getParkingSpace() < Integer.parseInt(filterParking)) ||
                    // filter tent
                    (!filterTent.equals(" ") && spot.getTentSpace() < Integer.parseInt(filterTent)) ||
                    // filter price
                    (filterPrice != 0.0 && spot.getPrice() > filterPrice) ||
                    // filter handicap
                    (!filterHandicap.equals(" "))){
                // filter handicap
                if ((filterHandicap.equals("Y") && spot.isHandicap() == true) ||
                        (filterHandicap.equals("N") && spot.isHandicap() == false)){
                    seats[i].setBackground(firColor);
                    seats[i].setEnabled(true);
                }
                else {
                    seats[i].setBackground(Color.LIGHT_GRAY);
                    seats[i].setEnabled(false);
                }
            }
            else {
                seats[i].setBackground(firColor);
            }
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
                        currentSpot = getCampSpot(labelName);
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

    public JPanel getSidePanel(String people, String parking, String tent, Double prices, String handicaps){
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(3, 0, 0, 100));
        filterPanel.add(drawFilter(people, parking, tent, prices, handicaps));
        filterPanel.add(drawCampSpotInfo());

        // Panel that holds compare and Reserve buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 2, 5, 50));

        // Panel that holds compare
        JPanel comparePanel = new JPanel();
        comparePanel.setLayout(new GridLayout(4, 2, 20, 0));

        // Panel that holds cancel and reserve
        JPanel nextPage = new JPanel();
        JButton cancelButton = new JButton("Cancel");
        nextPage.add(cancelButton);
        JButton reserveButton = new JButton("Reserve");
        nextPage.add(reserveButton);

        // Compare panel information
        DateFormat acceptedDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        JLabel date1Label = new JLabel("Date 1: ");
        date1Compare = new JFormattedTextField(acceptedDateFormat);
        JLabel date2Label = new JLabel("Date 2: ");
        date2Compare = new JFormattedTextField(acceptedDateFormat);
        comparePanel.add(date1Label);
        comparePanel.add(date1Compare);
        comparePanel.add(date2Label);
        comparePanel.add(date2Compare);
        JButton threadButton = new JButton("Compare");
        comparePanel.add(threadButton);

        bottomPanel.add(comparePanel);
        bottomPanel.add(nextPage);
        filterPanel.add(bottomPanel);

        threadButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (date1Compare.getText().equals("") || date2Compare.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please enter 2 dates to compare");
                }
                else {
                    new CampSpotCompareController(date1Compare.getText(), date2Compare.getText());
                }

            }
        });

        cancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });

        // Add reservation to reservation.json
        reserveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (currentSpot == null){
                    JOptionPane.showMessageDialog(frame, "Please select a camp spot");
                    return;
                }
                try{
                    Object obj = parser.parse(new FileReader("data/reservation.json"));
                    Object campSpotParser = parser.parse(new FileReader("data/CampSpotManager.json"));
                    JSONObject reservations = (JSONObject) obj;
                    JSONArray campsArr = (JSONArray) campSpotParser;
                    JSONArray array;
                    // If account exists
                    if (reservations.containsKey(Conf.account)){
                        array = (JSONArray) reservations.get(Conf.account);
                    }
                    else{
                        array = new JSONArray();
                    }
                    // Create new Object and append to array
                    JSONObject newReservation = new JSONObject();
                    newReservation.put("pricePerDay", currentSpot.getPrice());
                    newReservation.put("startTime", acceptedDateFormat.format(startDate));
                    if (endDate == null) {
                        String tempEndDate = acceptedDateFormat.format(getNextDate(startDate));
                        endDate = acceptedDateFormat.parse(tempEndDate);
                    }
                    //System.out.println(endDate);
                    newReservation.put("endTime", acceptedDateFormat.format(endDate));
                    newReservation.put("reserveTime", acceptedDateFormat.format(new Date()));
                    JSONObject campSpotData = new JSONObject();
                    campSpotData.put("label", currentSpot.getLabel());
                    campSpotData.put("parkingSpace", currentSpot.getParkingSpace());
                    campSpotData.put("recommendedPeople", currentSpot.getRecommendedPeople());
                    campSpotData.put("tentSpace", currentSpot.getTentSpace());
                    campSpotData.put("handicap", currentSpot.isHandicap());
                    newReservation.put("campSpot", campSpotData);
                    array.add(newReservation);

                    for (int i = 0; i<campsArr.size(); i++) {
                        JSONObject currCamp = (JSONObject) campsArr.get(i);
                        if(currCamp.get("label").equals(currentSpot.getLabel())) {
                            JSONArray datesForReservation = (JSONArray) currCamp.get("reservations");
                            ArrayList<Date> datesReserved = getDatesBetween(startDate, endDate);
                            for (Date currDate: datesReserved)
                                datesForReservation.add(acceptedDateFormat.format(currDate));
                        }
                    }

                    reservations.put(Conf.account, array);
                    FileWriter reservationFile = new FileWriter("data/reservation.json", false);
                    FileWriter campManagerFile = new FileWriter("data/CampSpotManager.json", false);
                    reservationFile.write(reservations.toJSONString());
                    reservationFile.flush();
                    reservationFile.close();
                    campManagerFile.write(campsArr.toJSONString());
                    campManagerFile.flush();
                    campManagerFile.close();
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
                JOptionPane.showMessageDialog(frame, "Your reservation has been submitted!");
                frame.dispose();
            }
        });
        return filterPanel;
    }

    private static ArrayList<Date> getDatesBetween(Date startDate, Date endDate) {
        ArrayList<Date> datesInRange = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        datesInRange.add(endDate);
        return datesInRange;
    }

    public JPanel drawFilter(String people, String parking, String tent, Double prices, String handicaps){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(8, 2, 0, 5));
        // Recommended people
        String[] recommendedPeopleOptions = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Over 10"};
        JLabel recommendedPeople = new JLabel("People in Party: ");
        JComboBox recommendedPeopleBox = new JComboBox(recommendedPeopleOptions);
        recommendedPeopleBox.setSelectedItem(people);
        jPanel.add(recommendedPeople);
        jPanel.add(recommendedPeopleBox);

        // Parking spaces
        String[] parkingSpaceOptions = {" ", "1", "2", "3"};
        JLabel parkingSpace = new JLabel("Vehicles: ");
        JComboBox parkingSpaceBox = new JComboBox(parkingSpaceOptions);
        parkingSpaceBox.setSelectedItem(parking);
        jPanel.add(parkingSpace);
        jPanel.add(parkingSpaceBox);

        // Tent spaces
        String[] tentSpaceOptions = {" ", "1", "2", "3", "4", "5"};
        JLabel tentSpace = new JLabel("Tents: ");
        JComboBox tentSpaceBox = new JComboBox(tentSpaceOptions);
        tentSpaceBox.setSelectedItem(tent);
        jPanel.add(tentSpace);
        jPanel.add(tentSpaceBox);

        // Price
        JLabel price = new JLabel("Below Price: ");
        JTextField priceField = new JTextField("", 5);
        priceField.setText(prices.toString());
        jPanel.add(price);
        jPanel.add(priceField);

        // Handicap
        String[] handicapOptions = {" ", "Y", "N"};
        JLabel handicap = new JLabel("Handicap: ");
        JComboBox handicapBox = new JComboBox(handicapOptions);
        handicapBox.setSelectedItem(handicaps);
        jPanel.add(handicap);
        jPanel.add(handicapBox);

        JLabel startDate = new JLabel("Start date: ");

        JFormattedTextField startDateTextField = new JFormattedTextField(acceptedDateFormat);
        startDateTextField.setValue(this.startDate);

        jPanel.add(startDate);
        jPanel.add(startDateTextField);

        JLabel endDate = new JLabel("End date: ");
        JFormattedTextField endDateTextField = new JFormattedTextField(acceptedDateFormat);
        if(this.endDate != null) {
            endDateTextField.setValue(this.endDate);
        }

        jPanel.add(endDate);
        jPanel.add(endDateTextField);


        JButton filterButton = new JButton("Filter");
        jPanel.add(filterButton);

        filterButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                    // Get filter Information
                    String people = recommendedPeopleBox.getSelectedItem().toString();
                    if (people.equals("Over 10")) people = "10";
                    String parking = parkingSpaceBox.getSelectedItem().toString();
                    String tent = tentSpaceBox.getSelectedItem().toString();
                    Double price;
                    if (priceField.getText().equals("")) price = 0.0;
                    else price = Double.parseDouble(priceField.getText());
                    String handicap = handicapBox.getSelectedItem().toString();
                    Date tempStartDate = acceptedDateFormat.parse(startDateTextField.getText());
                    String tempStartDateStr = acceptedDateFormat.format(tempStartDate);
                    System.out.println("this is temp date str " + tempStartDateStr);
                    Date startDate = acceptedDateFormat.parse(tempStartDateStr);
                    System.out.println("this is start date " + startDate);
                    Date endDate;
                    if(endDateTextField.getText().isEmpty())
                        endDate = getNextDate(startDate);
                    else
                        endDate = acceptedDateFormat.parse(endDateTextField.getText());
                    setFilter(people, parking ,tent, price, handicap, startDate, endDate);


                    //if(startDateTextField.getText().equals())
                    // Refresh CampSpots and CampSpotsInfo, keep same filter settings
                    frame.getContentPane().remove(drawCampSpot);
                    frame.getContentPane().remove(sidePanel);
                    drawCampSpot = drawCampSpots();
                    frame.add(drawCampSpot);
                    sidePanel = getSidePanel(people, parking, tent, price, handicap);
                    frame.add(sidePanel);
                    frame.getContentPane().invalidate();
                    frame.getContentPane().validate();

                    currentSpot = null;
                }
                catch (NumberFormatException | ParseException er){
                    if(er instanceof  NumberFormatException)
                        JOptionPane.showMessageDialog(frame, "Please enter a number in price");
                    else
                        JOptionPane.showMessageDialog(frame, "Use mm/dd/yyyy format for dates");
                }

            }
        });
        return jPanel;
    }

    public void setFilter(String people, String parking, String tent, Double price, String handicap, Date startDate, Date endDate){
        this.filterPeople = people;
        this.filterParking = parking;
        this.filterTent = tent;
        this.filterPrice = price;
        this.filterHandicap = handicap;
        this.startDate = startDate;
        this.endDate = endDate;
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

    /**
     * Utility function to increment today's date for default storage.
     * @param tempDate
     * @return
     */
    private Date getNextDate(Date tempDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(tempDate);
        c.add(Calendar.DATE, 1);
        Date nextDate = c.getTime();
        return nextDate;
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
