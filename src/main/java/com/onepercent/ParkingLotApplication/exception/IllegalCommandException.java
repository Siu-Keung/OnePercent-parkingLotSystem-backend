package com.onepercent.ParkingLotApplication.exception;

/**
 * @author Dylan Wei
 * @date 2018-08-02 16:48
 */
public class IllegalCommandException extends RuntimeException {
    private static final String defaultMessage = "无效指令";

    public IllegalCommandException(){
        super(defaultMessage);
    }

    public IllegalCommandException(String message){
        super(message);
    }


}
