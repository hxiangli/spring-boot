package com.hlfc.springboot.db.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlfc.springboot.db.mybatisplus.entity.SysUser;
import com.hlfc.springboot.db.mybatisplus.services.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * https://www.jianshu.com/p/f227a3b1137e
 * @Auther: hxl
 * @Date: 2019/5/10 10:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void test() {
        //测试逻辑删除功能(自动会加上 where delete = 0的条件)
        SysUser sysUser = sysUserService.selectById(1);

        //测试分页
        IPage<SysUser> sysUserIPage = sysUserService.selectPage(
                new Page<SysUser>(1, 10), new QueryWrapper<>());

        //测试公共字段自动填充
        SysUser sysUser1 = new SysUser();
        sysUser1.setPassword("shen");
        sysUser1.setName("hxl");
        sysUserService.insert(sysUser1);

        //测试xml配置方式查询
        SysUser sysUser3 = sysUserService.getByName("admin");
        System.out.println(sysUser3.getPassword());

//        //测试乐观锁（没试出效果）
//        SysUser sysUser2 = new SysUser();
//        sysUser2.setId(19);
//        sysUser2.setPassword("shen2");
//        sysUser2.setUpdateVersion(1);
//        sysUserService.updateById(sysUser2);
    }
}