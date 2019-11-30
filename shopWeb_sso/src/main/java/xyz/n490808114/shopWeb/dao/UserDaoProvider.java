package xyz.n490808114.shopWeb.dao;

import org.apache.ibatis.jdbc.SQL;
import xyz.n490808114.shopWeb.po.User;
import xyz.n490808114.shopWeb.util.ShopConstants;

public class UserDaoProvider {
    public String addUser(User user){
        return new SQL(){{
            INSERT_INTO(ShopConstants.TABLE_USER);
            VALUES("id",user.getId());
            if(user.getName() != null){
                VALUES("name",user.getName());
            }
            VALUES("username",user.getUsername());
            VALUES("password",user.getPassword());
            if(user.getEmail() !=null){
                VALUES("email",user.getEmail());
            }
            if(user.getAvatar() != null){
                VALUES("avatar",user.getAvatar());
            }
        }}.toString();
    }
}
