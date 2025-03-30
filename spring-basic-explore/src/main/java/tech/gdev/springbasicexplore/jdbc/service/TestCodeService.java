package tech.gdev.springbasicexplore.jdbc.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return testCodeMapper.insert(testCode);
    }

    public int delete(int id) {
        return testCodeMapper.deleteById(id);
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
    public int updateThenRollbackById(TestCode testCode) {
        TestCode current = testCodeMapper.selectById(testCode.getId());
        log.info("current testCode: {}", current);
        int res = testCodeMapper.updateById(testCode);
        log.info("update res: {}", res);
        TestCode after = testCodeMapper.selectById(testCode.getId());
        log.info("after-update testCode: {}", after);
        throw new DebugRuntimeException("debug Transaction");
    }
}
