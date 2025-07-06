👉 `@SpringBootApplication` একটা ক্লাসে ব্যবহার করলে **আসলে ভিতরে ভিতরে কী কী ঘটে**, তাহলে নিচে আমি একদম বিস্তারিতভাবে ব্যাখ্যা করছি — **behind the scenes**, step-by-step কী কী হয় যখন তুমি `@SpringBootApplication` দিয়ে Spring Boot অ্যাপ চালাও।

---

## 🔥 @SpringBootApplication ব্যবহার করলে কী হয়? (বিস্তারিত ব্যাখ্যা)

---

### 🧠 ১. Annotation Composition (মূলত ৩টা জিনিস একত্রে হয়)

```java
@SpringBootApplication
```

এই annotation এর ভেতরে আসলে তিনটি annotation থাকে:

```java
@Configuration  
@EnableAutoConfiguration  
@ComponentScan
```

---

### 🔎 ২. @Configuration → Bean Definition এর জন্য

এই annotation বোঝায়:

> 👉 “এই ক্লাসটা Spring-এর configuration class হিসেবে কাজ করবে।”

#### এর মানে:

* তুমি এই ক্লাসে `@Bean` annotation ব্যবহার করে Bean define করতে পারো।
* এটা XML config-এর বিকল্প (Java-based config)

```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

---

### ⚙️ ৩. @EnableAutoConfiguration → Auto Config চালু হয়

এই অংশটা Spring Boot-এর সবচেয়ে গুরুত্বপূর্ণ ও "smart" ফিচার।

#### কী ঘটে?

* Spring classpath স্ক্যান করে দেখে তোমার প্রজেক্টে কোন কোন dependency (jar) আছে।
* সেই অনুযায়ী configuration automatically apply করে।

📌 উদাহরণ:

* `spring-boot-starter-web` থাকলে: Spring নিজেই Tomcat, Spring MVC, DispatcherServlet configure করে।
* `spring-boot-starter-data-jpa` থাকলে: Spring নিজেই Hibernate, EntityManager, DataSource configure করে।

#### ভিতরে কীভাবে হয়?

* এই annotation → `META-INF/spring.factories` ফাইল স্ক্যান করে
* এর ভিতরে অনেক `@Configuration` ক্লাসের নাম দেওয়া থাকে
* Spring conditionally এগুলা load করে:

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

Spring এগুলা তখন চালায় যদি:

* প্রয়োজনীয় class (e.g., `javax.sql.DataSource`) পাওয়া যায়
* তুমি আগে থেকে ওই Bean define করোনি

---

### 🔍 ৪. @ComponentScan → Bean গুলো খুঁজে বের করে

এইটা instruct করে:

> 👉 "এই ক্লাস যে প্যাকেজে আছে, সেখান থেকে শুরু করে সব sub-packages স্ক্যান করো এবং Component annotation গুলো খুঁজে বের করে Bean হিসেবে রেজিস্টার করো।"

#### স্ক্যান করে নিচের annotation গুলো:

* `@Component`
* `@Controller`, `@RestController`
* `@Service`
* `@Repository`

📌 উদাহরণ:

```java
@SpringBootApplication // package: com.shafi.myapp
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

Spring এই প্যাকেজ স্ক্যান করে:

```
com.shafi.myapp.*
```

---

### 🚀 ৫. SpringApplication.run(...) → App Bootstrap

```java
SpringApplication.run(MyApp.class, args);
```

এই লাইন চালানোর সময়:

#### কী কী ঘটে?

1. SpringApplication object তৈরি হয়
2. Environment তৈরি হয় (dev/prod config)
3. ApplicationContext তৈরি হয় (IoC Container)
4. Bean গুলো scan হয়ে তৈরি হয়
5. Embedded server (যেমন Tomcat) চালু হয়
6. Application ready হয়ে যায়

---

### 🏁 Extra Internal Step (বুঝলে তুমি Master!)

| কাজ                | Behind-the-scene Details                                                               |
| ------------------ | -------------------------------------------------------------------------------------- |
| Logging            | Spring Boot Logback ব্যবহার করে — শুরুতেই Banner দেখায়                                 |
| Profiles           | `application.properties` বা `application-dev.properties` ফাইল অনুযায়ী profile load করে |
| Application Events | `ApplicationStartedEvent`, `ApplicationReadyEvent` ইত্যাদি ছোঁড়ে                       |
| Listeners          | তুমি চাইলে custom listener দিয়েও কিছু করতে পারো                                        |

---

## 📌 সংক্ষেপে Flowchart:

```text
@SpringBootApplication
         ↓
@Configuration → @Bean define করা যায়
@EnableAutoConfiguration → dependencies দেখে নিজেরা config করে
@ComponentScan → classpath থেকে component খুঁজে বের করে
         ↓
SpringApplication.run() → সব initialize + server start
```

---

## ✅ উপসংহার (বাংলায়):

তুমি যখন `@SpringBootApplication` ব্যবহার করো, তখন Spring:

* তোমার পুরো অ্যাপের configuration central করে নেয়
* classpath থেকে কোন কোন dependency আছে দেখে smart ভাবে config করে
* তোমার ক্লাস/প্যাকেজ থেকে সব Bean খুঁজে নেয়
* internal server চালু করে দেয়
* শেষে তোমার অ্যাপ ready করে দেয় production use এর জন্য


