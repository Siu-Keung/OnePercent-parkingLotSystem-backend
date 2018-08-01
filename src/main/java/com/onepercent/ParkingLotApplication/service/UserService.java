package com.onepercent.ParkingLotApplication.service;

import com.onepercent.ParkingLotApplication.domain.Role;
import com.onepercent.ParkingLotApplication.domain.User;
import com.onepercent.ParkingLotApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    public User save(User user) {
        Random random = new Random();
        String randomPassword="";
        String randomloginName="";
        for (int i=0;i<6;i++)
        {
            randomPassword+=random.nextInt(10);
            randomloginName+=random.nextInt(10);
        }
        user.setLoginName(randomloginName);
        user.setPassword(randomPassword);
        user.setLoginFlag("1");
        if (user.getRoles()==null){
            Role role=new Role(3,"Employee");
            List<Role> roles=new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);

        }
        userRepository.save(user);
        return user;
    }

    public User update(int id, User user) {
        User oldUser=userRepository.findById(id).get();
        if (oldUser==null) return null;
        if (user.getName()!=null)
            oldUser.setName(user.getName());
        if(user.getEmail()!=null)
            oldUser.setEmail(user.getEmail());
        if (user.getPhone()!=null)
            oldUser.setPhone(user.getPhone());
        if(user.getLoginFlag()!=null)
            oldUser.setLoginFlag(user.getLoginFlag());
        if(user.getRoles()!=null)
            oldUser.setRoles(user.getRoles());
        userRepository.save(oldUser);
        return oldUser;
    }

}
