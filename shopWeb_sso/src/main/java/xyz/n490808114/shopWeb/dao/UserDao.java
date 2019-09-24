package xyz.n490808114.shopWeb.dao;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Select;

import xyz.n490808114.shopWeb.po.User;

@Resource
public interface UserDao{

    @Select("SELECT * FROM users WHERE id = #{id}")
	User findUserById(int id);

}