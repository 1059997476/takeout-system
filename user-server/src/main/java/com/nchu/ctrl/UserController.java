package com.nchu.ctrl;

import com.nchu.bean.Code;
import com.nchu.bean.Result;
import com.nchu.bean.User;
import com.nchu.dao.UsersDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UsersDao usersDao;
    @PostMapping("addUser")
    public Result<String> addUser(@RequestBody User userInfo){

        String newPassword = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(newPassword);
        usersDao.insert(userInfo);
        return new Result<>("插入成功", Code.success.getCode());
    }
}
