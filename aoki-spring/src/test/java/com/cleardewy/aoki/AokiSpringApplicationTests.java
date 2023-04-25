package com.cleardewy.aoki;

import com.cleardewy.aoki.entity.dto.AnswerDto;
import com.cleardewy.aoki.entity.dto.LessonDto;
import com.cleardewy.aoki.entity.dto.QuestionDto;
import com.cleardewy.aoki.entity.dto.TaskDto;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
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


    @Test
    void test() {
        AnswerDto answer = new AnswerDto(null, 1, "你说啥", 1);
        taskMapper.submitAnswer(answer);
//        System.out.println(teamMapper.getTeamIdByTaskId(1,1));;
    }

}
