package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.wms.MaterialNumberService;
import com.skeqi.mes.service.wms.StorageDetailService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.ToolUtils;

/**
 * excel操作
 *
 * @author Administrator
 *
 */
@Controller
@RequestMapping("wms/excel")
public class ExcelController {

	@Autowired
	MaterialNumberService mnService;

	@Autowired
	StorageDetailService sdService;

	/**
	 * 导出物料库存
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@RequestMapping(value = "materialNumber/export")
	public void materialNumberExcel(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {

		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
			HSSFWorkbook book = new HSSFWorkbook();
			String headers[] = { "物料编号", "物料名称", "物料库存", "所属项目", "所属仓库", "所属区域", "所属库区", "所属库位",
					"入库时间", "托盘码" };
			ExcelUtil.materialNumberExcel(mnService.findMaterialNumberList(null), book, headers);
			ExcelUtil.export(response, book, "物料库存"+s.format(new Date()) + ".xls");

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
	}

	/**
	 * 导出物料
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@RequestMapping(value = "material/export")
	public void materialExcel(HttpServletResponse response)
			throws IOException, ServicesException {

//		try {
//			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
//			HSSFWorkbook book = new HSSFWorkbook();
//			String headers[] = { "物料编号", "物料名称", "物料库存", "所属项目", "所属仓库", "所属区域", "所属库区", "所属库位",
//					"入库时间", "托盘码" };
//			ExcelUtil.materialNumberExcel(mnService.findMaterialNumberList(null), book, headers);
//			ExcelUtil.export(response, book, "物料库存"+s.format(new Date()) + ".xls");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * 导出物料详情
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@RequestMapping(value = "StorageDetail/export")
	public void StorageDetailExcel(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {

		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
			HSSFWorkbook book = new HSSFWorkbook();
			String headers[] = { "单据号", "物料名称", "物料数量", "所属项目", "所属仓库", "所属区域", "所属库区", "所属库位",
					"操作时间", "操作类型" };
			ExcelUtil.StorageDetailExcel(sdService.findStorageDetailList(null), book, headers);
			ExcelUtil.export(response, book, "库存详情"+s.format(new Date()) + ".xls");

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
	}
}
