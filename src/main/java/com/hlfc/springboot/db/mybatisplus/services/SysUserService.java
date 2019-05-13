package com.hlfc.springboot.db.mybatisplus.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hlfc.springboot.db.mybatisplus.entity.SysRole;
import com.hlfc.springboot.db.mybatisplus.entity.SysUser;

import javax.annotation.Resource;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author shen
 * @since 2018-07-16
 */
public interface SysUserService extends IService<SysUser> {

    public SysUser selectByName(String name) ;

    public SysUser getByName(String name) ;
}