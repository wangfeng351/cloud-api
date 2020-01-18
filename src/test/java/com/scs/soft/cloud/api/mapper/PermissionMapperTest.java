package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.entity.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class PermissionMapperTest {
    @Resource
    private PermissionMapper mapper;

    @Test
    void insertPermission() throws SQLException {
        Permission permission = Permission.builder().name("删除好友").type((short) 0).permissionCode("del_frend").
                                   parentId(0).routerUrl("www.baidu.com").icon("http.jklj.jpg").authorization(0).
                                   build();
        mapper.insertPermission(permission);
    }

    @Test
    void getTopMenuPermission() throws SQLException {
        List<Map<String, Object>> permissions = mapper.getTopMenuPermission();
        System.out.println(permissions);
    }

    @Test
    void deletePermissionById() throws SQLException {
        mapper.deletePermissionById(5);
    }

    @Test
    void updatePermissionById() throws SQLException {
        Permission permission = Permission.builder().id(9).name("修改资料").type((short) 1).permissionCode("update_data").build();
        mapper.updatePermissionById(permission);
    }

    @Test
    void selectPermission() throws SQLException {
        System.out.println(mapper.getPermissionById(9));
    }

    @Test
    void getTopPermission() throws SQLException {
    }

    @Test
    void getPermissionByName() throws SQLException {
        Permission queryDto = Permission.builder().name("资源").build();
        System.out.println( mapper.getPermissionByName(queryDto));
    }
}