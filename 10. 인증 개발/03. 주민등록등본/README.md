# 주민등록등본, 가족관계증명서, 출생신고서, 사망신고서
- JPA 평가 과제였던 "주민등록등본, 가족관계증명서, 출생신고서, 사망신고서 - JPA 구현" 을 기반으로...

# 아이디/비밀번호 인증 추가
## 데이터베이스 테이블 확장
- 주민 (resident 테이블) 에 아래 컬럼을 추가
    - 아이디 (id)
    - 비밀번호 (password)
    - 이메일 (email)
- 비밀번호는 plain text로 저장되면 안 된다
    - 단방향 hash 함수의 digest 기반으로 저장되어야 한다

## 기능 추가
- 로그인/로그아웃 기능 추가
    - 인증 쿠키는 expire를 3일로 한다
    - 세션은 redis에 저장한다

## 기능 변경
- 주민 목록
    - 전체 주민 목록이 아니라
    - 로그인한 본인과
    - 본인의 세대에 속하는 주민들 목록이 나와야 한다

## OAuth2 인증 추가
- 아이디/비밀번호 인증과 OAuth2 인증은 동시에 기능이 제공되어야 한다
    - 로그인 시 아이디/비밀번호 인증과 OAuth2 인증 중 한 가지를 사용자가 선택할 수 있어야 한다
- OAuth2 Provider는 github 만 제공하면 된다

## OAuth2 인증 구현
- Spring Security 라이브러리를 이용하지 않고 github API를 이용해서 직접 구현한다
- https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps#web-application-flow

## OAuth2 인증 처리 방식
- github에서 인증한 결과 중 email 값이 실제 resident 테이블에 있는 값이면 그 계정으로 로그인을 시켜준다
    - github에서 인증이 성공했다고 하더라도 email 값이 resident 테이블에 없는 값이면 로그아웃 시킨다
- https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps#3-use-the-access-token-to-access-the-api

<br/>
<br/>
<br/>

# 결과
- Spring Security 이용한 아이디/비밀번호 인증 추가
    - [X] 아이디/비밀번호 인증 추가(커스텀 로그인 UI)
    - [X] 데이터베이스 테이블 확장 ( 아이디/비밀번호/이메일 추가)
    - [X] 비밀번호  : 단방향 hash 함수의 digest 기반으로 저장
    - [X] 로그인/로그아웃 기능 추가
    - [X] 인증 쿠키 : expire 3일
    - [X] 세션은 redis에 저장
- OAuth2 인증 추가
    - [ ] 아이디/비밀번호 인증과 OAuth2 인증은 동시에 기능이 제공되어야 한다
    - [X] OAuth2 Provider는 github 만 제공하면 된다.
    - [ ] Spring Security 라이브러리를 이용하지 않고 github API를 이용해서 직접 구현한다.
    - [ ] github에서 인증한 결과 중 email 값이 실제 resident 테이블에 있는 값이면 그 계정으로 로그인을 시켜준다.
    - [ ] github에서 인증이 성공했다고 하더라도 email 값이 resident 테이블에 없는 값이면 로그아웃 시킨다