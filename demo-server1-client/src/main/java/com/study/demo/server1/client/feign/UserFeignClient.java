package com.study.demo.server1.client.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.demo.server1.client.dto.UserDTO;
import com.study.demo.server1.client.feign.hystrix.UserHystrixFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: UserFeignClient
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/4 3:46 下午
 * @Version: v1.0
 **/

/**
 * @FeignClient 中可以为当前客户端添加别名 qualifier
 */
@FeignClient(name = "demo-server1", fallback = UserHystrixFallBack.class)
public interface UserFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getUser")
    UserDTO getUser(@RequestParam("userId") String userId);

    @RequestMapping(method = RequestMethod.GET, value = "/saveUser")
    int saveUser(@RequestBody UserDTO user);

    @RequestMapping(method = RequestMethod.GET, value = "/removeUser")
    int removeUser(@RequestParam("userId") String userId);
}
