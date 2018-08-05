package com.onepercent.ParkingLotApplication.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author Dylan Wei
 * @date 2018-08-02 14:38
 */
@Entity
@Table(name = "indent")
public class Indent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receiptNo = UUID.randomUUID().toString();
    private String carNo;
    private Integer coordinatorId;
    private Long parkingLotId;
    private String status = IndentStatus.PENDING;

    private Date createDate = new Date();

    public Indent() {
    }

    public Indent(String carNo) {
        this.carNo = carNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Integer getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Integer coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
