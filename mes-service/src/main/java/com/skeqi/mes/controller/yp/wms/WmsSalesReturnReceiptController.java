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
import com.skeqi.mes.service.yp.wms.WmsSalesReturnReceiptService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 销售退货入库单
 *
 * @author yinp
 * @date 2021年7月27日
 */
@RestController
@RequestMapping("/api/wms/wmsSalesReturnReceipt")
public class WmsSalesReturnReceiptController {

	@Autowired
	WmsSalesReturnReceiptService service;

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
			String checkUser = EqualsUtil.string(request, "checkUser", "收货人", false);
			String receivingDt = EqualsUtil.string(request, "receivingDt", "收货时间", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("status", status);
			json.put("creator", creator);
			json.put("checkUser", checkUser);

			if (receivingDt != null && receivingDt.split(",").length == 2) {
				json.put("startReceivingDt", receivingDt.split(",")[0]);
				json.put("endReceivingDt", receivingDt.split(",")[1]);
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
			Integer receivingFactoryId = EqualsUtil.integer(request, "receivingFactoryId", "收货工厂id", false);
			Integer receivingLocationId = EqualsUtil.integer(request, "receivingLocationId", "收货库位id", false);
			String salesReturnListNo = EqualsUtil.string(request, "salesReturnListNo", "销售退货单号", true);
			String customerId = EqualsUtil.string(request, "customerId", "客户id", true);
			String establishUserId = EqualsUtil.string(request, "establishUserId", "创建用户id", true);
			String status = EqualsUtil.string(request, "status", "状态", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("receivingFactoryId", receivingFactoryId);
			json.put("receivingLocationId", receivingLocationId);
			json.put("salesReturnListNo", salesReturnListNo);
			json.put("customerId", customerId);
			json.put("establishUserId", establishUserId);
			json.put("status", status);
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
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer receivingFactoryId = EqualsUtil.integer(request, "receivingFactoryId", "收货工厂id", false);
			Integer receivingLocationId = EqualsUtil.integer(request, "receivingLocationId", "收货库位id", false);
			String status = EqualsUtil.string(request, "status", "状态", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("receivingFactoryId", receivingFactoryId);
			json.put("receivingLocationId", receivingLocationId);
			json.put("status", status);
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
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			service.delete(listNo);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);

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
	 * 新增R
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addR")
	public Rjson addR(HttpServletRequest request) {
		try {

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新R
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateR")
	public Rjson updateR(HttpServletRequest request) {
		try {

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除R
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteR")
	public Rjson deleteR(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "ID", true);

			service.deleteR(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			Integer rId = EqualsUtil.integer(request, "rId", "行ID", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("rId", rId);

			List<JSONObject> list = service.listD(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增D
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addD")
	public Rjson addD(HttpServletRequest request) {
		try {
			String packingId = EqualsUtil.string(request, "packingId", "包装ID", false);
			String code = EqualsUtil.string(request, "code", "单件码", false);
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);

			if (packingId == null || packingId == "") {
				if (code == null || code == "") {
					throw new Exception("包装ID或单件码其中一项不能为空");
				}
			}

			JSONObject json = new JSONObject();
			json.put("packingId", packingId);
			json.put("code", code);
			json.put("listNo", listNo);

			service.addD(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新D
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateD")
	public Rjson updateD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "ID", true);
			Integer packingNumber = EqualsUtil.integer(request, "packingNumber", "包装数量", true);
			Integer number = EqualsUtil.integer(request, "number", "单件数量", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("packingNumber", packingNumber);
			json.put("number", number);

			service.updateD(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除D
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteD")
	public Rjson deleteD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "ID", true);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("listNo", listNo);

			service.deleteD(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询销售退货单
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findSalesReturn")
	public Rjson findSalesReturn(HttpServletRequest request) {
		try {

			String orderNumber = EqualsUtil.string(request, "orderNumber", "单据号", false);
			String contractNo = EqualsUtil.string(request, "contractNo", "状态", false);
			String customerNumber = EqualsUtil.string(request, "customerNumber", "创建人", false);
			String founder = EqualsUtil.string(request, "founder", "收货人", false);

			JSONObject json = new JSONObject();
			json.put("orderNumber", orderNumber);
			json.put("contractNo", contractNo);
			json.put("customerNumber", customerNumber);
			json.put("founder", founder);

			List<JSONObject> list = service.findSalesReturn(json);

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
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户iD", true);

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
	 * 查询
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
