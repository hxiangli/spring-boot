package com.hlfc.springboot.db.mongodb;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * mongodb配置
 * @Author hxl
 * @Date  2019/6/21
 **/
@Configuration
public class MongoConf {

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Autowired
    private  MongoConverter converter;

    //操作文件
    @Bean
    public GridFSBucket getGridFSBuckets() {
        MongoDatabase db = mongoDbFactory.getDb();
        return GridFSBuckets.create(db,"file");
    }


    //文件查询
    @Bean
    public GridFsTemplate getGridFsTemplate() {
        return new GridFsTemplate(mongoDbFactory, converter,"file");
    }
}
