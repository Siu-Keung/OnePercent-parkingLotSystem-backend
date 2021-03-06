package com.onepercent.ParkingLotApplication.dto;

/**
 * @author Dylan Wei
 * @date 2018-08-02 20:26
 */
public class UpdateIndentParams {
    private String operation;
    private Integer coordinatorId;
    private Long parkingLotId;
    private String receiptNo;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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

}
