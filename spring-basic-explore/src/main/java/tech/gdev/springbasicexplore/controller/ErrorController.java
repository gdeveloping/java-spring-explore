package tech.gdev.springbasicexplore.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.gdev.springbasicexplore.controller.entity.PersonInfo;
import tech.gdev.springbasicexplore.support.exception.runtimeexception.DebugRuntimeException;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Map;

/**
 * @author gdev
 * @date 2025/6/23 23:29
 */
@Log4j2
@RestController
@Validated
@RequestMapping("/error")
public class ErrorController {
    @GetMapping("/debug-runtime-exception")
    public Map<String, String> errorDebugRuntimeException(@RequestBody PersonInfo user) {
        throw new DebugRuntimeException(this.getClass().getSimpleName());
    }

    @GetMapping("/illegal-state-exception")
    public Map<String, String> errorIllegalStateException(@RequestBody PersonInfo user) {
        throw new IllegalStateException(this.getClass().getSimpleName());
    }

    @GetMapping("/runtime-exception")
    public Map<String, String> errorRuntimeException(@RequestBody PersonInfo user) {
        throw new RuntimeException(this.getClass().getSimpleName());
    }

    @GetMapping("/method-argument-not-valid-exception")
    public Map<String, String> errorMethodArgumentNotValidException(
            @RequestBody PersonInfo user) {
        throw new RuntimeException(this.getClass().getSimpleName());
    }

    @GetMapping("/constraint-violation-exception")
    public Map<String, String> errorConstraintViolationException(
            @RequestParam("id") @Positive Integer id,
            @RequestParam("user_id") @Positive Integer userId,
            @RequestParam("name") @NotBlank String name) {
        throw new RuntimeException(this.getClass().getSimpleName());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> exceptionHandlerConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getClass().getName());
    }
}
