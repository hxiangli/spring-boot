package com.hlfc.springboot.db.mybatisplus.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hlfc.springboot.db.mybatisplus.dao.SysUserMapper;
import com.hlfc.springboot.db.mybatisplus.entity.SysUser;
import com.hlfc.springboot.db.mybatisplus.services.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author shen
 * @since 2018-07-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper userMapper;

    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }

    public SysUser getByName(String name) {
        return userMapper.getByName(name);
    }
}
