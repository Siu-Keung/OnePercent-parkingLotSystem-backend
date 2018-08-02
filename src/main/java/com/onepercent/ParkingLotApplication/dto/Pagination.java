package com.onepercent.ParkingLotApplication.dto;

/**
 * @author Dylan Wei
 * @date 2018-08-02 8:39
 */
public class Pagination {
    private Integer page;
    private Integer size;

    public Pagination(){

    }

    public Pagination(int i, int i1) {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
