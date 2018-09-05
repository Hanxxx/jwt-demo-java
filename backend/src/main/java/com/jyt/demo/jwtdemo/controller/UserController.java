package com.jyt.demo.jwtdemo.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.jyt.demo.jwtdemo.Package.ResultVOUtil;
import com.jyt.demo.jwtdemo.Security.SecurityConstants;
import com.jyt.demo.jwtdemo.VO.ResultVO;
import com.jyt.demo.jwtdemo.data.UserInfo;
import com.jyt.demo.jwtdemo.data.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Gson gson;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/auth/signup")
    public ResultVO signup(@RequestBody String Body) {

        UserInfo newUser;
        try {
            newUser = gson.fromJson(Body, UserInfo.class);
        } catch (Exception e) {
            log.error("From json to string error. E = {}", e.toString());
            return ResultVOUtil.error(100, "INPUT FORMAT ERROR");
        }

        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return ResultVOUtil.error(104, "USER NAME EXISTS");
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        return ResultVOUtil.success();
    }

    @PostMapping("auth/login")
    public ResultVO login(@RequestBody String Body,
                          HttpServletResponse response) {
        UserInfo loginUser;
        try {
            loginUser = gson.fromJson(Body, UserInfo.class);
        } catch (Exception e) {
            log.error("From json to string error. E = {}", e.toString());
            return ResultVOUtil.error(100, "INPUT FORMAT ERROR");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String token = JWT.create().withSubject(
                ((User) authentication.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

        response.addHeader(SecurityConstants.HEADER_STRING,
                SecurityConstants.TOKEN_PREFIX + token);

        return ResultVOUtil.success();
    }


}
