package com.nchu.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * manager
 * @author
 */
@Data
public class Manager implements Serializable {
    /**
     * 骑手或商家登陆名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 头像
     */
    private String head;

    /**
     * 0表示存在，1表示删除
     */
    private Integer flag;

    /**
     * 身份证
     */
    private String identity;

    /**
     * 0代表骑手1代表商家。
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 姓名
     */
    private String name;

    private static final long serialVersionUID = 1L;
}
