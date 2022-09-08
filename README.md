
## SpringBoot 와 Spring Security 연동

✔️ 학습할 내용
- 스프링 시큐리티에서 제공하는 로그인 처리 방식의 이해
- JPA 와 연동하는 커스텀 로그인 처리
- Thymeleaf 에서 로그인 정보 활용하기


- 스프링 시큐리티 객체들을 처리하기 위한 Thymeleaf 확장 플로그인 추가

```groovy
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

- 기타 설정 추가

데이터베이스, JPA 관련 설정과 파일 업로드 관련 설정 추가, 시큐리티와 관련된 로그 레벨 낮게 설정해서 자세한 로그 확인할 수 있게 설정

```properties
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://localhost:3306/bootex
spring.datasource.username=bootuser
spring.datasource.password=bootuser

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true

spring.thymeleaf.cache=false

spring.servlet.multipart.enabled=true
spring.servlet.multipart.location=C:\\upload
spring.servlet.multipart.max-request-size=30MB
spring.servlet.multipart.max-file-size=10MB

logging.level.org.springframework.security.web=trace
logging.level.org.example=debug
```