package hu.kukutyin.engine.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan(basePackages = "hu.kukutyin.engine")
@PropertySources({
        @PropertySource(value = "classpath:application.properties")
})
public class ApplicationContextConfig {
}
