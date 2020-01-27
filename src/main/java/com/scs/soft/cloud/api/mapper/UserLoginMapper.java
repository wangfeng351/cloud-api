package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.domain.vo.UserVo;
import com.scs.soft.cloud.api.entity.UserLogin;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
public interface UserLoginMapper {

    /**
     * 根据手机号获取登录信息
     * @param mobile
     * @return
     * @throws SQLException
     */
    @Select("SELECT status FROM t_user_login WHERE mobile=#{mobile}")
    Map<String, Object> getUserLoginByMobile(@Param("mobile") String mobile) throws SQLException;

    /**
     * 重置用户密码为123456
     * @param mobileList
     * @param password
     * @throws SQLException
     */
    @Update({"<script>",
            "UPDATE",
            "t_user_login",
            "SET password=#{password} " +
            "WHERE mobile IN ",
            "<foreach item='item' index='index' collection='mobileList'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    void updateUserLoginByMobile(@Param("mobileList") List<String> mobileList, @Param("password") String password) throws SQLException;

    /**
     * 更新用户登录状态
     * @param userVo
     * @throws SQLException
     */
    @Update("UPDATE t_user_login SET status=#{status} WHERE mobile=#{mobile}")
    void updateStatusByMobile(UserVo userVo) throws SQLException;

    /**
     * 新增用户登录信息
     * @param userLoginList
     * @throws SQLException
     */
    @Insert("<script>" +
            "INSERT INTO t_user_login VALUES " +
            "<foreach collection='userLoginList' item='item' index='index' separator=','>" +
            "(null,#{item.mobile},#{item.password},#{item.code},#{item.status})" +
            "</foreach>" +
            "</script>")
    void insertUserLoginInformation(@Param("userLoginList") List<UserLogin> userLoginList) throws SQLException;

    /**
     * 根据手机号删除信息
     * @param mobileList
     * @throws SQLException
     */
    @Delete({"<script>",
            "DELETE",
            "FROM t_user_login",
            "WHERE mobile IN",
            "<foreach item='item' index='index' collection='mobileList'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    void deleteUserLoginByMobile(@Param("mobileList") List<String> mobileList) throws SQLException;

    /**
     * 查询被禁用用户视图
     * @return
     * @throws SQLException
     */
    @Select("SELECT mobile FROM t_user_login WHERE mobile=#{mobile} AND status=0 ")
    Map<String, Object> getDisableUserBy(@Param("mobile") String mobile) throws SQLException;

    /**
     * 查询未被禁用用户视图
     * @return
     * @throws SQLException
     */
    @Select("SELECT mobile FROM t_user_login WHERE mobile=#{mobile} AND status=1 ")
    Map<String, Object> getEnableUser(@Param("mobile") String mobile) throws SQLException;

}
