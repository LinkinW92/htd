package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDefectEntryT;
import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesPatrolT;
import com.skeqi.mes.pojo.CMesReturnRepairT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.PTrackingT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.qh.CMesDefectManagerL;

public interface QualityService {

	//缺陷录入列表
	public List<CMesDefectEntryT> findAllDefectEntry(CMesDefectEntryT t) throws ServicesException;

	//根据ID查询
	public CMesDefectEntryT findDefectEntryByid(Integer id) throws ServicesException;

	//添加
	public Integer addDefectEntry(CMesDefectEntryT t) throws ServicesException;

	//修改
	public Integer updateDefectEntry(CMesDefectEntryT t) throws ServicesException;

	//删除
	public Integer delDefectEntry(Integer id) throws ServicesException;

	//巡检记录列表
	public List<CMesPatrolT> findAllPatrol(CMesPatrolT t) throws ServicesException;

	//根据ID查询
	public CMesPatrolT findPatrolByid(Integer id) throws ServicesException;

	//添加
	public Integer addPatrol(CMesPatrolT t) throws ServicesException;

	//修改
	public Integer updatePatrol(CMesPatrolT t) throws ServicesException;

	//删除
	public Integer delPatrol(Integer id) throws ServicesException;

	//返厂维修列表
	public List<CMesReturnRepairT> findAllReturnRepair(CMesReturnRepairT t) throws ServicesException;

	//根据id查询
	public CMesReturnRepairT findReturnRepairByid(Integer id) throws ServicesException;

	//添加
	public Integer addReturnRepair(CMesReturnRepairT t) throws ServicesException;

	//修改状态
	public Integer updateReturnRepair(Integer id) throws ServicesException;

	//删除
	public Integer delReturnRepair(Integer id) throws ServicesException;

	//缺陷等级列表
	public List<CMesDefectGradeManagerT> findAllDefectGrade(CMesDefectGradeManagerT t) throws ServicesException;

	//根据ID查询
	public CMesDefectGradeManagerT findDefectGradeByid(Integer id) throws ServicesException;

	//添加
	public Integer addDefectGrade(CMesDefectGradeManagerT t) throws ServicesException;

	//修改
	public Integer updateDefectGrade(CMesDefectGradeManagerT t) throws ServicesException;

	//删除
	public Integer delDefectGrade(Integer id) throws ServicesException;

	//缺陷列表
	public List<CMesDefectManager> findAllDefectManager(CMesDefectManager t) throws ServicesException;

	//根据ID查询
	public CMesDefectManager findDefectManagerByid(Integer id) throws ServicesException;

	//添加
	public Integer addDefectManager(CMesDefectManager t) throws ServicesException;

	//修改
	public Integer updateDefectManager(CMesDefectManager t) throws ServicesException;

	//删除
	public Integer delDefectManager(Integer id) throws ServicesException;

	//原因列表
	public List<CMesDefectResionT> findAllReason(CMesDefectResionT t) throws ServicesException;

	//根据ID查询
	public CMesDefectResionT findReasonByid(Integer id) throws ServicesException;

	//添加
	public Integer addReason(CMesDefectResionT t) throws ServicesException;

	//修改
	public Integer updateReason(CMesDefectResionT t) throws ServicesException;

	//删除
	public Integer delReason(Integer id) throws ServicesException;

	//责任类型列表
	public List<CMesDutyTypeManagerT> findAllDutyType(CMesDutyTypeManagerT t) throws ServicesException;

	//根据ID查询
	public CMesDutyTypeManagerT findDutyTypeByid(Integer id) throws ServicesException;

	//添加
	public Integer addDutyType(CMesDutyTypeManagerT t) throws ServicesException;

	//修改
	public Integer updateDutyType(CMesDutyTypeManagerT t) throws ServicesException;

	//删除
	public Integer delDutyType(Integer id) throws ServicesException;

	//责任类型列表
	public List<CMesDutyManagerT> findAllDutyManager(CMesDutyManagerT t) throws ServicesException;

	//根据ID查询
	public CMesDutyManagerT findDutyManagerByid(Integer id) throws ServicesException;

	//添加
	public Integer addDutyManager(CMesDutyManagerT t) throws ServicesException;

	//修改
	public Integer updateDutyManager(CMesDutyManagerT t) throws ServicesException;

	//删除
	public Integer delDutyManager(Integer id) throws ServicesException;

	//IQC列表
	public List<CMesIqcCheckT> findAllIQC(Map<String, Object> map) throws ServicesException;

	//根据ID查询
	public CMesIqcCheckT findIQCByid(Integer id) throws ServicesException;

	//添加
	public Integer addIQC(CMesIqcCheckT t) throws ServicesException;
//
	/**
	 * 根据id修改IQC校验信息
	 * @Title: updateIQC
	 * @param @param t
	 * @param @return
	 * @param @throws ServicesException 参数
	 * @return Integer返回类型
	 * @throws
	 */
	public Integer updateIQC(CMesIqcCheckT t) throws ServicesException;

	//复核
	public Integer updateStatus(Integer id,String name) throws ServicesException;

	//冻结/解冻
	public Integer updateFreeze(Integer id,Integer rid) throws ServicesException;

	//根据sn查询气密性表（P表）
	public List<PMesLeakageT> findPLeakage(String sn) throws ServicesException;

	//根据sn查询气密性表（R表）
	public List<RMesLeakage> findRLeakage(String sn) throws ServicesException;

	//查询关键件信息（R表)
	public List<RMesKeypart> findRKeypart(String sn) throws ServicesException;

	//查询关键件信息（P表)
	public List<PMesKeypartT> findPKeypart(String sn) throws ServicesException;

	//查询螺栓信息(R表)
	public List<RMesBolt> findRbolt(String sn) throws ServicesException;

	//查询螺栓信息(p表)
	public List<PMesBoltT> findPBolt(String sn) throws ServicesException;

	//查询总成表(R 表)
	public List<RTrackingT> findRTrack(String sn) throws ServicesException;

	//查询总成表(P表)
	public List<PTrackingT> findPTrack(String sn) throws ServicesException;

	//拆解关键件表R
	public Integer updateKeypartR(Integer id,String reasons) throws ServicesException;

	//拆解关键件表P
	public Integer updateKeypartP(Integer id,String reasons) throws ServicesException;

	//拆解螺栓表R
	public Integer updateBoltR(Integer id,String reasons) throws ServicesException;

	//拆解螺栓表P
	public Integer updateBoltP(Integer id,String reasons) throws ServicesException;

	//拆解气密性表
	public Integer updateLeakageR(Integer id,String reasons) throws ServicesException;

	//拆解气密性表
	public Integer updateLeakageP(Integer id,String reasons) throws ServicesException;

	//缺陷列表
	public List<CMesDefectManagerL> findAllDefectManagerL();


	//添加物料到P表
	void insertPKeypart(Integer id);
	//添加螺栓到P表
	void insertPBolt(Integer id);
	//添加气密到P表
	void insertLeakage(Integer id);

	//添加物料到P表
	void insertPKeypartByR(Integer id);
	//添加螺栓到P表
	void insertPBoltByR(Integer id);
	//添加气密到P表
	void insertLeakageByR(Integer id);



	//替换物料
	void updateKeyparts(Integer id,String num);
	//替换螺栓
	void updateBolt(Integer id,String a,String t);
	//替换气密
	void updateLeakage(Integer id,String leakagePv,String leakageLv);

	//替换物料
	void updateKeypartsR(Integer id,String num);
	//替换螺栓
	void updateBoltR(Integer id,String a,String t);
	//替换气密
	void updateLeakageR(Integer id,String leakagePv,String leakageLv);
}
