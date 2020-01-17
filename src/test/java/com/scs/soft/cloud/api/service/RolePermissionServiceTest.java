package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.CloudApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class RolePermissionServiceTest {
    @Resource
    private RolePermissionService rolePermissionService;

    @Test
    void getRolePermissionByRoId() {
        System.out.println(rolePermissionService.getRolePermissionByRoId(2));
    }
}