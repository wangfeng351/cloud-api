package com.scs.soft.cloud.api.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
public interface UserResourceMapper {

    /**
     * 根据用户id删除
     * @param userIdList
     * @throws SQLException
     */
    @Delete({"<script>" +
            "DELETE FROM t_user_resource " +
            "WHERE user_id IN " +
            "<foreach item='item' index='index' collection='userIdList' " +
            "open='(' separator=',' close=')'> " +
            "#{item}" +
            "</foreach> " +
            "</script>"})
    void deleteUserResourceByUserId(@Param("userIdList") List<Integer> userIdList) throws SQLException;

    /**
     * 根据资源id删除
     * @param idList
     * @throws SQLException
     */
    @Delete({"<script>" +
            "DELETE FROM t_user_resource " +
            "WHERE resource_id IN " +
            "<foreach item='item' index='index' collection='idList' " +
            "open='(' separator=',' close=')'> " +
            "#{item}" +
            "</foreach> " +
            "</script>"})
    void deleteUserResourceByResourceId(@Param("idList") List<Integer> idList) throws SQLException;
}
