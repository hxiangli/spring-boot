package com.hlfc.springboot.db.mybatisplus.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hlfc.springboot.db.mybatisplus.dao.SysRoleMapper;
import com.hlfc.springboot.db.mybatisplus.entity.SysRole;
import com.hlfc.springboot.db.mybatisplus.services.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }


}