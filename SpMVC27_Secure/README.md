## Spring MVC Security Project
### 2020-04-08
### 개요
* spring MVC 기반 Security Coding
* security 기능을 포함한 websocket coding
* react 기반 front-end 기능코딩

* jdbc, mybatis 연동
* mysql
* transaction 설정

* 회원가입 e-mail 인증
* 비밀번호 분실 e-mail 인증후 재 설정

### Spring Sec Dependency
* sec settings 
* spring sec core 
* spring sec web
* spring sec config
* spring sec taglibs 

* jasypt
* jasypt-spring31

### DB dependency(MySQL, Mybatis 연동)
* mysql-connector-java : mysql과 java를 연결해주는데 사용하는 db driver
* spring-jdbc : java(spring)와 db driver 사이에서 명령어, 데이터를 변환 시켜주는  보조 클래스
* mybatis : spring-jdbc와 db driver 사이에서 mybatis maper 등으로 작성된 sql을 변환하고, 데이터를 vo에 쉽게 매핑시키는 용도의 class
* mybatis-spring : spring-jdbc와 mybatis 엔진 사이에서 서로 잘 맞지않는 부분을 잘 조정하여 버전에 관계없이 연동이 쉽도록 만들어주는 보조 class
* commons-dbcp2 : jdbc와 driver 사이에서 db connection pool을 만들고, connection, disconnection 을 수행해주는 보조 class

## security와 관련용어

#### 접근주체(Principal)
* 보호된 대상에 접근하는 유저

#### 인증(authenticate)
* 접근하는 유저가 누구인가(로그인 절차)

#### 인가(autherize)
* 접근한 유저가 어떤 서비스, 어떤 페이지에 접근할수 있는 권한이 있는가 하는것을 검사

#### 권한(role)
* 인증(authenticate)된 주체(user)가 어떤 페이지, 기능, 서비스에 접근할수 있는 권한이 있다는것을 보증.

#### 무결성, 보안
* 무결성 파괴: 이가된 사용자에 의해 손상될수 있는 것들
* 보안 파괴: 인가되지 않은 사용자에 의해 손상될수 있는 것들

## Spring Security
* Filter를 사용하여 접근하는 사용자의 인증절차와 인가를 통해 권한이 있는가를 파악하고,
적절한 조치(redirect,사용가능)를 비교적 적은 코드 양으로 처리할수 있도록 만든 프레임워크
* spring sec 는 세션과 쿠키방식을 병행하여 사용.

### 유저가 로그인을 시도하면 
1. Authentication filter에서 부터 users table 까지 접근하여 사용자 정보를 인증하는 절차를 거침.
2. 인증이 되면 user table, user detail table에서 사용자 정보를 fetch(select)하여 session에 저장
3. 일반적인 httpsession은 서버의 할동영역 메모리에 session을 저장하는데 비해 spring sec는 securityContextHolder 라는 메모리에 저장.
4. view로 유저의 정보가 담긴 session 을 session id와 함께 응답으로 전달.
* JSESSINID 라는 쿠키에 session id를 담아서 보내고
5. 이후 유저가 접근을 하면 JSESSIONID에서 쿠키를 추출하여 사용자 인증을 시도한다.
* JSESSIONID=dsfsfd 라는 값이 url 뒤에 따라 붙기도 한다.
6. JSESSIONID에서 추출한 Session Id가 유효하면 접근 request에게 authentication을 부착.