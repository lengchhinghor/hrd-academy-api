package org.ksga.springboot.springsecuritydemo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.ksga.springboot.springsecuritydemo.model.auth.Role;
import org.ksga.springboot.springsecuritydemo.model.auth.User;

import java.util.Optional;
import java.util.Set;

@Mapper
public interface UserRepository {

    @Select("SELECT * FROM users WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "roles", column = "id", many = @Many(select = "findAllRolesByUserId"))
    })
    Optional<User> findUserByUsername(String username);

    @Select("SELECT * FROM users_roles ur INNER JOIN roles r ON ur.role_id = r.id WHERE ur.user_id = #{userId}")
    Set<Role> findAllRolesByUserId(Long userId);

    @Insert("INSERT INTO users ( username, password) VALUES (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insertUser(User user);

    @Insert("<script>INSERT INTO users_roles (user_id, role_id) VALUES "
            + "<foreach collection='roles' item='role' separator=','>"
            + "(#{userId}, #{role.id})"
            + "</foreach>"
            + "</script>")
    boolean insertUsersRoles(Long userId, Set<Role> roles);

    default boolean save(User user) {
        if (insertUser(user)) {
            return insertUsersRoles(user.getId(), user.getRoles());
        }
        return false;
    }

    @Select("SELECT CASE WHEN EXISTS " +
            "(SELECT id FROM users WHERE username = #{username}) " +
            "THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END")
    boolean existsByUsername(String username);
}
