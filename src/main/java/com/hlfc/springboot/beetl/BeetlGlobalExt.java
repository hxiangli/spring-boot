package com.hlfc.springboot.beetl;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.ext.web.WebRenderExt;
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

        template.binding("WEBURL", url);
    }
}
