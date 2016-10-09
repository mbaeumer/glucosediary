package se.mbaeumer.glucometriq.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by martinbaumer on 27/07/16.
 */
@Entity
@Table(name="GLUCOSEMEASUREMENT")
@javax.persistence.SequenceGenerator(name = "SEQ_GLUCOSEMEASUREMENT", sequenceName = "SEQ_GLUCOSEMEASUREMENT")
public class GlucoseMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GLUCOSEMEASUREMENT")
    private int id;
    @Column(name = "GLUCOSEVALUE")
    private BigDecimal glucoseValue;
    @Column(name = "MEASUREDATE")
    private Date measureDate;
    @ManyToOne
    @NotNull
    @JoinColumn(name="USERID")
    private User user;

    @Column(name = "COMMENT")
    private String comment;
    /*
    private Date created;
    private Date lastUpdated;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /*
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

    */
}
