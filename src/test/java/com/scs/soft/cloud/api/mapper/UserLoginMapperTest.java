package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.entity.UserLogin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class UserLoginMapperTest {
    @Resource
    private UserLoginMapper userLoginMapper;

    @Test
    void getUserLoginByMobile() throws SQLException {
        System.out.println(userLoginMapper.getUserLoginByMobile("14752191367"));
    }

    @Test
    void updateUserLoginByMobile() throws SQLException {
        List<String> list = new ArrayList<>();
        list.add("14752191369");
        list.add("14752191367");
        userLoginMapper.updateUserLoginByMobile(list,"123456");
    }

    @Test
    void insertUserLoginInformation() throws SQLException {
        List<UserLogin> userLogins = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            UserLogin userLogin = UserLogin.builder().mobile(String.valueOf(i)).status(true)
                    .password("123456").build();
            userLogins.add(userLogin);
        }
        userLoginMapper.insertUserLoginInformation(userLogins);
    }

    @Test
    void deleteUserLoginByMobile() throws SQLException {
        /*List<String> list = new ArrayList<>();
        list.add("14752191369");
        list.add("147521");
        userLoginMapper.deleteUserLoginByMobile(list);*/
        String mobile = "14752191369,123146";
        String[] strings = mobile.split(",");
        for(String i : strings){
            System.out.println(i);
        }
    }
}