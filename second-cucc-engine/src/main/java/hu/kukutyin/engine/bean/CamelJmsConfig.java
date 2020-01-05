package hu.kukutyin.engine.bean;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

import org.apache.camel.component.jms.JmsComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelJmsConfig {

    @Autowired
    ConnectionFactory ihsConnectionFactory;

    @Bean
    public JmsComponent jms() throws NamingException {
        final JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(ihsConnectionFactory);
        jmsComponent.setConcurrentConsumers(10);
        return jmsComponent;
    }

}
