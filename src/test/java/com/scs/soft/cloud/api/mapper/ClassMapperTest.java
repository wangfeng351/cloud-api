package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.domain.vo.ClassVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/28
 * @description TODO
 */
@SpringBootTest(classes = CloudApiApplication.class)
class ClassMapperTest {
    @Resource
    private ClassMapper classMapper;
    @Resource
    private UserMapper userMapper;

    @Test
    void selectAllClass() throws SQLException {
        System.out.println(classMapper.selectAllClass().size());
    }


    @Test
    void getClassBy() throws SQLException {
        QueryDto queryDto = QueryDto.builder().field("王").build();
        List<Map<String,Object>> maps = classMapper.getClassBy(queryDto);
        System.out.println(maps);
    }

    @Test
    void updateClassById() throws SQLException {
        ClassVo classVo = ClassVo.builder().classType("哈哈").name("嘎嘎").status(false)
                      .school("南工院").faculty("艺术院").id(5).build();
        classMapper.updateClassById(classVo);
    }

    @Test
    void deleteClassById() {

    }

    @Test
    void deleteClassByCreatorId() {
    }

    @Test
    void testDeleteClassByCreatorId() throws SQLException {
        List<Integer> list = new ArrayList<>();
        QueryDto queryDto = QueryDto.builder().field("18361649527").build();
        String id = userMapper.getUserById(queryDto).get("id").toString();
        list.add(Integer.parseInt(id));
        System.out.println(Integer.parseInt(id));
     classMapper.deleteClassByCreatorId(list);
    }
}