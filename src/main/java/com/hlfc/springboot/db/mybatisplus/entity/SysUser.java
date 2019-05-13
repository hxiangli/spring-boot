package com.hlfc.springboot.db.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *用户对象（security对象）
 * @Author hxl
 * @Date  2019/5/10
 **/
public class SysUser implements UserDetails {
    static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Integer id;

    private String name;

    private String password;

    //表示该属性不为数据库表字段，但又是必须使用的。
    @TableField(exist = false)
    List<SysRole> userRoles;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 乐观锁
     */
    @Version
    @TableField("update_version")
    private Integer updateVersion;

    /**
     * 是否删除(0:未删除 1:已删除)
     */
    @TableLogic
    private Integer deleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateVersion() {
        return updateVersion;
    }

    public void setUpdateVersion(Integer updateVersion) {
        this.updateVersion = updateVersion;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (SysRole role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<SysRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<SysRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}