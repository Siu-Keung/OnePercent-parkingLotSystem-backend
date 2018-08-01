package com.onepercent.ParkingLotApplication.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String loginName;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String loginFlag;//能否允许系统，0代表被冻结，1代表可以登录
    private String deleteFlag;//删除标记，0代表删除（前端页面不能显示），1代表未删除

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    public User() {
    }




    public User(int id, String name, String email, String phone, List<Role> roles) {
        Random random = new Random();
        String randomPassword="";
        String randomloginName="";
        for (int i=0;i<6;i++)
        {
            randomPassword+=random.nextInt(10);
            randomloginName+=random.nextInt(10);
        }
        this.id = id;
        this.loginName=randomloginName;
        this.name = name;
        this.password = randomPassword;
        this.email = email;
        this.phone = phone;
        this.loginFlag = "1";
        this.deleteFlag = "1";
        this.roles = roles;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
