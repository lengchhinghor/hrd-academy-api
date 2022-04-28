package org.ksga.springboot.springsecuritydemo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ksga.springboot.springsecuritydemo.model.auth.ERole;
import org.ksga.springboot.springsecuritydemo.model.auth.Role;

import java.util.Optional;

@Mapper
public interface RoleRepository {
    @Select("SELECT * FROM roles WHERE name = #{name}")
    Optional<Role> findByName(ERole role);
}
