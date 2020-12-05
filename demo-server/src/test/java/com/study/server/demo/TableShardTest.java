package com.study.server.demo;

import com.study.server.demo.mapper.UserOperateLogMapper;
import com.study.server.demo.model.UserOperateLog;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @ClassName: TableShardTest
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/2 12:42 下午
 * @Version: v1.0
 **/
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class TableShardTest {

    @Resource
    UserOperateLogMapper userOperateLogMapper;

    @Test
    public void tableShardTest() {

        UserOperateLog userOperateLog = new UserOperateLog();
        userOperateLog.setOperateResult((byte)1);
        userOperateLog.setOperateDesc("成功");
        userOperateLog.setHandlerClass("com.study.demo.mapper.UserOperateLogMapper");
        userOperateLog.setHandlerMethod("insert");
        userOperateLog.setUserId(2189001);
        for (int i = 0; i < 100; i++) {
            userOperateLog.setUserId(userOperateLog.getUserId() + i);
            int affectRows = userOperateLogMapper.insertUserOperateLog(userOperateLog, userOperateLog.getUserId());
            log.info("insertUserOperateLog = {}", userOperateLog);
        }

    }
}
