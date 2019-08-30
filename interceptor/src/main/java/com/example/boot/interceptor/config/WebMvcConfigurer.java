package com.example.boot.interceptor.config;

import com.example.boot.interceptor.interceptor.OneInterceptor;
import com.example.boot.interceptor.interceptor.TwoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截器按照顺序执行
         */
        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**")
                .addPathPatterns("/one/**");
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");

        super.addInterceptors(registry);
    }

}
