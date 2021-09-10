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
import com.skeqi.mes.service.yp.wms.WMSSendCommodityService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 来料入库
 * @date 2021-07-14
 */
@RestController
@RequestMapping("/api/wms/WMSSendCommodity")
public class WMSSendCommodityController {

	@Autowired
	WMSSendCommodityService service;

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
			String receivingGoodsDt = EqualsUtil.string(request, "receivingGoodsDt", "收货时间", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("status", status);
			json.put("creator", creator);
			
			if (receivingGoodsDt != null && receivingGoodsDt.split(",").length == 2) {
				json.put("startRreceivingGoodsDt", receivingGoodsDt.split(",")[0]);
				json.put("endRreceivingGoodsDt", receivingGoodsDt.split(",")[1]);
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
	 * 新增
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String creator = EqualsUtil.string(request, "creator", "创建人", true);
			String status = EqualsUtil.string(request, "status", "状态", true);
			String locationId = EqualsUtil.string(request, "locationId", "库位ID", false);
			String factoryId = EqualsUtil.string(request, "factoryId", "工厂Id", false);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("creator", creator);
			json.put("status", status);
			json.put("locationId", locationId);
			json.put("factoryId", factoryId);
			json.put("dis", dis);
			
			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String consignee = EqualsUtil.string(request, "consignee", "收货人", false);
			Integer creator = EqualsUtil.integer(request, "creator", "创建人", false);
			String status = EqualsUtil.string(request, "status", "状态", false);
			String locationId = EqualsUtil.string(request, "locationId", "库位ID", false);
			String factoryId = EqualsUtil.string(request, "factoryId", "工厂Id", false);
			String receivingGoodsDt = EqualsUtil.string(request, "receivingGoodsDt", "收货时间", false);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("consignee", consignee);
			json.put("creator", creator);
			json.put("status", status);
			json.put("locationId", locationId);
			json.put("factoryId", factoryId);
			json.put("receivingGoodsDt", receivingGoodsDt);
			json.put("dis", dis);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 新增行
	 * 
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addRow")
	public Rjson addRow(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			String purchaseOrderNo = EqualsUtil.string(request, "purchaseOrderNo", "采购订单号", true);
			Integer commodityId = EqualsUtil.integer(request, "commodityId", "送货单头表id", true);
			
			String materialList = EqualsUtil.string(request, "materialList", "物料集合", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("purchaseOrderNo", purchaseOrderNo);
			json.put("commodityId", commodityId);
			json.put("materialList", materialList);

			service.addRow(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除行
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteRow")
	public Rjson deleteRow(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteRow(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 新增详情
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addD")
	public Rjson addD(HttpServletRequest request) {
		try {
			String packingId = EqualsUtil.string(request, "packingId", "包装id", false);
			String code = EqualsUtil.string(request, "code", "单件码", false);
			String unitQuantity = EqualsUtil.string(request, "unitQuantity", "单件数量", false);
			String numberOfPackages = EqualsUtil.string(request, "numberOfPackages", "包装数量", false);
			String supplierBatchCode = EqualsUtil.string(request, "supplierBatchCode", "供应商批次码", false);
			String productionDt = EqualsUtil.string(request, "productionDt", "生产日期", false);
			String termOfValidityDt = EqualsUtil.string(request, "termOfValidityDt", "有效截止日期", false);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String rowId = EqualsUtil.string(request, "rowId", "行ID", true);

			JSONObject json = new JSONObject();
			json.put("packingId", packingId);
			json.put("code", code);
			json.put("unitQuantity", unitQuantity);
			json.put("numberOfPackages", numberOfPackages);
			json.put("supplierBatchCode", supplierBatchCode);
			json.put("productionDt", productionDt);
			json.put("termOfValidityDt", termOfValidityDt);
			json.put("listNo", listNo);
			json.put("rowId", rowId);

			service.addD(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新详情
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateD")
	public Rjson updateD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String packingId = EqualsUtil.string(request, "packingId", "包装id", false);
			String code = EqualsUtil.string(request, "code", "单件码", false);
			String unitQuantity = EqualsUtil.string(request, "unitQuantity", "单件数量", false);
			String numberOfPackages = EqualsUtil.string(request, "numberOfPackages", "包装数量", false);
			String supplierBatchCode = EqualsUtil.string(request, "supplierBatchCode", "供应商批次码", false);
			String productionDt = EqualsUtil.string(request, "productionDt", "生产日期", false);
			String termOfValidityDt = EqualsUtil.string(request, "termOfValidityDt", "有效截止日期", false);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String rowId = EqualsUtil.string(request, "rowId", "行ID", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("packingId", packingId);
			json.put("code", code);
			json.put("unitQuantity", unitQuantity);
			json.put("numberOfPackages", numberOfPackages);
			json.put("supplierBatchCode", supplierBatchCode);
			json.put("productionDt", productionDt);
			json.put("termOfValidityDt", termOfValidityDt);
			json.put("listNo", listNo);
			json.put("rowId", rowId);

			service.updateD(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除详情
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteD")
	public Rjson deleteD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer rowId = EqualsUtil.integer(request, "rowId", "行ID", true);

			service.deleteD(id,rowId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);

			service.guoZhang(listNo, userId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 拒账
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/juZhang")
	public Rjson juZhang(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);

			service.juZhang(listNo, userId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询R表跟D表
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
