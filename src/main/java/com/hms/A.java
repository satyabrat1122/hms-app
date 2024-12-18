package com.hms;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class A extends OncePerRequestFilter {
    public static void main(String[] args) {
        System.out.println(BCrypt.checkpw("testing","$2a$05$OlFIs6aBQTrYm/Xm0k1H6.ip6UbsZ1wRKQINjyxAUqDW1qDAudBDm"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    }
}
