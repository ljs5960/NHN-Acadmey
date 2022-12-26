package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.messagesender.User;
import org.springframework.web.client.RestTemplate;

public class DorrayMessageSender implements MessageSender {
    @Override
    public boolean sendMessage(User user, String message) {
        String hookUrl = "https://hook.dooray.com/services/3036349505739914786/3371740733259172017/cdnzcggTTWmx2GtusEMUJw";

        new DoorayHookSender(new RestTemplate(), hookUrl)
            .send(DoorayHook.builder()
                .botName("임재승")
                .text(message)
                .build());

        return true;
    }
}
