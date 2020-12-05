package com.study.demo.server1.client.dto;

import com.study.demo.server1.client.enums.GlobalCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应信息体
 *
 * @param <T> 泛型标记
 * @author 9f
 */
@Data
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 返回状态
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 协议信息
     */
    private T data;

    private ResponseResult(GlobalCodeEnum codeEnum) {
        this(codeEnum, null);
    }

    private ResponseResult(GlobalCodeEnum codeEnum, T data) {
        this(codeEnum.getCode(), codeEnum.getDesc(), data);
    }

    private ResponseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功
     *
     * @param <T> 泛型标记
     * @return Result
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(GlobalCodeEnum.CODE_000000);
    }

    /**
     * 成功-携带数据
     *
     * @param data 数据
     * @param <T>  泛型标记
     * @return Result
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(GlobalCodeEnum.CODE_000000, data);
    }


    /**
     * 返回失败信息，用于 web
     *
     * @param <T> 泛型标记
     * @return {Result}
     */
    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<>(GlobalCodeEnum.CODE_999999);
    }

    /**
     * 返回失败信息，用于 web
     *
     * @param message 失败信息
     * @param <T>     泛型标记
     * @return {Result}
     */
    public static <T> ResponseResult<T> fail(String message) {
        return fail(GlobalCodeEnum.CODE_999999.getCode(), message);
    }

    /**
     * 返回失败信息，用于 web
     *
     * @param codeEnum 返回枚举
     * @return {Result}
     */
    public static <T> ResponseResult<T> fail(GlobalCodeEnum codeEnum) {
        return new ResponseResult<>(codeEnum, null);
    }

    /**
     * 返回失败信息
     *
     * @param code    编码
     * @param message 消息
     * @return {Result}
     */
    public static <T> ResponseResult<T> fail(String code, String message) {
        return new ResponseResult<>(code, message, null);
    }

    /**
     * 返回失败信息
     *
     * @param codeEnum 返回枚举
     * @param message  消息
     * @return {Result}
     */
    public static <T> ResponseResult<T> fail(GlobalCodeEnum codeEnum, String message) {
        return new ResponseResult<>(codeEnum.getCode(), message, null);
    }
}
