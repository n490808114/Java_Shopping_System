package xyz.n490808114.shopWeb.dao;

import org.apache.ibatis.annotations.*;

import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import xyz.n490808114.shopWeb.po.User;

@Repository
public interface UserDao{

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results(id = "user",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "email",property = "email"),
            @Result(column = "avatar",property = "avatar"),
            @Result(column = "id",property = "roles",many =
                    @Many(select = "xyz.n490808114.shopWeb.dao.RoleDao.getRoleByUserId",fetchType = FetchType.EAGER)
            )
    })
	User findUserById(String id);
    @Select("SELECT * FROM users WHERE username = #{username},password = #{password}")
    @ResultMap("user")
    User login(String username,String password);

    @InsertProvider(value = UserDaoProvider.class,method = "addUser")
    void addUser(User user);

}