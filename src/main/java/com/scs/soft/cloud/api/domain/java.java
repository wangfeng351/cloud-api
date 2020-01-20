package com.scs.soft.cloud.api.domain;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * @author wf
 * @create 20202020/1/19
 * @description TODO
 */
public class java {

    public static void createExcel() throws IOException{
        // 获取桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = desktop + "/template.xls";

        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("订单号");
        row.createCell(2).setCellValue("下单时间");
        row.createCell(3).setCellValue("个数");
        row.createCell(4).setCellValue("单价");
        row.createCell(5).setCellValue("订单金额");
        row.setHeightInPoints(30); // 设置行的高度

        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("NO00001");

        // 日期格式化
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        HSSFCreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle2.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        sheet.setColumnWidth(2, 20 * 256); // 设置列的宽度

        HSSFCell cell2 = row1.createCell(2);
        cell2.setCellStyle(cellStyle2);

        row1.createCell(3).setCellValue(2);


        // 保留两位小数
        HSSFCellStyle cellStyle3 = workbook.createCellStyle();
        cellStyle3.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        HSSFCell cell4 = row1.createCell(4);
        cell4.setCellStyle(cellStyle3);
        cell4.setCellValue(29.5);


        // 货币格式化
        HSSFCellStyle cellStyle4 = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("华文行楷");
        font.setFontHeightInPoints((short)15);
        font.setColor(HSSFColor.RED.index);
        cellStyle4.setFont(font);

        HSSFCell cell5 = row1.createCell(5);
        cell5.setCellFormula("D2*E2");  // 设置计算公式

        // 获取计算公式的值
        HSSFFormulaEvaluator e = new HSSFFormulaEvaluator(workbook);
        cell5 = e.evaluateInCell(cell5);
        System.out.println(cell5.getNumericCellValue());


        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();
    }

    public static void readExcel() throws IOException{
        File filePath = new File("D:/template.xls");

        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheet("Sheet1");

        int lastRowIndex = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) { break; }

            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                String cellValue = row.getCell(j).getStringCellValue();
                System.out.println(cellValue);
            }
        }


        bufferedInputStream.close();
    }

    public static void createXlsx() throws IOException {
        //创建一个工作薄
        XSSFWorkbook wb = new XSSFWorkbook();
//创建一个电子表格createSheet
        XSSFSheet sheet = wb.createSheet("创建一个带名字的电子表格");
//XSSFSheet sheet = wb.createSheet();//调用默认构造创建电子表格
//创建第三行,行和列都是从0开始计算的
        XSSFRow row = sheet.createRow((short) 2);
        row.setHeightInPoints(30);//设置行高30
//1-8行的列宽为256像素 15在这里表示一个像素
        for (int i = 0; i < 8; i++) {
            //column width is set in units of 1/256th of a character width
            sheet.setColumnWidth(i, 256 * 15);
        }
        FileOutputStream fileOut = new FileOutputStream("D:/align.xlsx");
        wb.write(fileOut);
        fileOut.close();

    }

    public static void readExcel2(File excelFile) {
        try {
            Workbook workbook= WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheetAt(0);//或者根据表名称读取：sheet=workbook.getSheet("表1");

            /**
             * 遍历数据时可以使用迭代器Iterator,forEach,也可以使用for循环，
             * sheet.getFirstRowNum();//获取第一行的行号下标
             * sheet.getLastRowNum();//获取最后一行的行号下标,遍历时需要加1
             * row.getFirstCellNum();//获取某行的第一个单元格的下标
             * row.getLastCellNum();//获取某行的最后一个单元格数量
             */
//          for(int i=sheet.getFirstRowNum();i<sheet.getLastRowNum()+1;i++){
//              Row row = sheet.getRow(i);
//              for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++){
//                  Cell cell = row.getCell(j);
//              }
//          }

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {//遍历每一行的数据
                Row row = iterator.next();
                String cell  = row.getCell(0).getStringCellValue();
                Iterator<Cell> cellIterator = row.cellIterator();

               /* while (cellIterator.hasNext()) {//遍历某行的所有单元格
                    Cell cell =  cellIterator.next();
                   cell.setCellType(Cell.CELL_TYPE_STRING);
                    System.out.println(cell);
                   *//* switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING://String类型
                            System.out.println("String："+cell.getStringCellValue());
                           *//**//* try {
                                System.out.println(formatSS.parse(cell.getStringCellValue()));//读取字符串格式的日期
                            } catch (Exception e) {
                            }*//**//*
                            break;
                        case Cell.CELL_TYPE_BOOLEAN://Booble类型
                            System.out.println("Boolean:"+cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC://Double类型
                            if (DateUtil.isCellDateFormatted(cell)||cell.getCellStyle().getDataFormat() == 164) {//读取Double类型的日期
                                System.out.println("Data:————————》"+formatSS.format(cell.getDateCellValue()));
                            } else {
                                System.out.println("Double:"+cell.getNumericCellValue());
                            }
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            System.out.println("formula:"+formatSS.format(cell.getDateCellValue()));
                            break;
                        case Cell.CELL_TYPE_BLANK://空白的单元格
                            System.out.println("Blank");
                            break;
                        default:
                            break;
                    }*//*
                }*/
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        createExcel();
        File file = new File("D:\\test.xlsx");
        readExcel2(file);
    }

    private static String getStringVal(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() ? "true" : "false";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC:
                String cellvalue = "";
                short format = cell.getCellStyle().getDataFormat();

                if(format == 14 || format == 31 || format == 57 || format == 58){   //excel中的时间格式
                    Date date = cell.getDateCellValue();
                    cellvalue = DateFormatUtils.format(date, "yyyy-MM-dd");
                }
                // 判断当前的cell是否为 Date
                else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue= formater.format(date);
                } else { // 如果是纯数字
                    // 取得当前Cell的数值
                    cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                return  cellvalue;
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }
}
