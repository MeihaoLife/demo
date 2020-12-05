package com.study.demo.server2.controller;

import com.study.demo.server1.client.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LoadBalanceController
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/5 8:41 下午
 * @Version: v1.0
 **/
@RestController
public class LoadBalanceController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/port", method = RequestMethod.GET)
    public ResponseResult<String> getServerPort() {
        return ResponseResult.success(port);
    }
}
