package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.entity.Permission;
import com.scs.soft.cloud.api.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@RestController
@Api(value = "PermissionController",tags = {"权限接口"})
@RequestMapping(value = "/api/pm")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "新增权限")
    @PostMapping(value = "/post")
    Result insertPermission(@RequestBody Permission permission){
        return permissionService.insertPermission(permission);
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    Result selectPermission(){
        return permissionService.selectPermission();
    }

    @ApiOperation(value = "删除指定菜单")
    @DeleteMapping(value = "/d/{id}")
    Result deletePermissionById(@PathVariable int id){
        return permissionService.deletePermission(id);
    }
}
