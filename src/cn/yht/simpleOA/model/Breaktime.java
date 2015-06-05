package cn.yht.simpleOA.model;

import java.io.Serializable;

/**
 * Created by YHT on 2015/5/19.
 */
public class Breaktime implements Serializable{
    private Long id;
    private String userName;
    private Double duration;
    private String date;
    private String year;
    private String month;
    private String description;

    private Long userId;

    private User user;

    public Breaktime() {
    }

    public Breaktime(String userName, User user, int duration, String date, String year, String month, String description) {
        this.userName = userName;
        this.duration = (double) duration;
        this.date = date;
        this.year = year;
        this.month = month;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
