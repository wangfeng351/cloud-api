package com.scs.soft.cloud.api.mapper;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.domain.dto.PageDto;
import com.scs.soft.cloud.api.domain.dto.QueryDto;
import com.scs.soft.cloud.api.domain.vo.UserVo;
import com.scs.soft.cloud.api.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        userMapper.batchInsert(users);
    }

    @Test
    void selectUser() throws SQLException {
        /*PageDto pageDto = PageDto.builder().pageSize(1).currentPage(1).build();
        List<Map<String, Object>> list = userMapper.selectUser(pageDto);
        for(Map<String, Object> map : list){
            System.out.println(map);
        }*/
        System.out.println(userMapper.selectUser1().size());
    }

    @Test
    void deleteByUserId() throws SQLException, ParseException {
        /*formatter = new SimpleDateFormat( "yyyy-MM-dd ");
        Date date = formatter.parse("2013-1-1");
        System.out.println(date);*/
    }

    @Test
    void updateUserById() throws SQLException {
        /*UserVo user1 = userMapper.getUserById(10);
        user1.setName("陶永新");
        user1.setGender("女");
        userMapper.updateUserById(user1);*/
        UserVo userVo = UserVo.builder().id(3).name("颜子皓").build();
        userMapper.updateUserById(userVo);

    }

    @Test
    void getUserById() throws SQLException {
        QueryDto queryDto = QueryDto.builder().field("17826012341").build();
        System.out.println(userMapper.getUserById(queryDto));
    }

    @Test
    void testInsertUser() throws SQLException {
        Date current_date = new Date();
        //设置日期格式化样式为：yyyy-MM-dd
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        User user = User.builder().birthday(current_date).mobile("123").createTime(LocalDateTime.now()).build();
        userMapper.insertUser(user);
    }

    @Test
    void getUserByMonth() throws SQLException {
        List<Map<String, Object>> map = userMapper.getUserByMonth("2020");
        System.out.println(map);
    }

    @Test
    void getUserSchool() throws SQLException {
        List<Map<String, Object>> map = userMapper.getUserSchool();
        System.out.println(map);
    }

    @Test
    void getUserBy() throws SQLException {
        PageDto pageDto = PageDto.builder().year("王").currentPage(1).pageSize(5).build();
        System.out.println(userMapper.getUserBy(pageDto));
    }
}