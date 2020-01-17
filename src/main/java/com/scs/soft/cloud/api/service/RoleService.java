package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.entity.Role;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
public interface RoleService {

    /**
     * 查询所有所有角色
     * @return
     */
    Result selectRole();

    /**
     * 根据id删除角色信息
     * @return
     * @param id
     */
    Result deleteRoleById(int id);

    /**
     * 新增角色信息
     * @param role
     * @return
     */
    Result insertRole(Role role);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    Result updateRole(Role role);
}
