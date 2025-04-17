package tech.gdev.springbasicexplore.jdbc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

/**
 * @author gdev
 * @date 2025/4/17 23:45
 */
@Getter
@Setter
@Log4j2
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private String password;
    private List<Order> orderList; // 用于关联订单
}