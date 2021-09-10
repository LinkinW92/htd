package com.skeqi.mes.service.wms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.LocationDao;
import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月15日 库位
 */
@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationDao dao;

	/**
	 * 查询
	 *
	 * @param location
	 * @return
	 */
	@Override
	public List<CWmsLocationT> findLocationList(CWmsLocationT location) {
		List<CWmsLocationT> locationList = dao.findLocationList(location);
		return locationList;
	}

	/**
	 * 查询所有库位ID、NAME
	 *
	 * @param location
	 * @return
	 */
	@Override
	public List<CWmsLocationT> findLocationAll() {
		List<CWmsLocationT> locationList = dao.findLocationAll();
		return locationList;
	}

	/**
	 * 新增
	 *
	 * @param location
	 * @return
	 */
	@Override
	public int addLocation(CWmsLocationT location) throws Exception {
		int count = dao.findCountByNo(location.getLocationNo(), 0);
		if(count!=0) {
			throw new Exception("库位编号已存在");
		}
		count = dao.findCountByName(location.getLocationName(), 0);
		if(count!=0) {
			throw new Exception("库位名称已存在");
		}
		return dao.addLocation(location);
	}

	/**
	 * 更新
	 *
	 * @param location
	 * @return
	 */
	@Override
	public int updateLocation(CWmsLocationT location) throws Exception {
		int count = dao.findCountByNo(location.getLocationNo(), location.getId());
		if(count!=0) {
			throw new Exception("库位编号已存在");
		}
		count = dao.findCountByName(location.getLocationName(), location.getId());
		if(count!=0) {
			throw new Exception("库位名称已存在");
		}
		return dao.updateLocation(location);
	}

	/**
	 * 删除
	 *
	 * @param locationId
	 * @return
	 */
	@Override
	public int deleteLocation(Integer locationId) {
		return dao.deleteLocation(locationId);
	}

	@Override
	public List<WarehouseT> findWarehouseAll() {
		// TODO Auto-generated method stub
		return dao.findWarehouseAll();
	}

	@Override
	public List<CWmsAreaT> findAreaAll(Integer warehouseId) {
		// TODO Auto-generated method stub
		return dao.findAreaAll(warehouseId);
	}

	@Override
	public List<CWmsReservoirAreaT> findreservoirAreaAll(Integer areaId) {
		// TODO Auto-generated method stub
		return dao.findreservoirAreaAll(areaId);
	}

}
