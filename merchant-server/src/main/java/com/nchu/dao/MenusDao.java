package com.nchu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nchu.bean.Menus;

import java.util.List;

public interface MenusDao extends BaseMapper<Menus> {

    List<Menus> selectAllByType(Integer type);
}
