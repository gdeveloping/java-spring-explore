package tech.gdev.springbasicexplore.jdbc.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.gdev.springbasicexplore.jdbc.entity.User;
import tech.gdev.springbasicexplore.jdbc.mapper.UserOrderMapper;

/**
 * @author gdev
 * @date 2025/4/17 23:49
 */
@Service
@Getter
@Setter
public class UserOrderService {
    @Autowired
    private UserOrderMapper userOrderMapper;

    public User selectUserAndOrders(int id) {
        return userOrderMapper.selectUserAndOrders(id);
    }
}
