package cn.itrip.common;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class RedisHelp {
    public  void setRedis(String token,int seconds,String value){
        Jedis redis=new Jedis("127.0.0.1",6379);
        redis.auth("123456");
        redis.setex(token, seconds, value);
    }
    public  String getRedis(String key){
        Jedis redis=new Jedis("127.0.0.1",6379);
        redis.auth("123456");
        return redis.get(key);
    }

}
