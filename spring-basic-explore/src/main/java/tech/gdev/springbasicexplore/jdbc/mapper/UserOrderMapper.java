package tech.gdev.springbasicexplore.jdbc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tech.gdev.springbasicexplore.jdbc.entity.Order;
import tech.gdev.springbasicexplore.jdbc.entity.User;

import java.util.List;

/**
 * @author gdev
 * @date 2025/4/17 23:47
 */
@Mapper
public interface UserOrderMapper {
    User selectUserAndOrdersByCallProcedure(int id);

    User selectUserAndOrdersBySubQuery(int id);

    List<User> selectUserAndOrdersByFlatData(Integer id);

    Order selectOrderByUserId(int id);

    Order selectOrderById(int id);
}
