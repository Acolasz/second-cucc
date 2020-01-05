package hu.kukutyin.engine.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import org.springframework.stereotype.Component;

@Component
public class MyProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String originalContent = (String) exchange.getIn().getBody(String.class);
        String upperCaseContent = originalContent.toUpperCase();
        exchange.getIn().setBody(upperCaseContent);
    }
}
