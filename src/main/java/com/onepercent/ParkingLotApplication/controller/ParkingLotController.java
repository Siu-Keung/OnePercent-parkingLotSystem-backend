package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.dto.Condition;
import com.onepercent.ParkingLotApplication.dto.Pagination;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-08-01 16:11
 */
@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {
    private ParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService){
        this.parkingLotService = parkingLotService;
    }

    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage') ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void addParkingLot(
            @RequestBody ParkingLot parkingLot){
        this.parkingLotService.addParkingLot(parkingLot);
    }

    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage')")
    @GetMapping("/{id}")
    public ParkingLot getParkingLotById(
            @PathVariable Long id){
        return this.parkingLotService.getParkingLotById(id);
    }

    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage')")
    @GetMapping
    public List<ParkingLot> getParkingLotsPaging(
            Condition condition,
            Pagination pagination){
        if(pagination == null)
            pagination = new Pagination(1, 9);
        if(pagination.getPage() == null)
            pagination.setPage(1);
        if(pagination.getSize() == null)
            pagination.setSize(9);
        if(condition.isEmpty())
            return this.parkingLotService.getParkingLotsPaging(PageRequest.of(pagination.getPage() - 1, pagination.getSize()));
        else
            return this.parkingLotService.getParkingLotsByCondition(condition, pagination);
    }

    // TODO: 2018-08-02 here bug still exists that cannot update the coordinator of the parking lot; 
    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateParkingLot(
            @PathVariable Long id,
            @RequestBody ParkingLot parkingLot){
        parkingLot.setId(id);
        this.parkingLotService.updateParkingLot(parkingLot);
    }

}
