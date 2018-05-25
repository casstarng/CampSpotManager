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
    User user;

    public Reservation(int reserveID, int pricePerDay, Date reserveTime, Date startTime, Date endTime, User reservingUser) {
        this.reserveID = reserveID;
        this.pricePerDay = pricePerDay;
        this.reserveTime = reserveTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = reservingUser;
    }

    public int getReserveID() {
        return reserveID;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public User getUser(User user) {
        return user;
    }
}
