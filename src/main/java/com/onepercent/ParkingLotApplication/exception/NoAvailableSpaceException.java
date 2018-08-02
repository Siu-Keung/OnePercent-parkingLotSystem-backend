package com.onepercent.ParkingLotApplication.exception;

/**
 * @author Dylan Wei
 * @date 2018-08-02 14:58
 */
public class NoAvailableSpaceException extends  RuntimeException {
    private static String message = "车位已满，无法停车！";

    public NoAvailableSpaceException(){
        super(message);
    }

    public NoAvailableSpaceException(String message){
        super(message);
    }


}
