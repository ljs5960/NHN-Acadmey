package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.config.MainConfig;
import com.nhnacademy.edu.springframework.messagesender.config.ServiceConfig;
import com.nhnacademy.edu.springframework.messagesender.service.EmailMessageSender;
import com.nhnacademy.edu.springframework.messagesender.service.MessageSendService;
import com.nhnacademy.edu.springframework.messagesender.service.MessageSender;
import com.nhnacademy.edu.springframework.messagesender.service.SmsMessageSender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 10-11
//        User user = new User("test@test.com", "01012345678");
//        final String configPath = "classpath:/beans.xml";
//
////        new MessageSendService(new SmsMessageSender()).doSendMessgage();
////        new MessageSendService(new EmailMessageSender()).doSendMessgage();
//        // singleton pattern : 객체 최초 한번만 생성
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configPath);
//        context.getBean("smsMessageSender", MessageSender.class).sendMessage(user, "This is Message");
//        context.getBean("smsMessageSender", MessageSender.class).sendMessage(user, "This is Message");
//
//        System.out.println("----------");
//
//        // prototype pattern : 호출 시 매번 객체 생성
//        context.getBean("emailMessageSender", MessageSender.class).sendMessage(user, "This is Message");
//        context.getBean("emailMessageSender", MessageSender.class).sendMessage(user, "This is Message");
//
//        context.close();
//
//        System.out.println("===========의존성 주입 실습===============");
//        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext(configPath);
//        context1.getBean("messageSendService", MessageSendService.class).doSendMessgage();
////        context1.getBean("messageSendService", MessageSendService.class).doSendMessgage();

        // 10-12
//        final String configPath = "classpath:/beans.xml";
        User user = new User("limjs2671@gmail.com", "010-1234-5678");
//
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configPath);
//
//        context.getBean("messageSendService", MessageSendService.class).doSendMessgage();

//        String configPath = "com.nhnacademy.edu.springframework.messagesender.config";
//        AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(configPath);
//        AnnotationConfigApplicationContext context1 =
//            new AnnotationConfigApplicationContext(MainConfig.class, ServiceConfig.class);

//        MessageSendService messageSendService =
//            context1.getBean("messageSendService", MessageSendService.class);
//
//        messageSendService.doSendMessgage();

//        MessageSender smsMessageSender = (MessageSender) context1.getBean("smsMessageSender");
//        MessageSender emailMessageSender = (MessageSender) context1.getBean("emailMessageSender");
//        smsMessageSender.sendMessage(user, "SMS Message");
//        emailMessageSender.sendMessage(user, "Email Message");

        // 10-13
        String configPath = "com.nhnacademy.edu.springframework.messagesender.config";
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(configPath);

        context.getBean("smsMessageSender", MessageSender.class).sendMessage(user, "SMS");
        context.getBean("emailMessageSender", MessageSender.class).sendMessage(user, "EMAIL");
        context.getBean("doorayMessageSender", MessageSender.class).sendMessage(user, "Tessdfsdft");
    }
}
