package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mapper.wms
 * @author yp
 * @date 2020年2月14日10:05:57 库区
 */
public interface ReservoirAreaDao {

	/**
	 * 通过仓库ID查询区域
	 *
	 * @param warehouseId
	 * @return
	 */
	public List<CWmsAreaT> findAreaIdAndNameByWarehouseId(@Param("warehouseId") Integer warehouseId);

	/**
	 * 查询所有仓库ID、NAME
	 *
	 * @return
	 */
	public List<WarehouseT> findWarehouseAll();

	/**
	 * 查询
	 *
	 * @param reservoirArea
	 * @return
	 */
	public List<CWmsReservoirAreaT> findreservoirAreaList(CWmsReservoirAreaT reservoirArea);

	/**
	 * 通过编号查询数量
	 * @param raNo
	 * @param id
	 * @return
	 */
	public int findCountByNo(@Param("raNo")String raNo, @Param("id")int id);

	/**
	 * 通过名称查询数量
	 * @param raName
	 * @param id
	 * @return
	 */
	public int findCountByName(@Param("raName")String raName, @Param("id")int id);

	/**
	 * 新增
	 *
	 * @param reservoirArea
	 * @return
	 */
	public Integer addreservoirArea(CWmsReservoirAreaT reservoirArea);

	/**
	 * 删除
	 *
	 * @param reservoirAreaId
	 * @return
	 */
	public Integer deletereservoirArea(@Param("reservoirAreaId") Integer reservoirAreaId);

	/**
	 * 更新
	 *
	 * @param reservoirArea
	 * @return
	 */
	public Integer updatereservoirArea(CWmsReservoirAreaT reservoirArea);

}
