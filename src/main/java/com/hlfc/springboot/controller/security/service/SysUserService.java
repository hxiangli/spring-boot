package com.hlfc.springboot.controller.security.service;

import com.hlfc.springboot.controller.security.dao.SysUserMapper;
import com.hlfc.springboot.controller.security.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserService {
    @Resource
    private SysUserMapper userMapper;

    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }
}