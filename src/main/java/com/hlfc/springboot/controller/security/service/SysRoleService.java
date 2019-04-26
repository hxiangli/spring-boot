package com.hlfc.springboot.controller.security.service;

import com.hlfc.springboot.controller.security.dao.SysRoleMapper;
import com.hlfc.springboot.controller.security.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }
}