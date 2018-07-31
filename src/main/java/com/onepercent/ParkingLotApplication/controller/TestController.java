package com.onepercent.ParkingLotApplication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan Wei
 * @date 2018-07-31 11:29
 */
@RestController
public class TestController {

    @RequestMapping("/")
    public String sayHello(){
        return "欢迎使用！";
    }

}
