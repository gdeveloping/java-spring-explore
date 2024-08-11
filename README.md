## 介绍

Java Spring 学习笔记

阅读 Spring 源码，绘制主流程的思维导图。





## 思维导图

> 自制思维导图，直观理解 Spring 流程



Spring-core 流程

https://www.processon.com/view/link/6695310bba72ce54bd761a93



AOP

https://www.processon.com/view/link/66aedbe780d3552cffa0a457





## 参考文章

Spring源码解析

https://blog.csdn.net/qq_41907991/category_9907747.html



Spring官网读书笔记

https://blog.csdn.net/qq_41907991/category_9601507.html





## 调试环境

1. 以注解为主，忽略 XML 文件配置。
2. 使用 spring-boot 2.7.18，spring-framework 5.3.31。
3. 使用 AnnotationConfigApplicationContext。
4. 未使用注解 @SpringBootApplication，已使用其它常用注解，例如 @ComponentScan。
5. 未开启自动注入功能 @EnableAutoConfiguration，已开启其它常用功能，例如 @EnableAsync。
6. 主动配置了 PropertySource。
7. 主动配置了 MessageSource。
8. pom 依赖包含 web 相关，aop 相关，mybatis 相关，h2database 相关依赖。
9. pom 依赖不包含 spring-boot-devtools 依赖。
