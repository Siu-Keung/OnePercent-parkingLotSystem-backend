package com.onepercent.ParkingLotApplication.dto;

/**
 * @author Dylan Wei
 * @date 2018-08-02 0:42
 */
public class Condition {
    private String phoneNumber;
    private String name;
    private Integer lessThanEqual;
    private Integer greaterThanEqual;

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

    public Integer getLessThanEqual() {
        return lessThanEqual;
    }

    public void setLessThanEqual(Integer lessThanEqual) {
        this.lessThanEqual = lessThanEqual;
    }

    public Integer getGreaterThanEqual() {
        return greaterThanEqual;
    }

    public void setGreaterThanEqual(Integer greaterThanEqual) {
        this.greaterThanEqual = greaterThanEqual;
    }

    public boolean isEmpty(){
        return this.phoneNumber == null &&
                this.name == null &&
                this.greaterThanEqual == null &&
                this.lessThanEqual == null;
    }
}
