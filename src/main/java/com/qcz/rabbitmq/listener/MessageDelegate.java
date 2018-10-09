package com.qcz.rabbitmq.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDelegate {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    public void handleMessage(byte[] messageBody) {
//        logger.info("Received Message from [queue01]: " + new String(messageBody));
//    }
//
//    public void handleMessage2(byte[] messageBody) {
//        logger.info("Received Message from [queue02]: " + new String(messageBody));
//    }

    public void handleMessage(String messageBody) {
        logger.info("Received Message from [queue01]: " + messageBody);
    }

    public void handleMessage2(String messageBody) {
        logger.info("Received Message from [queue02]: " + messageBody);
    }

}
