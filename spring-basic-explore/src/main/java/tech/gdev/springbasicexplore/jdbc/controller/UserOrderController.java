package tech.gdev.springbasicexplore.jdbc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gdev.springbasicexplore.jdbc.entity.Order;
import tech.gdev.springbasicexplore.jdbc.entity.User;
import tech.gdev.springbasicexplore.jdbc.service.UserOrderService;

import java.util.List;

/**
 * @author gdev
 * @date 2025/4/17 23:51
 */
@Log4j2
@RestController
@RequestMapping("/jdbc/test-user-order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("/user/procedure-multi-result-set/{id}")
    public User selectUserAndOrdersByCallProcedure(@PathVariable int id) {
        return userOrderService.selectUserAndOrdersByCallProcedure(id);
    }

    @GetMapping("/user/subquery/{id}")
    public User selectUserAndOrdersBySubQuery(@PathVariable int id) {
        return userOrderService.selectUserAndOrdersBySubQuery(id);
    }

    @GetMapping("/user/flat-data-all")
    public List<User> selectUserAndOrdersByFlatDataAll() {
        return userOrderService.selectUserAndOrdersByFlatDataAll();
    }

    @GetMapping("/user/flat-data/{id}")
    public User selectUserAndOrdersByFlatData(@PathVariable int id) {
        return userOrderService.selectUserAndOrdersByFlatData(id);
    }

    @GetMapping("/order/jdbc-template/{id}")
    public Order selectOrderByIdByJdbcTemplate(@PathVariable int id) {
        return userOrderService.selectOrderByIdByJdbcTemplate(id);
    }

    @GetMapping("/order/sql-session-template/{id}")
    public Order selectOrderByIdBySqlSessionTemplate(@PathVariable int id) {
        return userOrderService.selectOrderByIdBySqlSessionTemplate(id);
    }
}
