package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.entity.Resource;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
public interface ResourceService {

    /**
     * 获取所有资源
     * @return
     */
    Result selectAllResource();

    /**
     * 根据id删除资源
     * @param idList
     * @return
     */
    Result deleteResourceById(String idList);

    /**
     * 查询某一时间段的数据
     * @param date
     * @return
     */
    Result getResourceByCreateTime(String date);

    /**
     * 根据类型将数据进行分组
     * @return
     */
    Result getResourceGroupByType();

    Result updateResource(Resource resource);
}
