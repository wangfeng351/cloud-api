package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.common.ResultCode;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.mapper.ResourceMapper;
import com.scs.soft.cloud.api.mapper.UserMapper;
import com.scs.soft.cloud.api.mapper.UserResourceMapper;
import com.scs.soft.cloud.api.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {
    @Resource
    private ResourceMapper resourceMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserResourceMapper userResourceMapper;

    @Override
    public Result selectAllResource() {
        /*不能定义为null*/
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            maps = resourceMapper.getResource();
            for(Map<String, Object> map1 : maps){
                QueryDto queryDto = QueryDto.builder()
                        .id(Integer.parseInt(map1.get("creator_id").toString())).build();
                map2 = userMapper.getUserById(queryDto);
                if(map2 != null) {
                    String creatorName = userMapper.getUserById(queryDto).get("nickname").toString();
                    if (creatorName != null) {
                        map1.put("creatorName", userMapper.getUserById(queryDto).get("nickname"));
                    } else {
                        map1.put("creatorName", "");
                    }
                }
            }
            map.put("selectAllApi", maps);
            map.put("typeNameApi", resourceMapper.getResourceBy());
        } catch (SQLException e) {
            log.error("获取资源数据异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
        if(map != null){
            return Result.success(map);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @Override
    public Result deleteResourceById(String idList) {
        String[] ids = idList.split(",");
        List<Integer> idList1 = new ArrayList<>();
        try {
            for(String id : ids){
                idList1.add(Integer.parseInt(id));
            }
            resourceMapper.deleteResourceById(idList1);
            userResourceMapper.deleteUserResourceByResourceId(idList1);
            return Result.success();
        } catch (SQLException e) {
            log.error("删除资源操作异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result getResourceByCreateTime(String date) {
        List<Map<String, Object>> maps = new ArrayList<>();
        String[] dates = date.split(",");
        try {
            maps = resourceMapper.getResourceByCreateTime(dates[0], dates[1]);
            for(Map<String, Object> map1 : maps){
                QueryDto queryDto = QueryDto.builder()
                        .id(Integer.parseInt(map1.get("id").toString())).build();
                map1.put("creatorName", userMapper.getUserById(queryDto).get("nickname"));
            }
        } catch (SQLException e) {
            log.error("查询一段时间内数据异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
        if(maps != null){
            return Result.success(maps);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @Override
    public Result getResourceGroupByType() {
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            maps = resourceMapper.getResourceGroupByType();
        } catch (SQLException e) {
            log.error("资源根据类型分组查询异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
        if(maps != null){
            return Result.success(maps);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @Override
    public Result updateResource(com.scs.soft.cloud.api.entity.Resource resource) {
        try {
            resourceMapper.updateResource(resource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result.success();
    }
}
