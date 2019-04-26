package com.hlfc.springboot.controller.security.service;

import com.hlfc.springboot.controller.security.dao.SysUserRoleMapper;
import com.hlfc.springboot.controller.security.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserRoleService {
    @Resource
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}