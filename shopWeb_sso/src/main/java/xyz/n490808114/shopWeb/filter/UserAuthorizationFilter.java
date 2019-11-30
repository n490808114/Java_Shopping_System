package xyz.n490808114.shopWeb.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import xyz.n490808114.shopWeb.po.User;
import xyz.n490808114.shopWeb.service.ShopWebService;
import xyz.n490808114.shopWeb.util.ShopConstants;

/**
 * 外部进入SSO,认证权限
 *
 * 进入SSO的情况如下：
 * 1. SSO中心的管理员进入SSO管理用户,会在头部中加入分系统的Token,把分系统的Token认证为角色"ADMIN"
 * 2. 各分系统服务器端进入SSO中验证用户Token,会在头部中加入分系统的Token,把分系统的Token认证为角色"SUBSYSTEM"
 * 3. 各分系统客户端获取SSO的登录页面，此时头部不带有Token,不认证角色，仅可访问登录界面并下发Token
 * 4. 各分系统客户端获取SSO的登录页面，此时头部带有Token,认证角色
 */
public class UserAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    ShopWebService service;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 获取Request头部中的Token
        String authorization = request.getHeader("Authorization");
        String token = null;
        if(authorization != null && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
        }
        // 如果token不为空，那么尝试获取token对应的User
        if(token != null){
            String userId = service.getUserIdByToken(token);
            User user = service.getUserById(userId);
            // 如果User获取到就把User的Role加载到SecurityContext中
            if(user != null){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                request.setAttribute(ShopConstants.REQUEST_USER,user);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // 继续执行其他filter
        filterChain.doFilter(request,response);
    }
    
}