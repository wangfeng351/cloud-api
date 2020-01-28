package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.domain.vo.ClassVo;
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
public interface ClassMapper {

    /**
     * 查询所有班课信息视图
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM t_class ")
    List<Map<String, Object>> selectAllClass() throws SQLException;

    /**
     * 根据班课名称模糊查询，根据创建者id,邀请码精确查询
     * @param queryDto
     * @return
     * @throws SQLException
     */
    @Select("<script> " +
            "SELECT * FROM t_class " +
            "WHERE 1=1 " +
            "<when test='queryDto.id!=null'> " +
            "AND creator_id=#{queryDto.id} OR invitation_code=#{queryDto.id} " +
            "</when> " +
            "<when test='queryDto.field!=null'> " +
            "AND name LIKE CONCAT('%', #{queryDto.field}, '%')" +
            "</when> " +
            "</script>")
    List<Map<String, Object>> getClassBy(@Param("queryDto") QueryDto queryDto) throws SQLException;

    /**
     * 根据id查询内容
     * @param id
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM t_class WHERE id=#{id} ")
    Map<String, Object> getUserClassById(@Param("id") int id) throws SQLException;

    /**
     * 根据id修改班课信息
     * @param classVo
     * @throws SQLException
     */
    @Update("UPDATE t_class SET class_type=#{classType},name=#{name},status=#{status},school=#{school},faculty=#{faculty} " +
            "WHERE id=#{id} ")
    void updateClassById(ClassVo classVo) throws SQLException;

    /**
     * 根据id删除班课信息（可多/单删除）
     * @param idList
     * @throws SQLException
     */
    @Delete("<script> " +
            "DELETE FROM t_class " +
            "WHERE id IN " +
            "<foreach item='item' index='index' collection='idList' " +
            "open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    void deleteClassById(@Param("idList") List<Integer> idList) throws SQLException;

    /**
     * 根据创建者id删除班课信息
     * @param idList
     * @throws SQLException
     */
    @Delete("<script>" +
            "DELETE FROM t_class " +
            "WHERE creator_id IN " +
            "<foreach item='item' index='index' collection='idList'>" +
            "open='(' separator',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script> ")
    void deleteClassByCreatorId(@Param("idList") List<Integer> idList) throws SQLException;
}
