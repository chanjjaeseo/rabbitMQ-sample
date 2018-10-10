package com.qcz.rabbitmq.pojo;

import java.time.LocalDateTime;

public class Package {

    private int id;

    private String desc;

//    private LocalDateTime sendTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

//    public LocalDateTime getSendTime() {
//        return sendTime;
//    }
//
//    public void setSendTime(LocalDateTime sendTime) {
//        this.sendTime = sendTime;
//    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id:")
                .append(id)
                .append(", desc:")
                .append(desc).toString();
    }
}
