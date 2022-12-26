# Java 결산과제 프로젝트를 maven project로 설정
- pom.xml 작성
        - groupId: com.nhnacademy
        - version: 1.0-SNAPSHOT
- Jar 플러그인 적용 (manifest)
- CheckStyle 플러그인 적용
- SonarQube 플러그인 적용
- 멀티 프로젝트로 설정
        - 게임 도메인 관련 클래스들은 game 프로젝트로
        - 게임 서버 관련 클래스들은 server 프로젝트로
        - 게임 클라이언트 관련 클래스들은 client 프로젝트로 구성

# 요구 사항
- bank 프로젝트를 multi project 로 변경해주세요.
    - domain 패키지에 있는 클래스는 domain 모듈로 옮겨주세요
    - server 패키지에 있는 클래스는 server 모듈로 옮겨주세요
    - client 패키지에 있는 클래스는 client 모듈로 옮겨주세요
    - root 는 최상위 프로젝트입니다. Application.java 를 옮겨주세요
- fat.jar 를 만들어주세요
    - maven-shade-jar 플러그인을 사용하시면 됩니다.
- 결과
```
$ java -jar application.jar
########################################
${이름}
########################################
```