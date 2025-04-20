package tech.gdev.springbasicexplore.jdbc.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tech.gdev.springbasicexplore.jdbc.entity.Order;
import tech.gdev.springbasicexplore.jdbc.entity.User;
import tech.gdev.springbasicexplore.jdbc.mapper.UserOrderMapper;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public User selectUserAndOrdersByCallProcedure(int id) {
        return userOrderMapper.selectUserAndOrdersByCallProcedure(id);
    }

    public User selectUserAndOrdersBySubQuery(int id) {
        return userOrderMapper.selectUserAndOrdersBySubQuery(id);
    }

    public List<User> selectUserAndOrdersByFlatDataAll() {
        return userOrderMapper.selectUserAndOrdersByFlatData(null);
    }

    public User selectUserAndOrdersByFlatData(int id) {
        List<User> res = userOrderMapper.selectUserAndOrdersByFlatData(id);
        return CollectionUtils.isEmpty(res) ? null : res.get(0);
    }

    public Order selectOrderByIdByJdbcTemplate(int id) {
        String sql = "select * from `order` where user_id = ?";
        List<Order> lst = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<Order>() {
            @Override
            public Order mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setName(rs.getString("name"));
                return order;
            }
        });
        return lst.get(0);
    }

    public Order selectOrderByIdBySqlSessionTemplate(int id) {
        String statement = "tech.gdev.springbasicexplore.jdbc.mapper.UserOrderMapper.selectOrderById";
        final Order[] ret = new Order[1];
        sqlSessionTemplate.select(statement, Collections.singletonMap("id", id), new RowBounds(0, 10), new ResultHandler<Order>() {
            @Override
            public void handleResult(ResultContext<? extends Order> resultContext) {
                Order order = resultContext.getResultObject();
                ret[0] = order;
            }
        });
        return ret[0];
    }
}