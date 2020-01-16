package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.entity.Permission;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
public interface PermissionService {
    /**
     * 新增权限
     * @param permission
     * @return
     */
    Result insertPermission(Permission permission);

    /**
     * 得到所有权限
     * @return
     */
    Result selectPermission();

    /**
     * 根据id删除权限
     * @return
     */
    Result deletePermission(int id);

}
