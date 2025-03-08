package tech.gdev.springbasicexplore.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * @author gdev
 * @date 2025/3/8 10:25
 */
@Controller
@RequestMapping("/explore")
@ResponseBody
@Log4j2
public class ControllerBean {

    @GetMapping("/hello")
    public Map hello(@RequestParam(value = "name", required = false) String name) {
        if (!StringUtils.hasLength(name)) {
            name = "DefaultName";
        }
        return Collections.singletonMap("body", "Hello, " + name);
    }

    @GetMapping("/hello/{name}")
    public Map hello2(@PathVariable(value = "name") String name) {
        return Collections.singletonMap("body", "Hello, " + name);
    }

    @GetMapping("/person-info")
    public Map build(@RequestBody PersonInfo personInfo) {
        return Collections.singletonMap("body", "Hello, " + personInfo.name);
    }

    @Getter
    @Setter
    static class PersonInfo {
        private String name;
        private int age;
    }
}
