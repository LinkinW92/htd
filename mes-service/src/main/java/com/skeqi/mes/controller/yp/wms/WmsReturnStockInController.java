package com.skeqi.mes.controller.yp.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.wms.WmsReturnStockInService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 退料入库
 * @date 2021-07-26
 */
@RestController
@RequestMapping("/api/wms/wmsReturnStockIn")
public class WmsReturnStockInController {

	@Autowired
	WmsReturnStockInService service;

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
			String status = EqualsUtil.string(request, "status", "状态", false);
			String creator = EqualsUtil.string(request, "creator", "创建人", false);
			String receivingGoodsUserId = EqualsUtil.string(request, "receivingGoodsUserId", "收货人", false);
			String receivingGoodsDt = EqualsUtil.string(request, "receivingGoodsDt", "收货时间", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("status", status);
			json.put("creator", creator);
			json.put("receivingGoodsUserId", receivingGoodsUserId);

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
			Integer receivingGoodsFactoryId = EqualsUtil.integer(request, "receivingGoodsFactoryId", "收货工厂", false);
			Integer receivingGoodsLocationId = EqualsUtil.integer(request, "receivingGoodsLocationId", "收货库位", false);
			String remarks = EqualsUtil.string(request, "remarks", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("receivingGoodsFactoryId", receivingGoodsFactoryId);
			json.put("receivingGoodsLocationId", receivingGoodsLocationId);
			json.put("remarks", remarks);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询行
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
	 * 查询详情
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/listD")
	public Rjson listD(HttpServletRequest request) {
		try {

			String RListNo = EqualsUtil.string(request, "RListNo", "详情单号", true);

			JSONObject json = new JSONObject();
			json.put("RListNo", RListNo);

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
	@Transactional
	@RequestMapping("/guoZhang")
	public Rjson guoZhang(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("userId", userId);

			service.guoZhang(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 拒帐
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/juZhang")
	public Rjson juZhang(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);

			service.juZhang(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

}
