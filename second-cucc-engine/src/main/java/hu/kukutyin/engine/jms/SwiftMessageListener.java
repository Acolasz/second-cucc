package hu.kukutyin.engine.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

import hu.kukutyin.core.utils.ThreadNameFromSessionId;

/**
 * @see "https://www.baeldung.com/spring-jms"
 */
@Component
public class SwiftMessageListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(SwiftMessageListener.class);

    @Override
    public void onMessage(Message message) {
        final String tn = Thread.currentThread().getName();
        if (message instanceof TextMessage) {
            try {
                ThreadNameFromSessionId.setJms(message.getJMSMessageID() + tn);
                String msg = ((TextMessage) message).getText();
                logger.info(String.format("Message has been consumed: %s", msg));
            } catch (JMSException e) {
                throw new RuntimeException(e);
            } finally {
                ThreadNameFromSessionId.setJms(tn);
            }
        } else {
            throw new IllegalArgumentException("Message Error");
        }
    }
}