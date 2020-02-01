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
 * @create 2020/1/28
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class UserClassMapperTest {
    @Resource
    private UserClassMapper userClassMapper;

    @Test
    void deleteUserClassByUserId() throws SQLException {
       /* UserClass userClass = UserClass.builder().userId(3).id(5).build();
        userClassMapper.deleteUserClassBy(userClass);*/
       List<Integer> list = new ArrayList<>();
       list.add(1);
       userClassMapper.deleteUserClassByUserId(list);
    }

    @Test
    void deleteUserClassBy() {
    }

    @Test
    void getUserClassById() throws SQLException {
        System.out.println(userClassMapper.getUserClassById(5));
    }

}