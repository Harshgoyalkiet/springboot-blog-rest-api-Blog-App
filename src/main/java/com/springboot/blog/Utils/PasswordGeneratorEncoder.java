package com.springboot.blog.Utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;

public class PasswordGeneratorEncoder {

    public static void main(String[]args)
    {
        PasswordEncoder passwordEncoder=new BcryptPassword4jPasswordEncoder();
        System.out.println(passwordEncoder.encode("harsh"));
        System.out.println(passwordEncoder.encode("ramesh"));

    }


}
