package com.study.demo.server2;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.demo.server1.client.dto.UserDTO;
import com.study.demo.server1.client.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: Server2Application
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/3 4:42 下午
 * @Version: v1.0
 **/
@SpringBootApplication
@RestController
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.study.demo.server1.client.feign")
@ComponentScan({"com.study.demo.server1.client.feign.hystrix", "com.study.demo.server2"})
@Slf4j
public class Server2Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Server2Application.class).web(WebApplicationType.SERVLET).run(args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

//    hystrix将使用与调用着相同的线程处理，保证线程安全
//    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
    // 类似：hystrix.shareSecurityContext：true
    // @SessionScope
    // @RequestScope
    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    @RequestMapping("/hystrix/failed")
    public String hystrixFailed() throws Exception{
        System.out.println(Thread.currentThread().getId());
        Thread.sleep(1000);
        return "44444";
    }

    public String fallback() {
        System.out.println(Thread.currentThread().getId());
        return "31313131";
    }

}
