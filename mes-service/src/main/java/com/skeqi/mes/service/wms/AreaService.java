package com.skeqi.mes.service.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mapper.wms
 * @author yp
 * @date 2020年2月14日10:05:57
 * 区域
 */
public interface AreaService {

	/**
	 * 查询
	 * @param area
	 * @return
	 */
	public List<CWmsAreaT> findAreaList(CWmsAreaT area);

	/**
	 * 新增
	 * @param areaT
	 * @return
	 */
	public int addArea(CWmsAreaT areaT) throws Exception;

	/**
	 * 删除
	 * @param areaId
	 * @return
	 */
	public int deleteArea(Integer areaId);

	/**
	 * 修改
	 * @param areaT
	 * @return
	 */
	public int updateArea(CWmsAreaT areaT) throws Exception;

	/**
	 * 查询所有仓库ID、NAME
	 * @return
	 */
	public List<WarehouseT> findWarehouseAll();

}
