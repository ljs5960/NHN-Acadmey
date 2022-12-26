# 프로젝트 마무리 실습
## 목표
- MessengerSender 의 새로운 구현체를 구현하여 Dependency Injection 을 수행한다.
## 과정
- Repository 추가
``` xml
<project>

    <repositories>
        <repository>
            <id>release</id>
            <url>https://github.com/edu-springboot/edu-springboot-maven-repo/raw/master/releases</url>
        </repository>

        <repository>
            <id>snapshot</id>
            <url>https://github.com/edu-springboot/edu-springboot-maven-repo/raw/master/snapshots</url>
        </repository>
    </repositories>
</project>
```

- Dependency 추가
``` xml
<dependencies>
        <dependency>
            <groupId>com.nhn.dooray.messenger</groupId>
            <artifactId>dooray-hook-sender</artifactId>
            <version>1.2.0-RELEASE</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.2.2</version>
        </dependency>


        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
</dependencies>
```

- MessageSender 를 구현한 DoorayMessageSender 개발
``` java
    @Override
    public boolean sendMessage(User user, String message) {
        new DoorayHookSender(new RestTemplate(), "${hookUrl}")
                .send(DoorayHook.builder()
                        .botName("${작성자 이름}")
                        .text("${동료들에게 하고싶은말}")
                        .build());
        return true;
    }
```

- Hook Url
    - https://hook.dooray.com/services/3036349505739914786/3371740733259172017/cdnzcggTTWmx2GtusEMUJw
- 강사 메시지에 내용이 표시되면 완료!