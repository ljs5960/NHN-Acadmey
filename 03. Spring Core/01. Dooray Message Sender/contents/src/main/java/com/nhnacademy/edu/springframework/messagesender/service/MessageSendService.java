package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MessageSendService {
    private MessageSender messageSender;

//    @Value("${type}")
//    private String type;

    public MessageSendService() {}

    @Autowired
    public MessageSendService (MessageSender messageSender) {
        System.out.println("--------injection--------");
        this.messageSender = messageSender;
    }

    public void setMessageSender(MessageSender messageSender) {
        System.out.println("Set by Setter!!");
        this.messageSender = messageSender;
    }

//    // autowire="byName"
//    // setter set 뒤에 정확한 클래스명 작성하여 Name으로 DI 수행
//    public void setEmailMessageSender(MessageSender messageSender) {
//        System.out.println("Set by Setter!!");
//        this.messageSender = messageSender;
//    }
//
//    public void setSmsMessageSender(MessageSender messageSender) {
//        System.out.println("Set by Setter!!");
//        this.messageSender = messageSender;
//    }

    public void doSendMessgage() {
//        System.out.println("TYPE: " + type);
        messageSender.sendMessage(
            new User("test@test.com", "01012345678"), "testMessage"
        );
    }
}
