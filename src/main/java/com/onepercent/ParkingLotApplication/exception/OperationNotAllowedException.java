package com.onepercent.ParkingLotApplication.exception;

/**
 * @author Dylan Wei
 * @date 2018-08-01 14:18
 */
public class OperationNotAllowedException extends RuntimeException {
    private static final String defaultMessage = "操作被禁止！";

    public OperationNotAllowedException(){
        super(defaultMessage);
    }

    public OperationNotAllowedException(String message){
        super(message);
    }

}
