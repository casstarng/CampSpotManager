package campspot;

/**
 * Created by Cassidy Tarng on 5/31/2018.
 */
public class CampSpotCompareController {

    public CampSpotCompareController(String date1, String date2){
        try{
            // Create 2 threads for CampSpotCompare
            Thread campSpotCompare1 = new CampSpotCompare(50,50, date1);
            Thread campSpotCompare2 = new CampSpotCompare(475,50, date2);
            campSpotCompare1.start();
            campSpotCompare2.start();

            campSpotCompare1.join();
            campSpotCompare2.join();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
