package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.ReservoirAreaDao;
import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月14日10:05:57 库区
 */
@Service
public class ReservoirAreaServiceImpl implements ReservoirAreaService {

	@Autowired
	ReservoirAreaDao dao;

	/**
	 * 查询所有仓库ID、NAME
	 */
	@Override
	public List<WarehouseT> findWarehouseAll() {
		List<WarehouseT> list = dao.findWarehouseAll();
		return list;
	}

	/**
	 * 查询
	 *
	 * @param reservoirArea
	 * @return
	 */
	@Override
	public List<CWmsReservoirAreaT> findreservoirAreaList(CWmsReservoirAreaT reservoirArea) {
		List<CWmsReservoirAreaT> reservoirAreaList = dao.findreservoirAreaList(reservoirArea);
		return reservoirAreaList;
	}

	/**
	 * 新增
	 *
	 * @param reservoirArea
	 * @return
	 * @throws Exception
	 */
	@Override
	public int addreservoirArea(CWmsReservoirAreaT reservoirArea) throws Exception {
		int count = dao.findCountByNo(reservoirArea.getRaNo(), 0);
		if(count>0) {
			throw new Exception("库区编号已存在");
		}
		count = dao.findCountByName(reservoirArea.getRaName(), 0);
		if(count>0) {
			throw new Exception("库区名称已存在");
		}
		return dao.addreservoirArea(reservoirArea);
	}

	/**
	 * 删除
	 *
	 * @param reservoirAreaId
	 * @return
	 */
	@Override
	public int deletereservoirArea(Integer reservoirAreaId) {
		return dao.deletereservoirArea(reservoirAreaId);
	}

	/**
	 * 更新
	 *
	 * @param reservoirArea
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updatereservoirArea(CWmsReservoirAreaT reservoirArea) throws Exception {
		int count = dao.findCountByNo(reservoirArea.getRaNo(), reservoirArea.getId());
		if(count>0) {
			throw new Exception("库区编号已存在");
		}
		count = dao.findCountByName(reservoirArea.getRaName(), reservoirArea.getId());
		if(count>0) {
			throw new Exception("库区名称已存在");
		}
		return dao.updatereservoirArea(reservoirArea);
	}

	@Override
	public List<CWmsAreaT> findAreaIdAndNameByWarehouseId(Integer warehouseId) {
		// TODO Auto-generated method stub
		return dao.findAreaIdAndNameByWarehouseId(warehouseId);
	}

}
