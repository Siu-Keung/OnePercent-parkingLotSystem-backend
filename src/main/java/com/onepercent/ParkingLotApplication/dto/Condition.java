package com.onepercent.ParkingLotApplication.dto;

/**
 * @author Dylan Wei
 * @date 2018-08-02 0:42
 */
public class Condition {
    private String phoneNumber;
    private String name;
    private Integer lessThan;
    private Integer greaterThan;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLessThan() {
        return lessThan;
    }

    public void setLessThan(Integer lessThan) {
        this.lessThan = lessThan;
    }

    public Integer getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(Integer greaterThan) {
        this.greaterThan = greaterThan;
    }
}
