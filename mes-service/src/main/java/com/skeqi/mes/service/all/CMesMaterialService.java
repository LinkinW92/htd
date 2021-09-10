package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.pojo.chenj.srm.req.CMesJlMaterialTReq;
import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import com.skeqi.mes.util.Rjson;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMESMaterialRepairT;
import com.skeqi.mes.pojo.CMesAssemblyType;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.pojo.CMesProductionProcessT;

import javax.servlet.http.HttpServletRequest;

public interface CMesMaterialService {



	// 物料类型列表
	public List<CMesMaterialTypeT> findAllMaterialType(CMesMaterialTypeT t) throws ServicesException;

	// 根据id查询物料类型
	public CMesMaterialTypeT findMaterialTypeByid(Integer id) throws ServicesException;

	// 添加物料类型
	public Integer addMaterialType(CMesMaterialTypeT t) throws ServicesException;

	// 修改物料类型
	public Integer updateMaterialType(CMesMaterialTypeT t) throws ServicesException;

	// 删除物料类型
	public Integer delMaterialType(Integer id) throws ServicesException;

	// 物料列表
	public List<CMesJlMaterialT> findAllMaterial(CMesJlMaterialT t) throws ServicesException;

	// 根据id查询物料
	public CMesJlMaterialT findMaterialByid(Integer id) throws ServicesException;

	// 查询ID和物料名称
	public CMesJlMaterialT findMaterialByidAndMaterialName(Integer id, String materialName);
	// 添加
	public Integer addMaterial(CMesJlMaterialT t) throws Exception;

	// 修改
	public Integer updateMaterial(HttpServletRequest request) throws Exception;

	// 删除
	public Integer delMaterial(Integer id) throws ServicesException;

	// 物料维修列表
	public List<CMESMaterialRepairT> findAllMaterialRepair(CMESMaterialRepairT t) throws ServicesException;

	// 根据id查询物料维修列表
	public CMESMaterialRepairT findMaterialRepairByid(Integer id) throws ServicesException;

	// 添加
	public Integer addMaterialRepair(CMESMaterialRepairT t) throws ServicesException;

	// 修改
	public Integer updateMaterialRepair(CMESMaterialRepairT t) throws ServicesException;

	// 删除
	public Integer delMaterialRepair(Integer id) throws ServicesException;

	// 工艺配置列表
	public List<CMesProductionProcessT> findAllProcess(CMesProductionProcessT p) throws ServicesException;

	// 根据ID查询列表
	public CMesProductionProcessT findProcessByid(Integer id) throws ServicesException;

	// 添加
	public Integer addProcess(CMesProductionProcessT p) throws ServicesException;

	// 修改
	public Integer updateProcess(CMesProductionProcessT p) throws ServicesException;

	// 删除
	public Integer delProcess(Integer id) throws ServicesException;

	// 总成类型列表
	public List<CMesAssemblyType> findAllAssembly(CMesAssemblyType type) throws ServicesException;

	// 根据ID查询
	public CMesAssemblyType findAssemblyByid(Integer id) throws ServicesException;

	// 添加
	public Integer addAssmbly(CMesAssemblyType t) throws ServicesException;

	// 修改
	public Integer updateAssmebly(CMesAssemblyType t) throws ServicesException;

	// 删除
	public Integer delAssmebly(Integer id) throws ServicesException;

	// 查询所有物料id+name
	public List<CMesJlMaterialT> findIdNameAll();

	// 物料列表
	public List<CMesJlMaterialT> findAllMaterialXT355_356_357(CMesJlMaterialT t) throws ServicesException;

	//验证物料编码
	public boolean checkMaterialNo(String materialNo);

	//验证物料名称
	public boolean checkMaterialName(String materialName);

	//删除物料
	public boolean deleteMaterial(Integer id) throws Exception;

    List<CMesJlMaterialT> findMaterialCodeList();

    //按物料编码查询物料列表
	List<CMesJlMaterialT> findProductMaterialList(CMesMaterialInstanceT cMesMaterialInstanceT);

    List<CMesMaterialT> findMaterialByMaterialCode(List<String> myList);

	Rjson findAllMaterialList(CMesJlMaterialTReq req);
}
