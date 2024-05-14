package com.example.untitled.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class JwtFilter implements jakarta.servlet.Filter {

    private static final String SECRET_KEY = "your_secret_key"; // Мій секретний ключ

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String token = httpRequest.getHeader("Authorization");

        if (token != null //&& token.startsWith("Bearer ")
         ) {
            try {
                String jwt = token.substring(7);
                Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
                String username = claims.getSubject();
                // Тут можемо зробити що завгодно з отриманими даними, наприклад, перевірити користувача в базі даних або встановити атрибути сесії

                // Продовжуємо ланцюг фільтрів
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (ExpiredJwtException e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            // Якщо токен не передано, відмовляємо в доступі
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
