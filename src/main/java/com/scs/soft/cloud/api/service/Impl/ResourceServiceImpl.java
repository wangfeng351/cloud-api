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
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            maps = resourceMapper.getResource();
            for(Map<String, Object> map : maps){
                QueryDto queryDto = QueryDto.builder()
                        .id(Integer.parseInt(map.get("id").toString())).build();
                map.put("creatorName", userMapper.getUserById(queryDto).get("nickname"));
            }
        } catch (SQLException e) {
            log.error("获取资源数据异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
        if(maps != null){
            return Result.success(maps);
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
}
