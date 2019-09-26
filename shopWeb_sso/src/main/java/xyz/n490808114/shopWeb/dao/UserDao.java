package xyz.n490808114.shopWeb.dao;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Repository;
import xyz.n490808114.shopWeb.po.User;

@Repository
public interface UserDao{

    @Select("SELECT * FROM users WHERE id = #{id}")
	User findUserById(int id);

}