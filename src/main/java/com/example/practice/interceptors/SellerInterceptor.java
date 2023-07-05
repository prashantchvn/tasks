package com.example.practice.interceptors;

import com.example.practice.entities.Users;
import com.example.practice.repository.UserRepository;
import com.example.practice.utilities.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class SellerInterceptor implements HandlerInterceptor {
    private final JwtUtils utils;
    @Autowired
    private UserRepository userRepository;

    public SellerInterceptor(JwtUtils utils) {
        this.utils = utils;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token = request.getHeader("Authorization").substring(7);
        String email = utils.getUsernameFromToken(token);
        Users authenticatedUser = userRepository.findByEmail(email);
        return authenticatedUser.getSeller();
    }
}
