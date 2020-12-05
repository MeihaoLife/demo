package com.study.demo.server2.controller;

import com.study.demo.server1.client.dto.UserDTO;
import com.study.demo.server1.client.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/4 4:07 下午
 * @Version: v1.0
 **/
@RestController
@Slf4j
public class OrderController {

    @Resource
    private UserFeignClient userFeignClient;

    /**
     *  调用demo-server1的服务的/getUser接口
     *  如果访问时间超过hystrix的超时时间，那么将会执行降级
     */
    @RequestMapping("/order/user")
    public UserDTO orderUser() {
        UserDTO userDTO = userFeignClient.getUser("1231");
        log.info("user = {}", userDTO);
        return userDTO;
    }
}
