package com.skeqi.mes.util.yp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

public class ExcelUtil {

	/**
	 * 解析Excel
	 *
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static List<JSONObject> AnalysisExcel(InputStream is) throws IOException {
		List<JSONObject> list = new ArrayList<JSONObject>();

		XSSFWorkbook workbook = new XSSFWorkbook(is);
		// 读取Sheet
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		// 获取最大行数
		int rownum = sheet.getPhysicalNumberOfRows();
		// 获取最大列数
		int colnum = row.getPhysicalNumberOfCells();
		for (int i = 0; i < rownum; i++) {
			// 获取第i行数据
			row = sheet.getRow(i);
			JSONObject json = new JSONObject();
			for (int j = 0; j < colnum; j++) {

				String cellText = "";
				try {
					cellText = row.getCell(j).getStringCellValue();
				} catch (NullPointerException nulle) {
					cellText = "";
				} catch (Exception e) {
					cellText = "" + row.getCell(j).getNumericCellValue();
					cellText = cellText.substring(0,cellText.length()-2);
				}
				json.put("" + j, cellText);
			}
			list.add(json);
		}
		return list;
	}

	/**
	 * 判断上传文件是否为空，是否符合类型
	 *
	 * @param fileToUpload
	 * @param suffix       文件类型参数
	 * @return
	 */
	public static String validateUploadFile(MultipartFile fileToUpload, String... suffix) {

		if (fileToUpload.getSize() == 0) {
			return "文件内容为空，请重新选择文件！";
		}
		if (suffix.length == 0) {
			return null;
		}
		String fileName = fileToUpload.getOriginalFilename();
		if (!FilenameUtils.isExtension(fileName, suffix)) {
			return "文件扩展名只能是" + org.apache.commons.lang.StringUtils.join(suffix, "、") + "，请重新选择文件上传！";
		}
		return null;
	}

}
