package com.onepercent.ParkingLotApplication.service;

import com.onepercent.ParkingLotApplication.domain.User;
import com.onepercent.ParkingLotApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Leon
 * @Description:
 * @Date: Create in 11:26 AM 8/1/2018
 * @Modified By:
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List findAll() {
        return userRepository.findAll();
    }


    public Optional<User> findUsers(String type, String content) {
        Optional<User> users=Optional.empty();
        switch(type){
            case "id":{
                if (content!=null) {
                    Integer id = Integer.valueOf(content);
                    users=userRepository.findById(id);
                }
            }
            case "name":{
                users=userRepository.findByName(content);
            }

            case "email":{
                users=userRepository.findByEmail(content);
                break;
            }
            case "phone":{
                users=userRepository.findByPhone(content);
                break;
            }
            default:
                break;
        }

        return users;
    }
//
//    public User save(User user) {
//        return userRepository.save(user);
//    }
//
//    public User update(int id, User user) {
//        User oldUser=userRepository.findById(id).get();
//        if (oldUser==null) return null;
//        oldUser.setName(user.getName());
//        oldUser.setEmail(user.getEmail());
//        oldUser.setPhone(user.getPhone());
//        oldUser.setLoginFlag(user.getLoginFlag());
//        oldUser.setDeleteFlag(user.getDeleteFlag());
//        userRepository.save(oldUser);
//        return oldUser;
//    }

}
