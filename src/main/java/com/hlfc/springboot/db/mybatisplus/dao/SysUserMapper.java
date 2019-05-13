package com.hlfc.springboot.db.mybatisplus.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hlfc.springboot.db.mybatisplus.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户 dao 接口
 * </p>
 *
 * @author shen
 * @since 2018-07-16
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    //注解方式
    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    SysUser selectByName(String name);

    //xml方式
    SysUser getByName(@Param("name") String name);
}