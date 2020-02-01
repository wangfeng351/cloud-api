package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.domain.dto.PageDto;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.mapper.*;
import com.scs.soft.cloud.api.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
@Service
@Slf4j
public class ExportServiceImpl implements ExportService {
    @Resource
    private UserLoginMapper userLoginMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private ResourceMapper resourceMapper;

    @Override
    public List<Map<String, Object>> getResourceInformation() {
        /*不能定义为null*/
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            maps = resourceMapper.getResource();
            System.out.println(maps);
            for(Map<String, Object> map1 : maps){
                QueryDto queryDto = QueryDto.builder()
                        .id(Integer.parseInt(map1.get("creator_id").toString())).build();
                map = userMapper.getUserById(queryDto);
                if(map != null){
                    String nickname = map.get("nickname").toString();
                    if(nickname != null) {
                        map1.put("creatorName", nickname);
                    }
                } else {
                    map1.put("creatorName", "");
                }

            }
        } catch (SQLException e) {
            log.error("获取资源数据异常");
        }
        return maps;
    }

    @Override
    public List<Map<String, Object>> getUserInformation() {
        List<Map<String, Object>> users = new ArrayList<>();
        Map<String, Object> map;
        try {
            int n = userMapper.selectUser1().size();
            PageDto pageDto = PageDto.builder().pageSize(n).currentPage(1).build();
            users = userMapper.selectUser(pageDto);
            int len = users.size();
            for (Map<String, Object> user : users) {
                map = userRoleMapper.getUserRoleById(Integer.parseInt(user.get("id").toString()));
                map = roleMapper.getRoleById(Integer.parseInt(map.get("role_id").toString()));
                user.put("roleName",map.get("name"));
                map = userLoginMapper.getUserLoginByMobile(user.get("mobile").toString());
                /*map报空异常*/
                if(map != null){
                    user.put("status", map.get("status"));
                }
            }
        } catch (SQLException e) {
            log.error("查询所有用户信息异常");
        }
        return users;
    }
}
