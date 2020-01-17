package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.entity.RolePermission;
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
class RolePermissionMapperTest {
    @Resource
    private RolePermissionMapper rp;

    @Test
    void deleteRolePermissionById() throws SQLException {
        RolePermission ro = RolePermission.builder().roleId(2).build();
        rp.deleteRolePermissionBy(ro);
    }

    @Test
    void getRolePermissionById() throws SQLException {
        System.out.println(rp.getRolePermissionById(2));
    }

    @Test
    void insertRolePermission() {
    }

    @Test
    void testDeleteRolePermissionById() {
    }
}