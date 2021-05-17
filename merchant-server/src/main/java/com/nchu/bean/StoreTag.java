package com.nchu.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * store_tag
 * @author
 */
@Data
@TableName("store_tag")
public class StoreTag implements Serializable {
    private String id;

    private String tagName;

    private String storeId;

    public StoreTag(String tagName, String storeId) {
        this.tagName = tagName;
        this.storeId = storeId;
    }

    private static final long serialVersionUID = 1L;
}
