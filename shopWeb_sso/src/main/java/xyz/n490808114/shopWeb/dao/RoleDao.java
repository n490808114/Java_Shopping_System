package xyz.n490808114.shopWeb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.n490808114.shopWeb.po.Role;

@Repository
public interface RoleDao {

    @Select("SELECT * FROM roles WHERE id = (SELECT role_id FROM users_roles WHERE user_id = #{userId})")
    Role[] getRoleByUserId(String userId);

    @Insert("INSERT INTO users_roles(user_id,role_id) VALUES(#{userId},#{roleId}")
    int addUserRole(String userId,String roleId);

    @Select("SELECT * FROM roles WHERE id = #{id}")
    Role getRoleById(String id);
}
