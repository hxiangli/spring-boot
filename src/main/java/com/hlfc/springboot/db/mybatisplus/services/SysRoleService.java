package com.hlfc.springboot.db.mybatisplus.services;


import com.hlfc.springboot.db.mybatisplus.entity.SysRole;

public interface SysRoleService {


    public SysRole selectById(Integer id);
}