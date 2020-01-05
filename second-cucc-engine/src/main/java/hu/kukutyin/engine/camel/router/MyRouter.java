package hu.kukutyin.engine.camel.router;

import org.apache.camel.builder.RouteBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyRouter extends RouteBuilder {

    @Value("${router.queue.test}")
    private String TEST_QUEUE;

    @Override
    public void configure() throws Exception {
        from(TEST_QUEUE).to("stream:out");
    }
}
