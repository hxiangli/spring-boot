package com.hlfc.springboot.log4j;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * https://www.jianshu.com/p/caa3d39f1113
 * log4j2 比 log4的性能好10倍以上。slf4j 是java 提供的日志输出标准类似 jdbc
 * @Auther: hxl
 * @Date: 2019/5/30 16:14
 */

public class HTest {
    Logger logger = LoggerFactory.getLogger(HTest.class);

    @Test
    public void test(){
        String str = "日志参数";
        System.out.println("111");
        //不显示
        logger.debug("这个是debugger日志，参数:{}",str);

        logger.info("这个是info日志，参数:{}",str);


        logger.warn("这个是warn日志，参数：{}",str);

        logger.error("这个是error日志，参数：{}",str);
    }

}
