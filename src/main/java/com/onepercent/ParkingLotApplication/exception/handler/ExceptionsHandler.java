package com.onepercent.ParkingLotApplication.exception.handler;

import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Dylan Wei
 * @date 2018-08-01 11:26
 */
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(ResourceNotFoundException e){
        return e.getMessage();
    }


}
