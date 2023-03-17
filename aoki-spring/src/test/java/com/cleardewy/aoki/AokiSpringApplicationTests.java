package com.cleardewy.aoki;

import com.cleardewy.aoki.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AokiSpringApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Test
    void contextLoads() {
        redisUtils.set("connect","true",10);
        System.out.println(redisUtils.get("connect"));
    }

}
