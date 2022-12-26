package com.nhnacademy.edu.springframework.messagesender.config;

import com.nhnacademy.edu.springframework.messagesender.service.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Autowired
    private JavaConfig javaConfig;

    @Bean
    public MessageSendService messageSendService() {
        return new MessageSendService(javaConfig.smsMessageSender());
    }
}
