package com.manage.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lyq
 * @time 2024/1/25 11:19
 */

@Data
public class ResponseResult<T> implements Serializable {

    private T data;
    private Meta meta;

    public ResponseResult(T data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    public ResponseResult() {
        this.meta=new Meta();
    }

//    success
    public void Success(){
        meta.setMsg(ResponseCode.SUCCESS.getMsg());
        meta.setStatus(ResponseCode.SUCCESS.getCode());
    }

    public void Success(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.SUCCESS.getCode());
    }

    public void Success(String msg,T data){
        meta.setStatus(ResponseCode.SUCCESS.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    ERROR
    public void Error(){
        meta.setMsg(ResponseCode.ERROR.getMsg());
        meta.setStatus(ResponseCode.ERROR.getCode());
    }

    public void Error(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.ERROR.getCode());
    }

    public void Error(String msg,T data){
        meta.setStatus(ResponseCode.ERROR.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    NOT_FOUND
    public void NotFound(){
        meta.setMsg(ResponseCode.NOT_FOUND.getMsg());
        meta.setStatus(ResponseCode.NOT_FOUND.getCode());
    }

    public void NotFound(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.NOT_FOUND.getCode());
    }

    public void NotFound(String msg,T data){
        meta.setStatus(ResponseCode.NOT_FOUND.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    UNAUTHORIZED
    public void UnAuthorized(){
        meta.setMsg(ResponseCode.UNAUTHORIZED.getMsg());
        meta.setStatus(ResponseCode.UNAUTHORIZED.getCode());
    }

    public void UnAuthorized(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.UNAUTHORIZED.getCode());
    }

    public void UnAuthorized(String msg,T data){
        meta.setStatus(ResponseCode.UNAUTHORIZED.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    FORBIDDEN
    public void Forbidden(){
        meta.setMsg(ResponseCode.FORBIDDEN.getMsg());
        meta.setStatus(ResponseCode.FORBIDDEN.getCode());
    }

    public void Forbidden(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.FORBIDDEN.getCode());
    }

    public void Forbidden(String msg,T data){
        meta.setStatus(ResponseCode.FORBIDDEN.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    BAD_REQUEST
    public void BadRequest(){
        meta.setMsg(ResponseCode.BAD_REQUEST.getMsg());
        meta.setStatus(ResponseCode.BAD_REQUEST.getCode());
    }

    public void BadRequest(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.BAD_REQUEST.getCode());
    }

    public void BadRequest(String msg,T data){
        meta.setStatus(ResponseCode.BAD_REQUEST.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    METHOD_NOT_ALLOWED
    public void MethodNotAllowed(){
        meta.setMsg(ResponseCode.METHOD_NOT_ALLOWED.getMsg());
        meta.setStatus(ResponseCode.METHOD_NOT_ALLOWED.getCode());
    }

    public void MethodNotAllowed(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.METHOD_NOT_ALLOWED.getCode());
    }

    public void MethodNotAllowed(String msg,T data){
        meta.setStatus(ResponseCode.METHOD_NOT_ALLOWED.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    NOT_ACCEPTABLE
    public void NotAcceptable(){
        meta.setMsg(ResponseCode.NOT_ACCEPTABLE.getMsg());
        meta.setStatus(ResponseCode.NOT_ACCEPTABLE.getCode());
    }

    public void NotAcceptable(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.NOT_ACCEPTABLE.getCode());
    }

    public void NotAcceptable(String msg,T data){
        meta.setStatus(ResponseCode.NOT_ACCEPTABLE.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    UNSUPPORTED_MEDIA_TYPE
    public void UnSupportedMediaType(){
        meta.setMsg(ResponseCode.UNSUPPORTED_MEDIA_TYPE.getMsg());
        meta.setStatus(ResponseCode.UNSUPPORTED_MEDIA_TYPE.getCode());
    }

    public void UnSupportedMediaType(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.UNSUPPORTED_MEDIA_TYPE.getCode());
    }

    public void UnSupportedMediaType(String msg,T data){
        meta.setStatus(ResponseCode.UNSUPPORTED_MEDIA_TYPE.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    INTERNAL_SERVER_ERROR
    public void InternalServerError(){
        meta.setMsg(ResponseCode.INTERNAL_SERVER_ERROR.getMsg());
        meta.setStatus(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
    }

    public void InternalServerError(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
    }

    public void InternalServerError(String msg,T data){
        meta.setStatus(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    BAD_GATEWAY
    public void BadGateway(){
        meta.setMsg(ResponseCode.BAD_GATEWAY.getMsg());
        meta.setStatus(ResponseCode.BAD_GATEWAY.getCode());
    }

    public void BadGateway(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.BAD_GATEWAY.getCode());
    }

    public void BadGateway(String msg,T data){
        meta.setStatus(ResponseCode.BAD_GATEWAY.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    SERVICE_UNAVAILABLE
    public void ServiceUnavailable(){
        meta.setMsg(ResponseCode.SERVICE_UNAVAILABLE.getMsg());
        meta.setStatus(ResponseCode.SERVICE_UNAVAILABLE.getCode());
    }

    public void ServiceUnavailable(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.SERVICE_UNAVAILABLE.getCode());
    }

    public void ServiceUnavailable(String msg,T data){
        meta.setStatus(ResponseCode.SERVICE_UNAVAILABLE.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    GATEWAY_TIMEOUT
    public void GatewayTimeout(){
        meta.setMsg(ResponseCode.GATEWAY_TIMEOUT.getMsg());
        meta.setStatus(ResponseCode.GATEWAY_TIMEOUT.getCode());
    }

    public void GatewayTimeout(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.GATEWAY_TIMEOUT.getCode());
    }

    public void GatewayTimeout(String msg,T data){
        meta.setStatus(ResponseCode.GATEWAY_TIMEOUT.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    HTTP_VERSION_NOT_SUPPORTED
    public void HttpVersionNotSupported(){
        meta.setMsg(ResponseCode.HTTP_VERSION_NOT_SUPPORTED.getMsg());
        meta.setStatus(ResponseCode.HTTP_VERSION_NOT_SUPPORTED.getCode());
    }

    public void HttpVersionNotSupported(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.HTTP_VERSION_NOT_SUPPORTED.getCode());
    }

    public void HttpVersionNotSupported(String msg,T data){
        meta.setStatus(ResponseCode.HTTP_VERSION_NOT_SUPPORTED.getCode());
        meta.setMsg(msg);
        this.data=data;
    }
}
