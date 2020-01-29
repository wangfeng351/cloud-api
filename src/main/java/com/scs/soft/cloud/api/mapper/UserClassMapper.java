package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.entity.UserClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/28
 * @description TODO
 */
public interface UserClassMapper {

    /**
     * 根据用户id删除信息
     * @param userIdList
     * @throws SQLException
     */
    @Delete("<script>" +
            "DELETE FROM t_user_class" +
            "WHERE user_id IN " +
            "<foreach item='item' index='index' collection='userIdList' " +
            "open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    void deleteUserClassByUserId(@Param("userIdList") List<Integer> userIdList) throws SQLException;

    /**
     * 根据用户id和班课id删除用户班课信息
     * @param userClass
     * @throws SQLException
     */
    @Delete("DELETE FROM t_user_class WHERE user_id=#{userId} AND class_id=#{classId} ")
    void deleteUserClassBy(UserClass userClass) throws SQLException;

    /**
     * 根据id查询用户班课信息
     * @param id
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM t_user_class WHERE class_id=#{id} ")
    Map<String, Object> getUserClassById(@Param("id") int id) throws SQLException;

    /**
     * 根据用户id修改用户角色信息
     * @param userClass
     * @throws SQLException
     */
    @Update("UPDATE t_user_class SET role_id=#{roleId} WHERE user_id=#{userId} ")
    void updateUserClass(UserClass userClass) throws SQLException;
}
