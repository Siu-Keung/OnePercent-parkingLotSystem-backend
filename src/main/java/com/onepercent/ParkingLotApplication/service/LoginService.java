package com.onepercent.ParkingLotApplication.service;

import com.onepercent.ParkingLotApplication.domain.User;
import com.onepercent.ParkingLotApplication.dto.LoginDTO;
import com.onepercent.ParkingLotApplication.filter.JwtAuthenticationTokenFilter;
import com.onepercent.ParkingLotApplication.repository.UserRepository;
import com.onepercent.ParkingLotApplication.utils.JWTTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    public String login(LoginDTO loginDTO) throws Exception{


        User user=  userRepository.findByName(loginDTO.getUsername()).get();
        if(user.getLoginFlag().equals("0")){
            new Exception("用户不存在");
        }
        String role=  user.getRoles().get(0).getName();
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

            return token+" "+role+" "+user.getId()+" "+user.getName();
        }catch (BadCredentialsException authentication){
            throw new Exception("密码错误");
        }

    }
    public String  checkUserInfo(HttpServletRequest httpServletRequest) {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter=new JwtAuthenticationTokenFilter();
        String jwt=jwtAuthenticationTokenFilter.resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwt) && this.jwtTokenUtils.validateToken(jwt)) {
            return  "istrue";
        }
        return "isFalse";}

}
