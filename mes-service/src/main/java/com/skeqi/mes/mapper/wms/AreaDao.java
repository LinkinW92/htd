package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mapper.wms
 * @author yp
 * @date 2020年2月14日10:05:57
 * 区域
 */
public interface AreaDao {

	/**
	 * 查询所有仓库ID、NAME
	 * @return
	 */
	public List<WarehouseT> findWarehouseAll();

	/**
	 * 查询区域集合
	 * @param area
	 * @return
	 */
	public List<CWmsAreaT> findAreaList(CWmsAreaT area);

	/**
	 * 通过区域名称查询区域count
	 * @param name
	 * @param id
	 * @return
	 */
	public int findAreaCountByName(@Param("name")String name, @Param("id")int id);

	/**
	 * 通过区域编号查询区域count
	 * @param name
	 * @param id
	 * @return
	 */
	public int findAreaCountByNo(@Param("areaNo")String areaNo, @Param("id")int id);

	/**
	 * 新增区域
	 * @param areaT
	 * @return
	 */
	public Integer addArea(CWmsAreaT areaT);

	/**
	 * 删除区域
	 * @param areaId
	 * @return
	 */
	public Integer deleteArea(@Param("areaId")Integer areaId);

	/**
	 * 修改区域
	 * @param areaT
	 * @return
	 */
	public Integer updateArea(CWmsAreaT areaT);

}
