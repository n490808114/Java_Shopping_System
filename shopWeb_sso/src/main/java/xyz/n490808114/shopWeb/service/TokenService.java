package xyz.n490808114.shopWeb.service;

import java.util.Date;

import jdk.nashorn.internal.parser.Token;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import xyz.n490808114.shopWeb.dao.UserDao;
import xyz.n490808114.shopWeb.po.User;
import xyz.n490808114.shopWeb.util.ShopConstants;

@Service
public class TokenService {
    private static final Log log = LogFactory.getLog(TokenService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;


    public User getUserByToken(String token) {
        //获取SecretKey
        String key = Base64.encodeBase64String(ShopConstants.SSO_TOKEN_SECRET_KEY.getBytes());
        //获取Token中的payload
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJwt(token).getBody();
        //获取User id以获取User ,如果获取不到User返回null
        int id = (Integer) claims.get("id");
        return userDao.findUserById(id);
    }
    @Cacheable(value = "token",key = "#user.id")
    public String getTokenByUser(User user){
        return Jwts.builder().setId(""+user.getId())
                    .setIssuer(ShopConstants.SSO_TOKEN_ISSUER)
                    .setIssuedAt(new Date())
                    .setNotBefore(new Date())
                    .setExpiration(new Date(new Date().getTime()+ShopConstants.SSO_TOKEN_EXPRIED_TIME))
                    .signWith(SignatureAlgorithm.HS512, ShopConstants.SSO_TOKEN_SECRET_KEY)
                    .compact();
    }
}