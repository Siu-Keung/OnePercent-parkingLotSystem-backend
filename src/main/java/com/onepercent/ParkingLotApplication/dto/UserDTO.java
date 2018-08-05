package com.onepercent.ParkingLotApplication.dto;

import com.onepercent.ParkingLotApplication.domain.Role;
import com.onepercent.ParkingLotApplication.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Leon
 * @Description:
 * @Date: Create in 9:59 PM 8/1/2018
 * @Modified By:
 */
public class UserDTO {

    private final int id;
    private final String name;//登录名
    private final String userName;//用户真实名字
    private final String password;
    private final String email;
    private final String phone;
    private final String loginFlag;
    private final List<Role> roles;//能否允许系统，0代表被冻结，1代表可以登录

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.loginFlag=user.getLoginFlag();
        this.roles = user.getRoles();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
