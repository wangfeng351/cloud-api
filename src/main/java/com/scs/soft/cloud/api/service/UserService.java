package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.domain.dto.PageDto;
import com.scs.soft.cloud.api.domain.vo.UserVo;

import java.io.File;
import java.util.List;
import java.util.Map;

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

    /**
     * 模糊分页查询
     * @param pageDto
     * @return
     */
    Result getUserBy(PageDto pageDto);

    /**
     * 查询所有用户
     * @return
     */
    List<Map<String, Object>> selectAllUser1(PageDto pageDto);
}
