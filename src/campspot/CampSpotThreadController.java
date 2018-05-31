package campspot;

/**
 * Created by Cassidy Tarng on 5/31/2018.
 */
public class CampSpotThreadController {

    public CampSpotThreadController(String date1, String date2){
        try{
            Thread campSpotThread1 = new CampSpotThread(50,50, date1);
            Thread campSpotThread2 = new CampSpotThread(475,50, date2);
            campSpotThread1.start();
            campSpotThread2.start();

            campSpotThread1.join();
            campSpotThread2.join();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
