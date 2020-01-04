package hu.kukutyin.engine.bean;

import javax.jms.ConnectionFactory;

import hu.kukutyin.engine.jms.SwiftMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.JndiDestinationResolver;

/**
 * @see "https://www.baeldung.com/spring-jms"
 */
@EnableJms
@Configuration
public class JmsTemplateConfig {

    @Value("${jndi.queue.to.clavis}")
    private String swiftQueueName;

    @Value("${jms.consumer.number}")
    private String consumerNumber;

    @Autowired
    @Qualifier("ihsConnectionFactory")
    private ConnectionFactory ihsConnectionFactory;

    @Autowired
    @Qualifier("ihsJndiDestinationResolver")
    private JndiDestinationResolver jndiDestinationResolver;

    @Autowired
    private SwiftMessageListener swiftMessageListener;

    @Bean
    public DefaultMessageListenerContainer jmsListenerContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(ihsConnectionFactory);
        container.setDestinationResolver(jndiDestinationResolver);
        container.setMessageListener(swiftMessageListener);
        container.setDestinationName(swiftQueueName);
        container.setConcurrency(consumerNumber);
        return container;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(ihsConnectionFactory);
        return jmsTemplate;
    }
}