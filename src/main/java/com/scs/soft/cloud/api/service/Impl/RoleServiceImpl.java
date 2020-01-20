package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.common.ResultCode;
import com.scs.soft.cloud.api.entity.Role;
import com.scs.soft.cloud.api.entity.RolePermission;
import com.scs.soft.cloud.api.mapper.CommonMapper;
import com.scs.soft.cloud.api.mapper.RoleMapper;
import com.scs.soft.cloud.api.mapper.RolePermissionMapper;
import com.scs.soft.cloud.api.service.RoleService;
import com.scs.soft.cloud.api.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private CommonMapper commonMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Result selectRole(){
        List<Map<String, Object>> maps;
        try {
            maps = roleMapper.selectRole();
        }catch (SQLException e){
            log.error("查询所有异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
        if(maps != null){
            return Result.success(maps);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @Override
    public Result deleteRoleById(int id) {
        try {
            RolePermission rolePermission = RolePermission.builder().roleId(id).build();
            roleMapper.deleteRole(id);
            rolePermissionMapper.deleteRolePermissionBy(rolePermission);
            return Result.success();
        } catch (SQLException e) {
            log.error("删除角色异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result insertRole(Role role) {
        try {
            commonMapper.alert("t_role");
            role.setCode(StringUtil.getRoleCode());
            roleMapper.insertRole(role);
            return Result.success();
        } catch (SQLException e) {
            log.error("新增角色信息");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result updateRole(Role role) {
        try {
            roleMapper.updateRole(role);
            return Result.success();
        } catch (SQLException e) {
            log.error("修改角色信息异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }
}
