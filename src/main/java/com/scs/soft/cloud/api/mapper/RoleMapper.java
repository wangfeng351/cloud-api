package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.entity.Permission;
import com.scs.soft.cloud.api.entity.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
public interface RoleMapper {

    /**
     * 查询所有角色
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM t_role")
    List<Map<String, Object>> selectRole() throws SQLException;

    /**
     * 修改角色信息
     * @param role
     * @throws SQLException
     */
    @Update("UPDATE t_role SET name=#{name},code=#{code} WHERE id=#{id}")
    void updateRole(Role role) throws SQLException;

    /**
     * 删除角色信息
     * @param id
     * @throws SQLException
     */
    @Delete("DELETE FROM t_role WHERE id=#{id}")
    void deleteRole(@Param("id") int id) throws SQLException;

    /**
     * 新增角色信息
     * @param role
     * @throws SQLException
     */
    @Insert("INSERT INTO t_role VALUES(null,#{name},#{code})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertRole(Role role) throws SQLException;
}
