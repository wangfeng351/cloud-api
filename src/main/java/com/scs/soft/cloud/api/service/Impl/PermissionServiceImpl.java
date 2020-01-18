package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.common.ResultCode;
import com.scs.soft.cloud.api.entity.Permission;
import com.scs.soft.cloud.api.entity.RolePermission;
import com.scs.soft.cloud.api.mapper.CommonMapper;
import com.scs.soft.cloud.api.mapper.PermissionMapper;
import com.scs.soft.cloud.api.mapper.RolePermissionMapper;
import com.scs.soft.cloud.api.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private CommonMapper commonMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Result insertPermission(Permission permission) {
        try {
            commonMapper.alert("t_permission");
            permissionMapper.insertPermission(permission);
            return Result.success();
        } catch (SQLException e) {
            log.error("权限新增失败");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result selectPermission() {
        List<Map<String, Object>> permissions = null;
        try {
            permissions = permissionMapper.getTopMenuPermission();
        } catch (SQLException e) {
            log.error("获取所有权限异常");
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
        if(permissions != null){
            return Result.success(permissions);
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    @Override
    public Result deletePermissionById(int id) {
        try {
            RolePermission rolePermission = RolePermission.builder().permissionId(id).build();
            permissionMapper.deletePermissionById(id);
            rolePermissionMapper.deleteRolePermissionBy(rolePermission);
            return Result.success();
        } catch (SQLException e) {
            log.error("删除权限");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Result updatePermissionById(Permission permission) {
        try {
            permissionMapper.updatePermissionById(permission);
            return Result.success();
        } catch (SQLException e) {
            log.error("权限更新异常");
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @Override
    public Result getPermissionByName(Permission permission) {
        List<Permission> permissions;
        try {
            permissions = permissionMapper.getPermissionByName(permission);
        } catch (SQLException e) {
            log.error("权限模糊查询异常");
            return Result.failure(ResultCode.DATABASE_ERROR);
        }
        if(permission != null){
            return Result.success(permissions);
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
}
