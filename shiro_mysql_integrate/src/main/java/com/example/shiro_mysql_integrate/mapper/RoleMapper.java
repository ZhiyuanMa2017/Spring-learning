package com.example.shiro_mysql_integrate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper {

    // select p.name
    // from permission p
    // inner join role_permission rp
    // on rp.permission_id = p.od
    // where rp.role_id = #{role_id}
    @Select("select p.name "
            + "from permission p "
            + "inner join role_permission rp "
            + "on rp.permission_id = p.id  "
            + "where rp.role_id = #{role_id}")
    Set<String> getPermissionByRoleId(@Param("role_id") Long roleId);
}
