package com.scs.soft.cloud.api.util;

import com.scs.soft.cloud.api.entity.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
