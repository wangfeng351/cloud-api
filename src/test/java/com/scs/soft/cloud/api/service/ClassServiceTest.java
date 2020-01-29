package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.CloudApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wf
 * @create 2020/1/28
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class ClassServiceTest {
    @Resource
    private ClassService classService;

    @Test
    void selectAll() {
        System.out.println(classService.selectAll());
    }

    @Test
    void deleteClassById() {
    }

    @Test
    void updateClassById() {
    }
}