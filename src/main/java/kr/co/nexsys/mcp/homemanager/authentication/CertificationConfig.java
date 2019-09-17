package kr.co.nexsys.mcp.homemanager.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


/*
  인터셉터 등록
 */
@Configuration
public class CertificationConfig implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor handlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(handlerInterceptor)
                .addPathPatterns("/entity/**")
                .addPathPatterns("/mms/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
