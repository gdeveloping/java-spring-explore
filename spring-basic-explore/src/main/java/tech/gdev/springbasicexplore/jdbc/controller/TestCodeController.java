package tech.gdev.springbasicexplore.jdbc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.gdev.springbasicexplore.jdbc.entity.TestCode;
import tech.gdev.springbasicexplore.jdbc.service.TestCodeService;
import tech.gdev.springbasicexplore.support.exception.runtimeexception.DebugRuntimeException;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

/**
 * @author gdev
 * @date 2025/3/30 16:25
 */
@Log4j2
@RestController
@RequestMapping("/jdbc/test-code")
@Validated
public class TestCodeController {

    @Autowired
    private TestCodeService testCodeService;

    @GetMapping
    public List<TestCode> getByOption(@RequestParam(value = "id", required = false) Integer id,
                                      @RequestParam(value = "code", required = false) Integer code,
                                      @RequestParam(value = "note", required = false) String note) {
        return testCodeService.getByOption(new TestCode(id, code, note));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestCode>> getAllCodes() {
        return ResponseEntity.ok(testCodeService.getAllCodes());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TestCode> getById(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(testCodeService.getById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<List<TestCode>> getByCode(@PathVariable @Positive int code) {
        return ResponseEntity.ok(testCodeService.getByCode(code));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestCode> save1(@RequestBody TestCode testCode) {
        mustNotExists(testCode);
        testCodeService.save(testCode);
        return ResponseEntity.ok(testCode);
    }

    @PostMapping(value = "/save",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public TestCode save2(@RequestParam("code") @Positive Integer code, @RequestParam("note") String note, @RequestParam("id") @Positive Integer id) {
        TestCode testCode = new TestCode(id, code, note);
        mustNotExists(testCode);
        testCodeService.save(testCode);
        return testCode;
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        mustExists(id);
        testCodeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam List<Integer> ids) {
        testCodeService.delete(ids);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/code")
    public ResponseEntity<TestCode> updateCodeById(@RequestBody TestCode testCode) {
        mustExists(testCode);
        testCodeService.updateCodeById(testCode);
        return ResponseEntity.ok(testCodeService.getById(testCode.getId()));
    }

    @PostMapping("/update/note")
    public ResponseEntity<TestCode> updateNoteById(@RequestBody TestCode testCode) {
        mustExists(testCode);
        testCodeService.updateNoteById(testCode);
        return ResponseEntity.ok(testCodeService.getById(testCode.getId()));
    }

    @PostMapping("/debug/update-then-rollback1")
    public ResponseEntity<TestCode> updateThenRollbackById1(@RequestBody TestCode testCode) {
        mustExists(testCode);
        try {
            testCodeService.updateThenRollbackById1(testCode);
        } catch (DebugRuntimeException e) {
            // ignore
        }
        testCode = testCodeService.getById(testCode.getId());
        log.info("after-rollback testCode: {}", testCode);
        return ResponseEntity.ok(testCode);
    }

    @PostMapping("/debug/update-then-rollback2")
    public ResponseEntity<TestCode> updateThenRollbackById2(@RequestBody TestCode testCode) {
        mustExists(testCode);
        try {
            testCodeService.updateThenRollbackById2(testCode);
        } catch (DebugRuntimeException e) {
            // ignore
        }
        testCode = testCodeService.getById(testCode.getId());
        log.info("after-rollback testCode: {}", testCode);
        return ResponseEntity.ok(testCode);
    }

    private void mustExists(Integer id) {
        if (Objects.isNull(testCodeService.getById(id))) {
            throw new DebugRuntimeException("TestCode with id " + id + " already exists");
        }
    }

    private void mustExists(TestCode testCode) {
        mustExists(testCode.getId());
    }

    private void mustNotExists(Integer id) {
        if (Objects.nonNull(testCodeService.getById(id))) {
            throw new DebugRuntimeException("TestCode with id " + id + " does not exist");
        }
    }

    private void mustNotExists(TestCode testCode) {
        mustNotExists(testCode.getId());
    }

    @ExceptionHandler(DebugRuntimeException.class)
    public ResponseEntity<Void> exceptionHandlerDebugRuntimeException(DebugRuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> exceptionHandlerDefault(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
