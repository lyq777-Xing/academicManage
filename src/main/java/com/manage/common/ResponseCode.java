package com.manage.common;

import lombok.Data;

/**
 * @author lyq
 * @time 2024/1/25 11:04
 */


public enum ResponseCode {
    SUCCESS(200,"操作成功"),
    ERROR(500,"操作失败"),
    NOT_FOUND(404,"资源不存在"),
    UNAUTHORIZED(401,"未授权"),
    FORBIDDEN(403,"禁止访问"),
    BAD_REQUEST(400,"错误请求"),
    METHOD_NOT_ALLOWED(405,"方法不允许"),
    NOT_ACCEPTABLE(406,"无法接受"),
    UNSUPPORTED_MEDIA_TYPE(415,"不支持的媒体类型"),
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"),
    BAD_GATEWAY(502,"错误的网关"),
    SERVICE_UNAVAILABLE(503,"服务不可用"),
    GATEWAY_TIMEOUT(504,"网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505,"HTTP版本不支持");

    private Integer code;
    private String msg;

    ResponseCode() {
    }

    ResponseCode(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer code(){
        return this.code;
    }

    public String msg(){
        return this.msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
