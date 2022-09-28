
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

- 실행

```shell
17:48:47.839 [Thread-0] DEBUG org.springframework.boot.devtools.restart.classloader.RestartClassLoader - Created RestartClassLoader org.springframework.boot.devtools.restart.classloader.RestartClassLoader@2271d2cb

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.3)

2022-09-08 17:48:48.542  INFO 4160 --- [  restartedMain] c.e.s.SpringSecurityApplication          : Starting SpringSecurityApplication using Java 17.0.2 on KunYoung with PID 4160 (C:\Users\ur2ku\OneDrive\바탕 화면\WORKSPACE\[Spring]workspace\SpringSecurity\build\classes\java\main started by ur2ku in C:\Users\ur2ku\OneDrive\바탕 화면\WORKSPACE\[Spring]workspace\SpringSecurity)
2022-09-08 17:48:48.544  INFO 4160 --- [  restartedMain] c.e.s.SpringSecurityApplication          : No active profile set, falling back to 1 default profile: "default"
2022-09-08 17:48:48.585  INFO 4160 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2022-09-08 17:48:48.585  INFO 4160 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2022-09-08 17:48:49.032  INFO 4160 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2022-09-08 17:48:49.043  INFO 4160 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 4 ms. Found 0 JPA repository interfaces.
2022-09-08 17:48:49.484  INFO 4160 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-09-08 17:48:49.492  INFO 4160 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-09-08 17:48:49.492  INFO 4160 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.65]
2022-09-08 17:48:49.543  INFO 4160 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-09-08 17:48:49.543  INFO 4160 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 958 ms
2022-09-08 17:48:49.670  INFO 4160 --- [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2022-09-08 17:48:49.715  INFO 4160 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.6.10.Final
2022-09-08 17:48:49.828  INFO 4160 --- [  restartedMain] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2022-09-08 17:48:49.906  INFO 4160 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2022-09-08 17:48:49.966  INFO 4160 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2022-09-08 17:48:49.989  INFO 4160 --- [  restartedMain] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MariaDB106Dialect
2022-09-08 17:48:50.229  INFO 4160 --- [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2022-09-08 17:48:50.235  INFO 4160 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2022-09-08 17:48:50.262  WARN 4160 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2022-09-08 17:48:50.524  WARN 4160 --- [  restartedMain] .s.s.UserDetailsServiceAutoConfiguration : 

Using generated security password: 9a7fb52e-4c3a-4a73-82f3-7d15067e5cca

This generated password is for development use only. Your security configuration must be updated before running your application in production.

2022-09-08 17:48:50.595 DEBUG 4160 --- [  restartedMain] edFilterInvocationSecurityMetadataSource : Adding web access control expression [authenticated] for any request
2022-09-08 17:48:50.612 TRACE 4160 --- [  restartedMain] o.s.s.w.a.i.FilterSecurityInterceptor    : Validated configuration attributes
2022-09-08 17:48:50.612 TRACE 4160 --- [  restartedMain] o.s.s.w.a.i.FilterSecurityInterceptor    : Validated configuration attributes
2022-09-08 17:48:50.615  INFO 4160 --- [  restartedMain] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@4cc47b75, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@37391e7b, org.springframework.security.web.context.SecurityContextPersistenceFilter@6cb9892a, org.springframework.security.web.header.HeaderWriterFilter@15b0ec06, org.springframework.security.web.csrf.CsrfFilter@1e5a0d82, org.springframework.security.web.authentication.logout.LogoutFilter@3e1da4fe, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@55c80996, org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter@371888a0, org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter@2930a576, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@57559a6c, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@7cd98d0, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@68dabf46, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@24f804c1, org.springframework.security.web.session.SessionManagementFilter@6c2d5330, org.springframework.security.web.access.ExceptionTranslationFilter@572d32b6, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@3aea7a0e]
2022-09-08 17:48:50.646  INFO 4160 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2022-09-08 17:48:50.674  INFO 4160 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-09-08 17:48:50.685  INFO 4160 --- [  restartedMain] c.e.s.SpringSecurityApplication          : Started SpringSecurityApplication in 2.827 seconds (JVM running for 3.659)
2022-09-08 17:49:06.249  INFO 4160 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2022-09-08 17:49:06.251  INFO 4160 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2022-09-08 17:49:06.253  INFO 4160 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 130
```

<br/>

- 중간에 패스워드 하나가 출력되는 것을 볼 수 있음
```shell
Using generated security password: 9a7fb52e-4c3a-4a73-82f3-7d15067e5cca
```

생성된 패스워드는 기본으로 사용해 볼 수 있는 'user' 계정의 패스워드로 프로젝트 초기에 아무 계정도 없을 때 사용할 수 있는 임시 패스워드 역할을 함

<br/>

- http://localhost:8080/login 의 경로로 접근해서 로그인 테스트

![](readmeFile/img.png)


<br/>

### 10.1.1 시큐리티 설정 클래스 설정

스프링 부트는 자동 설정 기능이 있어 별도의 설정 없이도 연동 처리는 위와 같이 가능하지만 스프링 시큐리티를 이용하는 모든 프로젝트는 프로젝트에 맞는 
설정을 추가하는 것이 일반적임. 따라서 별도의 시큐리티 설정 클래스를 만들어보자

- SecurityConfig 클래스 추가

```java
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Log4j2
@EnableWebSecurity
public class SecurityConfig {}
```

원래는 WebSecurityConfigurerAdapter 를 상속받고 오버라이드를 통해서 설정을 조정했는데 deprecate 되고 @EnableWebSecurity 사용.

| 키워드                  | 내용                            |
|:---------------------|:------------------------------|
| SecurityConfig.class | 앞으로 모든 시큐리티 관련 설정은 여기다 추가할 것임 |

<br/>

### 10.1.2 확인을 위한 SampleController

<br/>

- SampleController  

```java
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {

    @GetMapping("/all")
    public void exAll() {
        log.info("exAll........");
    }

    @GetMapping("/member")
    public void exMember() {
        log.info("exMember.......");
    }

    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin.......");
    }

}
```

SampleController 에는 현재 사용자의 권한에 따라 접근할 수 있는 경로를 지정할 것임

> - 로그인을 하지 않은 사용자도 접근할 수 있는 '/sample/all'
> - 로그인한 사용자만이 접근할 수 있는 '/sample/member'
> - 관리자 (admin) 권한이 있는 사용자만이 접근할 수 있는 '/sample/admin'


<br/>

- [테스트용 파일 추가 ]()

```html
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>admin</title>
</head>
<body>
	<h2>admin</h2>
</body>
</html>
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>all</title>
</head>
<body>

	<h2>all</h2>

</body>
</html>
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>member</title>
</head>
<body>

	<h2>member</h2>

</body>
</html>
```

---

<br/>

### 10.1.3 스프링 시큐리티 용어와 흐름

프로젝트를 실행하고 '/sample/all' 과 같은 경로를 호출하면 시큐리티로 인해 로그인 화면이 보이는 것을 확인할 수 있음.
이를 서버 로그를 중심으로 살펴보자

로그인 세션이 없을 경우 'localhost:8080/sample/admin' 으로 접속하든 'localhost:8080/sample/all'으로 접속하든
'localhost:8080/sample/member' 로 접속하든 'http://localhost:8080/login' 페이지가 호출되는 것을 확인할 수 있다.

![](readmeFile/img_1.png)

- '/sample/all' 등을 호출할 경우 내부적으로 여러 개의 필터 (filter)가 동작하는 것을 확인할 수 있음.
![](readmeFile/img_2.png)

<br/>

- 스프링 시큐리티의 동작에는 여러 개의 객체가 서로 데이터를 주고 받으면서 이루어짐

![](readmeFile/img_3.png)

> 핵심 역할은 AuthenticationProvider (인증 매니저)를 통해서 이루어진다. AuthenticationProvider 는 인증 매니저가 어떻게 동작해야 하는지를 결정하고 최종적으로 실제 인증은 UserDetailService 에 의해서 이루어진다.

스프링 시큐리티의 가장 핵심 개념은 인증 (Authentication)과 인가 (Authorization) 이다. 예를 들어 은행에 금고가 하나 있고, 사용자가 금고의 내용을 열어 본다고 가정해 보면 다음과 같은 과정을 거치게 된다.

> 1. 사용자는 은행에 가서 자신이 어떤 사람인지 자신의 신분증으로 증명한다.
> 2. 은행에서는 사용자의 신분을 확인한다.
> 3. 은행에서 사용자가 금고를 열어볼 수 있는 사람인지를 판단한다.
> 4. 만일 적절한 관리나 권한이 있는 사용자의 경우 금고를 열어준다.

위의 과정에서 1은 인증에 해당하는 작업으로 자신을 '증명'하는 것이다. 3에서는 사용자를 '인가'하는 일종의 허가를 해 주는 과정으로 스프링 시큐리티 역시 내부적으로 위와 유사한 과정을 거쳐서 동작한다.

<br/>

- 필터와 필터 체이닝

스프링 시큐리티에서 필터 (Filter)는 서블릿이나 JSP 에서 사용하는 필터와 같은 개념이지만, 스프링 시큐리티에서는 스프링의 빈과 연동할 수 있는 구조로 설계되어 있다.
일반적인 필터는 스프링의 빈을 사용할 수 없기 때문에 별도의 클래스를 상속받는 형태가 많다.

스프링 시큐리티의 내부에는 여러 개의 필터가 Filter Chain 이라는 구조로 Request 를 처리하게 된다. 앞에서 실행되었던 로그를 살펴보면 15개 정도의 필터가 동작하는 것을 확인할 수 있다.
개발 시에 필터를 확장하고 설정하면 스프링 시큐리티를 이용해서 다양한 형태의 로그인 처리가 가능하게 된다. 아래는 스프링 시큐리티 내부에 사용되는 주요 필터이다.

![](readmeFile/img_4.png)


<br/>

- 인증을 위한 AuthenticationManager

필터의 핵심적인 동작은 AuthenticationManager 를 통해서 인증 (Authentication) 이라는 타입의 객체로 작업을 하게 된다.
AuthenticationManager가 가진 인증 처리 메서드는 파라미터도 Authentication 타입으로 받고 리턴 타입 또한 Authentication 이다.

인증 (Authentication) 는 '주민등록증' 과 비슷한 개념으로 생각하면 된다. '인증' 이라는 용어는 '스스로 증명하다' 라는 의미이다. 예를 들어 로그인하는 과정에서는 사용자의 아이디/패스워드로 자신이 어떤 사람인지를 전달한다.
전달된 아이디/패스워드로 실제 사용자에 대해서 검증하는 행위는 AuthenticationManager (인증 매니저)를 통해서 이루어진다.

실제 동작에서 전달되는 파라미터는 UsernamePasswordAuthenticationToken 과 같이 토큰이라는 이름으로 전달된다. 즉, 스프링 시큐리티 필터의 주요 역할이 인증 관련된 정보를 토큰이라는 객체로 만들어 전달한다는 의미이다.
아래는 기본으로 제공되는 필터 중 UsernamePasswordAuthenticationFilter 클래스 코드 중 일부이다.

```
String username = obtainUsername(request);
username = (username != null) ? username : "";
username = username.trim();
String password = obtainPassword(request);
password = (password ! = null) ? password : "";
UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
// Allow subckasses to set the "details" property
setDetails(request, authRequest);
return this.getAuthenticationManager().authenticate(authRequest);
```

request 를 이용해서 사용자의 아이디와 패스워드를 받아서 UsernamePasswordAuthenticationToken 이라는 객체를 만들고 
이를 AuthenticationManager 의 authenticate() 에 파라미터로 전달하는 것을 확인할 수 있음.
AuthenticationManager 는 이러한 처리를 AuthenticationProvider 로 처리한다.

![](readmeFile/img_6.png)












































