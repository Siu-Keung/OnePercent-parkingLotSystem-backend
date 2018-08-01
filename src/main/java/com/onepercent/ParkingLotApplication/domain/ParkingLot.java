package com.onepercent.ParkingLotApplication.domain;

import javax.persistence.*;

/**
 * @author Dylan Wei
 * @date 2018-08-01 11:28
 */
@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer totalSize;
    private Integer spareSize;
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private User user;

    public ParkingLot(){

    }

    public ParkingLot(String name, Integer totalSize, Integer spareSize, Boolean available) {
        this.name = name;
        this.totalSize = totalSize;
        this.spareSize = spareSize;
        this.available = available;
    }

    public ParkingLot(Long id, String name, Integer totalSize, Integer spareSize, Boolean available) {
        this.id = id;
        this.name = name;
        this.totalSize = totalSize;
        this.spareSize = spareSize;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getSpareSize() {
        return spareSize;
    }

    public void setSpareSize(Integer spareSize) {
        this.spareSize = spareSize;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
