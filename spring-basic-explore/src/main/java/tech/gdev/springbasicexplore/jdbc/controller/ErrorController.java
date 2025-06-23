package tech.gdev.springbasicexplore.jdbc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.gdev.springbasicexplore.jdbc.entity.Order;
import tech.gdev.springbasicexplore.jdbc.entity.User;
import tech.gdev.springbasicexplore.support.exception.runtimeexception.DebugRuntimeException;

/**
 * @author gdev
 * @date 2025/6/23 23:29
 */
@Log4j2
@RestController
@RequestMapping("/error")
public class ErrorController {
    @GetMapping("/user")
    public User errorUser(@RequestBody User user) {
        throw new DebugRuntimeException("/error/user");
    }

    @GetMapping("/order")
    public Order errorOrder(@RequestParam("id") Integer id, @RequestParam("user_id") Integer userId, @RequestParam("name") String name) {
        throw new RuntimeException("/error/order");
    }
}
