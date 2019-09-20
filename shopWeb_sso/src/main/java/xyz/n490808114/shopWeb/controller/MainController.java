package xyz.n490808114.shopWeb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMain(HttpServletRequest request){
        String authentication =request.getHeader("Authentication");
        String token = null;
        if(authentication != null && authentication.startsWith("Bearer ")){
            token = authentication.substring(7);
        }
        if(token != null){
            return "redirect:http://" + request.getContextPath();
        }else{
            return "login.html";
        }
    }
}
