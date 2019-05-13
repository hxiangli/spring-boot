package com.hlfc.springboot.db.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class SysUserRole implements Serializable {
    static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Integer id;

    private Integer userId;

    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
