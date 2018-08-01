package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
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
            @RequestParam(required = false)Integer page,
            @RequestParam(required = false)Integer size){
        if(page == null)
            page = 1;
        if(size == null)
            size = 9;
        return this.parkingLotService.getParkingLotsPaging(PageRequest.of(page - 1, size));
    }

    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateParkingLot(
            @RequestBody ParkingLot parkingLot){
        this.parkingLotService.updateParkingLot(parkingLot);
    }

}
