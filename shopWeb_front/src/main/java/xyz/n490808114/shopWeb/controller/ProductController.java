package xyz.n490808114.shopWeb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.n490808114.shopWeb.dto.ProductListDto;

import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public ResponseEntity<ProductListDto> getList(@RequestParam Map<String,String> param){
        return ResponseEntity.status(HttpStatus.OK).body(new ProductListDto());
    }
}
