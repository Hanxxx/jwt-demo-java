package com.jyt.demo.jwtdemo.controller;


import com.google.gson.Gson;
import com.jyt.demo.jwtdemo.Package.ResultVOUtil;
import com.jyt.demo.jwtdemo.VO.ResultVO;
import com.jyt.demo.jwtdemo.data.UserInfo;
import com.jyt.demo.jwtdemo.data.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Gson gson;

    @PostMapping("/signup")
    public ResultVO signup(@RequestBody String Body) {

        UserInfo newUser;
        try {
            newUser = gson.fromJson(Body, UserInfo.class);
        } catch (Exception e) {
            log.error("From json to string error. E = {}", e.toString());
            return ResultVOUtil.error(100, "INPUT FORMAT ERROR");
        }
        userRepository.save(newUser);

        return null;
    }


}
