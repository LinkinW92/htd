package com.skeqi.mes.service.wms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.wms.OutTaskqueueDao;
import com.skeqi.mes.pojo.CMesSystem;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.service.all.CMesSystemService;
import com.skeqi.mes.util.ClientApiUseUtil;

@Service
public class OutTaskqueueServiceImpl implements OutTaskqueueService {

	@Autowired
	OutTaskqueueDao dao;

	@Autowired
	CMesSystemService sService;

	/**
	 * 查询审批表
	 *
	 * @param outTaskqueue
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfo<CWmsApprovalT> findApproval(Integer userId, String listNo, Integer pageNumber) throws Exception {
		CWmsApprovalT dx = new CWmsApprovalT();
		dx.setUserId(userId);
		dx.setListNo(listNo);

		/**
		 * 查询系统配置
		 */
		List<CMesSystem> systemList = sService.findByAll(null);
		if (systemList == null || systemList.size() == 0) {
			throw new Exception("查询系统配置出错了");
		}

		for (CMesSystem cMesSystem : systemList) {
			// 比对项目号
			if (cMesSystem.getName().equals("通用.项目")) {
				if (cMesSystem.getParameter().equals("XT357")) {
					PageHelper.startPage(pageNumber, 8);
					List<CWmsApprovalT> list = dao.findApprovalXT355_356_357(dx);
					PageInfo<CWmsApprovalT> pageInfo = new PageInfo<CWmsApprovalT>(list, 5);
					return pageInfo;
				}
			}
		}
		// 结束

		PageHelper.startPage(pageNumber, 8);
		List<CWmsApprovalT> list = dao.findApproval(dx);
		PageInfo<CWmsApprovalT> pageInfo = new PageInfo<CWmsApprovalT>(list, 5);
		return pageInfo;
	}

	/**
	 * 查询
	 *
	 * @param outTaskqueue
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CWmsOutTaskqueueT> findOutTaskqueue(String listNo, Integer userId) throws Exception {

		CWmsOutTaskqueueT dx = new CWmsOutTaskqueueT();
		dx.setListNo(listNo);

		List<CWmsOutTaskqueueT> list = dao.findOutTaskqueue(dx);
		return list;
	}

	/**
	 * 通过id查询
	 *
	 * @param outTaskqueueId
	 * @return
	 */
	@Override
	public CWmsOutTaskqueueT findOutTaskqueueById(Integer outTaskqueueId) {
		CWmsOutTaskqueueT outTaskqueue = new CWmsOutTaskqueueT();
		outTaskqueue.setId(outTaskqueueId);

		List<CWmsOutTaskqueueT> list = dao.findOutTaskqueue(outTaskqueue);
		if (list.size() == 1) {
			outTaskqueue = list.get(0);
			return outTaskqueue;
		} else {
			return null;
		}
	}

	/**
	 * 新增
	 *
	 * @param outTaskqueue
	 * @return
	 */
	@Override
	public boolean addOutTaskqueue(CWmsOutTaskqueueT outTaskqueue) {
		Integer result = dao.addOutTaskqueue(outTaskqueue);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 新增P表数据
	 *
	 * @param outTaskqueue
	 * @return
	 */
	@Override
	public boolean addPOutTaskqueue(CWmsOutTaskqueueT outTaskqueue) {
		Integer result = dao.addPOutTaskqueue(outTaskqueue);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新
	 *
	 * @param outTaskqueue
	 * @return
	 */
	@Override
	public boolean updateOutTaskqueue(CWmsOutTaskqueueT outTaskqueue) {
		Integer result = dao.updateOutTaskqueue(outTaskqueue);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除
	 *
	 * @param outTaskqueueId
	 * @return
	 */
	@Override
	public boolean deleteOutTaskqueue(Integer outTaskqueueId) {
		Integer result = dao.deleteOutTaskqueue(outTaskqueueId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	// @Test
	// public void findOutTaskqueue(){
	// CWmsOutTaskqueueT outTaskqueue = new CWmsOutTaskqueueT();
	// outTaskqueue.setId(1);
	// outTaskqueue.setListNo("CK123456");
	//
	// List<CWmsOutTaskqueueT> list = findOutTaskqueue(outTaskqueue);
	// for (CWmsOutTaskqueueT cWmsOutTaskqueueT : list) {
	// System.out.println(cWmsOutTaskqueueT);
	// }
	// }

	@Test
	public void findOutTaskqueueById() {
		Integer outTaskqueueId = 1;
		CWmsOutTaskqueueT outTaskqueue = findOutTaskqueueById(outTaskqueueId);
		System.out.println(outTaskqueue);
	}

	@Test
	public void addOutTaskqueue() {
		CWmsOutTaskqueueT outTaskqueue = new CWmsOutTaskqueueT(null, null, "CK123456", "SKQ123456", 1, "1", null, 1);
		boolean result = addOutTaskqueue(outTaskqueue);
		System.out.println(result);
	}

	@Test
	public void updateOutTaskqueue() {
		CWmsOutTaskqueueT outTaskqueue = new CWmsOutTaskqueueT();
		outTaskqueue.setId(1);
		outTaskqueue.setListNo("CK123456");

		boolean result = updateOutTaskqueue(outTaskqueue);
		System.out.println(result);
	}

	@Test
	public void deleteOutTaskqueue() {
		Integer outTaskqueueId = 1;
		boolean result = deleteOutTaskqueue(outTaskqueueId);
		System.out.println(result);
	}

	@Override
	public boolean chuku(Integer outTaskqueueId) throws Exception {

		CWmsOutTaskqueueT otDx = new CWmsOutTaskqueueT();
		otDx.setId(outTaskqueueId);

		// 查询出临时出库队列的数据
		List<CWmsOutTaskqueueT> otList = dao.findOutTaskqueue(otDx);
		if (otList.size() != 1) {
			throw new Exception("查询出临时出库队列的数据返回结果为null");
		}
		otDx = otList.get(0);

		// 删除出库队列临时表数据
		Integer res = dao.deleteOutTaskqueue(otDx.getId());
		if (res <= 0) {
			throw new Exception("删除出库队列临时表数据失败");
		}

		// 新增出库队列永久性表数据
		res = dao.addPOutTaskqueue(otDx);
		if (res <= 0) {
			throw new Exception("新增出库队列永久性表数据失败");
		}

//		CWmsStorageDetailT sdDx = new CWmsStorageDetailT();
//		sdDx.setListNo(otDx.getListNo());
//		sdDx.setLocationId(otDx.getLocationId());

		JSONObject json = new JSONObject();
		json.put("listNo", otDx.getListNo());
		json.put("locationId", otDx.getLocationId());

		// 查询
		List<CWmsStorageDetailT> sdList = dao.findStorageDetail(json);
		if (sdList.size() == 0) {
			throw new Exception("没有查询到库存详情");
		}

		for (CWmsStorageDetailT cWmsStorageDetailT : sdList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("locationId", cWmsStorageDetailT.getLocationId());
			map.put("projectId", cWmsStorageDetailT.getProjectId());
			map.put("materialId", cWmsStorageDetailT.getMaterialId());

			List<CWmsMaterialNumberT> mnDxList = dao.findMaterialNumberList(map);
			if (mnDxList == null) {
				throw new Exception("查询物料库存！");
			}

			CWmsMaterialNumberT mnDx = mnDxList.get(0);

			// 计算减去后的即将出库数量
			Integer num = mnDx.getLmminentRelease() - cWmsStorageDetailT.getMaterialNumber();
			if (num < 0) {
				throw new Exception("计算相减物料即将出库数量的时候库存小于0");
			}
			mnDx.setLmminentRelease(num);
			// 计算减去后的即将出库数量
			num = mnDx.getMaterialNumber() - cWmsStorageDetailT.getMaterialNumber();
			if (num < 0) {
				throw new Exception("计算相减物料库存的时候库存小于0");
			}

			// 判断是否还有物料库存
			// 如果没有就从物料库存里删除此条数据
			if (num != 0) {
				mnDx.setMaterialNumber(num);
				// 修改物料库存
				res = dao.updateMaterialNumber(mnDx);
				if (res <= 0) {
					throw new Exception("修改物料库存失败");
				}
			} else {
				// 删除库存
				res = dao.deleteMaterialNumber(mnDx.getId());
				if (res <= 0) {
					throw new Exception("删除物料库存失败");
				}
			}

		}

		// 查询当前库位总剩余物料数
		Integer countMaterialNumber = dao.findLocationCount(otDx.getLocationId());
		if (countMaterialNumber == 0) {
			// 库位为空 修改库位状态
			CWmsLocationT location = new CWmsLocationT();
			location.setId(otDx.getLocationId());
			location.setLocationStatus(0);
			location.setTray("");
			res = dao.updateLocation(location);
			if (res <= 0) {
				throw new Exception("修改库位状态失败");
			}

		}

		return true;
	}

	@Override
	public List<JSONObject> findStorageDetail(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.findStorageDetailSum(json);
	}

	@Override
	public boolean chuku0806(Integer outTaskqueueId, int locationId) throws Exception {

		JSONObject json = dao.findOutTaskqueueById(outTaskqueueId);
		json.put("trayCode", json.getString("tray"));
		if (json.getInteger("locationType") == 0) {
			// 立库
			json.put("methodName", "MaterialOutbound");
			ClientApiUseUtil.UseApi(json);

			// 通过库位id查询动作标记count
			int result = dao.findROuTaskqueueFlagCountByLocationId(locationId);
			if (result == 0) {
				// 修改临时队列表动作标记
				result = dao.updateROuTaskqueueFlag(outTaskqueueId);
				if (result != 1) {
					throw new Exception("修改临时队列表动作标记失败了");
				}
			} else {
				throw new Exception("该库位当前正在操作中，请稍后再试。");
			}

		} else {
			// 平库
			chuku(outTaskqueueId);
		}

		return false;
	}

	/**
	 * 直接出库
	 */
	@Override
	public void directDelivery(int outTaskqueueId, String listNo, String tray, int locationId) throws Exception {

		// 通过单号跟库位id查询临时库存详情
		List<JSONObject> storageDetailList = dao.findRStorageDetailByListNoAndLocationId(listNo, locationId);
		if(storageDetailList==null || storageDetailList.size()==0) {
			throw new Exception("查询临时库存详情为空");
		}

		// 删除临时库存详情
		int result = dao.deleteRStorageDetail(listNo, locationId);
		if (result <= 0) {
			throw new Exception("删除临时库存详情失败了");
		}

		StringBuffer storageDetailSqlBuff = new StringBuffer();
		storageDetailSqlBuff.append("value");

		for (int i = 0; i < storageDetailList.size(); i++) {
			JSONObject dx = storageDetailList.get(i);

			String sql = "(";
			sql += "now(),";
			sql += dx.getInteger("materialId") + ",";
			sql += dx.getInteger("materialNumber") + ",";
			sql += dx.getInteger("areaId") + ",";
			sql += dx.getInteger("reservoirAreaId") + ",";
			sql += dx.getInteger("locationId") + ",";
			sql += "'" + listNo + "',";
			sql += dx.getInteger("projectId") + ",";
			sql += "'" + tray + "',";
			sql += dx.getInteger("warehouseId") + ",";
			sql += "0,";
			sql += "0,";
			sql += "'" + dx.getString("barCode") + "',";
			sql += "'" + dx.getString("materialName") + "',";
			sql += "'" + dx.getString("warehouseName") + "',";
			sql += "'" + dx.getString("areaName") + "',";
			sql += "'" + dx.getString("reservoirAreaName") + "',";
			sql += "'" + dx.getString("locationName") + "',";
			sql += "'" + dx.getString("projectName") + "'";
			sql += "),";
			storageDetailSqlBuff.append(sql);
		}

		// 新增库存详情永久表数据
		result = dao.addPStorageDetail(
				storageDetailSqlBuff.toString().substring(0, storageDetailSqlBuff.toString().length() - 1));
		if (result <= 0) {
			throw new Exception("新增库存详情永久表数据失败了");
		}

		// 遍历出需要删除的库存id
		for (JSONObject jsonObject : storageDetailList) {

			//出库更新库存
			dao.updateStockMaterialNumber(jsonObject.getInteger("materialNumber"),
					jsonObject.getInteger("materialNumberId"));

		}

		//删除空库存的记录
		dao.deleteNullStock();

		// 通过id查询临时出库队列
		JSONObject rOutTaskqueue = dao.findROutTaskqueueById(outTaskqueueId);
		// 删除删除临时出库队列
		result = dao.deleteOutTaskqueue(outTaskqueueId);
		if (result <= 0) {
			throw new Exception("删除删除临时出库队列失败了");
		}
		// 新增永久出库队列
		result = dao.addPOutTaskqueueJson(rOutTaskqueue);
		if (result <= 0) {
			throw new Exception("新增永久出库队列失败了");
		}

		// 通过库位id查询该库位是否还有库存
		int count = dao.findMaterialNumberLocationCount(locationId);
		if (count == 0) {
			// 修改库位状态跟托盘码
			result = dao.updateLocationStateAndTray(locationId);
			if (result <= 0) {
				throw new Exception("修改库位状态跟托盘码失败了");
			}
		}

	}

	/**
	 * 查询条码
	 */
	@Override
	public List<JSONObject> findBarCode(String listNo, int materialId, int projectId, int locationId) {
		return dao.findBarCode(listNo, materialId, projectId, locationId);
	}

}
