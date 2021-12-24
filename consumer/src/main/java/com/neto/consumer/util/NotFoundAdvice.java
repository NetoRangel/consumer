package com.neto.consumer.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DeliveryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String DeliveryNotFoundHandler(DeliveryNotFoundException ex) {
        return ex.getMessage();
    }

}
