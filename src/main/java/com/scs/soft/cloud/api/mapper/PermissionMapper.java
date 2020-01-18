package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.entity.Permission;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
public interface PermissionMapper {

    /**
     * 新增权限
     * @param permission
     * @throws SQLException
     */
    @Insert("INSERT INTO t_permission VALUES(null,#{name},#{type},#{permissionCode}," +
            "#{parentId},#{routerUrl},#{icon},#{authorization},#{status},#{sort})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPermission(Permission permission) throws SQLException;

    /**
     * 查询顶级菜单及其子菜单的视图
     * @return
     * @throws SQLException
     */
    @Results({
            @Result(property = "childList",column = "id",
                    many = @Many(select = "com.scs.soft.cloud.api.mapper.PermissionMapper.getChildPermissionByParentId")
            ),
            @Result(property = "id", column = "id")
    })
    @Select("SELECT * FROM t_permission WHERE type=0 AND parent_id=0 ORDER BY id ASC")
    List<Map<String, Object>> getTopMenuPermission() throws SQLException;

    /**
     * 根据父类id查询子级权限
    * @param id
     * @return
     * @throws SQLException
     */
    @Results({
            @Result(property = "childList",column = "id",
                    many = @Many(select = "com.scs.soft.cloud.api.mapper.PermissionMapper.getChildPermissionByParentId")
            ),
            /*递归调用，将自己的子类权限全部调用出了*/
            @Result(property = "id", column = "id")
    })
    @Select("SELECT * FROM t_permission WHERE parent_id=#{id} ORDER BY id ASC")
    List<Map<String, Object>> getChildPermissionByParentId(@Param("id") int id) throws SQLException;

    /**
     * 根据id查询权限
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM t_permission WHERE id=#{id} ORDER BY id ASC")
    Permission selectPermission(@Param("id") int id) throws SQLException;


    /**
     * 根据id删除权限
     * @param id
     * @throws SQLException
     */
    @Delete("DELETE FROM t_permission WHERE id=#{id}")
    void deletePermissionById(@Param("id") int id) throws SQLException;

    /**
     * 根据权限id修改权限
     * @param permission
     * @return
     * @throws SQLException
     */
    @Update("UPDATE t_permission SET name=#{name},type=#{type},permission_code=#{permissionCode}," +
            "parent_id=#{parentId},router_url=#{routerUrl},icon=#{icon},authorization=#{authorization}," +
            "status=#{status},sort=#{sort} WHERE id=#{id}")
    void updatePermissionById(Permission permission) throws SQLException;

    @Select("SELECT * FROM t_permission WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Permission> getPermissionByName(Permission permission) throws SQLException;
}

