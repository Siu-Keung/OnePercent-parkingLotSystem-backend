package com.onepercent.ParkingLotApplication.controller;


import com.onepercent.ParkingLotApplication.config.WebSecurityConfig;
import com.onepercent.ParkingLotApplication.dto.LoginDTO;
import com.onepercent.ParkingLotApplication.dto.TokenDTO;
import com.onepercent.ParkingLotApplication.repository.UserRepository;
import com.onepercent.ParkingLotApplication.service.LoginService;
import com.onepercent.ParkingLotApplication.utils.JWTTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


@RestController
public class LoginController {

      @Autowired
      private LoginService loginService;
    @PostMapping("/auth/login")
    public TokenDTO login(@RequestBody LoginDTO loginDTO, HttpServletResponse httpResponse) throws Exception{

        String tokenAndRole= loginService.login(loginDTO);
        String token=tokenAndRole.split(" ")[0];
        String role=tokenAndRole.split(" ")[1];
        httpResponse.addHeader(WebSecurityConfig.AUTHORIZATION_HEADER,"Bearer "+token);
        TokenDTO tokenDTO=new TokenDTO();
        tokenDTO.setRole(role);
        tokenDTO.setToken("Bearer "+token);
        return   tokenDTO ;
    }
}
