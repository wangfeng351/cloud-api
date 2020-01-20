package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.entity.UserRole;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/21
 * @description TODO
 */
public interface UserRoleMapper {

    /*批量分配用户角色*/
    @Insert("<script>" +
            "INSERT INTO t_user_role VALUES " +
            "<foreach collection='userRoleList' item='item' index='index' separator=','>" +
            "(null,#{item.roleId},#{item.userId})" +
            "</foreach>" +
            "</script>")
    void batchInsertUserRole(@Param("userRoleList") List<UserRole> userRoleList) throws SQLException;

    /**
     * 根据id查询用户角色信息
     * @param id
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM t_user_role WHERE user_id=#{id}")
    Map<String, Object> getUserRoleById(@Param("id") int id) throws SQLException;

    /**
     * 根据id删除用户角色
     * @param idList
     * @throws SQLException
     */
    @Delete({"<script>",
            "DELETE",
            "FROM t_user_role",
            "WHERE user_id IN",
            "<foreach item='item' index='index' collection='idList'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    void deleteUserRoleById(@Param("idList") List<Integer> idList) throws SQLException;

    /**
     * 根据用户id修改角色信息
     * @param userRole
     * @throws SQLException
     */
    @Update("UPDATE t_user_role SET role_id=#{roId} WHERE user_id=#{userId}")
    void updateUserRoleByUserId(UserRole userRole) throws SQLException;
}
