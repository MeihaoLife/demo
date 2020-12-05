package com.study.demo.server1.client.enums;

import lombok.Getter;

/**
 * 返回编码
 *
 * @author 9f
 */
@Getter
public enum GlobalCodeEnum {
    // 返回编码
    CODE_000000("000000", "成功"),
    CODE_999999("999999", "失败"),
    CODE_000001("000001", "业务异常"),
    CODE_000002("000002", "参数错误"),
    CODE_000003("000003", "匹配中心服务不可用"),
    CODE_000005("000005", "未查询到相关的信息"),
    ORDER_CENTER_SERVICE_UNAVAILABLE_200501("200501", "正在处理中"),
    ;

    private GlobalCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String desc;


    public static GlobalCodeEnum of(String code) {
        if (code == null) {
            return null;
        }
        GlobalCodeEnum[] values = GlobalCodeEnum.values();
        for (GlobalCodeEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}