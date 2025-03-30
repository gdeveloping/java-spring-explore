package tech.gdev.springbasicexplore.jdbc.transaction;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot：直接使用 @Transactional，无需额外配置 @EnableTransactionManagement。
 * Spring Boot 的自动配置（通过 spring-boot-starter-jdbc 或 spring-boot-starter-data-jpa）已经默认启用了事务管理。
 * 底层是通过 TransactionAutoConfiguration 自动配置的，条件化地激活事务功能。
 *
 * 为了方便调试代码，此处显式配置 @EnableTransactionManagement。
 *
 * @author gdev
 * @date 2024/8/4 09:56
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {
}
