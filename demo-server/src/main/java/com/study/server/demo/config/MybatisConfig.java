package com.study.server.demo.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MybatisConfig
 * @Description: mybatis拦截器
 * @Author: zhānghào
 * @Date: 2020/12/2 2:45 下午
 * @Version: v1.0
 **/
@Configuration
public class MybatisConfig {
//    TableShardInterceptor 已经加上@Component注解，此处不需要重复配置
//    @Bean
//    ConfigurationCustomizer mybatisConfigurationCustomizer() {
//        return new ConfigurationCustomizer() {
//            @Override
//            public void customize(org.apache.ibatis.session.Configuration configuration) {
//                configuration.addInterceptor(new TableShardInterceptor());
//            }
//        };
//    }
}
