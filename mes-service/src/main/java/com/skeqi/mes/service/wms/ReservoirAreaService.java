package com.skeqi.mes.service.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月14日10:05:57 库区
 */
public interface ReservoirAreaService {

	/**
	 * 查询所有仓库ID、NAME
	 * @return
	 */
	public List<WarehouseT> findWarehouseAll();

	/**
	 * 通过仓库ID查询区域
	 *
	 * @param warehouseId
	 * @return
	 */
	public List<CWmsAreaT> findAreaIdAndNameByWarehouseId(@Param("warehouseId") Integer warehouseId);

	/**
	 * 查询
	 *
	 * @param reservoirArea
	 * @return
	 */
	public List<CWmsReservoirAreaT> findreservoirAreaList(CWmsReservoirAreaT reservoirArea);

	/**
	 * 新增
	 *
	 * @param reservoirArea
	 * @return
	 */
	public int addreservoirArea(CWmsReservoirAreaT reservoirArea) throws Exception;

	/**
	 * 删除
	 *
	 * @param reservoirAreaId
	 * @return
	 */
	public int deletereservoirArea(Integer reservoirAreaId);

	/**
	 * 更新
	 *
	 * @param reservoirArea
	 * @return
	 */
	public int updatereservoirArea(CWmsReservoirAreaT reservoirArea) throws Exception;
}
