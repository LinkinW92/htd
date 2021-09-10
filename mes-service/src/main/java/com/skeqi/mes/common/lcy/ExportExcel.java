package com.skeqi.mes.common.lcy;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.skeqi.mes.pojo.CMesTableColumnsReportT;

public class ExportExcel<T> {

	public void exportExcel(List<CMesTableColumnsReportT> nameList,Collection<T> dataset, String fileName,HttpServletResponse response,XSSFWorkbook workbook,String strs) {
		  	getNewSheetFile(nameList, dataset, fileName, response, workbook,strs);

	    }
	public  XSSFWorkbook createWookBook(){

		//生成一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		return workbook;


	}


	    /**
	     *
	     * 方法说明: 指定路径下生成EXCEL文件
	     * @return
	     */
	    public void getExportedFile(XSSFWorkbook workbook, String name,HttpServletResponse response){
	        BufferedOutputStream fos = null;

	        try {
	            String fileName = name + ".xlsx";
	            response.setContentType("application/x-msdownload");
	            response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "iso-8859-1" ));
	            fos = new BufferedOutputStream(response.getOutputStream());
	            workbook.write(fos);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (fos != null) {
	                try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

	            }
	        }


	}
	    		public void getQueryKey(XSSFWorkbook workbook,List<String> nameList,HttpServletResponse response){
	    			 // 生成一个表格
	    	        XSSFSheet sheet = workbook.createSheet("查询条件");

	    	        sheet.setDefaultColumnWidth((short) 20);
	    	        int i = 0;
	    			for (String str : nameList) {
	    				XSSFRow row = sheet.createRow(i);
	    				XSSFCell cell = row.createCell(0);
	    				XSSFRichTextString text = new XSSFRichTextString(str);
	    				cell.setCellValue(text);
	    				i++;
					}
	    		}





	    	    //新建页并插入数据
	    	    public void getNewSheetFile(List<CMesTableColumnsReportT> nameList,Collection<T> dataset, String fileName,HttpServletResponse response,XSSFWorkbook workbook){
	    	    	 // 生成一个表格
	    	        XSSFSheet sheet = workbook.createSheet(fileName);

	    	        // 设置表格默认列宽度为15个字节
	    	        sheet.setDefaultColumnWidth((short) 20);
	    	        // 产生表格标题行

	    	        //获取用户输入的值
	    	        XSSFRow row = sheet.createRow(0);


	    	        //获取列表名
	    	       int k=0;
	    	        	for (CMesTableColumnsReportT  str: nameList) {
	    	        		XSSFCell cell = row.createCell(k);
	    	        		XSSFRichTextString text = new XSSFRichTextString(str.getShowColumnsName());
	    	        		cell.setCellValue(text);
	    	        		k++;
	    				}
	    	        try {
	    	            // 遍历集合数据，产生数据行
	    	            Iterator<T> it = dataset.iterator();
	    	            int index = 0;
	    	            while (it.hasNext()) {

	    	                index++;
	    	                row = sheet.createRow(index);
	    	                T t = (T) it.next();
	    	                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    	                Field[] fields = t.getClass().getDeclaredFields();
	    	                int j = 0;
	    	                for (short i = 0; i < fields.length; i++) {
	    	                	XSSFCell cell = row.createCell(j);

	    	                    Field field = fields[i];
	    	                    String fieldName = field.getName();
	    	                    String getMethodName = new String();
	    	                    if("tLimit".equals(fieldName)||"aLimit".equals(fieldName)){
	    	                    	getMethodName = "get" + fieldName;
	    	                    }else{
	    	                    	getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	    	                    }


	    	                    @SuppressWarnings("rawtypes")
								Class tCls = t.getClass();

	    	                    @SuppressWarnings("unchecked")
								Method getMethod = tCls.getMethod(getMethodName, new Class[] {});

	    	                    Object value = getMethod.invoke(t, new Object[] {});


	    	                    // 判断值的类型后进行强制类型转换

	    	                    String textValue = null;
	    	                    // 其它数据类型都当作字符串简单处理
	    	                    if(value != null && value != ""){
	    	                        textValue = value.toString();
	    	                    }
	    	                    if (textValue != null) {

	    	                    		XSSFRichTextString richString = new XSSFRichTextString(textValue.toString());
	    	                    		cell.setCellValue(richString);
	    	                    		j++;
	    	                    }

	    	                }
	    	            }

	    	        } catch (Exception e) {
	    	            e.printStackTrace();
	    	        }


	    	    }












	    	  //新建页并插入数据
	    	    public void getNewSheetFile(List<CMesTableColumnsReportT> nameList,Collection<T> dataset, String fileName,HttpServletResponse response,XSSFWorkbook workbook,String strs){
	    	    	 // 生成一个表格
	    	        XSSFSheet sheet = workbook.createSheet(fileName);

	    	        // 设置表格默认列宽度为15个字节
	    	        sheet.setDefaultColumnWidth((short) 20);
	    	        // 产生表格标题行

	    	        //获取用户输入的值
	    	        XSSFRow row = sheet.createRow(0);


	    	        //获取列表名
	    	       int k=0;
	    	        	for (CMesTableColumnsReportT  str: nameList) {
	    	        		XSSFCell cell = row.createCell(k);
	    	        		XSSFRichTextString text = new XSSFRichTextString(str.getShowColumnsName());
	    	        		cell.setCellValue(text);
	    	        		k++;
	    				}
	    	        try {
	    	            // 遍历集合数据，产生数据行
	    	            Iterator<T> it = dataset.iterator();
	    	            int index = 0;
	    	            while (it.hasNext()) {

	    	                index++;
	    	                row = sheet.createRow(index);
	    	                T t = (T) it.next();
	    	                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    	                String[] fields = strs.split(",");

	    	                int j = 0;
	    	                for (short i = 0; i < fields.length; i++) {
	    	                	XSSFCell cell = row.createCell(j);

	    	                    String fieldName = fields[i];
	    	                    String getMethodName = new String();

	    	                    	fieldName = fieldName.toLowerCase();
	    	                    	StringBuffer sb = new StringBuffer();
	    	                    	String[] strs2 = fieldName.split("_");
	    	                    	sb.append("get");
	    	                    	for (String string : strs2) {
	    	                    		sb.append(string.substring(0,1).toUpperCase()+string.substring(1));
									}
	    	                    	getMethodName = sb.toString();
	    	                    @SuppressWarnings("rawtypes")
								Class tCls = t.getClass();

	    	                    @SuppressWarnings("unchecked")
								Method getMethod = tCls.getMethod(getMethodName, new Class[] {});

	    	                    Object value = getMethod.invoke(t, new Object[] {});


	    	                    // 判断值的类型后进行强制类型转换

	    	                    String textValue = null;
	    	                    // 其它数据类型都当作字符串简单处理
	    	                    if(value != null && value != ""){
	    	                        textValue = value.toString();
	    	                    } else{
	    	                    	textValue="";
	    	                    }
	    	                    if (textValue != null) {

	    	                    		XSSFRichTextString richString = new XSSFRichTextString(textValue.toString());
	    	                    		cell.setCellValue(richString);
	    	                    		j++;
	    	                    }

	    	                }
	    	            }

	    	        } catch (Exception e) {
	    	            e.printStackTrace();
	    	        }


	    	    }














}
