package com.onepercent.ParkingLotApplication.service;

import com.onepercent.ParkingLotApplication.domain.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Leon
 * @Description:
 * @Date: Create in 4:00 PM 8/1/2018
 * @Modified By:
 */

public class UserServiceTest {
    public UserService userService=new UserService();

    @Test
    public void should_return_all_Employee_When_call_getEmployeeList() {
        List<User> users= userService.findAll();
        Assert.assertEquals("[Employee{id=0, name='小明', age=20, gender='male', salary=1000}, Employee{id=1, name='小红', age=19, gender='female', salary=2000}]",
                users.toString());

    }

}