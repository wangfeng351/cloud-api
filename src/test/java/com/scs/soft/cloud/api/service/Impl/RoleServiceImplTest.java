package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.entity.Role;
import com.scs.soft.cloud.api.mapper.RoleMapper;
import com.scs.soft.cloud.api.service.RoleService;
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
class RoleServiceImplTest {
    @Resource
    private RoleService roleService;

    @Test
    void selectRole() {
    }

    @Test
    void deleteRoleById() throws SQLException {
        roleService.deleteRoleById(2);
    }

    @Test
    void insertRole() throws SQLException {
        Role role = Role.builder().name("学生").code(1003).build();
        roleService.insertRole(role);
    }

    @Test
    void updateRole() {
    }
}