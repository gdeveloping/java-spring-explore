package tech.gdev.springbasicexplore.jdbc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gdev.springbasicexplore.jdbc.entity.Order;
import tech.gdev.springbasicexplore.jdbc.entity.User;
import tech.gdev.springbasicexplore.jdbc.service.UserOrderService;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<User> selectUserAndOrdersByCallProcedure(@PathVariable int id) {
        User ret = userOrderService.selectUserAndOrdersByCallProcedure(id);
        return Objects.isNull(ret) ? ResponseEntity.notFound().build() : ResponseEntity.ok(ret);
    }

    @GetMapping("/user/subquery/{id}")
    public ResponseEntity<User> selectUserAndOrdersBySubQuery(@PathVariable int id) {
        User ret = userOrderService.selectUserAndOrdersBySubQuery(id);
        return Objects.isNull(ret) ? ResponseEntity.notFound().build() : ResponseEntity.ok(ret);
    }

    @GetMapping("/user/flat-data-all")
    public ResponseEntity<List<User>> selectUserAndOrdersByFlatDataAll() {
        List<User> ret = userOrderService.selectUserAndOrdersByFlatDataAll();
        return Objects.isNull(ret) ? ResponseEntity.notFound().build() : ResponseEntity.ok(ret);
    }

    @GetMapping("/user/flat-data/{id}")
    public ResponseEntity<User> selectUserAndOrdersByFlatData(@PathVariable int id) {
        User ret = userOrderService.selectUserAndOrdersByFlatData(id);
        return Objects.isNull(ret) ? ResponseEntity.notFound().build() : ResponseEntity.ok(ret);
    }

    @GetMapping("/order/jdbc-template/{id}")
    public ResponseEntity<Order> selectOrderByIdByJdbcTemplate(@PathVariable int id) {
        Order ret = userOrderService.selectOrderByIdByJdbcTemplate(id);
        return Objects.isNull(ret) ? ResponseEntity.notFound().build() : ResponseEntity.ok(ret);
    }

    @GetMapping("/order/sql-session-template/{id}")
    public ResponseEntity<Order> selectOrderByIdBySqlSessionTemplate(@PathVariable int id) {
        Order ret = userOrderService.selectOrderByIdBySqlSessionTemplate(id);
        return Objects.isNull(ret) ? ResponseEntity.notFound().build() : ResponseEntity.ok(ret);
    }
}
