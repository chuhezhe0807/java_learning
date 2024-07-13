package com.chuhezhe.webapplication.handler;

import com.chuhezhe.webapplication.exception.UserNotExistException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.chuhezhe.webapplication.handler
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/3 22:04
 * @Version 1.0
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    /**
     * 统一处理请求参数校验的异常
     *
     * @param e {ConstraintViolationException}
     * @return {String}
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        for (ConstraintViolation<?> violation : constraintViolations) {
            Path path = violation.getPropertyPath();
            String[] splitArr = StringUtils.split(path.toString(), ".");
            message.append(splitArr[1]).append(violation.getMessage()).append(",");
        }

        return message.substring(0, message.length() - 1);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintValidException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }

        return message.substring(0, message.length() - 1);
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistException(UserNotExistException e) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("message", e.getMessage());

        return map;
    }
}
