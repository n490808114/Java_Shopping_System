package xyz.n490808114.shopWeb.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import xyz.n490808114.shopWeb.po.User;
import xyz.n490808114.shopWeb.service.TokenService;

/** 
 * 
*/
public class UserAutherizationFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    /**
     * 进入SSO的情况如下：
     * 1. SSO中心的管理员进入SSO管理用户,会在头部中加入分系统的Token,把分系统的Token认证为角色"ADMIN"
     * 2. 各分系统服务器端进入SSO中验证用户Token,会在头部中加入分系统的Token,把分系统的Token认证为角色"SUBSYSTEM"
     * 3. 各分系统客户端获取SSO的登录页面，此时头部不带有Token,不认证角色，仅可访问登录界面并下发Token
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //获取Request头部中的Token
        String autherization = request.getHeader("Autherization");
        String token = null;
        if(autherization != null && autherization.startsWith("Bearer ")){
            token = autherization.substring(7);
        }

        if(token == null){//如果token为空，继续其他filter
            filterChain.doFilter(request,response);
        }else{
            User user = tokenService.getUserByToken(token);
        }

            
    }
    
}