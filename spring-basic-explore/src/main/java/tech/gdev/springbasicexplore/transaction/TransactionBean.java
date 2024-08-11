package tech.gdev.springbasicexplore.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Listener
 * @author gdev
 * @date 2024/8/4 09:47
 */
@Component
public class TransactionBean {
    @Transactional
    public void transaction() {
        System.out.println("transaction");
    }
}
