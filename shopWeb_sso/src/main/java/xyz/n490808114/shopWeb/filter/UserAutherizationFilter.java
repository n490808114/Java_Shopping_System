package xyz.n490808114.shopWeb.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class UserAutherizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String autherization = request.getHeader("Autherization");
        String token = null;
        if(autherization != null && autherization.startsWith("Bearer ")){
            token = autherization.substring(7);
        }
        if(token != null){
            filterChain.doFilter(request, response);
        }
            
    }
    
}