package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.domain.dto.UserDto;
import com.scs.soft.cloud.api.entity.Permission;
import com.scs.soft.cloud.api.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@RestController
@Api(value = "PermissionController",tags = {"权限接口"})
@RequestMapping(value = "/api")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "新增权限")
    @PostMapping(value = "/p/pm")
    Result insertPermission(@RequestBody Permission permission){
        return permissionService.insertPermission(permission);
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    Result selectPermission(){
        return permissionService.selectPermission();
    }

    @ApiOperation(value = "删除指定菜单")
    @DeleteMapping(value = "/d/pm/{id}")
    Result deletePermissionById(@PathVariable int id){
        return permissionService.deletePermissionById(id);
    }

    @ApiOperation(value = "修改权限内容")
    @PutMapping(value = "/u/pm")
    Result updatePermissionById(@RequestBody Permission permission){
        return permissionService.updatePermissionById(permission);
    }

    @ApiOperation(value = "模糊查询权限")
    @PostMapping(value = "/g/b/pm")
    Result getPermissionByName(@RequestBody UserDto userDto){
        return permissionService.getPermissionByName(userDto.getFieldList());
    }
}
