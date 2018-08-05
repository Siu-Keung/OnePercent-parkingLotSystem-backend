package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.Indent;
import com.onepercent.ParkingLotApplication.domain.User;
import com.onepercent.ParkingLotApplication.dto.UserDTO;
import com.onepercent.ParkingLotApplication.service.IndentService;
import com.onepercent.ParkingLotApplication.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    private UserServiceImpl userService;

//    @PreAuthorize(" hasAnyAuthority('Admin','Manage','ParkingBoy')")
    @GetMapping("/users")
    public List<UserDTO> findAll(){
        List<UserDTO> usersDTO= new ArrayList<>();
        userService.findAll().stream().forEach(user -> usersDTO.add(new UserDTO((User)user)));
        return usersDTO;
    }

//    @PreAuthorize(" hasAnyAuthority('Admin','Manage')")
    @GetMapping("/users/{type}/{content}")
    public List<UserDTO> findUsers(@PathVariable String type, @PathVariable String content) {
        List<UserDTO> usersDTO= new ArrayList<>();
        userService.findUsers(type,content).stream()
                .forEach(user -> usersDTO.add(new UserDTO(user)));
        return usersDTO;
    }

//    @PreAuthorize(" hasAnyAuthority('Admin','Manage')")
    @PostMapping("/users")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

//    @PreAuthorize(" hasAnyAuthority('Admin','Manage')")
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

//    @PreAuthorize(" hasAnyAuthority('Admin', 'Manager','ParkingBoy')")
    @GetMapping("/users/{id}/unfinishedOrders")
    public List<Indent> getUserUnfinishedOrders(@PathVariable Integer id){
        return this.indentService.getAllUnfinishedIndents(id);
    }

//    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage', 'ParkingBoy')")
    @GetMapping("/users/currentAccountInfo")
    public User getCurrentUserInfo(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return this.userService.findUserByAccountName(userDetails.getUsername());
    }

    @GetMapping("/users/parkingBoys")
    public List<User> getAllParkingBoys(User user){
        return this.userService.findParkingBoysBy(user);
    }

//    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage','ParkingBoy')")
    @PatchMapping("/users/{id}/work")
    boolean updateWorkStatus(@PathVariable Integer id, @RequestParam String status) {

        try {
            return userService.updateWorkStatus(id, status);
        } catch (Exception e) {
            return false;
        }
    }

}
