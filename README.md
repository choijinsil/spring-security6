# [개발자 유미의 스프링 시큐리티 동작원리](https://www.youtube.com/watch?v=NPRh2v7PTZg&list=PLJkjrxxiBSFCcOjy0AAVGNtIa08VLk1EJ)

### Spring Security 6버전
- Spring boot 3.3.0
- Security 6.15
- Spring Data JPA H2
- mustache

### 내용
- spring security 6 버전이 이전과 무엇이 달라졌는지 학습
  - Security 6버전부터는 Lambda 표현식이 필수사항.
  - WebSecurityConfigurerAdapter는 deprecated
- spring config 설정
  - api 서버로 사용할때엔 사용자 세션을 STATELESS로 관리하기 때문에 csrf enable 설정을 하지 않아도 된다.
- roleHierarchy(롤계층) 설정
- CustomUserDetails 사용방법
- 동영상에선 mysql을 사용하지만 편의성을 위해 h2로 설정 변경

### Additional Links

These additional references should also help you:

* [Spring Security roleHierarchy doc](https://docs.spring.io/spring-security/reference/servlet/authorization/architecture.html)
* [Spring Security 6.0 doc](https://docs.spring.io/spring-security/reference/5.8/migration/index.html)

