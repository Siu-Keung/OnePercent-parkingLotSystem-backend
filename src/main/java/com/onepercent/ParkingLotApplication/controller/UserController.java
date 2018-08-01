package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.User;
import com.onepercent.ParkingLotApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Leon
 * @Description:
 * @Date: Create in 11:18 AM 8/1/2018
 * @Modified By:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List findAll(){
        return userService.findAll();
    }

    @GetMapping("/users/{type}/{content}")
    public Optional<User> findUsers(@PathVariable String type, @PathVariable String content) {
        return userService.findUsers(type,content);
    }

    @PostMapping("/users")
    public User save(@RequestBody User user){
        return userService.save(user);
    }
//
//    @PutMapping("/users/{id}")
//    public User update(@PathVariable int id,@RequestBody User user){
//        return userService.update(id,user);
//    }

}
