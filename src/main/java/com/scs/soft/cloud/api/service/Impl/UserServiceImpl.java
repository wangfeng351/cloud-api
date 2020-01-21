package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.common.ResultCode;
import com.scs.soft.cloud.api.domain.dto.PageDto;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.domain.vo.UserVo;
import com.scs.soft.cloud.api.entity.User;
import com.scs.soft.cloud.api.entity.UserLogin;
import com.scs.soft.cloud.api.entity.UserRole;
import com.scs.soft.cloud.api.mapper.*;
import com.scs.soft.cloud.api.service.UserService;
import com.scs.soft.cloud.api.util.ImportDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Result ExportUserInformation(File file) {
        List<User> users = new ArrayList<>();
        List<UserLogin> userLogins = new ArrayList<>();
        List<UserRole> userRoles = new ArrayList<>();
        int position = file.getName().lastIndexOf(".");
        String ffx = file.getName().substring(position + 1);
        try {
            if(ffx.equals("xls")){
                users = ImportDataUtil.readExcel(file);
            }else if(ffx.equals("xlsx")) {
                users = ImportDataUtil.readExcel2(file);
            }
            commonMapper.alert("t_user_role");
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
            userMapper.batchInsert(users);

            for(User user : users){
                QueryDto queryDto = QueryDto.builder().field(user.getMobile()).build();
                Map<String, Object> map= userMapper.getUserById(queryDto);
                UserRole userRole = UserRole.builder().userId(Integer.parseInt(map.get("id").toString()))
                                 .roleId(1).build();
                userRoles.add(userRole);
            }
            userRoleMapper.batchInsertUserRole(userRoles);
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
                map = userRoleMapper.getUserRoleById(Integer.parseInt(user.get("id").toString()));
                map = roleMapper.getRoleById(Integer.parseInt(map.get("role_id").toString()));
                user.put("roleName",map.get("name"));
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
        List<String> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();

        try {
            String[] id = idList.split(",");
            for(String i : id){
                if(i != null){
                    list.add(i);
                    QueryDto queryDto = QueryDto.builder().field(i).build();
                    list1.add(Integer.parseInt(userMapper.getUserById(queryDto).get("id").toString()));
                }
            }
            userRoleMapper.deleteUserRoleById(list1);
            userLoginMapper.deleteUserLoginByMobile(list);
            userMapper.deleteByUserId(list);
            return Result.success();
        } catch (SQLException e) {
            log.error("删除账户信息异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result updateUserById(UserVo userVo) {
        Map<String, Object> map;
        try {
            QueryDto queryDto = QueryDto.builder().id(userVo.getId()).build();
             map = userMapper.getUserById(queryDto);
             userVo.setMobile(map.get("mobile").toString());
             userMapper.updateUserById(userVo);
             userLoginMapper.updateStatusByMobile(userVo);
             userRoleMapper.updateUserRoleByUserId(userVo);
             return Result.success();
        } catch (SQLException e) {
            log.error("账户信息修改异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result insertUser(UserVo userVo) {
        List<UserLogin> userLogins = new ArrayList<>();
        List<UserRole> userRoles = new ArrayList<>();
        Date current_date = new Date();
        try {
            commonMapper.alert("t_user_role");
            commonMapper.alert("t_user");
            commonMapper.alert("t_user_login");
            User user = User.builder().mobile(userVo.getMobile()).name(userVo.getName())
                       .gender(userVo.getGender()).jobNumber(userVo.getJobNumber())
                       .email(userVo.getEmail()).faculty(userVo.getFaculty())
                       .school(userVo.getSchool()).resourceNumber(0).charisma(0)
                       .experience(0).createClassNumber(0).joinClassNumber(0).activityNumber(0)
                       .profession("学生").nickname(userVo.getMobile()+"_用户").avatar("http")
                       .createTime(LocalDateTime.now()).birthday(current_date).build();
            userMapper.insertUser(user);
            UserLogin userLogin = UserLogin.builder().code("123456").password("123456").status(true)
                       .mobile(userVo.getMobile()).build();
            userLogins.add(userLogin);
            QueryDto queryDto = QueryDto.builder().field(userVo.getMobile()).build();
            Map<String, Object> map = userMapper.getUserById(queryDto);
            UserRole userRole = UserRole.builder().roleId(userVo.getRoleId())
                    .userId(Integer.parseInt(map.get("id").toString())).build();
            userRoles.add(userRole);
            userRoleMapper.batchInsertUserRole(userRoles);
            userLoginMapper.insertUserLoginInformation(userLogins);
            return Result.success();
        } catch (SQLException e) {
            log.error("用户新增异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }
}
