package com.cleardewy.aoki;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import com.cleardewy.aoki.constant.Constants;
import com.cleardewy.aoki.constant.FileConstants;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.mapper.UserMapper;
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

    @Test
    void test() {
        String fileName="1fd1cb48cee84920b56fd393f972afdf.png";
        File file = FileUtil.file(FileConstants.AVATAR_PATH + File.separator + fileName);
        System.out.println(file.length());
        System.out.println(DigestUtil.md5Hex(file));
    }

}
