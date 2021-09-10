package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.CMesBomDetailT;
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesLeakageInfoT;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMaterialListDetailT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.CMesMfParametersDetailT;
import com.skeqi.mes.pojo.CMesOtherDataT;
import com.skeqi.mes.pojo.CMesStationT;
import org.apache.ibatis.annotations.Param;

public interface CMesBomService {

	//料单列表
	public List<CMesMaterialListT> findAllMaterialList(CMesMaterialListT t) throws ServicesException;

	//根据ID查询
	public CMesMaterialListT findMaterialListByid(Integer id) throws ServicesException;

	//添加
	public Integer addMaterialList(CMesMaterialListT t) throws ServicesException;

	//修改
	public Integer updateMaterialList(CMesMaterialListT t) throws ServicesException;

	//删除
	public Integer delMaterialList(Integer id) throws ServicesException;

	//料单明细列表
	public List<CMesMaterialListDetailT> findMaterialListDetail(CMesMaterialListDetailT t) throws ServicesException;

	//根据ID查询
	public CMesMaterialListDetailT findMaterialListDetailByid(Integer id) throws ServicesException;
	// 根据ID或者图号查询
	public CMesMaterialListDetailT findMaterialListDetailByfigureNo(Integer id,String figureNo) throws ServicesException;

	//添加
	public Integer addMaterialListDetail(CMesMaterialListDetailT t) throws ServicesException;
	public Integer addMaterialListDetailTwo(CMesMaterialListDetailT t) throws Exception;

	//修改
	public Integer updateMaterialListDetail(CMesMaterialListDetailT t) throws ServicesException;
	public Integer updateMaterialListDetailTwo(CMesMaterialListDetailT t) throws Exception;
	//删除
	public Integer delMaterialListDetail(Integer id) throws ServicesException;

	//制造参数清单
	public List<CMesManufactureParametersT> findAllMFG(CMesManufactureParametersT t) throws ServicesException;

	//根据ID查询
	public CMesManufactureParametersT findMFGByid(Integer id) throws ServicesException;

	//添加
	public Integer addMFG(CMesManufactureParametersT t) throws ServicesException;

	//修改
	public Integer updateMFG(CMesManufactureParametersT t) throws ServicesException;

	//删除
	public Integer delMFG(Integer id) throws ServicesException;

	//制造参数明细列表
	public List<CMesMfParametersDetailT> findAllMFGDetail(CMesMfParametersDetailT t) throws ServicesException;

	//根据ID查询
	public CMesMfParametersDetailT findMFGDetailByid(Integer id) throws ServicesException;

	//添加
	public Integer addMFGDetail(CMesMfParametersDetailT t) throws ServicesException;

	//修改
	public Integer updateMFGDetail(CMesMfParametersDetailT t) throws ServicesException;

	//删除
	public Integer delMFGDetail(Integer id) throws ServicesException;

	//bom清单
	public List<CMesBomT> findAllBom(CMesBomT t) throws ServicesException;

	//根据ID查询
	public CMesBomT findBomByid(Integer id) throws ServicesException;

	//添加
	public Integer addBom(CMesBomT t) throws ServicesException;

	//修改
	public Integer updateBom(CMesBomT t) throws ServicesException;

	//删除
	public Integer delBom(Integer id) throws ServicesException;

	//物料信息
	public List<CMesMaterialMsgT> findAllMaterialMsg(CMesMaterialMsgT t) throws ServicesException;

	//根据ID查询
	public CMesMaterialMsgT findMaterialMsgByid(Integer id) throws ServicesException;

	//添加
	public Integer addMaterialMsg(CMesMaterialMsgT t) throws ServicesException;

	//修改
	public Integer updateMaterialMsg(CMesMaterialMsgT t) throws ServicesException;

	//删除
	public Integer delMaterialMsg(Integer id) throws ServicesException;

	//螺栓信息
	public List<CMesBoltInfomationT> findAllBolt(CMesBoltInfomationT t) throws ServicesException;

	//根据ID查询
	public CMesBoltInfomationT findBoltByid(Integer id) throws ServicesException;

	//添加
	public Integer addBolt(CMesBoltInfomationT t) throws ServicesException;

	//修改
	public Integer updateBolt(CMesBoltInfomationT t) throws ServicesException;

	//删除
	public Integer delBolt(Integer id) throws ServicesException;

	//气密性信息
	public List<CMesLeakageInfoT> findAllLeakage(CMesLeakageInfoT t) throws ServicesException;

	//根据ID查询
	public CMesLeakageInfoT findLeakageByid(Integer id) throws ServicesException;

	//添加
	public Integer addLeakage(CMesLeakageInfoT t) throws ServicesException;

	//修改
	public Integer updateLeakage(CMesLeakageInfoT t) throws ServicesException;

	//删除
	public Integer delLeakage(Integer id) throws ServicesException;

	//其他信息列表
	public List<CMesOtherDataT> findAllOther(CMesOtherDataT t) throws ServicesException;

	//根据id查询
	public CMesOtherDataT findOtherByid(Integer id) throws ServicesException;

	//添加
	public Integer addOther(CMesOtherDataT t) throws ServicesException;

	//修改
	public Integer updateOther(CMesOtherDataT t) throws ServicesException;

	//删除
	public Integer delOther(Integer id) throws ServicesException;

	//bom详情列表
	public List<CMesBomDetailT> findAllBomDetail(CMesBomDetailT t) throws ServicesException;

	//根据ID查询bom列表
	public CMesBomDetailT findBomDetailByid(Integer id) throws ServicesException;

	//添加
	public Integer addBomDetail(CMesBomDetailT t) throws ServicesException;

	//修改
	public Integer updateBomDetail(CMesBomDetailT t) throws ServicesException;

	//删除
	public Integer delBomDetail(Integer id) throws ServicesException;

	//根据制造参数清单id查询所属产线下的工位
	public List<CMesStationT> findStationById(Integer id) throws ServicesException;

	//按产品型号查询Bom料单
	List<CMesMaterialListT> findBOMMaterialList(CMesMaterialListT material);

	//根据id或者根据bom编号查询BOM
	List<CMesMaterialListT> findMaterialByIdAndListNo(CMesMaterialListT material);

	//按物料编码或bomId查询BOM详情
	List<CMesMaterialListT> findBOMDetailByMaterialCode(CMesMaterialListDetailT material);

	//按物料编码查询BOM明细
	List<CMesMaterialListDetailT> findMaterialListDetailByCode(String materialCode);

	List<CMesMaterialListDetailT> findMaterialListDetailByLike(CMesMaterialListDetailT materialListDetail);

	public CMesMaterialListT findAllBomIdAndListName(Integer id,String listName);
	/**
	 * 按listNo查询bom信息
	 */
	List<CMesMaterialListT> findAllMaterialListByListNo(CMesMaterialListT one);
}
