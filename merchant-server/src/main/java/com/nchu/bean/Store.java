package com.nchu.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * store
 * @author
 */
@Accessors(chain = true)
@Data
public class Store implements Serializable {
    private String id;

    /**
     * 商铺名称
     */
    private String name;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 接单时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryStart;

    /**
     * 打烊时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryEnd;

    /**
     * 配送方式 0平台专送，1商家配送，2跑腿
     */
    private int deliveryMode;

    /**
     * 店铺地址
     */
    private String address;

    /**
     * 店铺电话
     */
    private String telephone;

    /**
     * 店铺头像
     */
    private String sign;

    /**
     * 店铺公告
     */
    private String notice;

    /**
     * 配送费
     */
    private Double distributionFee;

    /**
     * 起送价格
     */
    private Integer startPrice;

    /**
     * 打包费用
     */
    private Integer packCharge;

    /**
     * 0表示存在，1表示删除
     */
    private int flag;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    private String backgroundImage;

    private List<Group> groups;
    private static final long serialVersionUID = 1L;
}
