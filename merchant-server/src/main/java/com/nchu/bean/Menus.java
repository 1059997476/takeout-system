package com.nchu.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * menus
 * @author
 */
@Data
public class Menus implements Serializable {
    private String id;

    private String icon;

    private String name;

    private String router;

    /**
     * 0代表骑手1代表商家。
     */
    private Integer type;

    private static final long serialVersionUID = 1L;
}
