package com.qcz.rabbitmq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Globals {

    @Value("${spring.rabbitmq.addresses}")
    private String rabbitAddress;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.cache.channel.size}")
    private int channelCacheSize;

    @Value("${spring.rabbitmq.cache.channel.checkout-timeout:180}")
    private int channelCheckoutTimeOut;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Value("${spring.rabbitmq.publisher-returns}")
    private boolean publisherReturns;

    @Value("${spring.rabbitmq.template.mandatory}")
    private boolean mandatory;

    public String getRabbitAddress() {
        return rabbitAddress;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getChannelCacheSize() {
        return channelCacheSize;
    }

    public int getChannelCheckoutTimeOut() {
        return channelCheckoutTimeOut;
    }

    public boolean isPublisherConfirms() {
        return publisherConfirms;
    }

    public boolean isPublisherReturns() {
        return publisherReturns;
    }

    public boolean isMandatory() {
        return mandatory;
    }
}
