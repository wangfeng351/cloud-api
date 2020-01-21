package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.domain.dto.PageDto;
import com.scs.soft.cloud.api.domain.vo.UserVo;

import java.io.File;

/**
 * @author wf
 * @create 20202020/1/20
 * @description TODO
 */
public interface UserService {

    /**
     * 导入用户信息
     * @param
     * @return
     */
    Result ExportUserInformation(File file);

    /**
     * 查询所有用户
     * @return
     */
    Result selectAllUser(PageDto pageDto);

    /**
     * 删除用户
     * @return
     */
    Result deleteUserById(String mobileList);

    /**
     * 修改账户信息
     * @return
     */
    Result updateUserById(UserVo userVo);

    /**
     * 新增账户
     * @param userVo
     * @return
     */
    Result insertUser(UserVo userVo);
}
