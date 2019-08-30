package com.example.boot.exception.exception;

import com.example.boot.exception.pojo.WebJsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dyz
 */
//@RestControllerAdvice
public class AjaxExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public WebJsonResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        e.printStackTrace();
        return WebJsonResult.errorException(e.getMessage());
    }

}
