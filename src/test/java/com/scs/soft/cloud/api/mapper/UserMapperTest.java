package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.domain.dto.PageDto;
import com.scs.soft.cloud.api.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 20202020/1/20
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void insertUser() throws SQLException {
        List<User> users = new ArrayList<User>();
        for(int i = 0; i < 4; i++) {
            User user = User.builder().activityNumber(1).avatar("123123").email("12312").mobile(String.valueOf(i)).build();
            users.add(user);
        }
        userMapper.insertUser(users);
    }

    @Test
    void selectUser() throws SQLException {
        PageDto pageDto = PageDto.builder().pageSize(1).currentPage(1).build();
        List<Map<String, Object>> list = userMapper.selectUser(pageDto);
        for(Map<String, Object> map : list){
            System.out.println(map);
        }
    }

    @Test
    void deleteByUserId() throws SQLException {

    }

    @Test
    void updateUserById() throws SQLException {
        /*User user1 = userMapper.getUserById(10);
        user1.setName("陶永新");
        user1.setGender("女");
        userMapper.updateUserById(user1);*/
        String id = "1,2, 3,4";
        String[] id1= id.split(",");
        for(String i : id1){
            System.out.println(i);
        }
    }
}