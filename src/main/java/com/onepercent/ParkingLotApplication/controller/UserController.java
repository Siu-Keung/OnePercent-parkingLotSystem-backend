package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.Indent;
import com.onepercent.ParkingLotApplication.domain.User;
import com.onepercent.ParkingLotApplication.dto.UserDTO;
import com.onepercent.ParkingLotApplication.service.IndentService;
import com.onepercent.ParkingLotApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private IndentService indentService;

    @Autowired
    private UserService userService;

//    @PreAuthorize(" hasAnyAuthority('Admin')")
    @GetMapping("/users")
    public List findAll(){
        return userService.findAll();
    }

//    @PreAuthorize(" hasAnyAuthority('Admin')")
    @GetMapping("/users/{type}/{content}")
    public List<User> findUsers(@PathVariable String type, @PathVariable String content) {
        return userService.findUsers(type,content);
    }

//    @PreAuthorize(" hasAnyAuthority('Admin')")
    @PostMapping("/users")
    public UserDTO save(@RequestBody User user){
        User newUser=userService.save(user);
        return new UserDTO(newUser);
    }

//    @PreAuthorize(" hasAnyAuthority('Admin')")
    @PutMapping("/users/{id}")
    public UserDTO update(@PathVariable int id, @RequestBody User user){
        User newUser= userService.update(id,user);
        return new UserDTO(newUser);
    }

//    @PreAuthorize(" hasAnyAuthority('Admin')")
    @PatchMapping("/users/{id}")
    public String  changeAccountStatus(@PathVariable int id){
        return userService.changeAccountStatus(id);
    }

//    @PreAuthorize(" hasAnyAuthority('Admin')")
    @GetMapping("/users/{id}/unfinishedOrders")
    public List<Indent> getUserUnfinishedOrders(@PathVariable Integer id){
        return this.indentService.getAllUnfinishedIndents(id);
    }

}
