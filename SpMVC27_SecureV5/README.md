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

### Spring-Security와 form데이터
* Web browser에서 서버로 요청하는것을 req 라고 하며, 요청할때 사용하는 주소를 URL, URI라고 한다.
* Web browser에서 서버에 req 하는 method 방식에는 Get,Post,Put,Delete 가 있고, 이중 spring mvc에서는 get,post를 사용한다.
* get method는 주소창에 url을 입력하고 enter를 누르거나, anchor tag를 마우스로 클릭하거나, 또는 formTag의 method가 없는경우
 서버로 요청하는 방식.
* get method는 단순히 리스트를 요구하거나, 입력form 화면을 요구하는 용도로 주로 사용된다.
* Post method는 입력화면에 값들을 입력한 후 서버로 전송할때 주로 사용하며, 입력화면의 form, input등의 tag에 값을 저장한후
 서버로 submit을 수행하는 경우이다.
* post method는 데이터의 양에 관계없이 서버로 전송할수 있으며 file upload등도 수행할수 있다.
* spring-security를 적용한 프로젝트에서는 get method 방식은 아무런 제약이 없으나, Post method 방식은 서버로부터 전달받은 csrf token을
 데이터들과 함께 보내야만 정상적으로 서버로 보낼수 있다.
* 그때문에 POST 방식의 form 코드에는 다음과 같은 코드를 추가해 주어야 한다.
  input name="${_csrf.parameterName }" value="${_csrf.token }"
 * 매번 form화면구현을 하면서 코드를 추가하다보면, 빠드리는 경우가 살생할수 있고, 해당 페이지의 데이터를 전송하면 서버는 
  403 오류를 보낸다.
 * 이러한 불편을 방지하기 위해 spring form taglibs를 사용하여 form을 작성하자.
 * <form:form> 형식의 form을 작성하면 자동으로 토큰을 추가해준다.
 
 ## E-mail인증 회원가입
 * 회원가입을 할때 username,password,email을 입력받고 email을 사용자에게 보낸후 email 인증을 수행하여 회원가입을 완료한다.
 * 회원가입 화면을 username,password를 입력받는 화면과, email을 입력받는 화면으로 분리하는 2개의 화면으로 이루어진 기능을 구현
 * sessionAttribute,ModelAttribuye을 활용하여 구현
 * sessionAttribute는 보통 vo 객체를 서버 메모리에 저장한후 form화면과 연동하는 구현 이때 반드시 ModelAttribute가 동반되어 구현되어야 한다.
 * sessionAttribute에 등록된 ModelAttribute vo 객체는 서버 메모리에 데이터를 보관하고 있다가 form:form을 통해서 서버로 전달되는 param vo 객체를 받고,
  form:form에서 누락된 input 항목들이 있으면 메모리의 보관된 ModelAttribute VO 에서  paramvo 데이터를 완성하여 사용할수 있도록 만들어 준다. 
  
## Transaction
* Mybatis와 common-dbcp 환경에서는 context.xml에 <tx:annotation-driven/> 설정을 통해서 자동으로 transaction을 구현할수 있다.
* Mybatis 환경에서 실제 dao 인터페이스와 mapper.xml 등을 연동하여 DB와 query를 주고 받을때는 sqlSessionTemplate 라는 클래스를 통해서
 사용한다.
* DataSourcetransactionManager를 context.xml에 설정을 하게되면 SqlSessionTemplate을 사용하지 않아도 내부적으로 자체 처리가 된다.
* DataSourceTransactionManager가 SqlSessionTemplte 역활을 대신 수행하기도 한다.
* 여기에서 <tx:annotation-driven/> 항목이 없고, class나 method에서 @transac 설정이 없으면 DataSoruceTrans...은
 SqlSessionTemp...과 같은 역활만 수행한다.
* 혹시 <tx:annotation-driven> 설정을 했는데, @transac 설정이 적용 안될때는  <tx:annotation-driven> 코드 위쪽에 
 <context:annotation-config>를 설정해 주어야 한다.
* @transac 에는 특별히 세세하게 설정할수 있는 옵션들이 있다.

## Transactional 옵션
#### isolation
* 현재 transaction이 작동되는 과정에서 다른 transaction등이 접근하는 정도를 설정하기
* read_unccmited: level 0
- 트랜잭션 처리중 또는 comit이 되기 전 다른 트랜잭션이 읽기를 수행할수 있다.
* READ_COMMITED : level 1
- 트랜잭션 commit 된 후에만 다른 트랜잭션이 읽을수 있다.
* REPEATABLE_READ : level 2
- 트랜잭션이 진행되는 동안에 select 문장이 사용된 table에 lock을 걸기, select이 실행되거나 실행된 예전인 DB table에는
 CUD를 수행할수 없도록 하며 단, 다른 트랜잭션에선느 제한적으로 select가 가능.
* serializable : level 3
- 완벽한 일관성있는 select를 보장

### propagation : 전파옵션
* 현재 트랜잭션이 시작되었음을 다른 트랜잭션에 어떻게 알릴것인가
* required
- 부모 트랜잭션이 실행되는 과정에서 또 자식(새부적인) 트랜잭션을 실행할수 있도록 허용
- 이미 자식 트랜잭션이 실행되고 있으면 새로 생성 금지
* required_new
- required와 비슷하지만 자식 트랜잭션이 이미 실행되고 있지만 무조건 새로 다시 생성하라.

### readonly
* 해당 트랜잭셩을 읽기 전용으로 설정하겠다. 기본값은 false.
### rollbackFor
* rollback조건을 무엇으로 하겠냐. 기본값은 exception
### noRollbackFor
* 특별한 예외에서는 rollback 무시. 기본값 null.
### timeout
* DB와 연결하여, transaction이 실행된느 시간이 과도하게 진행될경우 rollback을 수행하도록 설정.
 기본값 -1, timeout rollback 금지.
### list insert 수행할때 주의사항!!!
* 서비스 코드에서 다음과 같은 코드 절대 사용 금지
for(VO vo:list){
dao.insert(vo)
}