package com.cleardewy.aoki;

import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.TeamEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.mapper.LessonMapper;
import com.cleardewy.aoki.mapper.TaskMapper;
import com.cleardewy.aoki.mapper.TeamMapper;
import com.cleardewy.aoki.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

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
    @Autowired
    TeamEntityManager teamEntityManager;


    @Test
    void test() {
//        System.out.println(userEntityManager.getUserByUsername("202121091256"));;
//        System.out.println(taskMapper.getTaskMember(6));;
    }

}
