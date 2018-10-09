package com.qcz.rabbitmq.listener;


import com.qcz.rabbitmq.pojo.Package;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MessageDelegate {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*public void handleMessage(byte[] messageBody) {
        logger.info("Received Message from [queue01]: " + new String(messageBody));
    }

    public void handleMessage2(byte[] messageBody) {
        logger.info("Received Message from [queue01]: " + new String(messageBody));
    }*/

//    public void handleMessage(String messageBody) {
//        logger.info("Received Message from [queue01]: " + messageBody);
//    }
//
//    public void handleMessage2(String messageBody) {
//        logger.info("Received Message from [queue02]: " + messageBody);

//    public void handleMessage(Map messageBody) {
//        logger.info("Received Message from [queue01]: " + messageBody);
//    }
//
//    public void handleMessage2(Map messageBody) {
//        logger.info("Received Message from [queue02]: " + messageBody);
//    }

    public void handleMessage(Package package1) {
        logger.info("Received Message from [queue01]: " + package1.getId() + ";" + package1.getDesc());
    }

    public void handleMessage2(Package package1) {
        logger.info("Received Message from [queue02]: " + package1.getId() + ";" + package1.getDesc());
    }

}
