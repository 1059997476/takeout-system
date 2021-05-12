package com.nchu.ctrl;

import com.nchu.bean.Code;
import com.nchu.bean.Menus;
import com.nchu.bean.Result;
import com.nchu.dao.MenusDao;
import com.nchu.utils.JwtException;
import com.nchu.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RequestMapping("/menus")
@RestController
public class MenuController {
    @Resource
    private MenusDao menusDao;
    @GetMapping("/list")
    public Result getMenus(HttpServletRequest request) throws JwtException, ParseException, JOSEException {
        Integer value = JwtUtil.validToken(request.getHeader("Authorization")).getType();
        List<Menus> menus = menusDao.selectAllByType(value);
        return new Result(menus,"查询成功", Code.success.getCode());
    }
}
