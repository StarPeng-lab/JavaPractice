package cn.com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Configuration
@Component("cn.com")
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {
}
