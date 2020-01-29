package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.domain.dto.UserDto;
import com.scs.soft.cloud.api.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
@RestController
@Api(value = "ResourceController", tags = "资源管理接口")
@RequestMapping(value = "/api")
public class ResourceController {
    @Resource
    private ResourceService resourceService;

    @GetMapping(value = "/g/resource")
    @ApiOperation(value = "获取所有资源")
    Result selectAllResource(){
        return resourceService.selectAllResource();
    }

    @DeleteMapping(value = "/d/resource")
    @ApiOperation(value = "根据id删除资源")
    Result deleteResourceById(@RequestBody UserDto userDto){
        return resourceService.deleteResourceById(userDto.getFieldList());
    }
}
