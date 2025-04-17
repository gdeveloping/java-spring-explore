package tech.gdev.springbasicexplore.jdbc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * @author gdev
 * @date 2025/4/17 23:46
 */
@Getter
@Setter
@Log4j2
public class Order {
    private Integer id;
    private Integer userId;
    private String name;
}