package com.hlfc.springboot.db.mybatisplus.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hlfc.springboot.db.mybatisplus.dao.SysUserRoleMapper;
import com.hlfc.springboot.db.mybatisplus.entity.SysUserRole;
import com.hlfc.springboot.db.mybatisplus.services.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Resource
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}