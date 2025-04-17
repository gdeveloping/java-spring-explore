package tech.gdev.springbasicexplore.jdbc.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.gdev.springbasicexplore.jdbc.entity.User;

/**
 * @author gdev
 * @date 2025/4/17 23:47
 */
@Mapper
public interface UserOrderMapper {
    User selectUserAndOrders(int id);
}
