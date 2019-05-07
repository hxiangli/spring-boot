package com.hlfc.springboot.cache.redis;

import com.alibaba.fastjson.JSON;
import com.hlfc.springboot.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * redis 测试类  https://blog.csdn.net/weixin_37490221/article/details/78134521
 * @Auther: hxl
 * @Date: 2019/4/19 14:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTest {


    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    //添加
    @Test
    public void test() {

        RedisModel m = new RedisModel();
        m.setName("张三");
        m.setTel("1111");
        m.setAddress("深圳1");
        m.setRedisKey("zhangsanKey01");

        RedisModel m2 = new RedisModel();
        m2.setName("张三2");
        m2.setTel("2222");
        m2.setAddress("深圳2");
        m2.setRedisKey("zhangsanKey02");

        //String
//        this.opsForValue(m);

        //Hash
//        this.opsForHash(m, m2);

        //list
//        this.opsForList(m, m2);

        //set
//        this.opsForSet(m, m2);

        //zset
        this.opsForZSet(m,m2);

    }

    /**
     * hash 测试
     */
    public void opsForHash(RedisModel m, RedisModel m2) {

        redisTemplate.opsForHash().put("hashK", m.getRedisKey(), m);
        redisTemplate.opsForHash().put("hashK", m2.getRedisKey(), m2);


        RedisModel value = (RedisModel) redisTemplate.opsForHash().get("TEST_REDIS_KEY", m2.getRedisKey());

        System.out.println("map---------------" + value.getName());
    }

    /**
     * String 测试
     */
    public void opsForValue(RedisModel m) {
        redisTemplate.opsForValue().set("Stringk", JSON.toJSONString(m));
        System.out.println(redisTemplate.opsForValue().get("m"));
    }


    /**
     * list 测试
     *
     * @param m
     * @param m2
     */
    public void opsForList(RedisModel m, RedisModel m2) {

        System.out.println("先进后出(左进左出)");

        //先进后出(左进左出)
        redisTemplate.opsForList().leftPush("listk", m);
        redisTemplate.opsForList().leftPush("listk", m2);

        List<Object> range = redisTemplate.opsForList().range("listk", 0, -1);
        for (Object object : range) {
            RedisModel obj = (RedisModel) object;
            System.out.println(obj.getName());//m2 m
        }


        this.deleteAll();

        System.out.println("先进后出(左进左出)");

        //先进先出(右进左出)
        redisTemplate.opsForList().rightPush("listk", m);
        redisTemplate.opsForList().rightPush("listk", m2);

        List<Object> ranger = redisTemplate.opsForList().range("listk", 0, -1);
        for (Object object : ranger) {
            RedisModel obj = (RedisModel) object;
            System.out.println(obj.getName());//m m2
        }

        this.deleteAll();
    }

    /**
     * set 测试
     * Redis的Set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据
     */
    public void opsForSet(RedisModel m, RedisModel m2) {

        //存在重复，自动覆盖
        redisTemplate.opsForSet().add("setK", m);
        redisTemplate.opsForSet().add("setK", m);

        Set<Object> ranger = redisTemplate.opsForSet().members("setK");
        for (Object object : ranger) {
            RedisModel obj = (RedisModel) object;
            System.out.println(obj.getName());//m
        }

        this.deleteAll();

    }

    /**
     * 有序 zset（也不能重复）
     */
    public void opsForZSet(RedisModel m, RedisModel m2) {

        redisTemplate.opsForZSet().add("zsetK", m, 2);
        redisTemplate.opsForZSet().add("zsetK", m2, 1);
        //得到分数在最小和最大值之间的元素。(从小到大)
        Set<Object> rangeByScore = redisTemplate.opsForZSet().rangeByScore("zsetK", 1, 2);

        for (Object object : rangeByScore) {
            RedisModel obj = (RedisModel) object;
            System.out.println(obj.getName());//m
        }

        this.deleteAll();
    }


    /**
     * 删除全部
     *
     */
    public void deleteAll(){
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }


}
