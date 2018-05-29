package entity;

import entity.User;

import java.util.Date;

/**
 * Updated 5/23 by Sai Raghav
 */
public class Reservation {
    long reserveID;
    int pricePerDay;
    Date reserveTime;
    Date startTime;
    Date endTime;
    String userAccount;



    public Reservation() {
    }

    public Reservation(long reserveID, int pricePerDay, Date reserveTime, Date startTime, Date endTime, String userAccount) {
        this.reserveID = reserveID;
        this.pricePerDay = pricePerDay;
        this.reserveTime = reserveTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userAccount = userAccount;
    }

    public long getReserveID() {
        return reserveID;
    }

    public void setReserveID(long reserveID) {
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

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

}
