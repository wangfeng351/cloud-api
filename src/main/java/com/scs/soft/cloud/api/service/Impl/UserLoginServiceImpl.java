package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.common.ResultCode;
import com.scs.soft.cloud.api.mapper.UserLoginMapper;
import com.scs.soft.cloud.api.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private UserLoginMapper userLoginMapper;


    @Override
    public Result updateUserLoginPasswordByMobile(String mobiles) {
        List<String> list = new ArrayList<>();
        String[] mobile = mobiles.split(",");
        for(String i : mobile){
            list.add(i);
        }
        try {
            userLoginMapper.updateUserLoginByMobile(list,"123456");
            return Result.success();
        } catch (SQLException e) {
            log.error("密码重置异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }
}
