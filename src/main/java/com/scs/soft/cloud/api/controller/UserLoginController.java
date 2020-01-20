package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.domain.dto.UserDto;
import com.scs.soft.cloud.api.entity.UserLogin;
import com.scs.soft.cloud.api.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
@RestController
@Api(value = "UserLoginController", tags = "用户登录")
@RequestMapping(value = "/api")
public class UserLoginController {
    @Resource
    private UserLoginService userLoginService;

    @PutMapping(value = "/u/password")
    @ApiOperation(value = "重置密码")
    Result resetPassword(@RequestBody UserDto userDto){
       return userLoginService.updateUserLoginPasswordByMobile(userDto.getMobileList());
    }

    @PutMapping(value = "/p/status")
    @ApiOperation(value = "修改账户状态")
    Result updateStatusByMobile(@RequestBody UserLogin userLogin){
        return userLoginService.updateStatusByMobile(userLogin);
    }
}
