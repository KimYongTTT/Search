package com.example.search.api.exception.handler;

import com.example.search.api.exception.BusinessException;
import com.example.search.api.model.BaseResponse;
import com.example.search.api.utility.ResponseUtility;

import java.util.StringJoiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BaseResponse> handleException(Exception exception) {
        log.error(exception.getMessage());
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
            BindingResult bindingResult = ex.getBindingResult();
            StringJoiner message = new StringJoiner(",");
            for (FieldError err : bindingResult.getFieldErrors())
                message.add(err.getDefaultMessage());

            return ResponseUtility.createFailResponse(message.toString(), HttpStatus.BAD_REQUEST);
        } else if (exception instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException ex =
                    (MissingServletRequestParameterException) exception;
            return ResponseUtility.createFailResponse(
                    ex.getParameterName(), HttpStatus.BAD_REQUEST);
        } else if (exception instanceof BindException) {
            BindException ex = (BindException) exception;
            BindingResult bindingResult = ex.getBindingResult();
            StringJoiner message = new StringJoiner(",");
            for (FieldError err : bindingResult.getFieldErrors())
                message.add(err.getDefaultMessage());

            return ResponseUtility.createFailResponse(message.toString(), HttpStatus.BAD_REQUEST);
        } else {
            log.error("Unhandled Exception : ", exception);
            return ResponseUtility.createFailResponse(
                    exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse> handleException(BusinessException ex) {
        log.error(ex.getMessage());
        return ResponseUtility.createFailResponse(ex.getMessage(), ex.getCode());
    }
}
