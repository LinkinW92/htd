package com.skeqi.mes.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.util.yp.FileReading;

public class LogUtils {
	private static String logPath = FileReading.getValue("config.properties", "ApiLogFilePath");

	public synchronized static void writeApiLog(CMesWebApiLogs dx) {
		File logFile = createApiLogFile();
		try {
			FileInputStream fint = new FileInputStream(logFile);
			POIFSFileSystem poiFileSystem = new POIFSFileSystem(fint);
			Workbook wb = new HSSFWorkbook(poiFileSystem);
			Sheet sheet = wb.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			Row row = sheet.createRow(lastRowNum + 1);
			Cell cell = row.createCell(0);
			cell.setCellValue(dx.getApiName());
			cell = row.createCell(1);
			cell.setCellValue(dx.getCallTime());
			cell = row.createCell(2);
			cell.setCellValue(dx.getParameter());
			cell = row.createCell(3);
			cell.setCellValue(dx.getReturnResult());
			cell = row.createCell(4);
			cell.setCellValue(dx.getReturnTime());
			cell = row.createCell(5);
			cell.setCellValue(dx.getSn());
			FileOutputStream fo = new FileOutputStream(logFile);
			wb.write(fo);
	        fo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static File createApiLogFile() {
		File file = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String dirPath = logPath + "\\" +sdf.format(new Date());
			File dir = new File(dirPath);
			if  (!dir .exists()  && !dir .isDirectory()) {
				dir.mkdirs();
			}
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			String filePath = dirPath + "\\log-" + sdf.format(new Date()) + ".xls";
			file = new File(filePath);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 添加表头
				String[] header = new String[]{"API_NAME", "CALL_TIME", "PARAMETER", "RETURN_RESULT", "RETURN_TIME", "sn"};
				Workbook wb = new HSSFWorkbook();
				Sheet sheet = wb.createSheet("ApiLog");
				Row row = sheet.createRow(0);
				for (int i = 0; i < header.length; i++) {
					Cell cell = row.createCell(i);
					cell.setCellValue(header[i]);
					sheet.setColumnWidth(i, 256 * 40);
				}
				FileOutputStream fo = new FileOutputStream(file);
				wb.write(fo);
		        fo.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public static void main(String[] args) {
		String apiName = "checkSN";
		String callTime = "2021-04-20 16:51:37";
		String parameter = "{\"line\":\"PACK\",\"station\":\"OP01\",\"sn\":\"B4L00001\",\"s\":1618908697797}";
		String returnResult = "{\"code\":\"\",\"productionId\":453,\"errMsg\":\"\",\"stepList\":[],\"isSuccess\":true}";
		String returnTime = "2021-04-20 16:51:37";
		String sn = "B4L00001";
		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName(apiName);
		dx.setCallTime(callTime);
		dx.setParameter(parameter);
		dx.setReturnResult(returnResult);
		dx.setReturnTime(returnTime);
		dx.setSn(sn);
		writeApiLog(dx);
	}
}
