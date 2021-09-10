package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.wms.InTaskqueueDao;
import com.skeqi.mes.pojo.CMesSystem;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.service.all.CMesSystemService;
import com.skeqi.mes.util.ClientApiUseUtil;
import com.skeqi.mes.util.ZplPrintingUtil;

/**
 * @package com.skeqi.mes.service.wms
 * @date 2020年2月18日
 * @author yp 入库队列
 *
 */
@Service
public class InTaskqueueServiceImpl implements InTaskqueueService {

	// public InTaskqueueServiceImpl() {
	// ApplicationContext ap = new
	// ClassPathXmlApplicationContext("spring-config.xml");
	// mapper = (InTaskqueueDao) ap.getBean("inTaskqueueDao");
	// }

	@Autowired
	CMesSystemService sService;

	@Autowired
	InTaskqueueDao dao;

	/**
	 * 查询
	 *
	 * @param inTaskqueue
	 * @return
	 */
	@Override
	public List<CWmsInTaskqueueT> findInTaskqueue(CWmsInTaskqueueT inTaskqueue) {
		List<CWmsInTaskqueueT> list = dao.findInTaskqueue(inTaskqueue);
		return list;
	}

	/**
	 * 通过id查询
	 *
	 * @param inTaskqueueId
	 * @return
	 */
	@Override
	public CWmsInTaskqueueT findInTaskqueueById(Integer inTaskqueueId) {
		CWmsInTaskqueueT inTaskqueue = new CWmsInTaskqueueT();
		inTaskqueue.setId(inTaskqueueId);

		List<CWmsInTaskqueueT> list = dao.findInTaskqueue(inTaskqueue);
		if (list.size() == 1) {
			inTaskqueue = list.get(0);
			return inTaskqueue;
		} else {
			return null;
		}
	}

	/**
	 * 新增
	 *
	 * @param inTaskqueue
	 * @return
	 */
	@Override
	public boolean addInTaskqueue(CWmsInTaskqueueT inTaskqueue) {
		Integer result = dao.addInTaskqueue(inTaskqueue);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新
	 *
	 * @param inTaskqueue
	 * @return
	 */
	@Override
	public boolean updateInTaskqueue(CWmsInTaskqueueT inTaskqueue) {
		Integer result = dao.updateInTaskqueue(inTaskqueue);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除
	 *
	 * @param inTaskqueueId
	 * @return
	 */
	@Override
	public boolean deleteInTaskqueue(Integer inTaskqueueId) {
		Integer result = dao.deleteInTaskqueue(inTaskqueueId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Test
	public void findInTaskqueue() {
		CWmsInTaskqueueT inTaskqueue = new CWmsInTaskqueueT();
		inTaskqueue.setId(1);
		inTaskqueue.setListNo("RK123456");

		List<CWmsInTaskqueueT> list = findInTaskqueue(inTaskqueue);
		for (CWmsInTaskqueueT cWmsInTaskqueueT : list) {
			System.out.println(cWmsInTaskqueueT);
		}
	}

	@Test
	public void findInTaskqueueById() {
		Integer inTaskqueueId = 1;
		CWmsInTaskqueueT inTaskqueue = findInTaskqueueById(inTaskqueueId);
		System.out.println(inTaskqueueId);
	}

//	@Test
//	public void addInTaskqueue() {
//		CWmsInTaskqueueT inTaskqueue = new CWmsInTaskqueueT(null, null, "RK123456", "SKQ123456", "1", null, 1);
//		boolean result = addInTaskqueue(inTaskqueue);
//		System.out.println(result);
//	}

	@Test
	public void updateInTaskqueue() {
		CWmsInTaskqueueT inTaskqueue = new CWmsInTaskqueueT();
		inTaskqueue.setId(1);
		inTaskqueue.setListNo("SKQ123456789");
		boolean result = updateInTaskqueue(inTaskqueue);
		System.out.println(result);
	}

	@Test
	public void deleteInTaskqueue() {
		Integer inTaskqueueId = 1;
		boolean result = deleteInTaskqueue(inTaskqueueId);
		System.out.println(result);
	}

	@Override
	public PageInfo<CWmsApprovalT> findApproval(JSONObject json) throws Exception {

		Integer pageNumber = json.getInteger("pageNumber");
		if (pageNumber == null) {
			pageNumber = 1;
		}

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
					List<CWmsApprovalT> list = dao.findApprovalXT355_356_357(null);
					PageInfo<CWmsApprovalT> pageInfo = new PageInfo<CWmsApprovalT>(list, 5);
					return pageInfo;
				}
			}
		}

		// TODO Auto-generated method stub
		PageHelper.startPage(pageNumber, 8);
		List<CWmsApprovalT> list = dao.findApproval(null);
		PageInfo<CWmsApprovalT> pageInfo = new PageInfo<CWmsApprovalT>(list, 5);
		return pageInfo;
	}

	@Override
	public PageInfo<Map<String, Object>> findStorageDetail(String listNo, Integer locationId, Integer pageNumber)
			throws Exception {

		PageHelper.startPage(pageNumber, 8);
		List<Map<String, Object>> list = dao.findStorageDetail(listNo, locationId);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list, 5);
		return pageInfo;
	}

	@Override
	public CWmsInTaskqueueT findZuoBiaoAndListNo(String tray) {
		// TODO Auto-generated method stub
		return dao.findZuoBiaoAndListNo(tray);
	}

	@Override
	public CWmsLocationT findLocation(Integer locationId) {
		// TODO Auto-generated method stub
		return dao.findLocation(locationId);
	}

	@Override
	public CWmsOutTaskqueueT findOutTaskqueue(Integer locationId) {
		// TODO Auto-generated method stub
		return dao.findOutTaskqueue(locationId);
	}

	@Override
	public CWmsInTaskqueueT findInTaskqueueByTray(String trayCode) {
		CWmsInTaskqueueT dx = new CWmsInTaskqueueT();
		dx.setTray(trayCode);
		List<CWmsInTaskqueueT> list = dao.findInTaskqueue(dx);
		if (list.size() == 0) {
			return null;
		} else {
			dx = list.get(0);
			return dx;
		}

	}

	@Override
	public boolean addPInTaskqueue(CWmsInTaskqueueT inTaskqueue) {
		// TODO Auto-generated method stub
		Integer res = dao.addPInTaskqueue(inTaskqueue);
		if (res == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean MaterialWarehousingXT355_356_357(JSONObject json) throws Exception {

		String listNo = json.getString("listNo");
		Integer locationId = json.getInteger("locationId");

		// 查询库位坐标
		CWmsLocationT location = dao.findLocation(locationId);
		if (location == null) {
			throw new Exception("查询库位信息出错了");
		}

		CWmsInTaskqueueT inTaskqueue = new CWmsInTaskqueueT();
		inTaskqueue.setLocationId(locationId);
		inTaskqueue.setListNo(listNo);

		List<CWmsInTaskqueueT> inTaskqueueList = dao.findInTaskqueue(inTaskqueue);
		if (inTaskqueueList == null || inTaskqueueList.size() == 0) {
			throw new Exception("查询临时入库队列出错了");
		}
		inTaskqueue = inTaskqueueList.get(0);

		// 需要调用的接口名称
		json.put("methodName", "MaterialWarehousing");
		// 托盘码
		json.put("trayCode", inTaskqueue.getTray());
		// 放料X坐标
		json.put("To_Row", location.getLocationX());
		// 放料y坐标
		json.put("To_List", location.getLocationY());
		// 放料z坐标
		json.put("To_Layer", location.getLocationZ());

		// 调用客户端接口
		json = ClientApiUseUtil.UseApi(json);

		if (json.getBoolean("remark")) {
			return true;
		} else {
			throw new Exception("调用客户端接口出现错误");
		}
	}

	@Override
	public Integer findMaterialNumberCount(String tray) {
		// TODO Auto-generated method stub
		return dao.findMaterialNumberCount(tray);
	}

	@Override
	public boolean ruku(Integer intaskqueueId,int locationStatus) throws Exception {
		CWmsInTaskqueueT inDx = new CWmsInTaskqueueT();
		inDx.setId(intaskqueueId);

		// 查询出临时入库队列的数据
		List<CWmsInTaskqueueT> otList = dao.findInTaskqueue(inDx);
		if (otList.size() != 1) {
			throw new Exception("查询出临时入库队列的数据返回结果为null");
		}
		inDx = otList.get(0);

		// 删除入库队列临时表数据
		Integer res = dao.deleteInTaskqueue(inDx.getId());
		if (res <= 0) {
			throw new Exception("删除入库队列临时表数据失败");
		}

		// 新增入库队列永久性表数据
		res = dao.addPInTaskqueue(inDx);
		if (res <= 0) {
			throw new Exception("新增入库队列永久性表数据失败");
		}

		// 查询
		// 查询临时库存详情
		List<JSONObject> rStorageDetailList = dao.findRStorageDetail(inDx.getListNo(), inDx.getLocationId());
		if (rStorageDetailList.size() == 0) {
			throw new Exception("没有查询到库存详情");
		}

		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("value");

		StringBuffer numberBuff = new StringBuffer();
		numberBuff.append("value");

		for (int i = 0; i < rStorageDetailList.size(); i++) {
			JSONObject dx = rStorageDetailList.get(i);

			String sql = "(";
			sql += "now(),";
			sql += dx.getInteger("materialId") + ",";
			sql += dx.getInteger("materialNumber") + ",";
			sql += dx.getInteger("areaId") + ",";
			sql += dx.getInteger("reservoirAreaId") + ",";
			sql += dx.getInteger("locationId") + ",";
			sql += "'" + dx.getString("listNo") + "',";
			sql += dx.getInteger("projectId") + ",";
			sql += "'" + inDx.getTray() + "',";
			sql += dx.getInteger("warehouseId") + ",";
			sql += "0,";
			sql += "1,";
			sql += "'" + dx.getString("barCode") + "',";
			sql += "'" + dx.getString("materialName") + "',";
			sql += "'" + dx.getString("warehouseName") + "',";
			sql += "'" + dx.getString("areaName") + "',";
			sql += "'" + dx.getString("reservoirAreaName") + "',";
			sql += "'" + dx.getString("locationName") + "',";
			sql += "'" + dx.getString("projectName") + "'";
			sql += ")";

			String sqlNumber = "(";
			sqlNumber += "'" + dx.getString("materialNo") + "',";
			sqlNumber += "'" + dx.getString("materialName") + "',";
			sqlNumber += dx.getInteger("materialNumber") + ",";
			sqlNumber += dx.getInteger("projectId") + ",";
			sqlNumber += dx.getInteger("materialId") + ",";
			sqlNumber += dx.getInteger("warehouseId") + ",";
			sqlNumber += dx.getInteger("areaId") + ",";
			sqlNumber += dx.getInteger("locationId") + ",";
			sqlNumber += dx.getInteger("reservoirAreaId") + ",";
			sqlNumber += "0,";
			sqlNumber += "now(),";
			sqlNumber += "0,";
			sqlNumber += "0,";
			sqlNumber += "'" + inDx.getTray() + "',";
			sqlNumber += "'" + dx.getString("barCode") + "'";
			sqlNumber += ")";

			if (i != (rStorageDetailList.size() - 1)) {
				sql += ",";
				sqlNumber += ",";
			}

			sqlBuff.append(sql);
			numberBuff.append(sqlNumber);

		}

		// 新增永久库存详情
		int result = dao.addPStorageDetail(sqlBuff.toString());
		if (result <= 0) {
			throw new Exception("新增永久库存详情失败了");
		}

		// 删除临时库存详情
		result = dao.deleteRStorageDetail(inDx.getListNo(), inDx.getLocationId());
		if (result <= 0) {
			throw new Exception("删除临时库存详情失败了");
		}

		// 新增物料库存
		result = dao.addMaterialNumberSql(numberBuff.toString());
		if (result <= 0) {
			throw new Exception("新增物料库存失败了");
		}

		if(locationStatus==0) {
			// 修改库位状态
			result = dao.updateLocationState(inDx.getLocationId(), 5);
			if (result <= 0) {
				throw new Exception("修改库位状态失败了");
			}
		}


		// 修改库位托盘码
		result = dao.updateLocationTrayCode(inDx.getLocationId(), inDx.getTray());
		if (result <= 0) {
			throw new Exception("修改库位托盘码失败了");
		}

		return true;
	}

	@Override
	public boolean printing(String listNo) throws Exception {

		Map<String, Object> map = dao.findAllMaterialBarcodeIdAndBarCode(listNo);
		if (map == null) {
			throw new Exception("单据号未查询出需要打印的条码");
		}
		boolean res = ZplPrintingUtil.printing(map.get("barCode").toString());
		if (!res) {
			throw new Exception(map.get("barCode") + "打印失败！");
		}

		Integer resNum = dao.updataAllMaterialBarcode(Integer.parseInt(map.get("id").toString()));
		return true;
	}

	@Override
	public List<JSONObject> findBarCode(String listNo, Integer locationId, Integer materialId) throws Exception {
		// TODO Auto-generated method stub
		return dao.findBarCode(listNo, locationId, materialId);
	}

	@Override
	public boolean updateBarCode(String sourceBarCode, String presentBarCode) throws Exception {

		Integer count = dao.findBarCodeCount(sourceBarCode);
		if (count == 0) {
			throw new Exception("原条码有误");
		}
		count = dao.findBarCodeCount(presentBarCode);
		if (count > 0) {
			throw new Exception("新条码已存在");
		}
		Integer result = dao.updateAllMaterialBarCode(sourceBarCode, presentBarCode);
		if (result != 1) {
			throw new Exception("更新失败");
		}
		result = dao.updateStorageDetailBarCode(sourceBarCode, presentBarCode);
		if (result != 1) {
			throw new Exception("更新失败");
		}

		return true;
	}

	/**
	 * 直接入库
	 */
	@Override
	public void directWarehousing(int inTaskqueueId,int locationStatus) throws Exception {

		// 通过id查询临时入库队列
		JSONObject inTaskqueueJson = dao.findRIntaskqueueById(inTaskqueueId);
		if (inTaskqueueJson == null) {
			throw new Exception("未查询到入库队列");
		}

		// 单据号
		String listNo = inTaskqueueJson.getString("listNo");
		// 托盘码
		String tray = inTaskqueueJson.getString("tray");
		// 库位id
		int locationId = inTaskqueueJson.getInteger("locationId");

		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("value");

		StringBuffer numberBuff = new StringBuffer();
		numberBuff.append("value");

		// 查询临时库存详情
		List<JSONObject> rStorageDetailList = dao.findRStorageDetail(listNo, locationId);
		for (int i = 0; i < rStorageDetailList.size(); i++) {
			JSONObject dx = rStorageDetailList.get(i);

			String sql = "(";
			sql += "now(),";
			sql += dx.getInteger("materialId") + ",";
			sql += dx.getInteger("materialNumber") + ",";
			sql += dx.getInteger("areaId") + ",";
			sql += dx.getInteger("reservoirAreaId") + ",";
			sql += dx.getInteger("locationId") + ",";
			sql += "'" + dx.getString("listNo") + "',";
			sql += dx.getInteger("projectId") + ",";
			sql += "'" + tray + "',";
			sql += dx.getInteger("warehouseId") + ",";
			sql += "0,";
			sql += "1,";
			sql += "'" + dx.getString("barCode") + "',";
			sql += "'" + dx.getString("materialName") + "',";
			sql += "'" + dx.getString("warehouseName") + "',";
			sql += "'" + dx.getString("areaName") + "',";
			sql += "'" + dx.getString("reservoirAreaName") + "',";
			sql += "'" + dx.getString("locationName") + "',";
			sql += "'" + dx.getString("projectName") + "'";
			sql += ")";

			String sqlNumber = "(";
			sqlNumber += "'" + dx.getString("materialNo") + "',";
			sqlNumber += "'" + dx.getString("materialName") + "',";
			sqlNumber += dx.getInteger("materialNumber") + ",";
			sqlNumber += dx.getInteger("projectId") + ",";
			sqlNumber += dx.getInteger("materialId") + ",";
			sqlNumber += dx.getInteger("warehouseId") + ",";
			sqlNumber += dx.getInteger("areaId") + ",";
			sqlNumber += dx.getInteger("locationId") + ",";
			sqlNumber += dx.getInteger("reservoirAreaId") + ",";
			sqlNumber += "0,";
			sqlNumber += "now(),";
			sqlNumber += "0,";
			sqlNumber += "0,";
			sqlNumber += "'" + tray + "',";
			sqlNumber += "'" + dx.getString("barCode") + "'";
			sqlNumber += ")";

			if (i != (rStorageDetailList.size() - 1)) {
				sql += ",";
				sqlNumber += ",";
			}

			sqlBuff.append(sql);
			numberBuff.append(sqlNumber);

		}

		// 新增永久库存详情
		int result = dao.addPStorageDetail(sqlBuff.toString());
		if (result <= 0) {
			throw new Exception("新增永久库存详情失败了");
		}

		// 删除临时库存详情
		result = dao.deleteRStorageDetail(listNo, locationId);
		if (result <= 0) {
			throw new Exception("删除临时库存详情失败了");
		}

		// 新增物料库存
		result = dao.addMaterialNumberSql(numberBuff.toString());
		if (result <= 0) {
			throw new Exception("新增物料库存失败了");
		}

		CWmsInTaskqueueT inTaskqueue = new CWmsInTaskqueueT();
		inTaskqueue.setListNo(listNo);
		inTaskqueue.setTray(tray);
		inTaskqueue.setLocationId(locationId);

		// 新增永久入库队列表
		result = dao.addPInTaskqueue(inTaskqueue);
		if (result <= 0) {
			throw new Exception("新增永久入库队列表失败了");
		}

		// 删除临时入库队列
		result = dao.deleteInTaskqueue(inTaskqueueId);
		if (result <= 0) {
			throw new Exception("删除临时入库队列失败了");
		}

		if(locationStatus==0) {
			// 修改库位状态
			result = dao.updateLocationState(locationId, 5);
			if (result <= 0) {
				throw new Exception("修改库位状态失败了");
			}
		}

		// 修改库位托盘码
		result = dao.updateLocationTrayCode(locationId, tray);
		if (result <= 0) {
			throw new Exception("修改库位托盘码失败了");
		}

	}

	/**
	 * 放行
	 */
	@Override
	public void TrayPass(int id, int locationId) throws Exception {
		// 通过库位id查询动作标记count
		int result = dao.findRInTaskqueueFlagCountByLocationId(locationId);
		if (result == 0) {
			// 修改临时队列表动作标记
			result = dao.updateRInTaskqueueFlag(id);
			if (result != 1) {
				throw new Exception("修改临时队列表动作标记失败了");
			}
		} else {
			throw new Exception("该库位当前正在操作中，请稍后再试。");
		}

	}

	/**
	 * 更新库位状态
	 *
	 * @param locationId
	 * @param locationStatus
	 */
	@Override
	public void updateLocationStatus(int locationId, int locationStatus) throws Exception {
		if (dao.updateLocationStatus(locationId, locationStatus) != 1) {
			throw new Exception("更新失败");
		}
	}
}
