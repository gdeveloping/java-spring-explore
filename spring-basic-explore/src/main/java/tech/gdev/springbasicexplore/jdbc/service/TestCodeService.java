package tech.gdev.springbasicexplore.jdbc.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import tech.gdev.springbasicexplore.jdbc.entity.TestCode;
import tech.gdev.springbasicexplore.jdbc.mapper.TestCodeMapper;
import tech.gdev.springbasicexplore.support.exception.runtimeexception.DebugRuntimeException;

import java.util.List;

/**
 * @author gdev
 * @date 2025/3/30 16:22
 */
@Log4j2
@Service
public class TestCodeService {
    @Autowired
    private TestCodeMapper testCodeMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public TestCode getById(int id) {
        return testCodeMapper.selectById(id);
    }

    public TestCode getByCode(int code) {
        return testCodeMapper.selectByCode(code);
    }

    public List<TestCode> getAllCodes() {
        return testCodeMapper.selectAll();
    }

    public int save(TestCode testCode) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        int res = 0;
        try {
            res = testCodeMapper.insert(testCode);
        } catch (Exception e) {
            transactionManager.rollback(status);
            return res;
        }
        transactionManager.commit(status);
        return res;
    }

    public int delete(int id) {
        return transactionTemplate.execute(status -> testCodeMapper.deleteById(id));
    }

    @Transactional
    public int updateCodeById(TestCode testCode) {
        return testCodeMapper.updateCodeById(testCode);
    }

    @Transactional
    public int updateNoteById(TestCode testCode) {
        return jdbcTemplate.update("update test_code set note=? where id=?", testCode.getNote(), testCode.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateThenRollbackById1(TestCode testCode) {
        TestCode current = testCodeMapper.selectById(testCode.getId());
        log.info("current testCode: {}", current);

        int res = testCodeMapper.updateById(testCode);
        log.info("update res: {}", res);

        TestCode after = testCodeMapper.selectById(testCode.getId());
        log.info("after-update testCode: {}", after);

        throw new DebugRuntimeException("debug Transaction");
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateThenRollbackById2(TestCode testCode) {
        String selectSql = "SELECT * FROM test_code WHERE id = ?";
        String updateSql = "UPDATE test_code SET code = ?, note = ? WHERE id = ?";

        TestCode current = jdbcTemplate.queryForObject(selectSql, new Object[]{testCode.getId()},
                (rs, rowNum) -> new TestCode(rs.getInt("id"), rs.getInt("code"), rs.getString("note")));
        log.info("current testCode: {}", current);
        int res = jdbcTemplate.update(updateSql, testCode.getCode(), testCode.getNote(), testCode.getId());
        log.info("update res: {}", res);
        TestCode after = jdbcTemplate.queryForObject(selectSql, new Object[]{testCode.getId()},
                (rs, rowNum) -> new TestCode(rs.getInt("id"), rs.getInt("code"), rs.getString("note")));
        log.info("after-update testCode: {}", after);

        throw new DebugRuntimeException("debug Transaction");
    }
}
