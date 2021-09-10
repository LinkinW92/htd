package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.service.all.WmsClientApiService;
import com.skeqi.mes.util.ToolUtils;

/**
 * 立库客户端专用api
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping("api/wms")
public class WmsClientApi {

	@Autowired
	WmsClientApiService service;

	/**
	 * 堆垛机任务完全接口
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@Transactional
	@RequestMapping(value = "executionCompleted", method = RequestMethod.POST)
	public void executionCompleted(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		String tray = json.getString("trayCode");// 托盘码

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();

		try {

			if (!tray.equals("1")) {

				// 通过托盘码查询临时入库队列信息
				JSONObject rIntOutJson = service.findRInTaskqueueByTrayCode(tray);

				// rIntOutJson等于null表示是出库调用完成
				if (rIntOutJson == null) {
					System.out.println("出库调用============");
					outStockUpdate(tray);
				} else {
					// 如果没有单号 就只是入库而已
					if (rIntOutJson.getString("listNo") == null || rIntOutJson.getString("listNo").equals("")
							|| rIntOutJson.getString("listNo").equals("null")) {

						// 删除出库队列临时表数据
						service.deleteRInTaskqueue(rIntOutJson.getInteger("id"));

						// 新增永久入库队列信息
						service.addPInTaskqueue(rIntOutJson);

						data.put("msg", "执行成功！");
						data.put("code", 0);
						result.put("result", data);
						return;
					}
					System.out.println("入库调用============");
					inStockUpdate(tray);
				}

			}
			data.put("msg", "执行成功！");
			data.put("code", 0);
			result.put("result", data);
		} catch (Exception e) {
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", e.getMessage());
			data.put("code", -1);
			result.put("result", data);
		} finally {
			response.getWriter().append(result.toJSONString());
		}
	}

	/**
	 * 通过托盘码查询库位坐标跟单号
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@Transactional
	@RequestMapping(value = "findZuoBiao")
	public void findZuoBiao(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();

		try {
			// 接收参数
			String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
			response.setContentType("application/json");
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if (json == null) {
				throw new Exception("参数缺失");
			}
			if (json.getString("trayCode") == null) {
				throw new Exception("trayCode不能为空");
			}

			String tray = json.getString("trayCode");// 托盘码

			JSONObject resultJson = service.findlocationZuoBiaoByTayCode(tray);

			if (resultJson == null || resultJson.getInteger("x") == null) {
				throw new Exception("未查询到该托盘是入库是出库操作");
			}

			data.put("X", resultJson.getInteger("x"));
			data.put("Y", resultJson.getInteger("y"));
			data.put("Z", resultJson.getInteger("z"));
			data.put("locationId", resultJson.getInteger("locationId"));
			data.put("listNo", resultJson.getString("listNo"));

			data.put("msg", "执行成功！");
			data.put("code", 0);
			result.put("result", data);
		} catch (Exception e) {
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", e.getMessage());
			data.put("code", -1);
			result.put("result", data);
		} finally {
			response.getWriter().append(result.toJSONString());
		}
	}

	/**
	 * 入库库存更新
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	public void inStockUpdate(String tray) throws Exception {

		// 查询出临时入库队列的数据
		JSONObject rIntOutJson = service.findRInTaskqueueByTrayCode(tray);
		if (rIntOutJson == null) {
			throw new Exception("查询出临时入库队列的数据返回结果为null");
		}

		// 删除出库队列临时表数据
		service.deleteRInTaskqueue(rIntOutJson.getInteger("id"));

		// 新增入库队列永久性表数据
		service.addPInTaskqueue(rIntOutJson);

		CWmsStorageDetailT sdDx = new CWmsStorageDetailT();
		sdDx.setListNo(rIntOutJson.getString("listNo"));
		sdDx.setLocationId(rIntOutJson.getInteger("locationId"));
		// 查询
		List<CWmsStorageDetailT> sdList = service.findStorageDetail(sdDx);
		if (sdList.size() == 0) {
			throw new Exception("没有查询到库存详情");
		}

		List<JSONObject> updateLocationJsonList = new ArrayList<JSONObject>();

		for (CWmsStorageDetailT cWmsStorageDetailT : sdList) {
			CWmsMaterialNumberT mnDx = new CWmsMaterialNumberT();
			mnDx.setMaterialNo(cWmsStorageDetailT.getMaterial().getBomId());
			mnDx.setMaterialName(cWmsStorageDetailT.getMaterial().getMaterialName());
			mnDx.setMaterialNumber(cWmsStorageDetailT.getMaterialNumber());
			mnDx.setProjectId(cWmsStorageDetailT.getProjectId());
			mnDx.setMaterialId(cWmsStorageDetailT.getMaterialId());
			mnDx.setWareHouseId(cWmsStorageDetailT.getWarehouseId());
			mnDx.setAreaId(cWmsStorageDetailT.getAreaId());
			mnDx.setLocationId(cWmsStorageDetailT.getLocationId());
			mnDx.setReservoirareaId(cWmsStorageDetailT.getReservoirAreaId());
			mnDx.setLmminentRelease(0);
			mnDx.setFreezingNumber(0);
			mnDx.setReservedNumber(0);
			mnDx.setTray(tray);
			mnDx.setMaterialCode(cWmsStorageDetailT.getMaterialCode());
			mnDx.setBarCode(cWmsStorageDetailT.getBarCode());

			Integer stockId = service.findStockCount(cWmsStorageDetailT.getMaterialId(),
					cWmsStorageDetailT.getProjectId(), cWmsStorageDetailT.getLocationId());

			if (stockId != null && !"".equals(stockId)) {
				service.updateStockNumber(stockId, cWmsStorageDetailT.getMaterialNumber());

			} else {
				service.addMaterialNumber(mnDx);
			}

			// 需要更新的库位信息
			JSONObject updateLocationJson = new JSONObject();
			updateLocationJson.put("locationId", rIntOutJson.getInteger("locationId"));
			updateLocationJson.put("state", 5);
			updateLocationJson.put("trayCode", tray);
			updateLocationJson.put("projectId", cWmsStorageDetailT.getProjectId());
			updateLocationJson.put("materialId", cWmsStorageDetailT.getMaterialId());
			updateLocationJson.put("locationId", cWmsStorageDetailT.getLocationId());
			updateLocationJsonList.add(updateLocationJson);

			// 新增库存详情P表
			service.addPStorageDetail(cWmsStorageDetailT);

			// 删除库存详情R表
			service.deleteStorageDetail(cWmsStorageDetailT.getId());

			// cWmsStorageDetailT.getProjectId()
			// cWmsStorageDetailT.getMaterialId()
			// cWmsStorageDetailT.getLocationId()

		}

		// 去重后的需要更新的库位信息
		List<JSONObject> updateLocationJsonListH = new ArrayList<JSONObject>();
		for (JSONObject updateLocationJson : updateLocationJsonList) {
			if (updateLocationJsonListH.size() == 0) {
				updateLocationJsonListH.add(updateLocationJson);
				continue;
			}

			// 更新库存时间
			service.updateNumberDt(updateLocationJson.getInteger("projectId"),
					updateLocationJson.getInteger("materialId"), updateLocationJson.getInteger("locationId"));

			for (int i = 0; i < updateLocationJsonListH.size(); i++) {
				if (updateLocationJson.getInteger("locationId")
						.equals(updateLocationJsonListH.get(i).getInteger("locationId"))) {
					break;
				}

				if (i == (updateLocationJsonListH.size() - 1)) {
					updateLocationJsonListH.add(updateLocationJson);
				}
			}
		}

		// json.getInteger("locationId")

		// 更新库位信息
		for (JSONObject json : updateLocationJsonListH) {
			// 修改库位状态
			service.updateLocationState(json.getInteger("locationId"), json.getInteger("state"));
			// 修改库位托盘码
			service.updateTrayCode(json.getInteger("locationId"), json.getString("trayCode"));
		}
	}

	/**
	 * 出库库存更新
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws IOException
	 * @throws ServicesException
	 */
	public void outStockUpdate(String tray) throws Exception {

		CWmsOutTaskqueueT otDx = new CWmsOutTaskqueueT();
		otDx.setTray(tray);

		// 查询出临时出库队列的数据
		List<CWmsOutTaskqueueT> otList = service.findOutTaskqueue(otDx);
		if (otList.size() != 1) {
			throw new Exception("查询出临时出库队列的数据返回结果为null");
		}
		otDx = otList.get(0);

		// 如果没有单号 就只是出库而已
		if (otDx.getListNo() == null) {

			return;
		}

		// 删除出库队列临时表数据
		service.deleteOutTaskqueue(otDx.getId());

		// 新增出库队列永久性表数据
		service.addPOutTaskqueue(otDx);

		CWmsStorageDetailT sdDx = new CWmsStorageDetailT();
		sdDx.setListNo(otDx.getListNo());
		sdDx.setLocationId(otDx.getLocationId());
		// 查询
		List<CWmsStorageDetailT> sdList = service.findStorageDetail(sdDx);
		if (sdList.size() == 0) {
			throw new Exception("没有查询到库存详情");
		}

		/**
		 * 判断是没有生成条码的物料库存，如果生成物料条码的就按生成物料条码的出库方式出库
		 */
//		if(sdList.get(0).getBarCode()!=null
//				&&!sdList.get(0).getBarCode().equals("")) {
//			service.findBarcodeGeneratedMaterialNumberList(sdList.get(0).getLocationId(), sdList.get(0).getProjectId(), sdList.get(0).getMaterialId());
//		}

//			List<CWmsMaterialNumberT> mnDx = service.findBarcodeGeneratedMaterialNumberList(locationId, projectId, materialId);

//		}else {
		for (CWmsStorageDetailT cWmsStorageDetailT : sdList) {

			// 出库更新库存
			service.updateStockMaterialNumber(cWmsStorageDetailT.getMaterialNumber(),
					cWmsStorageDetailT.getMaterialNumberId());

			// 删除空库存的记录
			service.deleteNullStock();

//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("locationId", cWmsStorageDetailT.getLocationId());
//			map.put("projectId", cWmsStorageDetailT.getProjectId());
//			map.put("materialId", cWmsStorageDetailT.getMaterialId());
//
//			List<CWmsMaterialNumberT> mnDxList = service.findMaterialNumberList(map);
//			if (mnDxList == null) {
//				throw new Exception("查询物料库存！");
//			}
//			CWmsMaterialNumberT mnDx = mnDxList.get(0);
//
//			if (mnDx.getBarCode() != null && !mnDx.getBarCode().equals("")) {
//
//				mnDx = service.findBarcodeGeneratedMaterialNumberList(cWmsStorageDetailT.getLocationId(),
//						cWmsStorageDetailT.getProjectId(), cWmsStorageDetailT.getMaterialId());
//			}
//
//			// 计算减去后的即将出库数量
//			Integer num = mnDx.getLmminentRelease() - cWmsStorageDetailT.getMaterialNumber();
//			if (num < 0) {
//				throw new Exception("计算相减物料即将出库数量的时候库存小于0");
//			}
//			mnDx.setLmminentRelease(num);
//			// 计算减去后的即将出库数量
//			num = mnDx.getMaterialNumber() - cWmsStorageDetailT.getMaterialNumber();
//			if (num < 0) {
//				throw new Exception("计算相减物料库存的时候库存小于0");
//			}
//
//			// 判断是否还有物料库存
//			// 如果没有就从物料库存里删除此条数据
//			if (num != 0) {
//				mnDx.setMaterialNumber(num);
//				// 修改物料库存
//				service.updateMaterialNumber(mnDx);
//			} else {
//				// 删除库存
//				service.deleteMaterialNumber(mnDx.getId());
//			}
//			cWmsStorageDetailT.setBarCode(mnDx.getBarCode());
			// 新增库存详情P表
			service.addPStorageDetail(cWmsStorageDetailT);
			// 删除库存详情R 表
			service.deleteStorageDetail(cWmsStorageDetailT.getId());

		}
//		}

		// 查询当前库位总剩余物料数
		Integer countMaterialNumber = service.findLocationCount(otDx.getLocationId());
		if (countMaterialNumber > 0) {
			// 回流
			CWmsInTaskqueueT itDx = new CWmsInTaskqueueT();
			itDx.setTray(otDx.getTray());
			itDx.setLocationId(otDx.getLocationId());

			JSONObject rintOutJson = new JSONObject();
			rintOutJson.put("trayCode", otDx.getTray());
			rintOutJson.put("locationId", otDx.getLocationId());
			// 新增入库队列
			service.addRInTaskqueue(rintOutJson);

		} else {
			// 直流
			// 库位为空 修改库位状态
			CWmsLocationT location = new CWmsLocationT();
			location.setId(otDx.getLocationId());
			location.setLocationStatus(0);
			location.setTray("");

			// 修改库位状态
			service.updateLocationState(otDx.getLocationId(), 0);
			// 修改库位托盘码
			service.updateTrayCode(otDx.getLocationId(), "");

		}

	}

}
