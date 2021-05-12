package com.nchu.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.bean.Group;
import com.nchu.dao.GroupDao;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends ServiceImpl<GroupDao, Group> {
}
