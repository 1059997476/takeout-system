package com.nchu.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * receive_address
 * @author
 */
@Data
@TableName("receive_address")
public class Address implements Serializable {
    /**
     * 收货地址编号
     */
    private String id;

    private String userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 联系人
     */
    private String linkerName;

    /**
     * 收货地址
     */
    private String houseAddress;

    /**
     * 定位经度
     */
    private Double longitude;

    /**
     * 定位纬度
     */
    private Double latitude;

    private Object gender;

    /**
     * 0表示存在，1表示删除
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}
