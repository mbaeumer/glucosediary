package se.mbaeumer.glucometriq.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by martinbaumer on 27/07/16.
 */
@Entity
@Table(name="GLUCOSEMEASUREMENT")
public class GlucoseMeasurement {
    @Id
    private int id;
    @Column(name = "GLUCOSEVALUE")
    private BigDecimal glucoseValue;
    @Column(name = "MEASUREDATE")
    private Date measureDate;
    /*
    private String comment;
    private Date created;
    private Date lastUpdated;
    private User user;
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getGlucoseValue() {
        return glucoseValue;
    }

    public void setGlucoseValue(BigDecimal glucoseValue) {
        this.glucoseValue = glucoseValue;
    }

    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    /*
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
    */
}
