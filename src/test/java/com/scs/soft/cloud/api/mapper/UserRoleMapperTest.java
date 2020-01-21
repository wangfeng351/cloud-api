package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wf
 * @create 2020/1/21
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class UserRoleMapperTest {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Test
    void batchInsertUserRole() {
    }

    @Test
    void getUserRoleById() throws SQLException {

        System.out.println( userRoleMapper.getUserRoleById(1).get("roleName").toString());
    }

    @Test
    void deleteUserRoleById() throws SQLException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        userRoleMapper.deleteUserRoleById(list);
    }

    @Test
    void updateUserRoleByUserId() {
    }
}