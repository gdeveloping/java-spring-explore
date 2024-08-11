package tech.gdev.springbasicexplore.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author gdev
 * @date 2024/8/1 21:51
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class AppPropertyBean {
    private String myvar1;
}
