
## SpringBoot 와 Spring Security 연동

✔️ 학습할 내용
- 스프링 시큐리티에서 제공하는 로그인 처리 방식의 이해
- JPA 와 연동하는 커스텀 로그인 처리
- Thymeleaf 에서 로그인 정보 활용하기


- 스프링 시큐리티 객체들을 처리하기 위한 Thymeleaf 확장 플로그인 추가

```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    annotationProcessor 'org.projectlombok:lombok'
    providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
    
    implementation group: 'org.mariadb.jdbc', name: 'mariadb-java-client'
    implementation group: 'org.thymeleaf.extras', name: 'thymeleaf-extras-springsecurity5'
    implementation group: 'org.thymeleaf.extras', name: 'thymeleaf-extras-java8time'
}
```