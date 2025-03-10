package tech.gdev.springbasicexplore.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.Map;

/**
 * @author gdev
 * @date 2025/3/8 10:25
 */
@Controller
@RequestMapping("/explore")
@ResponseBody
@Validated
@Log4j2
public class ControllerBean {

    @GetMapping("/hello")
    public Map hello(@RequestParam(value = "name", required = false) String name) {
        if (!StringUtils.hasLength(name)) {
            name = "DefaultName";
        }
        return Collections.singletonMap("body", "Hello, " + name);
    }

    @GetMapping("/hello2/{name}")
    public Map hello2(@PathVariable(value = "name") @Length(min = 2) String name) {
        return Collections.singletonMap("body", "Hello, " + name);
    }

    @GetMapping("/person-info")
    public Map build(@Validated @RequestBody PersonInfo personInfo) {
        return Collections.singletonMap("body", "Hello, " + personInfo.name);
    }

    @Getter
    @Setter
    static class PersonInfo {
        @NotBlank
        private String name;

        @Positive
        private int age;
    }
}
