package hu.kukutyin.engine.bean;

import org.apache.camel.spring.javaconfig.CamelConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "hu.kukutyin.engine.camel")
public class CamelContextConfig extends CamelConfiguration {
}
