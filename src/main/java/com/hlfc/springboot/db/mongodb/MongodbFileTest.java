package com.hlfc.springboot.db.mongodb;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * mongodb 主要测试文件操作
 * https://blog.csdn.net/qw12312312/article/details/82288438
 * @Author hxl
 * @Date  2019/6/21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongodbFileTest {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Test
    public void delete() throws  Exception{
        gridFsTemplate.delete(query(where("_id").is("5b8a44ba63f89e2bc47285ad")));
    }


    //数据库中文件获取，并且写入文件
    @Test
    public void getFile() throws Exception{
        GridFSFile file = gridFsTemplate.findOne(query(whereFilename().is("1.png")));
        if(file != null){
            System.out.println("_id:"+file.getId());
            System.out.println("_objectId:"+file.getObjectId());
            GridFSDownloadStream in = gridFSBucket.openDownloadStream(file.getObjectId());

            GridFsResource resource = new GridFsResource(file,in);
            InputStream inputStream = resource.getInputStream();
            byte[] f = getBytes(inputStream);


            FileOutputStream out = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\lg.png");
            out.write(f);
        }

    }

    private byte[] getBytes(InputStream inputStream) throws  Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int  i = 0;
        while (-1!=(i=inputStream.read(b))){
            bos.write(b,0,i);
        }
        return bos.toByteArray();
    }

    //文件保存
    @Test
    public void storeFile() throws  Exception{
        org.springframework.core.io.Resource resource = new FileSystemResource("E:\\Workspaces\\spring-boot\\src\\main\\resources\\static\\file\\1.png");
        ObjectId id = gridFsTemplate.store(resource.getInputStream(), resource.getFilename(), ".png");
        System.out.println("_id:"+id);
    }
}
