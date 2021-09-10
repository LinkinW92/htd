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
import com.skeqi.mes.service.yp.wms.WmsNonProductionScrapReceiptService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 非生产报废入库
 * @date 2021-07-29
 */
@RestController
@RequestMapping("/api/wms/wmsNonProductionScrapReceipt")
public class WmsNonProductionScrapReceiptController {

	@Autowired
	WmsNonProductionScrapReceiptService service;

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
			Integer sourceDepartmentId = EqualsUtil.integer(request, "sourceDepartmentId", "来源部门ID", false);
			Integer sourceLocationId = EqualsUtil.integer(request, "sourceLocationId", "来源库位ID", false);
			Integer sourceProjectId = EqualsUtil.integer(request, "sourceProjectId", "来源项目ID", false);
			String establishUser = EqualsUtil.string(request, "establishUser", "创建用户", false);
			String receivingUser = EqualsUtil.string(request, "receivingUser", "收货用户", false);
			String receivingDt = EqualsUtil.string(request, "receivingDt", "收货时间", false);
			String status = EqualsUtil.string(request, "status", "状态", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("sourceDepartmentId", sourceDepartmentId);
			json.put("sourceLocationId", sourceLocationId);
			json.put("sourceProjectId", sourceProjectId);
			json.put("establishUser", establishUser);
			json.put("receivingUser", receivingUser);
			json.put("receivingDt", receivingDt);
			json.put("status", status);

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
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String scrapType = EqualsUtil.string(request, "scrapType", "报废类型", true);
			Integer sourceFactoryId = EqualsUtil.integer(request, "sourceFactoryId", "来源工厂ID", true);
			Integer sourceLocationId = EqualsUtil.integer(request, "sourceLocationId", "来源库位ID", true);
			Integer sourceDepartmentId = EqualsUtil.integer(request, "sourceDepartmentId", "来源部门ID", true);
			Integer sourceProjectId = EqualsUtil.integer(request, "sourceProjectId", "来源项目ID", true);
			Integer receivingFactoryId = EqualsUtil.integer(request, "receivingFactoryId", "收货工厂ID", false);
			Integer receivingLocationId = EqualsUtil.integer(request, "receivingLocationId", "收货库位ID", false);
			Integer establishUserId = EqualsUtil.integer(request, "establishUserId", "创建用户ID", true);
			String status = EqualsUtil.string(request, "status", "状态", false);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("scrapType", scrapType);
			json.put("sourceFactoryId", sourceFactoryId);
			json.put("sourceLocationId", sourceLocationId);
			json.put("sourceDepartmentId", sourceDepartmentId);
			json.put("sourceProjectId", sourceProjectId);
			json.put("receivingFactoryId", receivingFactoryId);
			json.put("receivingLocationId", receivingLocationId);
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
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String scrapType = EqualsUtil.string(request, "scrapType", "报废类型", true);
			Integer sourceFactoryId = EqualsUtil.integer(request, "sourceFactoryId", "来源工厂ID", true);
			Integer sourceLocationId = EqualsUtil.integer(request, "sourceLocationId", "来源库位ID", true);
			Integer sourceDepartmentId = EqualsUtil.integer(request, "sourceDepartmentId", "来源部门ID", true);
			Integer sourceProjectId = EqualsUtil.integer(request, "sourceProjectId", "来源项目ID", true);
			Integer receivingFactoryId = EqualsUtil.integer(request, "receivingFactoryId", "收货工厂ID", false);
			Integer receivingLocationId = EqualsUtil.integer(request, "receivingLocationId", "收货库位ID", false);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("scrapType", scrapType);
			json.put("sourceFactoryId", sourceFactoryId);
			json.put("sourceLocationId", sourceLocationId);
			json.put("sourceDepartmentId", sourceDepartmentId);
			json.put("sourceProjectId", sourceProjectId);
			json.put("receivingFactoryId", receivingFactoryId);
			json.put("receivingLocationId", receivingLocationId);
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
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料id", true);
			Integer number = EqualsUtil.integer(request, "number", "数量", true);
			String unit = EqualsUtil.string(request, "unit", "单位", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("materialId", materialId);
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
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料id", true);
			Integer number = EqualsUtil.integer(request, "number", "数量", true);
			String unit = EqualsUtil.string(request, "unit", "单位", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("listNo", listNo);
			json.put("materialId", materialId);
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
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
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
			json.put("listNo", listNo);
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
	 * 分组查询库存
	 * @param request
	 * @return
	 */
	@RequestMapping("/findStockGroupByMaterial")
	public Rjson findStockGroupByMaterial(HttpServletRequest request) {
		try {
			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂id", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			JSONObject json = new JSONObject();
			json.put("factoryId", factoryId);
			json.put("locationId", locationId);

			List<JSONObject> list = service.findStockGroupByMaterial(json);

			return Rjson.success(list);
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

}
