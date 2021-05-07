package com.nchu.ctrl;

import com.nchu.bean.User;
import com.nchu.service.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {
    @Resource
    private LoginService loginService;
    @RequestMapping("/login")
    public void login(@RequestBody User user){
        System.out.println(user.getUsername()+user.getPassword());
    }

}
