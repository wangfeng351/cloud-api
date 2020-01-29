package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class ResourceMapperTest {
    @Resource
    private ResourceMapper resourceMapper;

    @Test
    void getResource() throws SQLException {
        List<Map<String, Object>> map = resourceMapper.getResource();
        System.out.println(map);
    }

    @Test
    void deleteResourceById() {
    }
}