package com.nchu.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * group
 * @author
 */
@Data
public class Group implements Serializable {
    private String id;

    private String storeId;

    private String groupName;

    private static final long serialVersionUID = 1L;
}
