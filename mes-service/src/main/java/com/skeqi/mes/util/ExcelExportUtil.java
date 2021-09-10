package com.skeqi.mes.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelExportUtil {

	private String fileUrl;
	/**
	 * @param fileUrl 模板路径
	 * DESC:创建实例，并初始化模板
	 */
	public ExcelExportUtil(String fileUrl){
		this.fileUrl = fileUrl;
	}

	/**
	 *
	 * @param rows 模板内配置所占行数
	 * @param cols 模板内配置所占列数
	 * @param result 要导出报表的数据集
	 * @return excel报表
	 *
	 */
	@SuppressWarnings("deprecation")
	public InputStream export(int rows,int cols,List<?> result){
		try{
			File file = new File(fileUrl);
			FileInputStream fint = new FileInputStream(file);
			POIFSFileSystem poiFileSystem = new POIFSFileSystem(fint);
			HSSFWorkbook wb = new HSSFWorkbook(poiFileSystem);
			HSSFSheet sheet = wb.getSheetAt(0);
			GetProperty gp = new GetProperty();
			HSSFRow namesRow = sheet.getRow(rows-2);
			HSSFRow typeRow = sheet.getRow(rows-1);

			for(int i=0;i<result.size();i++){
				HSSFRow row = sheet.getRow(rows+i);
				if(row==null){
					row = sheet.createRow(rows+i);
				}
				for(int j=0;j<cols;j++){
					HSSFCell cell = row.getCell((short)j);
					if (cell == null){
						cell = row.createCell((short)j);
					}
					//获得方法名
					HSSFCell nameCell = namesRow.getCell((short)j);
					HSSFCell typeCell = typeRow.getCell((short)j);
					String name = nameCell.getRichStringCellValue().getString();
					String type = typeCell.getRichStringCellValue().getString();
					//java反射机制获得方法值
					Object obj = gp.getProperty(name, result.get(i).getClass(), result.get(i));
					//写入Excel中
					if("Number".equals(type)){
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						if(obj!=null&&!"".equals(obj)){
							cell.setCellValue(Double.valueOf(String.valueOf(obj)));
						}else{
							cell.setCellValue(0);
						}
					}else{
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if(obj==null){
							cell.setCellValue(new HSSFRichTextString(""));
						}else{
							cell.setCellValue(new HSSFRichTextString(String.valueOf(obj)));
						}
					}
				}
			}

			sheet.shiftRows(rows, result.size()+rows, -2);
			System.out.println("WRITE EXCEL REPORT IS OK..");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			return is;

		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

  //发送响应流方法
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
//            try {
//                fileName = new String(fileName.getBytes(),"ISO8859-1");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
