package tech.gdev.springbasicexplore.controller;

import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author gdev
 * @date 2025/3/8 10:25
 */
@RestController
@RequestMapping("/explore/hello/")
@Validated
@Log4j2
public class HelloController {

    @GetMapping("/hello1")
    public Map<String, String> hello(@RequestParam(value = "name", required = false) @Validated @Length(min = 2) String name) {
        if (!StringUtils.hasLength(name)) {
            name = "DefaultName";
        }
        return Collections.singletonMap("body", "Hello, " + name);
    }

    @GetMapping("/hello2/{name}")
    public Map<String, String> hello2(@PathVariable(value = "name") @Length(min = 2) String name) {
        return Collections.singletonMap("body", "Hello, " + name);
    }
}
