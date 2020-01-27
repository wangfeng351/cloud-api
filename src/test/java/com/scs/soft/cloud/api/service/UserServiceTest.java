package com.scs.soft.cloud.api.service;

import com.scs.soft.cloud.api.CloudApiApplication;
import com.scs.soft.cloud.api.common.Result;
import com.scs.soft.cloud.api.domain.vo.UserVo;
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
        list = ImportDataUtil.readExcel(file);*/
        Result rs = userService.ExportUserInformation(file);
       /*Result rs = userService.ExportUserInformation(file);
        System.out.println(rs);*/
    }

    @Test
    void deleteUserById() {
        String mobile = "14752191361";
        userService.deleteUserById(mobile);
    }

    @Test
    void updateUserById() throws ParseException {
        UserVo userVo = UserVo.builder().mobile("14752191361").gender("男").name("王锋")
                      .status(false).jobNumber("1802333111").email("1244353765").roleId(2).build();
        userService.insertUser(userVo);
       /* LocalDate date = LocalDate.of(2011,1,1);
        System.out.println(date);
        String string = "2016-10-24";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.parse(string));*/
    }

    @Test
    void selectAllUser() throws IOException {
       /* PageDto pageDto = PageDto.builder().currentPage(1).pageSize(5).year("2020").build();
        Result rs = userService.selectAllUser(pageDto);
        System.out.println(rs);*/

    }
}