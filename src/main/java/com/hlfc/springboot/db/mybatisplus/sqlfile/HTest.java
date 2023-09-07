package com.hlfc.springboot.db.mybatisplus.sqlfile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlfc.springboot.db.mybatisplus.entity.SysUser;
import com.hlfc.springboot.db.mybatisplus.services.SysUserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;

/**
 * 运行sql文件
 * @Auther: hxl
 * @Date: 2019/5/10 10:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTest {

    @Autowired
    SqlSessionFactory sqlSessionFactory;


    @Test
    public void test(){
        run("C:\\Users\\Administrator\\Desktop\\mysql-schema.sql");
    }
    /**
     * 运行sql脚本
     *
     * @param sqlFilePath 执行的脚本文件
     */
    public boolean run(String sqlFilePath) {
        try {
            // 建立连接
            Connection conn = sqlSessionFactory.openSession().getConnection();
            // 创建ScriptRunner，用于执行SQL脚本
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setErrorLogWriter(null);
            runner.setLogWriter(null);
            // 遇到错误回滚
            runner.setStopOnError(true);
            Resources.setCharset(Charset.forName("UTF-8"));
            // 执行SQL脚本
            runner.runScript(new InputStreamReader(new FileInputStream(sqlFilePath), "UTF-8"));
            // 关闭连接
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
