package com.hlfc.springboot.db.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.sql.Date;

public class SysRole implements Serializable {
    static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}