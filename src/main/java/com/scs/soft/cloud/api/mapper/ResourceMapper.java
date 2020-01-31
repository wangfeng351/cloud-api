package com.scs.soft.cloud.api.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
public interface ResourceMapper {

    /**
     * 查询资源视图
     * @return
     * @throws SQLException
     */
    @Select("SELECT id,name,url,creator_id,type,create_time FROM t_resource ")
    List<Map<String, Object>> getResource() throws SQLException;

    /**
     * 根据发布人id删除资源信息
     * @param creatorIdList
     * @throws SQLException
     */
    @Delete("<script> " +
            "DELETE FROM t_resource " +
            "WHERE creator_id IN " +
            "<foreach item='item' index='index' collection='creatorIdList' " +
            "open='(' separator=',' close=')'> " +
            "#{item} " +
            "</foreach>" +
            "</script>")
    void deleteResourceByCreatorId(@Param("creatorIdList") List<Integer> creatorIdList) throws SQLException;

    /**
     * 根据id删除资源信息
     * @param idList
     * @throws SQLException
     */
    @Delete({"<script>",
            "DELETE",
            "FROM t_resource",
            "WHERE id IN",
            "<foreach item='item' index='index' collection='idList'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    void deleteResourceById(@Param("idList") List<Integer> idList) throws SQLException;

    /**
     * 根据指定时间段查询信息
     * @param beginDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM t_resource WHERE 1=1 AND date_format(create_time,'%Y-%m-%d') between #{beginDate} and #{endDate}")
    List<Map<String, Object>> getResourceByCreateTime(@Param("beginDate") String beginDate, @Param("endDate") String endDate) throws SQLException;

    @Select("SELECT type AS 'typeName' FROM t_resource GROUP BY type")
    List<Map<String, Object>> getResourceBy() throws SQLException;
}
