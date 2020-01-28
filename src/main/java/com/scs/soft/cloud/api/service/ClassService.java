package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.domain.vo.ClassVo;

/**
 * @author wf
 * @create 2020/1/28
 * @description TODO
 */
public interface ClassService {

    /**
     * 查询所有班课信息
     * @return
     */
    Result selectAll();

    /**
     * 根据id删除班课
     * @param id
     * @return
     */
    Result deleteClassById(String id);

    /**
     * 根据id修改班课信息
     * @param classVo
     * @return
     */
    Result updateClassById(ClassVo classVo);

    /**
     * 模糊查询
     * @param queryDto
     * @return
     */
    Result getUserClassBy(QueryDto queryDto);
}
