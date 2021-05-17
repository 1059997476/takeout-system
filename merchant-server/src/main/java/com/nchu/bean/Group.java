package com.nchu.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * group
 * @author
 */
@Data
@NoArgsConstructor
@ToString
@TableName("`group`")
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String storeId;

    private String groupName;

    public Group(String storeId, String groupName) {
        this.storeId = storeId;
        this.groupName = groupName;
    }
}
