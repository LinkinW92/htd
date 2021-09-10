package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月15日
 * 库位
 */
public interface LocationService {

	/**
	 * 查询
	 * @param location
	 * @return
	 */
	public List<CWmsLocationT> findLocationList(CWmsLocationT location);

	/**
	 * 查询所有库位ID、NAME
	 * @param location
	 * @return
	 */
	public List<CWmsLocationT> findLocationAll();

	/**
	 * 新增
	 * @param location
	 * @return
	 */
	public int addLocation(CWmsLocationT location) throws Exception;

	/**
	 * 更新
	 * @param location
	 * @return
	 */
	public int updateLocation(CWmsLocationT location) throws Exception;

	/**
	 * 删除
	 * @param locationId
	 * @return
	 */
	public int deleteLocation(Integer locationId);

	/**
	 * 查询所有仓库ID、NAME
	 * @return
	 */
	public List<WarehouseT> findWarehouseAll();

	/**
	 * 查询所有区域ID、NAME
	 * @return
	 */
	public List<CWmsAreaT> findAreaAll(Integer warehouseId);

	/**
	 * 查询所有库区ID、NAME
	 * @return
	 */
	public List<CWmsReservoirAreaT> findreservoirAreaAll(Integer areaId);

}
