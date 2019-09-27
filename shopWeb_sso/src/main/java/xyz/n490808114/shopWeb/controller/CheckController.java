package xyz.n490808114.shopWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.n490808114.shopWeb.service.ShopWebService;

/**
 * 与分系统交互用
 * 接受分系统验证请求
 */
@RestController
@PreAuthorize("hasRole('SUBSYSTEM')")
public class CheckController {
    @Autowired
    @Qualifier("shopWebServiceImpl")
    private ShopWebService service;

    /**
     * 如果缓存中有userToken返回true，否则返回false
     * @param userToken 子系统提交的用户Token
     * @return 查询的结果
     */
    @PostMapping("/check")
    public ResponseEntity<Boolean> check(@RequestParam("userToken") String userToken){
        //解析出Token中的userId
        String userId = service.getUserIdByToken(userToken);
        //通过userId获取缓存中的cacheToken
        String cacheToken = service.getCache(userId);
        //如果获取到并且两者相等，返回true，否则返回false
        if(cacheToken !=null && cacheToken.equals(userToken)){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
}
