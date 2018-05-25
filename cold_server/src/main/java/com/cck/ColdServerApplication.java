package com.cck;

import com.cck.common.Utils.YmlConfigUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(YmlConfigUtil.class)
@SpringBootApplication
@EnableTransactionManagement //如果mybatis中service实现类中需要加入事务注解，需要此处添加该注解
@MapperScan(value = "com.cck.mapper")
public class ColdServerApplication  {


    /**
     * jwt拦截现在改为 WebMvcConfigurerAdapter拦截
     **/
   /* @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        registration.setFilter(filter);
        return registration;
    }*/


   //TODO
    /**
     * 打war包专用  war包打开  启动类继承 extends SpringBootServletInitializer
     **/
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ColdServerApplication.class);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ColdServerApplication.class, args);
    }

}
