package com.study.server.demo.model;

import lombok.Data;

/**
 * @ClassName: UserOperateLog
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/2 3:33 下午
 * @Version: v1.0
 **/
@Data
public class UserOperateLog {

    private long id;

    private long userId;

    private String operateDesc;

    private byte operateResult;

    private String handlerClass;

    private String handlerMethod;
}
