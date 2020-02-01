package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Test
    void getResourceByCreateTime() throws SQLException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime l1 = LocalDateTime.parse("2020-01-01 00:00:00",df);
        LocalDateTime l2 = LocalDateTime.parse("2020-01-31 00:00:00", df);
        List<Map<String, Object>> maps = resourceMapper.getResourceByCreateTime("2020-01-01 00:00:00", "2020-01-31 00:00:00");
        System.out.println(maps);
       /* DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2017-09-28 17:07:05", df);
        System.out.println(localDateTime);*/
    }

    @Test
    void getResourceBy() throws SQLException {
        List<Map<String, Object>> map = resourceMapper.getResourceBy();
        System.out.println(map);
    }

    @Test
    void updateResource() {
    }

    @Test
    void deleteResourceByCreatorId() throws SQLException {
        List<Integer> id = new ArrayList<>();
        id.add(1);
        resourceMapper.deleteResourceByCreatorId(id);
    }
}