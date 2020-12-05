package com.study.demo.server1.client.feign.hystrix;

import com.study.demo.server1.client.dto.UserDTO;
import com.study.demo.server1.client.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UserHystrixFallBack
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/4 6:17 下午
 * @Version: v1.0
 **/
@Component
@Slf4j
public class UserHystrixFallBack implements UserFeignClient {

    @Override
    public UserDTO getUser(String userId) {
        log.info("UserFeignClient getUser 降级 userId = {}", userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setUserName("降级");
        return userDTO;
    }

    @Override
    public int saveUser(UserDTO user) {
        return 0;
    }

    @Override
    public int removeUser(String userId) {
        return 0;
    }
}
