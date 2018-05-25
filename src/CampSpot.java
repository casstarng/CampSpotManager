/**
 * Created by Cassidy Tarng on 5/4/2018.
 */
import java.util.UUID;
public class CampSpot {
    UUID campSpotID;
    String label;
    int parkingSpace;
    int recommendedPeople;
    int tentSpace;
    double price;
    boolean handicap;
    boolean available;

    public CampSpot(String label, int parkingSpace, int recommendedPeople, int tentSpace, double price, boolean handicap, boolean available) {
        this.label = label;
        this.parkingSpace = parkingSpace;
        this.recommendedPeople = recommendedPeople;
        this.tentSpace = tentSpace;
        this.price = price;
        this.handicap = handicap;
        this.available = available;
        campSpotID = UUID.randomUUID();
    }

    public UUID getCampSpotID() {
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

    public String getLabel() {
        return label;
    }

    public boolean getAvailability() {return available;}

    public void setAvailability(boolean available) {this.available = available;}
}
