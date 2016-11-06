package se.mbaeumer.glucometriq.models;

import java.util.Date;

/**
 * Created by martinbaumer on 06/11/16.
 */
public class FilterParam {
    private int userId;
    private Date fromDate;
    private Date toDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
