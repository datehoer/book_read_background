package com.datehoer.bookapi.config;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new SaInterceptor(handler->{
            SaRouter
                    .match("/book/**")
                    .match("/chapter/**")
                    .match("/content/**")
                    .check(r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
    }
    @Bean
    public SaServletFilter getSaServletFilter(){
        return new SaServletFilter()
                .addInclude("/**")
                .setAuth(obj -> {
                    SaRouter
                        .match("/book/**")
                        .match("/chapter/**")
                        .match("/content/**")
                        .check(r -> StpUtil.checkLogin());
                });
    }
}
