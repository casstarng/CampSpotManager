package campspot;

/**
 * Created by Cassidy Tarng on 5/31/2018.
 */
public class CampSpotThreadController {

    public CampSpotThreadController(){
        try{
            Thread campSpotThread1 = new CampSpotThread(50,50);
            Thread campSpotThread2 = new CampSpotThread(475,50);
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
