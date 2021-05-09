package com.nchu.ctrl;

import com.nchu.bean.Code;
import com.nchu.bean.Result;
import com.nchu.utils.JwtException;
import com.nchu.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RequestMapping("/menus")
@RestController
public class MenuController {
    @GetMapping("/list")
    public Result getMenus(HttpServletRequest request) throws JwtException, ParseException, JOSEException {
        Integer value = JwtUtil.validToken(request.getHeader("Authorization")).getType();
        System.out.println(value);
        return new Result("查询成功", Code.success.getCode());
    }
}
