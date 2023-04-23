package com.cleardewy.aoki;

import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.mapper.LessonMapper;
import com.cleardewy.aoki.mapper.TaskMapper;
import com.cleardewy.aoki.mapper.TeamMapper;
import com.cleardewy.aoki.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AokiSpringApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserEntityManager userEntityManager;

    @Autowired
    LessonEntityManager lessonEntityManager;

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    TeamMapper teamMapper;
    @Autowired
    TaskMapper taskMapper;


    @Test
    void test() {
        System.out.println(taskMapper.getOwnerTasks(3,1));
    }

}
