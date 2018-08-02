package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.domain.User;
import com.onepercent.ParkingLotApplication.dto.Condition;
import com.onepercent.ParkingLotApplication.repository.ParkingLotRepository;
import com.onepercent.ParkingLotApplication.repository.UserRepository;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage')")
    @RequestMapping("/test")
    public Object test(){
        return parkingLotRepository.findAll();
    }


}
