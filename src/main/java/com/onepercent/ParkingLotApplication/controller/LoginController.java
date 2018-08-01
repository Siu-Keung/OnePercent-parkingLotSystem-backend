package com.onepercent.ParkingLotApplication.controller;


import com.onepercent.ParkingLotApplication.config.WebSecurityConfig;
import com.onepercent.ParkingLotApplication.dto.LoginDTO;
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

/**
 * Created by linyuan on 2017/12/13.
 */
@RestController
public class LoginController {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JWTTokenUtils jwtTokenUtils;
      @Autowired
      private LoginService loginService;
    @PostMapping("/auth/login")
    public String login(@RequestBody LoginDTO loginDTO, HttpServletResponse httpResponse) throws Exception{

       String tokenAndRole= loginService.login(loginDTO);
       String token=tokenAndRole.split(" ")[0];
       String role=tokenAndRole.split(" ")[1];
       httpResponse.addHeader(WebSecurityConfig.AUTHORIZATION_HEADER,"Bearer "+token);
        return tokenAndRole;
    }
}
