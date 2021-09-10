package com.skeqi.mes.controller.yp.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.wms.WmsProductionWarehousingService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 生产入库
 * @date 2021-07-20
 */
@RestController
@RequestMapping("/api/wms/wmsProductionWarehousing")
public class WmsProductionWarehousingController {

	@Autowired
	WmsProductionWarehousingService service;

	/**
	 * 查询
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			Integer pageNum = EqualsUtil.pageNum(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			
			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);
			Integer status = EqualsUtil.integer(request, "status", "状态", false);
			String lineName = EqualsUtil.string(request, "lineName", "产线", false);
			String creatorUserName = EqualsUtil.string(request, "creatorUserName", "创建人", false);
			String receivingGoodsDt = EqualsUtil.string(request, "receivingGoodsDt", "收货日期", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("status", status);
			json.put("lineName", lineName);
			json.put("creatorUserName", creatorUserName);
			
			if(receivingGoodsDt!=null && receivingGoodsDt.split(",").length==2) {
				json.put("startReceivingGoodsDt", receivingGoodsDt.split(",")[0]);
				json.put("endReceivingGoodsDt", receivingGoodsDt.split(",")[1]);
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
	 * 更新
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂id", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", false);
			String remarks = EqualsUtil.string(request, "remarks", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("factoryId", factoryId);
			json.put("locationId", locationId);
			json.put("remarks", remarks);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询R
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/listR")
	public Rjson listR(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);

			List<JSONObject> list = service.listR(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询D
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/listD")
	public Rjson listD(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);

			List<JSONObject> list = service.listD(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 过账
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/guoZhang")
	public Rjson guoZhang(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer id = EqualsUtil.integer(request, "id", "ID", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);

			service.guoZhang(listNo, id, userId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 拒账
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/juZhang")
	public Rjson juZhang(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer id = EqualsUtil.integer(request, "id", "ID", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);

			service.juZhang(listNo, id, userId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询R跟D表数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/findRAndD")
	public Rjson findRAndD(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			List<JSONObject> list = service.findRAndD(listNo);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 查询所有产线
	 * @param request
	 * @return
	 */
	@RequestMapping("/findLineAll")
	public Rjson findLineAll(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findLineAll();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
