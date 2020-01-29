package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.common.ResultCode;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.domain.vo.ClassVo;
import com.scs.soft.cloud.api.entity.UserClass;
import com.scs.soft.cloud.api.mapper.ClassMapper;
import com.scs.soft.cloud.api.mapper.UserClassMapper;
import com.scs.soft.cloud.api.mapper.UserMapper;
import com.scs.soft.cloud.api.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/28
 * @description TODO
 */
@Service
@Slf4j
public class ClassServiceImpl implements ClassService {
    @Resource
    private ClassMapper classMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserClassMapper userClassMapper;

    @Override
    public Result selectAll() {
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            maps = classMapper.selectAllClass();
            for(Map<String, Object> map : maps){
                int id = Integer.parseInt(map.get("creator_id").toString());
                QueryDto queryDto = QueryDto.builder()
                        .id(id).build();
                String nickname = userMapper.getUserById(queryDto).get("nickname").toString();
                String mobile = userMapper.getUserById(queryDto).get("mobile").toString();
                    map.put("nickname", nickname);
                    map.put("mobile", mobile);
            }
        } catch (SQLException e) {
            log.error("查询所有班课信息异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
        if(maps.size() > 0 ){
            return Result.success(maps);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @Override
    public Result deleteClassById(String id) {
        String[] idAry = id.split(",");
        List<Integer> idList = new ArrayList<>();
        for(String s : idAry){
            idList.add(Integer.parseInt(s));
        }
        try {
            classMapper.deleteClassById(idList);
            for(int i : idList){
                String userId = userClassMapper.getUserClassById(i).get("user_id").toString();
                UserClass userClass = UserClass.builder()
                        .userId(Integer.parseInt(userId))
                        .classId(i).build();
                userClassMapper.deleteUserClassBy(userClass);
            }
            return Result.success();
        } catch (SQLException e) {
            log.error("班课信息删除异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result updateClassById(ClassVo classVo) {
        try {
            classMapper.updateClassById(classVo);
            return Result.success();
        } catch (SQLException e) {
            log.error("修改班课信息异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result getUserClassBy(QueryDto queryDto) {
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            maps = classMapper.getClassBy(queryDto);
        } catch (SQLException e) {
            log.error("班课信息模糊查询异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
        if(maps != null){
            return Result.success(maps);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }
}
