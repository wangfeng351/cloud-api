package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.entity.RolePermission;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
public interface RolePermissionMapper {

    /**
     * 查询角色权限视图
     * @return
     * @throws SQLException
     */
    @Results({
            @Result(property = "permission", column = "id",
                    many = @Many(select = "com.scs.soft.cloud.api.mapper.PermissionMapper.getPermissionById")
            ),
            @Result(property = "id", column = "id")
    })
    @Select("SELECT * FROM t_role_permission WHERE role_id=#{roleId}")
    List<Map<String, Object>> getRolePermissionById(@Param("roleId") int roleId) throws SQLException;

    /**
     * 给角色新增权限
     * @param rolePermission
     * @throws SQLException
     */
    @Insert("INSERT INTO t_role_permission VALUES(null,#{roleId},#{permissionId})")
    void insertRolePermission(RolePermission rolePermission) throws SQLException;

    /**
     * 根据id、roleId,permissionId 删除角色权限映射记录
     * @param rolePermission
     * @throws SQLException
     */
    @Delete("<script>" +
            "DELETE FROM t_role_permission" +
            " WHERE 1=1" +
            "<when test='rolePermission.id!=null'>" +
            "AND id=#{rolePermission.id}" +
            "</when>" +
            "<when test='rolePermission.roleId!=null'>" +
            "AND role_id=#{rolePermission.roleId}" +
            "</when>" +
            "<when test='rolePermission.permissionId!=null'>" +
            "AND permission_id=#{rolePermission.permissionId}" +
            "</when>" +
            "</script>")
    void deleteRolePermissionBy(@Param("rolePermission") RolePermission rolePermission) throws SQLException;
}
