package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void batchInsertUser() throws IOException, ParseException {
        File file = new File("D:\\test.xlsx");
        List<User> list;
       /* int position = file.getName().lastIndexOf(".");
        String ffx = file.getName().substring(position + 1);
        System.out.println(ffx);
        list = ImportDataUtil.readExcel(file);
        *//*Result rs = userService.ExportUserInformation(file);*//*
        System.out.println(list.size());*/
       Result rs = userService.ExportUserInformation(file);
        System.out.println(rs);
    }
}