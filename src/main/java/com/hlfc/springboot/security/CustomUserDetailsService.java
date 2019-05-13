package com.hlfc.springboot.security;

import com.hlfc.springboot.db.mybatisplus.entity.SysRole;
import com.hlfc.springboot.db.mybatisplus.entity.SysUser;
import com.hlfc.springboot.db.mybatisplus.entity.SysUserRole;
import com.hlfc.springboot.db.mybatisplus.services.SysRoleService;
import com.hlfc.springboot.db.mybatisplus.services.SysUserRoleService;
import com.hlfc.springboot.db.mybatisplus.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser user = userService.selectByName(username);

        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
        List<SysRole> roles = new ArrayList<>();

        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.selectById(userRole.getRoleId());
            roles.add(role);
        }

        // 返回UserDetails实现类
        user.setUserRoles(roles);

        return user;
    }
}