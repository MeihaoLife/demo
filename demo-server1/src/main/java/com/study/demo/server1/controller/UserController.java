package com.study.demo.server1.controller;

import com.study.demo.server1.client.dto.UserDTO;
import com.study.demo.server1.client.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/4 3:52 下午
 * @Version: v1.0
 **/
@Slf4j
@RestController
public class UserController implements UserFeignClient {

    @Override
    public UserDTO getUser(String userId) {
        log.info("server1 request getUser userId = {}", userId);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.error("", e);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setUserName("zh");
        log.info("server1 response getUser userDTO = {}", userDTO);
        return userDTO;
    }

    @Override
    public int saveUser(UserDTO user) {
        log.info("save user = {}", user);
        return 1;
    }

    @Override
    public int removeUser(String userId) {
        log.info("save user userId = {}", userId);
        return 1;
    }
}
