package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mapper.wms
 * @author yp
 * @date 2020年2月15日
 * 库位
 */
public interface LocationDao {

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
	 * 通过库位名称查询同名数量
	 * @param locationName
	 * @param id
	 * @return
	 */
	public int findCountByName(@Param("locationName")String locationName, @Param("id")int id);

	/**
	 * 通过库位编号查询同名数量
	 * @param locationNo
	 * @param id
	 * @return
	 */
	public int findCountByNo(@Param("locationNo")String locationNo, @Param("id")int id);

	/**
	 * 新增
	 * @param location
	 * @return
	 */
	public Integer addLocation(CWmsLocationT location);

	/**
	 * 更新
	 * @param location
	 * @return
	 */
	public Integer updateLocation(CWmsLocationT location);

	/**
	 * 删除
	 * @param locationId
	 * @return
	 */
	public Integer deleteLocation(Integer locationId);

	/**
	 * 查询所有仓库ID、NAME
	 * @return
	 */
	public List<WarehouseT> findWarehouseAll();

	/**
	 * 查询所有区域ID、NAME
	 * @return
	 */
	public List<CWmsAreaT> findAreaAll(@Param("warehouseId")Integer warehouseId);

	/**
	 * 查询所有库区ID、NAME
	 * @return
	 */
	public List<CWmsReservoirAreaT> findreservoirAreaAll(@Param("areaId")Integer areaId);

}
