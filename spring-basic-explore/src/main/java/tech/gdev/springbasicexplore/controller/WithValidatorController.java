package tech.gdev.springbasicexplore.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gdev.springbasicexplore.controller.entity.PersonInfo;

import java.util.Collections;
import java.util.Map;

/**
 * @author gdev
 * @date 2025/6/29 10:44
 */
@RestController
@RequestMapping("/explore/with-validated/")
@Validated
@Log4j2
public class WithValidatorController {
    @GetMapping("/person-info/")
    public Map<String, String> build(@RequestBody PersonInfo personInfo) {
        return Collections.singletonMap("body", "Hello, " + personInfo.getName());
    }
}
