package hu.kukutyin.engine.bean;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:application.properties")
})
public class JndiConfig {

    @Value("${jndi.datasource.ebh}")
    private String mySqlDataSource;

    @Value("${jndi.connection.factory.ihs}")
    private String ihsConnectionFactory;

    @Bean(name = "mySql")
    public DataSource dataSource() throws NamingException {
        final JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName(mySqlDataSource);
        jndiObjectFactoryBean.setProxyInterface(DataSource.class);
        jndiObjectFactoryBean.setLookupOnStartup(true);
        jndiObjectFactoryBean.afterPropertiesSet();
        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    @Bean(name = "ihsConnectionFactory")
    public ConnectionFactory connectionFactory() throws NamingException {
        final JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName(ihsConnectionFactory);
        jndiObjectFactoryBean.setProxyInterface(ConnectionFactory.class);
        jndiObjectFactoryBean.afterPropertiesSet();
        return (ConnectionFactory) jndiObjectFactoryBean.getObject();
    }

    @Bean(name = "ihsJndiTemplate")
    public JndiTemplate jndiTemplate() {
        return new JndiTemplate();
    }

    @Bean(name = "ihsJndiDestinationResolver")
    public JndiDestinationResolver jndiDestinationResolver() {
        JndiDestinationResolver resolver = new JndiDestinationResolver();
        resolver.setJndiTemplate(jndiTemplate());
        return resolver;
    }

}
