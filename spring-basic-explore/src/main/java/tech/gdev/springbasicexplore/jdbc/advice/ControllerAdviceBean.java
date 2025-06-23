package tech.gdev.springbasicexplore.jdbc.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.gdev.springbasicexplore.support.exception.runtimeexception.DebugRuntimeException;

/**
 * @author gdev
 * @date 2025/6/23 23:23
 */
@Log4j2
@RestControllerAdvice
public class ControllerAdviceBean {
    @ExceptionHandler(DebugRuntimeException.class)
    public ResponseEntity<Void> exceptionHandlerDebugRuntimeException(DebugRuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> exceptionHandlerDefaultRuntime(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> exceptionHandlerDefaultAll(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
