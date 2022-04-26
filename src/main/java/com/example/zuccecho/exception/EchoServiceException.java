package com.example.zuccecho.exception;

/**
 * @author pengbin
 * @version 1.0
 * @date 2022/04/10 14:54
 */
public class EchoServiceException extends RuntimeException{
    public EchoServiceException(){};

    public EchoServiceException(String msg){
        super(msg);
    };
}
