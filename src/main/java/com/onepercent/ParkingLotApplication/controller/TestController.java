package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/")
    public String sayHello(){
        return "欢迎使用停车场管理系统！！！！！！";
    }

}
