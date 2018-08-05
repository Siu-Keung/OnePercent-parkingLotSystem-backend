package com.onepercent.ParkingLotApplication.service;

import com.onepercent.ParkingLotApplication.domain.User;
import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;

import java.util.List;

/**
 * @Author: Leon
 * @Description:
 * @Date: Create in 5:28 PM 8/5/2018
 * @Modified By:
 */
public interface  UserService {
    public List findAll();


    public List<User> findUsers(String type, String content) ;

    public User save(User user);

    public User update(int id, User user) throws ResourceNotFoundException;

    public String changeAccountStatus(int id) ;

    public User findUserByAccountName(String accountName) ;

    public boolean updateWorkStatus(Integer id,String status) throws Exception ;

    public List<User> findAllParkingBoys() ;

    public List<User> findParkingBoysBy(User user);

}
