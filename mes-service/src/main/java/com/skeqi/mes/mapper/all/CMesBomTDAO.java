package com.skeqi.mes.mapper.all;

import java.util.List;

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

/**
 * bom
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date   : 2020年2月27日 下午3:45:19
 */
public interface CMesBomTDAO {

	//料单列表
	public List<CMesMaterialListT> findAllMaterialList(CMesMaterialListT t);

	//根据ID查询
	public CMesMaterialListT findMaterialListByid(Integer id);

	public CMesMaterialListT findAllBomIdAndListName(@Param("id") Integer id,@Param("listName") String listName);


	//根据id或者根据bom编号查询
	public List<CMesMaterialListT> findMaterialByIdAndListNo(CMesMaterialListT t);

	//添加
	public Integer addMaterialList(CMesMaterialListT t);

	//修改
	public Integer updateMaterialList(CMesMaterialListT t);

	//删除
	public Integer delMaterialList(Integer id);

	//料单明细列表
	public List<CMesMaterialListDetailT> findMaterialListDetail(CMesMaterialListDetailT t);

	//根据ID查询
	public CMesMaterialListDetailT findMaterialListDetailByid(Integer id);
	// 根据ID或者图号查询
	public CMesMaterialListDetailT findMaterialListDetailByfigureNo(@Param("id") Integer id,@Param("figureNo") String figureNo);

	//添加
	public Integer addMaterialListDetail(CMesMaterialListDetailT t);

	//修改
	public Integer updateMaterialListDetail(CMesMaterialListDetailT t);

	//根据制造参数清单id查询所属产线下的工位
	public List<CMesStationT> findStationById(Integer id);

	//删除
	public Integer delMaterialListDetail(Integer id);

	//制造参数清单
	public List<CMesManufactureParametersT> findAllMFG(CMesManufactureParametersT t);

	//根据ID查询
	public CMesManufactureParametersT findMFGByid(Integer id);

	//添加
	public Integer addMFG(CMesManufactureParametersT t);

	//修改
	public Integer updateMFG(CMesManufactureParametersT t);

	//删除
	public Integer delMFG(Integer id);

	//制造参数明细列表
	public List<CMesMfParametersDetailT> findAllMFGDetail(CMesMfParametersDetailT t);

	//根据ID查询
	public CMesMfParametersDetailT findMFGDetailByid(Integer id);

	//添加
	public Integer addMFGDetail(CMesMfParametersDetailT t);

	//修改
	public Integer updateMFGDetail(CMesMfParametersDetailT t);

	//删除
	public Integer delMFGDetail(Integer id);

	//bom清单
	public List<CMesBomT> findAllBom(CMesBomT t);

	//根据ID查询
	public CMesBomT findBomByid(Integer id);

	//添加
	public Integer addBom(CMesBomT t);

	//修改
	public Integer updateBom(CMesBomT t);

	//删除
	public Integer delBom(Integer id);

	//物料信息
	public List<CMesMaterialMsgT> findAllMaterialMsg(CMesMaterialMsgT t);

	//根据ID查询
	public CMesMaterialMsgT findMaterialMsgByid(Integer id);

	//添加
	public Integer addMaterialMsg(CMesMaterialMsgT t);

	//修改
	public Integer updateMaterialMsg(CMesMaterialMsgT t);

	//删除
	public Integer delMaterialMsg(Integer id);

	//螺栓信息
	public List<CMesBoltInfomationT> findAllBolt(CMesBoltInfomationT t);

	//根据ID查询
	public CMesBoltInfomationT findBoltByid(Integer id);

	//添加
	public Integer addBolt(CMesBoltInfomationT t);

	//修改
	public Integer updateBolt(CMesBoltInfomationT t);

	//删除
	public Integer delBolt(Integer id);

	//气密性信息
	public List<CMesLeakageInfoT> findAllLeakage(CMesLeakageInfoT t);

	//根据ID查询
	public CMesLeakageInfoT findLeakageByid(Integer id);

	//添加
	public Integer addLeakage(CMesLeakageInfoT t);

	//修改
	public Integer updateLeakage(CMesLeakageInfoT t);

	//删除
	public Integer delLeakage(Integer id);

	//其他信息列表
	public List<CMesOtherDataT> findAllOther(CMesOtherDataT t);

	//根据id查询
	public CMesOtherDataT findOtherByid(Integer id);

	//添加
	public Integer addOther(CMesOtherDataT t);

	//修改
	public Integer updateOther(CMesOtherDataT t);

	//删除
	public Integer delOther(Integer id);

	//bom详情列表
	public List<CMesBomDetailT> findAllBomDetail(CMesBomDetailT t);

	//根据ID查询bom列表
	public CMesBomDetailT findBomDetailByid(Integer id);

	//添加
	public Integer addBomDetail(CMesBomDetailT t);

	//修改
	public Integer updateBomDetail(CMesBomDetailT t);

	//删除
	public Integer delBomDetail(Integer id);

	List<CMesMaterialListT> findBOMMaterialList(CMesMaterialListT material);

	//按物料编码或bomId查询BOM详情
    List<CMesMaterialListT> findBOMDetailByMaterialCode(CMesMaterialListDetailT material);

    List<CMesMaterialListDetailT> findMaterialListDetailByCode(@Param("materialCode") String materialCode);

	List<CMesMaterialListDetailT> findMaterialListDetailByLike(CMesMaterialListDetailT materialListDetail);

	/**
	 * 按listNo查询bom信息
	 */
	List<CMesMaterialListT> findAllMaterialListByListNo(CMesMaterialListT one);

    Integer findMaterialListDetailByFigureNo(@Param("figureNo")String figureNo);
}
