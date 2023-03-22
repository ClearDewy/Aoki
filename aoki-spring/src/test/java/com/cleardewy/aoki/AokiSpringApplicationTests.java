package com.cleardewy.aoki;

import com.cleardewy.aoki.manager.User.UserManager;
import com.cleardewy.aoki.mapper.UserMapper;
import com.cleardewy.aoki.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AokiSpringApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserMapper userMapper;

    @Test
    void test() {
        System.out.println(userMapper.getRoleIdByRole("my"));
    }

}
