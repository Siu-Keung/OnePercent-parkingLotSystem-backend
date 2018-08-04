package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.repository.ParkingLotRepository;
import com.onepercent.ParkingLotApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan Wei
 * @date 2018-07-31 11:29
 */
@RestController
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @RequestMapping("/")
    public String sayHello(){
        return "欢迎使用停车场管理系统！！！！！！";
    }

    @RequestMapping("/test11")
    public ParkingLot test11(){
        ParkingLot parkingLot = this.parkingLotRepository.findById(1L).get();
        parkingLot.setCoordinator(null);
        this.parkingLotRepository.save(parkingLot);
        return this.parkingLotRepository.findById(1L).get();
    }

}
