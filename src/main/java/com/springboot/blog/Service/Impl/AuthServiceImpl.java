//package com.springboot.blog.Service.Impl;
//
//import com.springboot.blog.Payload.LoginDto;
//import com.springboot.blog.Payload.SignUpDto;
//import com.springboot.blog.Service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//    private AuthenticationManager authenticationManager;
//    private PasswordEncoder passwordEncoder;
//
//     AuthServiceImpl(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//
//    @Override
//    public String login(LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return "User logged In Successfully";
//    }
//
//
//}
