package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void addParkingLot(
            @RequestBody ParkingLot parkingLot){
        this.parkingLotService.addParkingLot(parkingLot);
    }



}
