package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.domain.dto.UserDto;
import com.scs.soft.cloud.api.domain.vo.ClassVo;
import com.scs.soft.cloud.api.service.ClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Timer;

/**
 * @author wf
 * @create 2020/1/28
 * @description TODO
 */
@RestController
@Api(value = "ClassController", tags = "班课管理接口")
@RequestMapping(value = "/api")
public class ClassController {
    @Resource
    private ClassService classService;

    @GetMapping(value = "/g/class")
    @ApiOperation(value = "获取所有班课信息接口")
    Result selectAllClass(){
        return classService.selectAll();
    }

    @PutMapping(value = "/p/class")
    @ApiOperation(value = "根据id修改班课信息")
    Result updateClassById(@RequestBody ClassVo classVo){
        return classService.updateClassById(classVo);
    }

    @DeleteMapping(value = "/d/class")
    @ApiOperation(value = "根据id删除班课信息")
    Result deleteClassById(@RequestBody UserDto userDto){
        return classService.deleteClassById(userDto.getFieldList());
    }

    @PostMapping(value = "/g/class/blur")
    @ApiOperation(value = "模糊查询")
    Result getClassBy(@RequestBody QueryDto queryDto){
        return classService.getUserClassBy(queryDto);
    }

    @GetMapping(value = "/g/c/b")
    @ApiOperation(value = "定时器")
    public void timerTest(){
        Timer timer = new Timer();
    }
}
