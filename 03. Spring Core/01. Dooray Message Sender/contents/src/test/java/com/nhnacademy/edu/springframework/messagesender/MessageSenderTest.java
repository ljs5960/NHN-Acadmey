package com.nhnacademy.edu.springframework.messagesender;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.springframework.messagesender.service.MessageSendService;
import com.nhnacademy.edu.springframework.messagesender.service.MessageSender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MessageSenderTest {
    @Test
    void messageSenderTest() {
        MessageSender messageSender = mock(MessageSender.class);
        MessageSendService messageSendService = new MessageSendService(messageSender);

        User user = new User("limjs2671@gmail.com", "010-1234-5678");

        messageSender.sendMessage(user, "Test Message");

        verify(messageSender, times(100)).sendMessage(any(User.class), any(String.class));
    }
}
