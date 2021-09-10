package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.wms.WmsWarehouseDao;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月14日10:05:57 仓库
 */
@Service
public class WmsWarehouseServiceImpl implements WmsWarehouseService {

	@Autowired
	WmsWarehouseDao dao;

	/**
	 * 查询仓库集合
	 *
	 * @param warehouse
	 * @return
	 */
	@Override
	public List<WarehouseT> findWarehouseList(WarehouseT warehouse) throws ServicesException {
		// TODO Auto-generated method stub
		List<WarehouseT> warehouseList = dao.findWarehouseList(warehouse);
		return warehouseList;
	}

	/**
	 * 新增仓库
	 *
	 * @param warehouse
	 * @return
	 * @throws Exception
	 */
	@Override
	public int addWarehouse(WarehouseT warehouse) throws Exception {
		// 判断仓库名称是否存在
		int warehouseCount = dao.findWarehouseCount(warehouse.getName(), 0);
		if (warehouseCount > 0) {
			throw new Exception("仓库名称已存在");
		}

		return dao.addWarehouse(warehouse);

	}

	/**
	 * 删除仓库
	 *
	 * @param warehouseId
	 * @return
	 */
	@Override
	public int deleteWarehouse(Integer warehouseId) throws ServicesException {
		return dao.deleteWarehouse(warehouseId);
	}

	/**
	 * 更新仓库
	 *
	 * @param warehouse
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateWarehouse(WarehouseT warehouse) throws Exception {
		// 判断仓库名称是否存在
		int warehouseCount = dao.findWarehouseCount(warehouse.getName(), warehouse.getId());
		if (warehouseCount > 0) {
			throw new Exception("仓库名称已存在");
		}
		return dao.updateWarehouse(warehouse);
	}

	@Override
	public List<JSONObject> findUserAll() {
		// TODO Auto-generated method stub
		return dao.findUserAll();
	}

}
