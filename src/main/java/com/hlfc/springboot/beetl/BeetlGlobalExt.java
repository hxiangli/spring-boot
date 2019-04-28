package com.hlfc.springboot.beetl;

import com.hlfc.springboot.controller.security.entity.SysUser;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.ext.web.WebRenderExt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 扩展beetl 全局变量
 * @Auther: hxl
 * @Date: 2019/4/19 11:33
 */
public class BeetlGlobalExt implements WebRenderExt {

    @Override
    public void modify(Template template, GroupTemplate groupTemplate, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String url = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort();
        String ctx = httpServletRequest.getContextPath();
        if (!StringUtils.isEmpty(ctx) && !"/".equalsIgnoreCase(ctx)) {
            url = url + ctx;
        }

        //项目URL
        template.binding("WEBURL", url);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if(authentication ==null){
            return ;
        }

        Object principal =  authentication.getPrincipal();
        if (principal == null) {
            return ;
        }

        //当前用户
        if (principal instanceof SysUser) {
            template.binding("CURUSER", (SysUser) principal);
        }
    }
}
