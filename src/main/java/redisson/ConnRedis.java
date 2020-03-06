package redisson;

import org.redisson.Redisson;
import org.redisson.RedissonMap;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zenglp
 * @date 2020/3/6 15:07
 */
public class ConnRedis {




    public static void main(String[] args) {
        ConnRedis connRedis = new ConnRedis();
        connRedis.connSingleServer();

    }


    public void connSingleServer(){
        Config config= new Config();
//        config.setTransportMode(TransportMode.EPOLL);
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://127.0.0.1:6379");
        singleServerConfig.setPassword("hundsun@1");
        //数据库编号默认0
        singleServerConfig.setDatabase(2);
        RedissonClient redisson = Redisson.create(config);
        // Map对象
        RedissonMap rm = (RedissonMap) redisson.getMap("test001");
        rm.put("mykey", "key001");
        rm.expire(15000, TimeUnit.MILLISECONDS);


        //Object
        RBucket<Object> mybucket = redisson.getBucket("mybucket");
        mybucket.set("helloworld");
        mybucket.expire(5000, TimeUnit.MILLISECONDS);
        redisson.shutdown();
    }
}
