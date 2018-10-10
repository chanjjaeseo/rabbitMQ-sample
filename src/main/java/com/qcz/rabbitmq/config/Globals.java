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

    public String getRabbitAddress() {
        return rabbitAddress;
    }

    public void setRabbitAddress(String rabbitAddress) {
        this.rabbitAddress = rabbitAddress;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getChannelCacheSize() {
        return channelCacheSize;
    }

    public void setChannelCacheSize(int channelCacheSize) {
        this.channelCacheSize = channelCacheSize;
    }

    public int getChannelCheckoutTimeOut() {
        return channelCheckoutTimeOut;
    }

    public void setChannelCheckoutTimeOut(int channelCheckoutTimeOut) {
        this.channelCheckoutTimeOut = channelCheckoutTimeOut;
    }
}
