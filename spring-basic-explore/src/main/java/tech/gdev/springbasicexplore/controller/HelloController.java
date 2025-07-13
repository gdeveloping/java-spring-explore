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

    @GetMapping("/hello")
    public Map<String, String> hello1(@RequestParam(value = "name", required = false) @Validated @Length(min = 2) String name) {
        if (!StringUtils.hasLength(name)) {
            name = "DefaultName";
        }
        return Collections.singletonMap("body", "Hello, " + name);
    }

    /**
     * 包含路径参数的 url，例如 `/explore/hello//hello/{name}/morning` 不会被缓存到
     * `org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.MappingRegistry#pathLookup` 中。
     */
    @GetMapping("/hello/{name}/morning")
    public Map<String, String> hello2(@PathVariable(value = "name") @Length(min = 2) String name) {
        return Collections.singletonMap("body", "Hello, " + name + " , morning");
    }

    /**
     * URL 优先级高于包含通配符的 URL {@link HelloController#morning2()} }
     */
    @GetMapping("/morning/{name}")
    public Map<String, String> morning1(@PathVariable(value = "name") @Length(min = 2) String name) {
        return Collections.singletonMap("body", "Morning, " + name);
    }

    /**
     * 因包含通配符，所以 URL 优先级低于 {@link HelloController#morning1(String)}
     */
    @GetMapping("/morning/**")
    public Map<String, String> morning2() {
        return Collections.singletonMap("body", "Morning");
    }
}
