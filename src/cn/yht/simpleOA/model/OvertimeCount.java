package cn.yht.simpleOA.model;

import java.io.Serializable;

/**
 * Created by YHT on 2015/5/28.
 */
public class OvertimeCount implements Serializable {
    private Long id;
    private String year;
    private String month;
    private Double hours;

    private User user;

    public OvertimeCount(String year, String month, Double hours, User user) {
        this.year = year;
        this.month = month;
        this.hours = hours;
        this.user = user;
    }

    public OvertimeCount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
