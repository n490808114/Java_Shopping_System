package xyz.n490808114.shopWeb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.n490808114.shopWeb.po.User;
import xyz.n490808114.shopWeb.service.ShopWebService;
import xyz.n490808114.shopWeb.util.ShopConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    @Qualifier("shopWebServiceImpl")
    private ShopWebService service;
    /**
     * 如果已经有User,说明之前登陆过，那么跳转回之前的网页并加上User的Token
     * 如果没有User,说明未登录或者已过期，那么跳转到登录页面
     * @param request 用来获取存放在Request中的User
     * @return 返回跳转页面或者登录页面
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request){
        User user =(User) request.getAttribute(ShopConstants.REQUEST_USER);
        if(user != null){
            return "redirect:http://" + request.getContextPath();
        }else{
            return "login.html";
        }
    }

    /**
     * 用户提交用户名和密码，做登录用，如果查询到
     * @param username 前端提交的用户名
     * @param password 前段提交的密码
     * @return 如果查询到User则返回true，否则返回false
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Boolean> login(@RequestParam("username") String username,
                                                    @RequestParam("password") String password){
        if(service.login(username, password)){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Map<String,String>> register(@RequestBody User user){
        Objects.hash("123");
        return null;
    }
}
