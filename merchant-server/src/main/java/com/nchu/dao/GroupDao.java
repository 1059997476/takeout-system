package com.nchu.dao;

import com.nchu.bean.Group;

public interface GroupDao {
    int deleteByPrimaryKey(String id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
}
