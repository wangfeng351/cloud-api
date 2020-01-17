package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class RoleMapperTest {
    @Resource
    private RoleMapper roleMapper;

    @Test
    void selectRole() throws SQLException {
        System.out.println(roleMapper.selectRole());
    }

    @Test
    void testSelectRole() {
    }

    @Test
    void updateRole() {
    }

    @Test
    void deleteRole() {

    }

    @Test
    void insertRole() throws SQLException {
        Role role = Role.builder().name("学生").code(1003).build();
        roleMapper.insertRole(role);
    }
}