package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.common.ResultCode;
import com.scs.soft.cloud.api.domain.dto.PageDto;
import com.scs.soft.cloud.api.entity.User;
import com.scs.soft.cloud.api.entity.UserLogin;
import com.scs.soft.cloud.api.mapper.CommonMapper;
import com.scs.soft.cloud.api.mapper.UserLoginMapper;
import com.scs.soft.cloud.api.mapper.UserMapper;
import com.scs.soft.cloud.api.service.UserService;
import com.scs.soft.cloud.api.util.ImportDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 20202020/1/20
 * @description TODO
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private CommonMapper commonMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserLoginMapper userLoginMapper;

    @Override
    public Result ExportUserInformation(File file) {
        List<User> users = new ArrayList<>();
        List<UserLogin> userLogins = new ArrayList<>();
        int position = file.getName().lastIndexOf(".");
        String ffx = file.getName().substring(position + 1);
        try {
            if(ffx.equals("xls")){
                users = ImportDataUtil.readExcel(file);
            }else if(ffx.equals("xlsx")) {
                users = ImportDataUtil.readExcel2(file);
            }
            commonMapper.alert("t_user");
            commonMapper.alert("t_user_login");
            for(User user : users){
                UserLogin userLogin = UserLogin.builder().mobile(user.getMobile())
                        .password("123456")
                        .status(true)
                        .code("123123")
                        .build();
                userLogins.add(userLogin);
            }
            userLoginMapper.insertUserLoginInformation(userLogins);
            userMapper.insertUser(users);
            return Result.success();
        } catch (SQLException | ParseException | IOException e) {
            log.error("用户批量导入异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result selectAllUser(PageDto pageDto) {
        List<Map<String, Object>> users = new ArrayList<>();
        Map<String, Object> map;
        try {
            users = userMapper.selectUser(pageDto);
            int len = users.size();
            for (Map<String, Object> user : users) {
                map = userLoginMapper.getUserLoginByMobile(user.get("mobile").toString());
                /*map报空异常*/
                if(map != null){
                user.put("status", map.get("status"));
                }
            }
        } catch (SQLException e) {
            log.error("查询所有用户信息异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
        if(users != null){
            return Result.success(users);
        }
        return Result.failure(ResultCode.DATABASE_ERROR);
    }

    @Override
    public Result deleteUserById(String idList) {
        System.out.println(idList);
        List<String> list = new ArrayList<>();
        try {
            String[] id = idList.split(",");
            for(String i : id){
                if(i != null){
                    list.add(i);
                }
            }
            userLoginMapper.deleteUserLoginByMobile(list);
            userMapper.deleteByUserId(list);
            return Result.success();
        } catch (SQLException e) {
            log.error("删除账户信息异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result updateUserById(User user) {
        try {
             userMapper.updateUserById(user);
             return Result.success();
        } catch (SQLException e) {
            log.error("账户信息修改异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }
}
