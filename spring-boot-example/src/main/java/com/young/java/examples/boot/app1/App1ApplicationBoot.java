package com.young.java.examples.boot.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * @EnableAutoConfiguration Spring Boot建议只有一个带有该注解的类。
 * @EnableAutoConfiguration作用：Spring Boot会自动根据你jar包的依赖来自动配置项目。例如当你项目下面有HSQLDB的依赖时，Spring Boot会创建默认的内存数据库的数据源DataSource，如果你自己创建了DataSource，Spring Boot就不会创建默认的DataSource。
 * <p/>
 * 如果你不想让Spring Boot自动创建，你可以配置注解的exclude属性，例如：
 * @Configuration
 * @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) public class MyConfiguration {
 * }
 */
//@EnableAutoConfiguration

/**
 * @SpringBootApplication 由于大量项目都会在主要的配置类上添加@Configuration,@EnableAutoConfiguration,@ComponentScan三个注解。
 * <p/>
 * 因此Spring Boot提供了@SpringBootApplication注解，该注解可以替代上面三个注解（使用Spring注解继承实现）。
 */
@SpringBootApplication
public class App1ApplicationBoot {

    public static void main(String[] args) {

        /**
         * 启动项目SpringApplication.run
         启动Spring Boot项目最简单的方法就是执行下面的方法：
         SpringApplication.run(Application.class, args);
         该方法返回一个ApplicationContext对象，使用注解的时候返回的具体类型是AnnotationConfigApplicationContext或AnnotationConfigEmbeddedWebApplicationContext，当支持web的时候是第二个。
         除了上面这种方法外，还可以用下面的方法：
         SpringApplication application = new SpringApplication(Application.class);
         application.run(args);
         SpringApplication包含了一些其他可以配置的方法，如果你想做一些配置，可以用这种方式。
         除了上面这种直接的方法外，还可以使用SpringApplicationBuilder：
         new SpringApplicationBuilder()
         .showBanner(false)
         .sources(Application.class)
         .run(args);
         当使用SpringMVC的时候由于需要使用子容器，就需要用到SpringApplicationBuilder，该类有一个child(xxx...)方法可以添加子容器。
         * */
        SpringApplication.run(App1ApplicationBoot.class, args);
    }
}
