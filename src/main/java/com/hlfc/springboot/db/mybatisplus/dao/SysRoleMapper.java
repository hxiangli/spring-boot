package com.hlfc.springboot.db.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hlfc.springboot.db.mybatisplus.entity.SysRole;
import com.hlfc.springboot.db.mybatisplus.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRole selectById(Integer id);

    int delete(Integer id);
}