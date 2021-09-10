package com.skeqi.mes.service.qh;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.qh.CMesReportTableDAO;
import com.skeqi.mes.pojo.CMesReportColumn;
import com.skeqi.mes.pojo.CMesTableReportT;
import com.skeqi.mes.util.excel.BaseFrontController;
import com.skeqi.mes.util.excel.ExcelFormatUtil;

@Service
public class CMesReportTableServiceImpl implements CMesReportTableService {

	@Autowired
	CMesReportTableDAO dao;

	@Override
	public List<CMesTableReportT> findAllReport() {
		// TODO Auto-generated method stub
		return dao.findAllReport(null);
	}

	@Override
	public List<CMesReportColumn> findColumnById(Integer tableReportId) {
		return dao.findColumnById(tableReportId);
	}

	@Override
	public Integer updateStatus(Integer tableReportId, String tableColumnsName, Integer showFlag) {
		// TODO Auto-generated method stub
		return dao.updateStatus(tableReportId, tableColumnsName, showFlag);
	}

	@Override
	public List<Map<String, Object>> findDataReport(Map<String, String> map) throws ServicesException {
		String tableName = map.get("tableName");
		String startTime = map.get("startTime");
		String endTime = map.get("endTime");
		String sn = map.get("sn");
		List<Map<String, Object>> list = dao.findDataReport(tableName, startTime, endTime, sn, null, null);
		return list;
	}

	@Override
	public String getTableNameById(Integer tableReportId) {
		return dao.getTableNameById(tableReportId);
	}

	@Override
	public ResponseEntity<byte[]> exportExcel(Map<String, String> map) throws ServicesException {
		try {
			System.out.println(">>>>>>>>>>开始导出excel>>>>>>>>>>");

			Integer pageNum = Integer.parseInt(map.get("pageNum"));
			Integer pageSize = Integer.parseInt(map.get("pageSize"));
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			String sn = map.get("sn");

			Integer tableReportId = Integer.parseInt(map.get("tableReportId"));
			List<CMesTableReportT> listTable = dao.findAllReport(tableReportId);
			String tableName = listTable.get(0).getTableName();
			String showName = listTable.get(0).getShowName();

			List<CMesReportColumn> listColumn = dao.findColumnById(tableReportId);
			Integer pageStart = (pageNum - 1) * pageSize;
			List<Map<String, Object>> list = dao.findDataReport(tableName, startTime, endTime, sn, pageStart, pageSize);

			BaseFrontController baseFrontController = new BaseFrontController();
			return baseFrontController.buildResponseEntity(export(list, listColumn), showName + ".xls");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(">>>>>>>>>>导出excel 异常，原因为：" + e.getMessage());
		}
		return null;
	}

	private InputStream export(List<Map<String, Object>> list, List<CMesReportColumn> listColumn) {
		// System.out.println(">>>>>>>>>>>>>>>>>>>>开始进入导出方法>>>>>>>>>>");
		ByteArrayOutputStream output = null;
		InputStream inputStream1 = null;
		SXSSFWorkbook wb = new SXSSFWorkbook(1000);// 保留1000条数据在内存中
		SXSSFSheet sheet = (SXSSFSheet) wb.createSheet();
		// 设置报表头样式
		CellStyle header = ExcelFormatUtil.headSytle(wb);// cell样式
		CellStyle content = ExcelFormatUtil.contentStyle(wb);// 报表体样式

		// 每一列字段名
		String[] strs = new String[listColumn.size()];
		for (int i = 0; i < strs.length; i++) {
			CMesReportColumn column = listColumn.get(i);
			strs[i] = column.getShowColumnsName();
		}

		// 字段名所在表格的宽度
		int[] ints = new int[listColumn.size()];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = 5000;
		}

		// 设置表头样式
		ExcelFormatUtil.initTitleEX(sheet, header, strs, ints);
		// System.out.println(">>>>>>>>>>>>>>>>>>>>表头样式设置完成>>>>>>>>>>");

		if (list != null && list.size() > 0) {
			// System.out.println(">>>>>>>>>>>>>>>>>>>>开始遍历数据组装单元格内容>>>>>>>>>>");
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				System.out.println("map: " + map);
				SXSSFRow row = (SXSSFRow) sheet.createRow(i + 1);

				SXSSFCell cell = null;
				for (int j = 0; j < listColumn.size(); j++) {
					cell = (SXSSFCell) row.createCell(j);
					CMesReportColumn column = listColumn.get(j);
					String tableColumnsName = column.getTableColumnsName();
					Object tableColumnsNameValue = map.get(tableColumnsName);
					if(tableColumnsNameValue == null) {
						tableColumnsNameValue = map.get(tableColumnsName.toUpperCase());
					}

					System.out.println("tableColumnsName: " + tableColumnsName);
					cell.setCellValue(tableColumnsNameValue != null ? tableColumnsNameValue.toString() : "");
					cell.setCellStyle(content);

				}
			}
			// System.out.println(">>>>>>>>>>>>>>>>>>>>结束遍历数据组装单元格内容>>>>>>>>>>");
		}
		try {
			output = new ByteArrayOutputStream();
			wb.write(output);
			inputStream1 = new ByteArrayInputStream(output.toByteArray());
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
					if (inputStream1 != null)
						inputStream1.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return inputStream1;
	}

}
