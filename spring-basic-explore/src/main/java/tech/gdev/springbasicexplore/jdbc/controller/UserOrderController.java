package tech.gdev.springbasicexplore.jdbc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gdev.springbasicexplore.jdbc.entity.User;
import tech.gdev.springbasicexplore.jdbc.service.UserOrderService;

/**
 * @author gdev
 * @date 2025/4/17 23:51
 */
@Log4j2
@RestController
@RequestMapping("/jdbc/test_user_order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserAndOrders(@PathVariable int id) {
        User ret = userOrderService.selectUserAndOrders(id);
        if (ret == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ret);
    }
}
