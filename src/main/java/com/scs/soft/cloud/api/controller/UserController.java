package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.domain.dto.PageDto;
import com.scs.soft.cloud.api.domain.dto.UserDto;
import com.scs.soft.cloud.api.entity.User;
import com.scs.soft.cloud.api.service.UserService;
import com.scs.soft.cloud.api.util.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
@RestController
@Api(value = "UserController", tags = "账户接口")
@RequestMapping(value = "/api")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping(value = "/p/user")
    @ApiOperation(value = "批量导入用户信息")
    Result uploadUserInformation(@RequestParam("file") MultipartFile file){
        File file1 = UploadUtil.fileConversion(file);
        return userService.ExportUserInformation(file1);
    }

    @PutMapping(value = "/u/user")
    @ApiOperation(value = "修改账户信息")
    Result updateUserById(@RequestBody User user){
        return userService.updateUserById(user);
    }

    @GetMapping(value = "/s/user")
    @ApiOperation(value = "查询所有账户信息")
    Result selectAllUser(@RequestBody PageDto pageDto){
        return userService.selectAllUser(pageDto);
    }

    @DeleteMapping(value = "/d/user")
    @ApiOperation(value = "批量删除账户信息")
    Result deleteUserById(@RequestBody UserDto userDto){
        return userService.deleteUserById(userDto.getMobileList());

    }
}
