package com.scs.soft.cloud.api.util;

import com.scs.soft.cloud.api.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
public class ImportDataUtil {

    public static List<User> readExcel2(File file) {
        List<User> list = new ArrayList<>();
        try {
            Workbook workbook= WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);//或者根据表名称读取：sheet=workbook.getSheet("表1");
             /*遍历数据时可以使用迭代器Iterator,forEach,也可以使用for循环，*/
              int first = sheet.getFirstRowNum();//获取第一行的行号下标
              int end = sheet.getLastRowNum();//获取最后一行的行号下标,遍历时需要加1
              for(int i=first+1;i<sheet.getLastRowNum()+1;i++){
              Row row = sheet.getRow(i);
                  row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                  row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                  row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                  row.getCell(3).setCellType(Cell.CELL_TYPE_FORMULA);
                  row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                  row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                  row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                  row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                  User user = User.builder().jobNumber(row.getCell(0).getStringCellValue())
                          .name(row.getCell(1).getStringCellValue())
                          .gender(row.getCell(2).getStringCellValue())
                          .birthday(row.getCell(3).getDateCellValue())
                          .mobile(row.getCell(4).getStringCellValue())
                          .email(row.getCell(5).getStringCellValue())
                          .faculty(row.getCell(6).getStringCellValue())
                          .school(row.getCell(7).getStringCellValue())
                          .avatar("http:www")
                          .activityNumber(0)
                          .joinClassNumber(0)
                          .createClassNumber(0)
                          .experience(0)
                          .charisma(0)
                          .resourceNumber(0)
                          .nickname(row.getCell(4).getStringCellValue()+"_用户")
                          .profession("老师")
                          .createTime(LocalDateTime.now())
                          .build();
                  list.add(user);
          }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<User> readExcel(File file) throws IOException, ParseException {
        List<User> list = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheet("Sheet1");

        int lastRowIndex = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowIndex; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3).setCellType(Cell.CELL_TYPE_FORMULA);
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
            User user = User.builder().jobNumber(row.getCell(0).getStringCellValue())
                    .name(row.getCell(1).getStringCellValue())
                    .gender(row.getCell(2).getStringCellValue())
                    .birthday(row.getCell(3).getDateCellValue())
                    .mobile(row.getCell(4).getStringCellValue())
                    .email(row.getCell(5).getStringCellValue())
                    .faculty(row.getCell(6).getStringCellValue())
                    .school(row.getCell(7).getStringCellValue())
                    .avatar("http:www")
                    .activityNumber(0)
                    .joinClassNumber(0)
                    .createClassNumber(0)
                    .experience(0)
                    .charisma(0)
                    .resourceNumber(0)
                    .nickname(row.getCell(4).getStringCellValue() + "_用户")
                    .profession("老师")
                    .createTime(LocalDateTime.now())
                    .build();
            list.add(user);
        }
        return list;
    }

    public static void createExcel(File file, List<Map<String, Object>> maps) throws IOException {
        String filePath = file + "账户信息表.xls";
        OutputStream outputStream = new FileOutputStream(filePath);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("教工号");
        row.createCell(1).setCellValue("用户名");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("角色");
        row.createCell(4).setCellValue("电话");
        row.createCell(5).setCellValue("邮箱");
        row.createCell(6).setCellValue("学校");
        row.createCell(7).setCellValue("院系");
        row.setHeightInPoints(20); // 设置行的高度

        int i = -1;
        int len = maps.size();
        while (++i < len){
            HSSFRow row1 = sheet.createRow(i+1);
            row1.createCell(0).setCellValue(maps.get(i).get("job_number").toString());
            row1.createCell(1).setCellValue(maps.get(i).get("name").toString());
            row1.createCell(2).setCellValue(maps.get(i).get("gender").toString());
            row1.createCell(3).setCellValue(maps.get(i).get("roleName").toString());
            row1.createCell(4).setCellValue(maps.get(i).get("mobile").toString());
            row1.createCell(5).setCellValue(maps.get(i).get("email").toString());
            row1.createCell(6).setCellValue(maps.get(i).get("school").toString());
            row1.createCell(7).setCellValue(maps.get(i).get("faculty").toString());
        }

        // 日期格式化
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        HSSFCreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle2.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        sheet.setColumnWidth(2, 20 * 256); // 设置列的宽度
        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();
    }

    public static void main(String[] args) throws SQLException {

    }
}
