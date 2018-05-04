/**
 * Created by Cassidy Tarng on 5/4/2018.
 */
import java.util.UUID;
public class CampSpot {
    int campSpotID;
    int parkingSpace;
    int recommendedPeople;
    int tentSpace;
    double price;
    boolean handicap;

    public CampSpot() {
        campSpotID = UUID.randomUUID();
    }

    public int getCampSpotID() {
        return campSpotID;
    }

    public int getRecommendedPeople() {
        return recommendedPeople;
    }

    public boolean isHandicap() {
        return handicap;
    }

    public int getTentSpace() {
        return tentSpace;
    }

    public double getPrice() {
        return price;
    }

    public int getParkingSpace() {
        return parkingSpace;
    }

    public void setHandicap(boolean handicap) {
        this.handicap = handicap;
    }

    public void setParkingSpace(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRecommendedPeople(int recommendedPeople) {
        this.recommendedPeople = recommendedPeople;
    }

    public void setTentSpace(int tentSpace) {
        this.tentSpace = tentSpace;
    }
}
