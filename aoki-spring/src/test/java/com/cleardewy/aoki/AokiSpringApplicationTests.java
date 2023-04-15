package com.cleardewy.aoki;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import com.cleardewy.aoki.constant.Constants;
import com.cleardewy.aoki.constant.FileConstants;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.LessonMemberEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.manager.user.UserManager;
import com.cleardewy.aoki.mapper.LessonMapper;
import com.cleardewy.aoki.mapper.LessonMemberMapper;
import com.cleardewy.aoki.mapper.UserMapper;
import com.cleardewy.aoki.service.user.UserService;
import com.cleardewy.aoki.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

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
    LessonMemberEntityManager lessonMemberEntityManager;
    @Autowired
    LessonMemberMapper lessonMemberMapper;

    @Test
    void test() {
//        System.out.println(lessonEntityManager.getLessonList(3));
        System.out.println(lessonMemberMapper.getLessonMember(3));
    }

}
