package com.skeqi.mes.mapper.yin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesAssemblyType;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMaterialListDetailT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.pojo.CMesMfParametersDetailT;
import com.skeqi.mes.pojo.CMesProductionProcessT;
import com.skeqi.mes.pojo.CMesStationT;

public interface MaterialDao {
	//根据物料名称查询物料编号
	public String findNo(@Param("name")String name);

	/**
	 * 工位列表
	 */
	List<CMesStationT> findStation();
	/**
	 * 查询总成类型
	 */
	List<CMesAssemblyType> assemblyTypeList(Map<String, Object> map);
	/**
	 * 通过总成类型编号查询总成类型数量
	 */
	Integer countAssemblyTypeByAddAssemblyTypeNo(Map<String, Object> map);
	/**
	 * 添加总成类型
	 */
	void addAssemblyType(Map<String, Object> map);
	/**
	 * 删除总成类型
	 */
	void delAssemblyType(Map<String, Object> map);
	/**
	 * 修改总成类型
	 */
	void editAssemblyType(Map<String, Object> map);
	/**
	 * 物料信息
	 */
	List<CMesMaterialT> materialList(Map<String, Object> map);

	void editstatus(String id);
	void editstatustwo();
	/**
	 * 添加物料信息
	 */
	Integer addMaterial(Map<String, Object> map);
	/**
	 * 删除物料
	 */
	Integer del();
	/**
	 * 修改物料信息
	 */
	void editMaterial(Map<String, Object> map);
	/**
	 * 删除物料信息
	 */
	void delMaterial(Map<String, Object> map);
	/**
	 * 物料类型信息
	 */
	List<CMesMaterialTypeT> materialTypeList(Map<String, Object> map);
	/**
	 * 添加物料类型
	 */
	void addMaterialType(Map<String, Object> map);
	/**
	 * 按名称统计物料类型个数
	 */
	Integer countMaterialTypeByName(Map<String, Object> map);
	/**
	 * 删除物料类型信息
	 */
	void delMaterialType(Map<String, Object> map);
	/**
	 * 修改物料类型信息
	 */
	void editMaterialType(Map<String, Object> map);
	/**
	 * 通过名称统计物料信息数量
	 */
	Integer countMaterialByName(Map<String, Object> map);
	/**
	 * 通过编号统计物料信息数量
	 */
	Integer countMaterialByNo(Map<String, Object> map);
	/**
	 * 料单列表
	 */
	List<CMesMaterialListT> materialLists(Map<String, Object> map);
	/**
	 * 获取最大的料单版本
	 */
	Double findMaxVersion();
	/**
	 * 料单清单列表
	 */
	List<CMesMaterialListDetailT> materialListDetails(Map<String, Object> map);
	/**
	 * 添加料单
	 */
	void addMaterialList(HashMap<String, Object> map);
	/**=
	 * 通过料单编号查询料单数量
	 */
	Integer countMaterialListByNo(HashMap<String, Object> map);

	/**
	 * 修改料单信息
	 */
	void editMaterialList(HashMap<String, Object> map);
	/**
	 * 删除料单信息
	 */
	void delMaterialList(HashMap<String, Object> map);
	/**
	 * 添加料单明细信息
	 */
	void addMaterialDetail(HashMap<String, Object> map);
	/**
	 * 修改料单明细信息
	 */
	void editMaterialDetail(HashMap<String, Object> map);
	/**
	 * 删除料单明细信息
	 */
	void delMaterialDetail(HashMap<String, Object> map);
	/**
	 * 查询料单参数最大的版本号
	 */
	Double findMaxmanu();
	/**
	 * 制造参数清单
	 */
	List<CMesManufactureParametersT> manuParameterLists(Map<String, Object> map);

	/**
	 * 添加制造清单
	 */
	void addManuParameterList(HashMap<String, Object> map);
	/**
	 * 通过制造清单编号查询数量
	 */
	Integer countaddManuParameterListByNo(HashMap<String, Object> map);
	/**
	 * 修改制造清单
	 */
	void editManuParameterList(HashMap<String, Object> map);
	/**
	 * 删除制造清单信息
	 */
	void delManuParameterList(HashMap<String, Object> map);
	/**
	 * 制造参数清单明细信息
	 */
	List<CMesMfParametersDetailT> mfParametersDetailList(Map<String, Object> map);
	/**
	 * 添加制造参数清单明细
	 */
	void addManuParameterListDetail(HashMap<String, Object> map);
	/**
	 * 修改制造清单明细
	 */
	void editManuParameterListDetail(HashMap<String, Object> map);
	/**
	 * 删除制造清单明细
	 */
	void delManuParameterListDetail(HashMap<String, Object> map);
	/**
	 * 工艺配置信息
	 */
	List<CMesProductionProcessT> productionProcess(Map<String, Object> map);
	/**
	 * 添加工艺配置
	 */
	void addProcessConfig(HashMap<String, Object> map);
	/**
	 * 检查工艺配置是否已存在
	 */
	int countProcessConfigByAll(HashMap<String, Object> map);
	/**
	 * 删除工艺配置
	 */
	void delProcessConfig(HashMap<String, Object> map);
	/**
	 * 修改工艺配置
	 */
	void editProcessConfig(HashMap<String, Object> map);

	int countMaterialListDetailByMaterilaListId(HashMap<String, Object> map);

	int countMaterialMsgByMaterialType(HashMap<String, Object> map);
	int countMaterialListByMaterialListId(HashMap<String, Object> map);
	int countProductionProcessByParameterListId(HashMap<String, Object> map);
	int countMaterialByAssemblyTypeId(HashMap<String, Object> map);
	Integer countMaterialListByName(HashMap<String, Object> map);
	Integer countaddManuParameterListByName(HashMap<String, Object> map);
	Integer countAssemblyTypeByAddAssemblyTypeName(HashMap<String, Object> map);

	public List<Map<String, Object>> findMaterialTypeStatistics();

	public List<Map<String, Object>> findMaterialNumStatistics();

	public List<Map<String, Object>> findMaterialList(Map<String, Object> map);
}
