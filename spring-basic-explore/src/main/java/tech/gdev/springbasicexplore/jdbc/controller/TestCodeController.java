package tech.gdev.springbasicexplore.jdbc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gdev.springbasicexplore.jdbc.entity.TestCode;
import tech.gdev.springbasicexplore.jdbc.service.TestCodeService;
import tech.gdev.springbasicexplore.support.exception.runtimeexception.DebugRuntimeException;

import java.util.List;
import java.util.Map;

/**
 * @author gdev
 * @date 2025/3/30 16:25
 */
@Log4j2
@RestController
@RequestMapping("/jdbc/test_code")
public class TestCodeController {

    @Autowired
    private TestCodeService testCodeService;

    // 使用 GET 方法获取单个 TestCode 对象
    @GetMapping("/{id}")
    public ResponseEntity<TestCode> getById(@PathVariable int id) {
        TestCode testCode = testCodeService.getById(id);
        if (testCode == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(testCode);
    }

    // 使用 GET 方法通过 code 获取单个 TestCode 对象
    @GetMapping("/code/{code}")
    public ResponseEntity<TestCode> getByCode(@PathVariable int code) {
        TestCode testCode = testCodeService.getByCode(code);
        if (testCode == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(testCode);
    }

    // 使用 GET 方法获取所有 TestCode 对象
    @GetMapping("/all")
    public ResponseEntity<List<TestCode>> getAllCodes() {
        List<TestCode> testCodes = testCodeService.getAllCodes();
        return ResponseEntity.ok(testCodes);
    }

    // 使用 POST 方法保存 TestCode 对象
    @PostMapping("/save")
    public ResponseEntity<Integer> save(@RequestBody TestCode testCode) {
        int result = testCodeService.save(testCode);
        return ResponseEntity.ok(result);
    }

    // 使用 POST 方法删除 TestCode 对象
    @PostMapping("/delete")
    public ResponseEntity<Integer> delete(@RequestBody Map<String, Integer> requestBody) {
        int id = requestBody.get("id");
        int result = testCodeService.delete(id);
        return ResponseEntity.ok(result);
    }

    // 使用 POST 方法更新 TestCode 对象的 code 属性
    @PostMapping("/update/code")
    public ResponseEntity<Integer> updateCodeById(@RequestBody TestCode testCode) {
        int result = testCodeService.updateCodeById(testCode);
        return ResponseEntity.ok(result);
    }

    // 使用 POST 方法更新 TestCode 对象的 note 属性
    @PostMapping("/update/note")
    public ResponseEntity<Integer> updateNoteById(@RequestBody TestCode testCode) {
        int result = testCodeService.updateNoteById(testCode);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/debug/update-then-rollback")
    public ResponseEntity<TestCode> updateThenRollbackById(@RequestBody TestCode testCode) {
        try {
            testCodeService.updateThenRollbackById(testCode);
        } catch (DebugRuntimeException e) {
            // ignore
        }
        testCode = testCodeService.getById(testCode.getId());
        log.info("after-rollback testCode: {}", testCode);
        return ResponseEntity.ok(testCode);
    }
}
