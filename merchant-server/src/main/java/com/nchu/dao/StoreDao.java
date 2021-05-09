package com.nchu.dao;

import com.nchu.bean.Store;

public interface StoreDao {
    int deleteByPrimaryKey(String id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}
