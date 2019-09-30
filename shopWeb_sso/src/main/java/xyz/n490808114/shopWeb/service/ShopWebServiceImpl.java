package xyz.n490808114.shopWeb.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.n490808114.shopWeb.dao.RoleDao;
import xyz.n490808114.shopWeb.dao.UserDao;
import xyz.n490808114.shopWeb.po.Role;
import xyz.n490808114.shopWeb.po.User;
import xyz.n490808114.shopWeb.util.ShopConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("shopWebServiceImpl")
public class ShopWebServiceImpl implements ShopWebService{

    private static final Log log = LogFactory.getLog(ShopWebServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public String getUserIdByToken(String token) {
        //获取SecretKey
        String key = Base64.encodeBase64String(ShopConstants.SSO_TOKEN_SECRET_KEY.getBytes());
        //获取Token中的payload
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJwt(token).getBody();
        //获取User id以获取User ,如果获取不到User返回null
        return (String) claims.get("id");

    }

    @Override
    public boolean login(String username, String password) {
        return userDao.login(username, password) != null;
    }

    @Override
    public User getUserById(String userId) {
        return userDao.findUserById(userId);
    }

    @Transactional
    public boolean register(User user)  {
        user.setId(UUID.randomUUID().toString());
        userDao.addUser(user);
        for(Role role : user.getRoles()){
            if(roleDao.getRoleById(role.getId()) != null){
                roleDao.addUserRole(user.getId(),role.getId());
            }else{
                throw new NullPointerException("用户权限"+ role.getId()+" : "
                                                        + role.getName() +"找不到");
            }
        }
        return true;
    }


    /**
     * 创建Token 并把 user.id : token 存入缓存
     * @param user 用户实例
     * @return 创建的Token字符串,其中存有userId，和username
     */
    @Override
    @CachePut(value = "token",key = "#user.id")
    public String createTokenByUser(User user){

        Map<String,Object> claims = new HashMap<>();
        claims.put("username",user.getUsername());

        return Jwts.builder().setId(user.getId())
                .setIssuer(ShopConstants.SSO_TOKEN_ISSUER)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setNotBefore(new Date())
                .setExpiration(new Date(new Date().getTime()+ShopConstants.SSO_TOKEN_EXPRIED_TIME))
                .signWith(SignatureAlgorithm.HS512, ShopConstants.SSO_TOKEN_SECRET_KEY)
                .compact();
    }




    /**
     * Cacheable会先从缓存中查找，如果缓存中有的话就返回缓存中的User
     * 否则返回null
     * @param userId 调用传入的userId
     * @return 返回缓存中User对应的Token,没有userId则返回null
     */
    @Override
    @Cacheable(value = "token",key = "#userId")
    public String getCache(String userId){
        return null;
    }

    /**
     * 如果调用该方法，那么就从缓存中删除该user这条数据
     * @param userId userId值，用来在缓存中查找Key
     */
    @Override
    @CacheEvict(value = "token",key = "#userId")
    public void removeCache(String userId){
        log.info("Redis 移除Token："+ userId);
    }



}
