package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.entity.Role;
import com.scs.soft.cloud.api.service.RoleService;
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
@RequestMapping(value = "/api")
@Api(value = "RoleController", tags = {"角色接口"})
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 查询所有角色信息
     * @return
     */
    @GetMapping(value = "/g")
    @ApiOperation(value = "查询所有角色")
    Result selectRole(){
        return roleService.selectRole();
    }

    /**
     * 新增用户信息
     * @param role
     * @return
     */
    @PostMapping(value = "/p/re")
    @ApiOperation(value = "新增角色")
    Result insertRole(@RequestBody Role role){
        System.out.println(role);
        return roleService.insertRole(role);
    }

    /**
     * 修改用户信息
     * @param role
     * @return
     */
    @PutMapping(value = "/u/re")
    @ApiOperation(value = "修改角色信息")
    Result updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping(value = "/d/re/{id}")
    @ApiOperation(value = "删除角色")
    Result deleteRole(@PathVariable int id){
        return roleService.deleteRoleById(id);
    }
}
