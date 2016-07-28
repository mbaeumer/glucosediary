package se.mbaeumer.glucosediary.models;

import java.util.Date;

/**
 * Created by martinbaumer on 27/07/16.
 */
public class GlucoseMeasurement {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

    public Date getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Date measurementTime) {
        this.measurementTime = measurementTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int id;
    private double measurement;
    private Date measurementTime;
    private String comment;
    private Date created;
    private Date lastUpdated;
    private User user;

}
