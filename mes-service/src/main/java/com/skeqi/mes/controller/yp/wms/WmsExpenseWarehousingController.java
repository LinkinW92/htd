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
import com.skeqi.mes.service.yp.wms.WmsExpenseWarehousingService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 费用化入库
 * @date 2021-07-28
 */
@RestController
@RequestMapping("/api/wms/wmsExpenseWarehousing")
public class WmsExpenseWarehousingController {

	@Autowired
	WmsExpenseWarehousingService service;

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

			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门ID", false);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目ID", false);
			String establishUserName = EqualsUtil.string(request, "establishUserName", "创建人", false);
			String receivingUserName = EqualsUtil.string(request, "receivingUserName", "收货人", false);
			String receivingGoodsDt = EqualsUtil.string(request, "receivingGoodsDt", "收货时间", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("departmentId", departmentId);
			json.put("projectId", projectId);
			json.put("establishUserName", establishUserName);
			json.put("receivingUserName", receivingUserName);

			if (receivingGoodsDt != null && receivingGoodsDt.split(",").length == 2) {
				json.put("startReceivingDt", receivingGoodsDt.split(",")[0]);
				json.put("endReceivingDt", receivingGoodsDt.split(",")[1]);
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
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			Integer receivingFactoryId = EqualsUtil.integer(request, "receivingFactoryId", "收货仓库", false);
			Integer receivingLocationId = EqualsUtil.integer(request, "receivingLocationId", "收货库位", false);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门ID", true);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目ID人", true);
			Integer establishUserId = EqualsUtil.integer(request, "establishUserId", "创建人ID", true);
			String status = EqualsUtil.string(request, "status", "状态", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("receivingFactoryId", receivingFactoryId);
			json.put("receivingLocationId", receivingLocationId);
			json.put("departmentId", departmentId);
			json.put("projectId", projectId);
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
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);
			Integer receivingFactoryId = EqualsUtil.integer(request, "receivingFactoryId", "收货仓库", false);
			Integer receivingLocationId = EqualsUtil.integer(request, "receivingLocationId", "收货库位", false);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门ID", true);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目ID人", true);
			Integer establishUserId = EqualsUtil.integer(request, "establishUserId", "创建人ID", true);
			String status = EqualsUtil.string(request, "status", "状态", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("receivingFactoryId", receivingFactoryId);
			json.put("receivingLocationId", receivingLocationId);
			json.put("departmentId", departmentId);
			json.put("projectId", projectId);
			json.put("establishUserId", establishUserId);
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
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);

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
	 * 新增R
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addR")
	public Rjson addR(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String materialNo = EqualsUtil.string(request, "materialNo", "物料编号", true);
			Integer number = EqualsUtil.integer(request, "number", "数量", true);
			String unit = EqualsUtil.string(request, "unit", "单位", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("materialNo", materialNo);
			json.put("number", number);
			json.put("unit", unit);

			service.addR(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新R
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateR")
	public Rjson updateR(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String materialNo = EqualsUtil.string(request, "materialNo", "物料编号", true);
			Integer number = EqualsUtil.integer(request, "number", "数量", true);
			String unit = EqualsUtil.string(request, "unit", "单位", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("listNo", listNo);
			json.put("materialNo", materialNo);
			json.put("number", number);
			json.put("unit", unit);

			service.updateR(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除R
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteR")
	public Rjson deleteR(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

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
	 * @param request
	 * @return
	 */
	@RequestMapping("/listD")
	public Rjson listD(HttpServletRequest request) {
		try {

			Integer rId = EqualsUtil.integer(request, "rId", "行id", true);

			JSONObject json = new JSONObject();
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
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addD")
	public Rjson addD(HttpServletRequest request) {
		try {
			Integer rId = EqualsUtil.integer(request, "rId", "行id", true);
			String packingId = EqualsUtil.string(request, "packingId", "包装id", false);
			String code = EqualsUtil.string(request, "code", "单件码", false);
			Integer packingNumber = EqualsUtil.integer(request, "packingNumber", "包装数量", true);
			Integer number = EqualsUtil.integer(request, "number", "单件数量", true);

			if(packingId==null || packingId.equals("")) {
				if(code==null || code.equals("")) {
					throw new Exception("包装码与单件码必须存在一种");
				}
			}

			JSONObject json = new JSONObject();
			json.put("rId", rId);
			json.put("packingId", packingId);
			json.put("code", code);
			json.put("packingNumber", packingNumber);
			json.put("number", number);

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
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateD")
	public Rjson updateD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
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
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteD")
	public Rjson deleteD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteD(id);

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
