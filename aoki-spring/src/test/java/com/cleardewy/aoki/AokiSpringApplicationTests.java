package com.cleardewy.aoki;

import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.TeamEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.mapper.LessonMapper;
import com.cleardewy.aoki.mapper.TaskMapper;
import com.cleardewy.aoki.mapper.TeamMapper;
import com.cleardewy.aoki.utils.RedisUtils;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;

import java.util.List;

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

    @Autowired
    private TemplateEngine templateEngine;
    @Test
    void test() throws MessagingException {

        lessonEntityManager.addLessonMembers(1, List.of(new Integer[]{3, 4}));
    }

}
