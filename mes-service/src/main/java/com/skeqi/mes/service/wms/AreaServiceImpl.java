package com.skeqi.mes.service.wms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.AreaDao;
import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	AreaDao dao;

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
	 * @param area
	 * @return
	 */
	@Override
	public List<CWmsAreaT> findAreaList(CWmsAreaT area) {
		List<CWmsAreaT> areaList = dao.findAreaList(area);
		return areaList;
	}

	/**
	 * 新增
	 *
	 * @param areaT
	 * @return
	 * @throws Exception
	 */
	@Override
	public int addArea(CWmsAreaT areaT) throws Exception {
		int count = dao.findAreaCountByName(areaT.getAreaName(), 0);
		if(count>0) {
			throw new Exception("区域名称已存在");
		}

		count = dao.findAreaCountByNo(areaT.getAreaNo(), 0);
		if(count>0) {
			throw new Exception("区域编号已存在");
		}
		return dao.addArea(areaT);
	}

	/**
	 * 删除
	 *
	 * @param areaId
	 * @return
	 */
	@Override
	public int deleteArea(Integer areaId) {
		return dao.deleteArea(areaId);
	}

	/**
	 * 修改
	 *
	 * @param areaT
	 * @return
	 */
	@Override
	public int updateArea(CWmsAreaT areaT) throws Exception {
		int count = dao.findAreaCountByName(areaT.getAreaName(), areaT.getId());
		if(count>0) {
			throw new Exception("区域名称已存在");
		}

		count = dao.findAreaCountByNo(areaT.getAreaNo(), areaT.getId());
		if(count>0) {
			throw new Exception("区域编号已存在");
		}
		return dao.updateArea(areaT);
	}

}
