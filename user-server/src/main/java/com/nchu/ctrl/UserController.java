package com.nchu.ctrl;

import com.nchu.bean.Code;
import com.nchu.bean.Manager;
import com.nchu.bean.Result;
import com.nchu.bean.User;
import com.nchu.dao.UsersDao;
import com.nchu.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UsersDao usersDao;
    @PostMapping("/addUser")
    public Result<String> addUser(@RequestBody User userInfo){

        String newPassword = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(newPassword);
        usersDao.insert(userInfo);
        return new Result<>("插入成功", Code.success.getCode());
    }
    @GetMapping("/login")
    public Result loginManager(User userInfo, HttpServletResponse response) throws JOSEException {
        //根据用户名来查询
        User user = usersDao.selectById(userInfo.getUsername());
        if(user==null)
            return new Result( "用户名不存在",Code.error.getCode());
        // 判断密码是否正确
        boolean rs = passwordEncoder.matches(userInfo.getPassword(),user.getPassword());

        if(rs){
            String token = JwtUtil.genToken( 1,user.getUsername());
            response.setHeader("Authorization",token);
            //返回结果集
            return new Result("登录成功",Code.success.getCode());
        }
        return new Result("用户名或密码错误",Code.error.getCode());
    }
}
