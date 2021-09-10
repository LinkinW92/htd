package com.skeqi.mes.controller.yp.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.wms.WmsStockService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 库存
 * @date 2021-07-12
 */
@RestController
@RequestMapping("/api/wms/wmsStock")
public class WmsStockController {

	@Autowired
	WmsStockService service;

	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			Integer pageNum = EqualsUtil.pageNum(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			
			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂ID", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位ID", false);
			String packageId = EqualsUtil.string(request, "packageId", "包装ID", false);
			String code = EqualsUtil.string(request, "code", "单件码", false);
			String materialNo = EqualsUtil.string(request, "materialNo", "物料编号", false);
			String dt = EqualsUtil.string(request, "dt", "入库时间", false);

			JSONObject json = new JSONObject();
			json.put("factoryId", factoryId);
			json.put("locationId", locationId);
			json.put("packageId", packageId);
			json.put("code", code);
			json.put("materialNo", materialNo);
			
			if (dt != null && dt.split(",").length == 2) {
				json.put("startDt", dt.split(",")[0]);
				json.put("endDt", dt.split(",")[1]);
			}

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过工厂id跟库位id查询库存
	 * @param request
	 * @return
	 */
	@RequestMapping("/findStockByFactoryIdAndLocationId")
	public Rjson findStockByFactoryIdAndLocationId(HttpServletRequest request) {
		try {
			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂id", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			List<JSONObject> list = service.findStockByFactoryIdAndLocationId(factoryId, locationId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
