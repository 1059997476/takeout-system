package com.nchu.bean;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * users
 * @author
 */
@Data
@TableName("users")
public class User implements Serializable {

    /**
     * 登录用户
     */
    @TableId("username")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册时间
     */
    private Date createdAt;

    /**
     * 是否会员
     */
    private Boolean ismember;

    /**
     * 会员到期时间
     */
    private Date memberTime;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户头像
     */
    private String head;

    /**
     * 0表示存在，1表示删除
     */
    private Integer flag;

    private static final long serialVersionUID = 1L;
}
