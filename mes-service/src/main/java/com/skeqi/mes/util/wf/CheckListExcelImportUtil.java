package com.skeqi.mes.util.wf;

import com.skeqi.mes.util.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lenovo
 * 质检清单导入解析工具类
 */
public class CheckListExcelImportUtil {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    /**
     * 解析文件的方法.
     * 导入清单excel
     *  @param inputStream 文件输入流，要解析的Excel文件输入流
     *  @param suffix 文件后缀名，xls或xlsx，代码决定使用什么方式解析zxcel.@param startRow从第几行开始读取数据.
     * @param importExcelType 区分解析清单还是解析清单详情.
     * @return List<Map<String, Object>>集合中的一个元素对应一行解析的数据.
     */
    public static List<Map<String, Object>> importExcel(InputStream inputStream,String suffix,Integer startRow,String importExcelType) throws Exception {
        List<Map<String, Object>> data = new ArrayList<>();
            //1.定义excel对象变量
            Workbook workbook = null;
            //2.判断后缀.决定使用的解析方式，决定创建的具体对象
            if (xls.equals(suffix)){
                //2003版
                workbook = new XSSFWorkbook(inputStream);
            }else if (xlsx.equals(suffix)){
                //2007
                workbook = new XSSFWorkbook(inputStream);
            }else {
                //未知内容
                throw new IOException(suffix + "不是excel文件");
            }
            //获取工作表
            Sheet sheetAt = workbook.getSheetAt(0);
            if (sheetAt==null){
                throw new Exception("文件内容为空");
            }
            //获取表格中总行数
            int countRowNum = sheetAt.getPhysicalNumberOfRows();

            //总行数大于startRow
            if (countRowNum<=startRow){
                throw new Exception("文件内容为空");
            }
            //循环读取
            Row row = null;
            Cell cell =null;
            for (int rowNum = startRow; rowNum <countRowNum; rowNum++) {
                row = sheetAt.getRow(rowNum);
                //获取当前行的第一列和最后一列的标记
                short firstCellNum = row.getFirstCellNum();
                short lastCellNum = row.getLastCellNum();
                if (firstCellNum!=lastCellNum&&lastCellNum!=0){
                    Map<String, Object> map = new HashMap<>();
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        String key = "";
                        //获取单元格
                        cell = row.getCell(cellNum);
                        //获取数据
                        String cellData = parseCell(cell, suffix);
                        //解析清单
                        if (importExcelType.equals("importChecklistExcel")){
                            switch (cellNum){
                                case 0:
                                    key = "ID";
                                    break;
                                case 1:
                                    key = "NAME";
                                    break;
                                case 2:
                                    key = "CODE";
                                    break;
                                case 3:
                                    key = "PRODUCE_TYPE";
                                    break;
                                case 4:
                                    key = "DT";
                                    break;
                                case 5:
                                    key = "TYPE";
                                    break;
                                default:
                                    break;
                            }
                            if (cell!=null){
                                if (cellNum==5){
                                    switch (cellData) {
                                        case "自检":
                                            cellData = "1";
                                            break;
                                        case "首检":
                                            cellData = "2";
                                            break;
                                        case "巡检":
                                            cellData = "3";
                                            break;
                                        case "尾检":
                                            cellData = "4";
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                        }else {
                        //解析清单详情
                            switch (cellNum){
                                case 0:
                                    key = "ID";
                                    break;
                                case 1:
                                    key = "DT";
                                    break;
                                case 2:
                                    key = "VERSIONS";
                                    break;
                                case 3:
                                    key = "START";
                                    break;
                                case 4:
                                    key = "CONTENT";
                                    break;
                                case 5:
                                    key = "CHECK_LIST_CODE";
                                    break;
                                default:
                                    break;
                            }
                            if (cell!=null) {
                                if (cellNum==3){
                                    switch (cellData) {
                                        case "启用":
                                            cellData = "1";
                                            break;
                                        default:
                                            cellData = "0";
                                            break;
                                    }
                                }
                            }
                        }
                        //数据存储
                        map.put(key,cellData);
                    }
                    data.add(map);
                }
            }
        return data;
    }

    /**
     * 解析单元格
     * @param cell
     * @return
     */
    private static String parseCell(Cell cell,String suffix) {
        String cellStr = null;
        if (xls.equals(suffix)){
            //2003版
            //判断单元格类型
            if (!StringUtils.isEmpty(cell)) {
                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_STRING:
                        //字符串单元格
                        cellStr = cell.getRichStringCellValue().toString();
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                        //空数据
                        cellStr = "";
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        //日期
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            SimpleDateFormat sdf = null;
                            //判断是具体时间还是日期
                            if (cell.getCellType() == HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd HH:mm")) {
                                //日期时间
                                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            } else if (cell.getCellType() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                                //时间
                                sdf = new SimpleDateFormat("HH:mm");
                            } else {
                                //日期
                                sdf = new SimpleDateFormat("yyyy-MM-dd");
                            }
                            Date dateCellValue = cell.getDateCellValue();
                            cellStr = sdf.format(dateCellValue);
                        } else {
                            //数字
                            double numericCellValue = cell.getNumericCellValue();
                            cellStr = "" + numericCellValue;
                        }
                        break;
                    default:
                        cellStr = "";
                        break;
                }
            }
        }else if (xlsx.equals(suffix)){
            //2007
            //判断单元格类型
            if (!StringUtils.isEmpty(cell)){
                switch (cell.getCellType()){
                    case XSSFCell.CELL_TYPE_STRING:
                        //字符串单元格
                        cellStr = cell.getRichStringCellValue().toString();
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        //空数据
                        cellStr = "";
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        //日期
                        if (HSSFDateUtil.isCellDateFormatted(cell)){
                            SimpleDateFormat sdf = null;
                            //判断是具体时间还是日期
                            if(cell.getCellType()==HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd HH:mm")){
                                //日期时间
                                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            }else if (cell.getCellType()==HSSFDataFormat.getBuiltinFormat("h:mm")){
                                //时间
                                sdf = new SimpleDateFormat("HH:mm");
                            }else {
                                //日期
                                sdf = new SimpleDateFormat("yyyy-MM-dd");
                            }
                            Date dateCellValue = cell.getDateCellValue();
                            cellStr = sdf.format(dateCellValue);
                        }else {
                            //数字
                            double numericCellValue = cell.getNumericCellValue();
                            //数学格式化工具
                            DecimalFormat decimalFormat = new DecimalFormat();
                            //查看单元格具体样式类型
                            String dataFormatString = cell.getCellStyle().getDataFormatString();
                            if (dataFormatString.equals("General")){
                                //定义格式化正则，使用具体有效数据进行格式化时只保留有效数据
                                decimalFormat.applyPattern("#");
                            }
                            cellStr = decimalFormat.format(numericCellValue);
                        }
                        break;
                    default:
                        cellStr = "";
                        break;
                }
            }

        }else {
            //未知内容
            return null;
        }
        return cellStr;
    }


    /**
     * 导出清单excel
     * @param maps
     * @return
     * @throws IOException
     */
    public static void exportExcelCheckList(List<Map<String, Object>> maps) throws IOException {
        String path = "C:\\Users\\Lenovo\\Documents\\";
        //创建一个薄
        Workbook workbook = new SXSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet();
        //写入数据
        for (int rowNum =0; rowNum < maps.size();rowNum++) {
            //当前行
            Row row = sheet.createRow(rowNum);
            //循环当前行
            for (int cellNum = 0; cellNum < maps.get(rowNum).size() ; cellNum++) {
                //获取当前单元格
                Cell cell = row.createCell(cellNum);
                switch (cellNum) {
                    case 0:
                        //设置值
                        cell.setCellValue(maps.get(rowNum).get("ID").toString());
                        break;
                    case 1:
                        //设置值
                        cell.setCellValue(maps.get(rowNum).get("NAME").toString());
                        break;
                    case 2:
                        //设置值
                        cell.setCellValue(maps.get(rowNum).get("CODE").toString());
                        break;
                    case 3:
                        //设置值
                        cell.setCellValue(maps.get(rowNum).get("PRODUCE_TYPE").toString());
                        break;
                    case 4:
                        //设置值
                        cell.setCellValue(maps.get(rowNum).get("DT").toString());
                        break;
                    case 5:
                        //设置值
                        if (rowNum==0){
                            cell.setCellValue(maps.get(rowNum).get("TYPE").toString());
                        }else {
                            Integer type = Integer.valueOf(maps.get(rowNum).get("TYPE").toString());
                            String typeName = "";
                            switch (type) {
                                case 1:
                                    typeName = "自检";
                                    break;
                                case 2:
                                    typeName = "首检";
                                    break;
                                case 3:
                                    typeName = "巡检";
                                    break;
                                case 4:
                                    typeName = "尾检";
                                    break;
                                default:
                                    break;
                            }
                            cell.setCellValue(typeName);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.print( "over" );
        FileOutputStream outputStream = new FileOutputStream(path+"质检清单excel.xls");
        workbook.write(outputStream);
        outputStream.close();
    }

    /**
     * 导出清单详情
     * @param maps
     * @throws IOException
     */
    public static void exportExcelCheckListDetail(List<Map<String, Object>> maps) throws IOException {
        String path = "C:\\Users\\Lenovo\\Documents\\";
        //创建一个薄
        Workbook workbook = new SXSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet();
        //写入数据
        for (int rowNum =0; rowNum < maps.size();rowNum++) {
            //当前行
            Row row = sheet.createRow(rowNum);
            //循环当前行
            for (int cellNum = 0; cellNum < maps.get(rowNum).size() ; cellNum++) {
                //获取当前单元格
                Cell cell = row.createCell(cellNum);
                switch (cellNum){
                    case 0:
                        cell.setCellValue(maps.get(rowNum).get("ID").toString());
                        break;
                    case 1:
                        cell.setCellValue(maps.get(rowNum).get("DT").toString());
                        break;
                    case 2:
                        cell.setCellValue(maps.get(rowNum).get("VERSIONS").toString());
                        break;
                    case 3:
                        //设置值
                        if (rowNum==0){
                            cell.setCellValue(maps.get(rowNum).get("START").toString());
                        }else {
                            String start = maps.get(rowNum).get("START").toString();
                            String startName = "";
                            switch (start) {
                                case "0":
                                    startName = "关闭";
                                    break;
                                case "1":
                                    startName = "启用";
                                    break;
                                default:
                                    break;
                            }
                            cell.setCellValue(startName);
                        }
                        break;
                    case 4:
                        cell.setCellValue(maps.get(rowNum).get("CONTENT").toString());
                        break;
                    case 5:
                        cell.setCellValue(maps.get(rowNum).get("CHECK_LIST_CODE").toString());
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.print( "over" );
        FileOutputStream outputStream = new FileOutputStream(path+"质检清单详情excel.xls");
        workbook.write(outputStream);
        outputStream.close();
    }
}
