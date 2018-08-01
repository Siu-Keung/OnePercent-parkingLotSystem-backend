package com.onepercent.ParkingLotApplication.exception;

/**
 * @author Dylan Wei
 * @date 2018-08-01 11:23
 */
public class ResourceNotFoundException extends RuntimeException{
    private static final String defaultMessage = "请求资源不存在！";

    public ResourceNotFoundException(){
        super(defaultMessage);
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}
