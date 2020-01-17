package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.entity.RolePermission;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
public interface RolePermissionService {

    /**
     * 查询指定角色权限
     * @param roleId
     * @return
     */
    Result getRolePermissionByRoId(int roleId);

    /**
     * 删除权限
     * @param rolePermission
     * @return
     */
    Result deleteRolePermissionBy(RolePermission rolePermission);

    /**
     * 给角色分配权限
     * @param rolePermission
     * @return
     */
    Result insertRolePermission(RolePermission rolePermission);
}
