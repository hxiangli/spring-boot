package com.hlfc.springboot.db.mybatisplus.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hlfc.springboot.db.mybatisplus.entity.SysUserRole;
import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {

    public List<SysUserRole> listByUserId(Integer userId);
}