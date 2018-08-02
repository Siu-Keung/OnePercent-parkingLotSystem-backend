package com.onepercent.ParkingLotApplication.controller;


import com.onepercent.ParkingLotApplication.config.WebSecurityConfig;
import com.onepercent.ParkingLotApplication.dto.LoginDTO;
import com.onepercent.ParkingLotApplication.dto.TokenDTO;
import com.onepercent.ParkingLotApplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController {

      @Autowired
      private LoginService loginService;
    @PostMapping("/auth/login")
    public TokenDTO login(@RequestBody LoginDTO loginDTO, HttpServletResponse httpResponse) throws Exception{

        String tokenAndRole= loginService.login(loginDTO);
        String token=tokenAndRole.split(" ")[0];
        String role=tokenAndRole.split(" ")[1];
        String id=tokenAndRole.split(" ")[2];
        String name=tokenAndRole.split(" ")[3];
        httpResponse.addHeader(WebSecurityConfig.AUTHORIZATION_HEADER,"Bearer "+token);
        TokenDTO tokenDTO=new TokenDTO();
        tokenDTO.setRole(role);
        tokenDTO.setUserId(id);
        tokenDTO.setName(name);
        tokenDTO.setToken("Bearer "+token);
        return   tokenDTO ;
    }

    @GetMapping("/userInfo")
    public String  checkWeb(HttpServletRequest httpServletRequest){
        return loginService.checkUserInfo(httpServletRequest);
    }

}
