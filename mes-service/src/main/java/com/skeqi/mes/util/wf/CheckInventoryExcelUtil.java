package com.skeqi.mes.util.wf;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lenovo
 * 线边库盘点导入解析工具类
 */
public class CheckInventoryExcelUtil {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    /**
     * 解析文件的方法.
     * 导入盘点excel
     *  @param inputStream 文件输入流，要解析的Excel文件输入流
     *  @param suffix 文件后缀名，xls或xlsx，代码决定使用什么方式解析excel.
     *  @param startRow 从第几行开始读取数据.
     * @return List<Map<String, Object>>集合中的一个元素对应一行解析的数据.
     */
    public static List<Map<String, Object>> importExcel(InputStream inputStream, String suffix, Integer startRow) throws Exception {
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
        for (int i = 0; i <workbook.getNumberOfSheets() ; i++) {
            Sheet sheetAt = workbook.getSheetAt(i);
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
            if (i!=0){
                startRow = 0;
            }
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
                        //解析清单详情
                        switch (cellNum){
                            case 0:
                                key = "materialSn";
                                break;
                            case 1:
                                key = "realQuantity";
                                break;
                            default:
                                break;
                        }
                        //数据存储
                        map.put(key,cellData);
                    }
                    data.add(map);
                }
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
}
