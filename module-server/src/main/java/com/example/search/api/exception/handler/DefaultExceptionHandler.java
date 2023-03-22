package com.example.search.api.exception.handler;

import com.example.search.api.constants.StatusCodeConstants;
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
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {
    public static final String TYPE_MISMATCH = "typeMismatch";

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BaseResponse<String>> handleException(Exception exception) {
        log.error(exception.getMessage());
        if (exception instanceof BindException) {
            BindException ex = (BindException) exception;
            BindingResult bindingResult = ex.getBindingResult();

            StringJoiner message = new StringJoiner(" ,");
            for (FieldError err : bindingResult.getFieldErrors()) {
                if (TYPE_MISMATCH.equals(err.getCode())) {
                    StringBuffer stringBuffer = new StringBuffer();
                    if (err.getRejectedValue() != null) {
                        stringBuffer.append(err.getRejectedValue().toString());
                    } else {
                        stringBuffer.append("NULL");
                    }
                    stringBuffer.append(" is invalid value for : ");
                    stringBuffer.append(err.getField());
                    message.add(stringBuffer.toString());
                } else {
                    message.add(err.getDefaultMessage());
                }
            }
            return ResponseUtility.createFailResponse(
                    StatusCodeConstants.INVALID_PARAMETER,
                    message.toString(),
                    HttpStatus.BAD_REQUEST);
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            return ResponseUtility.createFailResponse(
                    StatusCodeConstants.INVALID_PARAMETER,
                    exception.getMessage(),
                    HttpStatus.BAD_REQUEST);

        } else {
            log.error("Unhandled Exception : ", exception);
            return ResponseUtility.createFailResponse(
                    exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<String>> handleException(BusinessException ex) {
        log.error(ex.getMessage());
        if (ex.getStatusCode().equals(StatusCodeConstants.EMPTY_RESULT))
            return ResponseUtility.createGetSuccessResponse(
                    StatusCodeConstants.EMPTY_RESULT, ex.getMessage());

        return ResponseUtility.createFailResponse(ex.getStatusCode(), ex.getMessage());
    }
}
