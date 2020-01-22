package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.domain.dto.PageDto;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.domain.vo.UserVo;
import com.scs.soft.cloud.api.entity.User;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 20202020/1/20
 * @description TODO
 */
public interface UserMapper {

    /**
     * 批量新增
     * @param userList
     * @throws SQLException
     */
    @Insert("<script>" +
            "INSERT INTO t_user VALUES " +
            "<foreach collection='userList' item='item' index='index' separator=','>" +
            "(null,#{item.mobile},#{item.nickname},#{item.email},#{item.name},#{item.gender}," +
            "#{item.school},#{item.faculty},#{item.jobNumber},#{item.experience},#{item.charisma}," +
            "#{item.joinClassNumber},#{item.createClassNumber},#{item.resourceNumber}," +
            "#{item.activityNumber},#{item.createTime},#{item.avatar},#{item.profession},#{item.birthday})" +
            "</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void batchInsert (@Param(value = "userList") List<User> userList) throws SQLException;

    /*查询所有用户*/
    @Select("SELECT * FROM t_user " +
            "LIMIT ${pageDto.pageSize*(pageDto.currentPage-1)},#{pageDto.pageSize}")
    List<Map<String, Object>> selectUser(@Param("pageDto") PageDto pageDto) throws SQLException;

    /*批量删除*/
    @Delete({"<script>",
            "DELETE",
            "FROM t_user",
            "WHERE mobile IN",
            "<foreach item='item' index='index' collection='mobileList'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    void deleteByUserId(@Param("mobileList") List<String> mobileList) throws SQLException;

    /*根据id查询用户信息*/
    @Select("<script>" +
            "SELECT * FROM t_user " +
            "WHERE 1=1" +
            "<when test='queryDto.id!=null'>" +
            "AND id=#{queryDto.id} " +
            "</when>" +
            "<when test='queryDto.field!=null'>" +
            "AND mobile=#{queryDto.field} " +
            "</when>" +
            "</script>")
    Map<String, Object> getUserById(@Param("queryDto") QueryDto queryDto) throws SQLException;

    /*根据id修改数据*/
    @Update("UPDATE t_user SET email=#{email},name=#{name},gender=#{gender}," +
            "school=#{school},faculty=#{faculty},job_number=#{jobNumber} " +
            "WHERE id=#{id}")
    void updateUserById(UserVo userVo) throws SQLException;

    /**
     * 新增用户信息
     * @param user
     * @throws SQLException
     */
    @Insert("INSERT INTO t_user VALUES (null,#{mobile},#{nickname},#{email},#{name},#{gender}," +
            "#{school},#{faculty},#{Q},#{experience},#{charisma}, " +
            "#{joinClassNumber},#{createClassNumber},#{resourceNumber}, " +
            "#{activityNumber},#{createTime},#{avatar},#{profession},#{birthday})")
    void insertUser(User user) throws SQLException;

    /**
     * 某年月注册量视图
     * @param year
     * @return
     * @throws SQLException
     */
    @Select("SELECT MONTH(create_time) AS 'month' , COUNT(*) AS 'count' FROM t_user " +
            "WHERE YEAR(create_time)=#{year} GROUP BY YEAR (create_time) DESC, MONTH(create_time)")
   List<Map<String, Object>> getUserByMonth(@Param("year") String year) throws SQLException;

    @Select("SELECT school AS 'schoolName' , COUNT(*) AS 'peoperNumber' FROM t_user " +
            "GROUP BY school ")
    List<Map<String, Object>> getUserSchool() throws SQLException;
}
