package tech.gdev.springbasicexplore.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.gdev.springbasicexplore.controller.ErrorController;

import javax.validation.ConstraintViolationException;

/**
 * @author gdev
 * @date 2025/7/20 16:15
 */
@Log4j2
@RestControllerAdvice(basePackageClasses = ErrorController.class)
public class ControllerAdviceBean2 {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> exceptionHandlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getClass().getName());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> exceptionHandlerConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getClass().getName());
    }
}
