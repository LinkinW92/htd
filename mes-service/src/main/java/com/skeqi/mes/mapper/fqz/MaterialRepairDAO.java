package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMESMaterialRepairT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesStationT;

/**
 * 物料维修dao
 * @author : FQZ
 * @Package: com.skeqi.mapper.fqz
 * @date   : 2019年10月12日 上午9:05:10
 */
public interface MaterialRepairDAO {

	/**
	 * 查询物料维修列表
	* @author FQZ
	* @date 2019年10月12日上午9:31:39
	 */
	public List<CMESMaterialRepairT> findAll(Map<String,Object> map);

	/**
	 * 工位列表
	 */
	public List<CMesStationT> findStation();

	/**
	 * 物料列表
	* @author FQZ
	* @date 2019年10月12日上午10:08:03
	 */
	public List<CMesMaterialT> findMateial();

	/**
	 * 添加
	* @author FQZ
	* @date 2019年10月12日上午11:30:41
	 */
	public void insertM(CMESMaterialRepairT c);

	/**
	 * 修改状态
	* @author FQZ
	* @date 2019年10月12日上午11:30:59
	 */
	public void updatestatus(CMESMaterialRepairT c);

	/**
	 * 删除
	 */
	public void delmaterial(String id);
}
