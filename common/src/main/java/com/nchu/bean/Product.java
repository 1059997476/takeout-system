package com.nchu.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * product
 * @author 
 */
@Data
public class Product implements Serializable {
    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 商品原价
     */
    private Double price;

    /**
     * 促销价格
     */
    private Double salePrice;

    /**
     * 商品状态，0可售，1不可售
     */
    private Boolean state;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品描述
     */
    private String describes;

    /**
     * 是否必选商品
     */
    private Boolean mandatory;

    /**
     * 上架时间
     */
    private Date created;

    /**
     * 0表示存在，1表示删除
     */
    private Integer flag;

    private String groupId;

    private static final long serialVersionUID = 1L;
}