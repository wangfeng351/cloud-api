package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.common.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author wf
 * @create 2020/1/31
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class ResourceServiceTest {
    @Resource
    private ResourceService resourceService;

    @Test
    void getResourceByCreateTime() {
       /* String time = "2020-01-01,2020-01-31";
        Result mapList = resourceService.getResourceByCreateTime(time);
        System.out.println(mapList);*/
       Result rs = resourceService.selectAllResource();
        System.out.println(rs);
    }
}