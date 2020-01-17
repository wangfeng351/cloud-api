package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.common.ResultCode;
import com.scs.soft.cloud.api.entity.Permission;
import com.scs.soft.cloud.api.entity.RolePermission;
import com.scs.soft.cloud.api.mapper.CommonMapper;
import com.scs.soft.cloud.api.mapper.RolePermissionMapper;
import com.scs.soft.cloud.api.service.RolePermissionService;
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
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private CommonMapper commonMapper;

    @Override
    public Result getRolePermissionByRoId(int roleId) {
        List<Map<String, Object>> maps;
        try {
            maps = rolePermissionMapper.getRolePermissionById(roleId);
            return Result.success(maps);
        } catch (SQLException e) {
            log.error("查询角色的权限");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result deleteRolePermissionBy(RolePermission rolePermission) {
        try {
            rolePermissionMapper.deleteRolePermissionBy(rolePermission);
            return Result.success();
        } catch (SQLException e) {
            log.error("删除角色异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result insertRolePermission(RolePermission rolePermission) {
        try {
            commonMapper.alert("t_role_permission");
            rolePermissionMapper.insertRolePermission(rolePermission);
            return Result.success();
        } catch (SQLException e) {
            log.error("角色权限异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }
}
