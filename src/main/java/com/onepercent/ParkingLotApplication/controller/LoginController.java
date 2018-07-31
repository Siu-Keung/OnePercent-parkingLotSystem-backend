package com.onepercent.ParkingLotApplication.controller;


import com.onepercent.ParkingLotApplication.config.WebSecurityConfig;
import com.onepercent.ParkingLotApplication.dto.LoginDTO;
import com.onepercent.ParkingLotApplication.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @PostMapping("/auth/login")
    public String login(@RequestBody LoginDTO loginDTO, HttpServletResponse httpResponse) throws Exception{
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "*");
        httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
        //通过用户名和密码创建一个 Authentication 认证对象，实现类为 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());
        //如果认证对象不为空
        if (Objects.nonNull(authenticationToken)){
            userRepository.findByName(authenticationToken.getPrincipal().toString())
                    .orElseThrow(()->new Exception("用户不存在"));
        }
        try {

            //通过 AuthenticationManager（默认实现为ProviderManager）的authenticate方法验证 Authentication 对象
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            //将 Authentication 绑定到 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成Token
            String token = jwtTokenUtils.createToken(authentication,false);
            //将Token写入到Http头部
            httpResponse.addHeader(WebSecurityConfig.AUTHORIZATION_HEADER,"Bearer "+token);
            return "Bearer "+token;
        }catch (BadCredentialsException authentication){
            throw new Exception("密码错误");
        }
    }
}
