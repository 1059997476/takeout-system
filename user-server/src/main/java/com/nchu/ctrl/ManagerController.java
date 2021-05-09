package com.nchu.ctrl;

import com.nchu.bean.Code;
import com.nchu.bean.Manager;
import com.nchu.bean.Result;
import com.nchu.bean.User;
import com.nchu.dao.ManagerDao;
import com.nchu.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("manager")
public class ManagerController {
    @Resource
    private ManagerDao managerDao;
    @Resource
    private PasswordEncoder passwordEncoder;
    @PostMapping("/add")
    public Result addManager(@RequestBody Manager manager){
        Manager repeatManger = managerDao.selectByPrimaryKey(manager.getUsername());
        if(repeatManger!=null)
            return new Result("用户已存在", Code.error.getCode());
        manager.setCreatTime(new Date());
        String encode = passwordEncoder.encode(manager.getPassword());
        manager.setPassword(encode);
        managerDao.insert(manager);
        return new Result<>("插入成功", Code.success.getCode());
    }

    @GetMapping("/login")
    public Result loginManager(Manager manager, HttpServletResponse response) throws JOSEException {
        //根据用户名来查询
        Manager repeatManger = managerDao.selectByPrimaryKey(manager.getUsername());
        if(repeatManger==null)
            return new Result( "用户名不存在",Code.error.getCode());
        // 判断密码是否正确
        boolean rs = passwordEncoder.matches(manager.getPassword(),repeatManger.getPassword());

        if(rs){
            String token = JwtUtil.genToken( repeatManger.getType(),repeatManger.getUsername());

            //返回结果集
            return new Result(token,"登录成功",Code.success.getCode());
        }
        return new Result("用户名或密码错误",Code.error.getCode());
    }
}
