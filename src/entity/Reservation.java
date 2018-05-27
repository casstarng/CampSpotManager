package entity;

import entity.User;

import java.util.Date;

/**
 * Updated 5/23 by Sai Raghav
 */
public class Reservation {
    int reserveID;
    int pricePerDay;
    Date reserveTime;
    Date startTime;
    Date endTime;
    int userID;
    int campSpotID;

    public Reservation() {
    }

    public Reservation(int reserveID, int pricePerDay, Date reserveTime, Date startTime, Date endTime, int userID, int campSpotID) {
        this.reserveID = reserveID;
        this.pricePerDay = pricePerDay;
        this.reserveTime = reserveTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userID = userID;
        this.campSpotID = campSpotID;
    }

    public int getReserveID() {
        return reserveID;
    }

    public void setReserveID(int reserveID) {
        this.reserveID = reserveID;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCampSpotID() {
        return campSpotID;
    }

    public void setCampSpotID(int campSpotID) {
        this.campSpotID = campSpotID;
    }
}
