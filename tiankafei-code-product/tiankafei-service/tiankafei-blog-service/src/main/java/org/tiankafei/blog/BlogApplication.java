package org.tiankafei.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tiankafei
 * @since 1.0
 **/
@SpringCloudApplication
@EntityScan(basePackages = "org.tiankafei")
@MapperScan(basePackages = {"org.tiankafei.**.mapper"})
@ComponentScan(basePackages = {"org.tiankafei"})
@EnableFeignClients(basePackages = {"org.tiankafei"})
@EnableHystrixDashboard
public class BlogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BlogApplication.class);
    }

}
