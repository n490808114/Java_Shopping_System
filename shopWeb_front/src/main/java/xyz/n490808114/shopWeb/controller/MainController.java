package xyz.n490808114.shopWeb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PreAuthorize("hasRole('USER')")
public class MainController{

    @GetMapping("/")
    @ResponseBody
    public String getMainPage(){
        return "这是主页";
    }
}