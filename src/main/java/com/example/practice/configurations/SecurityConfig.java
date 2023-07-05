package com.example.practice.configurations;

import com.example.practice.interceptors.AdminInterceptor;
import com.example.practice.interceptors.AuthTokenInterceptor;
import com.example.practice.interceptors.SellerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private final AuthTokenInterceptor authTokenInterceptor;
    private final AdminInterceptor adminInterceptor;
    private final SellerInterceptor sellerInterceptor;

    @Autowired
    public SecurityConfig(AuthTokenInterceptor authTokenInterceptor, AdminInterceptor adminInterceptor, SellerInterceptor sellerInterceptor){
        this.authTokenInterceptor = authTokenInterceptor;
        this.adminInterceptor = adminInterceptor;
        this.sellerInterceptor = sellerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authTokenInterceptor).addPathPatterns("/users/admin");
        registry.addInterceptor(sellerInterceptor).addPathPatterns("/product/create","/product/update","/product/delete");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/product/admin");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
