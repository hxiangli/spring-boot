package com.hlfc.springboot.db.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hlfc.springboot.db.mybatisplus.entity.SysUser;
import com.hlfc.springboot.db.mybatisplus.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);
}