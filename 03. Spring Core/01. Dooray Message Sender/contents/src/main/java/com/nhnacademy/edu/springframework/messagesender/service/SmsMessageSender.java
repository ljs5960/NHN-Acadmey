package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.User;

public class SmsMessageSender implements MessageSender {
    public SmsMessageSender() {
        System.out.println("+ SmsMessageSender Created!!");
    }

    // beans.xml 의 init-method 속성에 정의된 init(), 함수명은 자유롭게 변경 가능 -> init1()
    // 외부 라이브러리 의존 X -> 여전히 POJO
    private void init() {
        System.out.println("+ SmsMessageSender Initialized!!");
    }

    private void cleanUp() {
        System.out.println("+ SmsMessageSender Terminated!!");
    }

    @Override
    public boolean sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : \"" + message + "\"");
        return true;
    }
}
