package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.all.WmsClientApiDao;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * 立库客户端专用api
 *
 * @author yinp
 *
 */
@Service
public class WmsClientApiServiceImpl implements WmsClientApiService {

	@Autowired
	WmsClientApiDao dao;

	@Override
	public List<CWmsOutTaskqueueT> findOutTaskqueue(CWmsOutTaskqueueT outTaskqueue) {
		// TODO Auto-generated method stub
		return dao.findOutTaskqueue(outTaskqueue);
	}

	/**
	 * 删除出库队列
	 *
	 * @param outTaskqueueId
	 * @return
	 */
	@Override
	public int deleteOutTaskqueue(Integer outTaskqueueId) throws Exception {
		Integer result = dao.deleteOutTaskqueue(outTaskqueueId);
		if (result != 1) {
			throw new Exception("删除出库队列失败了");
		}
		return result;
	}

	/**
	 * 新增出库队列P表数据
	 * @param outTaskqueue
	 * @return
	 */
	@Override
	public int addPOutTaskqueue(CWmsOutTaskqueueT outTaskqueue) throws Exception {
		Integer result = dao.addPOutTaskqueue(outTaskqueue);
		if (result != 1) {
			throw new Exception("删除出库队列失败了");
		}
		return result;
	}

	@Override
	public List<CWmsMaterialNumberT> findMaterialNumberList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findMaterialNumberList(map);
	}

	/**
	 * 新增物料库存
	 *
	 * @param MaterialNumber
	 * @return
	 */
	@Override
	public int addMaterialNumber(CWmsMaterialNumberT materialNumber) throws Exception {
		int result = dao.addMaterialNumber(materialNumber);
		if (result != 1) {
			throw new Exception("新增物料库存失败了");
		}
		return result;
	}

	/**
	 * 更新物料库存
	 * @param MaterialNumber
	 * @return
	 */
	@Override
	public int updateMaterialNumber(CWmsMaterialNumberT materialNumber) throws Exception {
		int result = dao.updateMaterialNumber(materialNumber);
		if (result != 1) {
			throw new Exception("更新物料库存失败了");
		}
		return result;
	}

	/**
	 * 删除物料库存
	 * @param MaterialNumberId
	 * @return
	 */
	@Override
	public int deleteMaterialNumber(Integer materialNumberId) throws Exception {
		int result = dao.deleteMaterialNumber(materialNumberId);
		if (result != 1) {
			throw new Exception("删除物料库存失败了");
		}
		return result;
	}

	@Override
	public Integer findLocationCount(Integer locationId) {
		return dao.findLocationCount(locationId);
	}

	@Override
	public List<CWmsStorageDetailT> findStorageDetail(CWmsStorageDetailT storageDetail) {
		// TODO Auto-generated method stub
		return dao.findStorageDetail(storageDetail);
	}

	/**
	 * 新增库存详情P表
	 *
	 * @param dx
	 * @return
	 * @throws Exception
	 */
	@Override
	public int addPStorageDetail(CWmsStorageDetailT dx) throws Exception {
		int result = dao.addPStorageDetail(dx);
		if (result != 1) {
			throw new Exception("新增库存详情P表失败了");
		}
		return result;
	}

	/**
	 * 删除库存详情R 表
	 *
	 * @param id
	 * @return
	 */
	@Override
	public int deleteStorageDetail(Integer id) throws Exception {
		int result = dao.deleteStorageDetail(id);
		if (result != 1) {
			throw new Exception("删除库存详情R 表失败了");
		}
		return result;
	}

	@Override
	public CWmsMaterialNumberT findBarcodeGeneratedMaterialNumberList(Integer locationId, Integer projectId,
			Integer materialId) {
		// TODO Auto-generated method stub
		return dao.findBarcodeGeneratedMaterialNumberList(locationId, projectId, materialId);
	}

	/**
	 * 修改库位状态
	 *
	 * @param locationId
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateLocationState(int locationId, int state) throws Exception {
		int result = dao.updateLocationState(locationId, state);
//		if (result != 1) {
//			throw new Exception("修改库位状态失败了");
//		}
		return result;
	}

	/**
	 * 修改库位托盘码
	 *
	 * @param locationId
	 * @param trayCode
	 * @return
	 */
	@Override
	public int updateTrayCode(int locationId, String trayCode) throws Exception {
		int result = dao.updateTrayCode(locationId, trayCode);
		if (result != 1) {
			throw new Exception("修改库位托盘码失败了");
		}
		return result;
	}

	/**
	 * 通过托盘码查询临时入库队列信息
	 *
	 * @param trayCode
	 * @return
	 */
	@Override
	public JSONObject findRInTaskqueueByTrayCode(String trayCode) {
		return dao.findRInTaskqueueByTrayCode(trayCode);
	}

	/**
	 * 新增永久入库队列信息
	 *
	 * @param json
	 * @return
	 */
	@Override
	public int addPInTaskqueue(JSONObject json) throws Exception {
		int result = dao.addPInTaskqueue(json);
		if (result != 1) {
			throw new Exception("新增永久入库队列信息失败了");
		}
		return result;
	}

	/**
	 * 删除出库队列临时表数据
	 *
	 * @param id
	 * @return
	 */
	@Override
	public int deleteRInTaskqueue(int id) throws Exception {
		int result = dao.deleteRInTaskqueue(id);
		if (result != 1) {
			throw new Exception("删除出库队列临时表数据失败了");
		}
		return result;
	}

	/**
	 * 查询库位坐标
	 *
	 * @param locationId
	 * @return
	 */
	@Override
	public JSONObject findlocationZuoBiaoByTayCode(String trayCode) {
		return dao.findlocationZuoBiaoByTayCode(trayCode);
	}

	/**
	 * 新增临时入库队列表信息
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Override
	public int addRInTaskqueue(JSONObject json) throws Exception {
		int result = dao.addRInTaskqueue(json);
		if (result != 1) {
			throw new Exception("新增临时入库队列表信息失败了");
		}
		return result;
	}

	@Override
	public void updateNumberDt(Integer projectId, Integer materialId, Integer locationId) {
		// TODO Auto-generated method stub
		dao.updateNumberDt(projectId, materialId, locationId);
	}

	@Override
	public int updateStockMaterialNumber(int materialNumber, int materialNumberId) {
		// TODO Auto-generated method stub
		return dao.updateStockMaterialNumber(materialNumber, materialNumberId);
	}

	@Override
	public int deleteNullStock() {
		// TODO Auto-generated method stub
		return dao.deleteNullStock();
	}

	@Override
	public Integer findStockCount(int materialId, int projectId, int locationId) {
		// TODO Auto-generated method stub
		return dao.findStockCount(materialId, projectId, locationId);
	}

	@Override
	public int updateStockNumber(int id, int number) {
		// TODO Auto-generated method stub
		return dao.updateStockNumber(id, number);
	}

}
