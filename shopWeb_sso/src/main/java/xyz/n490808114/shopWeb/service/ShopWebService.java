package xyz.n490808114.shopWeb.service;


import org.springframework.stereotype.Service;
import xyz.n490808114.shopWeb.po.User;

@Service
public interface ShopWebService {

    public String getUserIdByToken(String token);
    public String getCache(String userId);
    public void removeCache(String userId);
    public String createTokenByUser(User user);
    public User getUserById(String userId);

    public boolean login(String username,String password);
}
