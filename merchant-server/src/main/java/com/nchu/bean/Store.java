package com.nchu.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * store
 * @author
 */
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
    private Date deliveryStart;

    /**
     * 打烊时间
     */
    private Date deliveryEnd;

    /**
     * 配送方式 0平台专送，1商家配送，2跑腿
     */
    private Integer ddeliveryMode;

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
    private Integer packeCharge;

    /**
     * 0表示存在，1表示删除
     */
    private Integer flag;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    private String backgroundImage;

    private static final long serialVersionUID = 1L;
}
