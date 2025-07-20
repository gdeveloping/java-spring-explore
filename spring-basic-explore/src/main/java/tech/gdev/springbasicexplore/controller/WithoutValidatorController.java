package tech.gdev.springbasicexplore.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gdev.springbasicexplore.controller.entity.PersonInfo;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

/**
 * @author gdev
 * @date 2025/6/29 10:51
 */
@RestController
@RequestMapping("/explore/without-validated/")
@Log4j2
public class WithoutValidatorController {
    @GetMapping("/person-info/way1")
    public Map<String, String> build1(@RequestBody PersonInfo personInfo) {
        return Collections.singletonMap("body", "Hello, " + personInfo.getName());
    }

    @GetMapping("/person-info/way2")
    public Map<String, String> build2(@Valid PersonInfo personInfo) {
        return Collections.singletonMap("body", "Hello, " + personInfo.getName());
    }
}
