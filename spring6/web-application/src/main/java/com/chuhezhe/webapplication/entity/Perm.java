package com.chuhezhe.webapplication.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * ClassName: Perm
 * Package: com.chuhezhe.webapplication.entity
 * Description: 权限表
 *
 * @Author Chuhezhe
 * @Create 2024/7/20 19:49
 * @Version 1.0
 */
@TableName("t_perm")
public class Perm {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 权限名称
    @TableField("name")
    private String name;

    // 权限标识
    @TableField("tag")
    private String tag;

    // 所属用户
    @TableField("user_id")
    private Long userId;

    @TableField(exist = false)
    private UserDetail userDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
