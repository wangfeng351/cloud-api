package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.entity.RolePermission;
import com.scs.soft.cloud.api.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@RestController
@Api(value = "RolePermissionController", tags = "角色权限接口")
@RequestMapping(value = "/api")
public class RolePermissionController {
    @Resource
    private RolePermissionService rolePermissionService;

    @GetMapping(value = "/g/rp/{roleId}")
    @ApiOperation(value = "角色权限查询")
    Result getRolePermissionByRoleId(@PathVariable int roleId){
        System.out.println(roleId);
        return rolePermissionService.getRolePermissionByRoId(roleId);
    }

    @DeleteMapping(value = "/d/rp")
    @ApiOperation(value = "角色权限映射删除")
    Result deletePermissionBy(@RequestBody RolePermission rolePermission){
        return rolePermissionService.deleteRolePermissionBy(rolePermission);
    }

    @PostMapping(value = "/p/rp")
    @ApiOperation(value = "角色分配权限")
    Result insertRolePermission(@RequestBody RolePermission rolePermission){
        return rolePermissionService.insertRolePermission(rolePermission);
    }
}
