package org.atomicHabit.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class InterceptorAppConfig implements WebMvcConfigurer {


    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/habit/findUsersHabits/*");
        //registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns("/swagger-ui.html","/login","/swagger-ui/index.html","/v3/api-docs/swagger-config","/v3/api-docs");
    }


}