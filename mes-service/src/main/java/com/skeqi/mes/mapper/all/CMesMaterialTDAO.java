package com.skeqi.mes.mapper.all;

import java.util.List;

import com.skeqi.mes.pojo.chenj.srm.req.CMesJlMaterialTReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CMesJlMaterialTRsp;
import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMESMaterialRepairT;
import com.skeqi.mes.pojo.CMesAssemblyType;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.pojo.CMesProductionProcessT;

/**
 * 物料管理
 *
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date : 2020年2月26日 下午1:50:18
 */
public interface CMesMaterialTDAO {


	// 物料类型列表
	public List<CMesMaterialTypeT> findAllMaterialType(CMesMaterialTypeT t);

	// 根据id查询物料类型
	public CMesMaterialTypeT findMaterialTypeByid(Integer id);

	// 添加物料类型
	public Integer addMaterialType(CMesMaterialTypeT t);

	// 修改物料类型
	public Integer updateMaterialType(CMesMaterialTypeT t);

	// 删除物料类型
	public Integer delMaterialType(Integer id);

	// 物料列表
	public List<CMesJlMaterialT> findAllMaterial(CMesJlMaterialT t);

	// 根据名称查询
	public Integer findMaterialByName(@Param("name") String name);

	// 根据id查询物料
	public CMesJlMaterialT findMaterialByid(Integer id);

	// 查询ID和物料名称
	public CMesJlMaterialT findMaterialByidAndMaterialName(@Param("id") Integer id,@Param("materialName") String materialName);

	// 添加
	public Integer addMaterial(CMesJlMaterialT t);

	// 修改
	public Integer updateMaterial(CMesJlMaterialT t);

	// 删除
	public Integer delMaterial(Integer id);

	// 物料维修列表
	public List<CMESMaterialRepairT> findAllMaterialRepair(CMESMaterialRepairT t);

	// 根据id查询物料维修列表
	public CMESMaterialRepairT findMaterialRepairByid(Integer id);

	// 添加
	public Integer addMaterialRepair(CMESMaterialRepairT t);

	// 修改状态(已完成)
	public Integer updateMaterialRepair(CMESMaterialRepairT t);

	// 删除
	public Integer delMaterialRepair(Integer id);

	// 工艺配置列表
	public List<CMesProductionProcessT> findAllProcess(CMesProductionProcessT p);

	// 根据ID查询列表
	public CMesProductionProcessT findProcessByid(Integer id);

	// 添加
	public Integer addProcess(CMesProductionProcessT p);

	// 修改
	public Integer updateProcess(CMesProductionProcessT p);

	// 删除
	public Integer delProcess(Integer id);

	// 总成类型列表
	public List<CMesAssemblyType> findAllAssembly(CMesAssemblyType type);

	// 根据ID查询
	public CMesAssemblyType findAssemblyByid(Integer id);

	// 添加
	public Integer addAssmbly(CMesAssemblyType t);

	// 修改
	public Integer updateAssmebly(CMesAssemblyType t);

	// 删除
	public Integer delAssmebly(Integer id);

	// 查询所有物料id+name
	public List<CMesJlMaterialT> findIdNameAll();

	// 添加物料
	//XT355_356_357
	public Integer addMaterialXT355_356_357(CMesJlMaterialT t);

	// 物料列表
	//XT355_356_357
	public List<CMesJlMaterialT> findAllMaterialXT355_356_357(CMesJlMaterialT t);

	// 修改物料
	//XT355_356_357
	public Integer updateMaterialXT355_356_357(CMesJlMaterialT t);

	//验证物料编码
	public Integer checkMaterialNo(@Param("materialNo")String materialNo);

	//验证物料名称
	public Integer checkMaterialName(@Param("materialName")String materialName);

	//删除物料
	public Integer deleteMaterial(@Param("id")Integer id);

    List<CMesJlMaterialT> findMaterialCodeList();

    //按物料编码查询物料列表
    List<CMesJlMaterialT> findProductMaterialList(CMesMaterialInstanceT cMesMaterialInstanceT);

	List<CMesMaterialT> findMaterialByMaterialCode(List<String> myList);

	// 查询物料列表
	List<CMesJlMaterialTRsp> findAllMaterialList(CMesJlMaterialTReq req);

}
