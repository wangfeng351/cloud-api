package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.common.Result;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
public interface UserLoginService {

    /**
     * 重置用户登录密码
     * @param mobiles
     * @return
     */
    Result updateUserLoginPasswordByMobile(String mobiles);

}
