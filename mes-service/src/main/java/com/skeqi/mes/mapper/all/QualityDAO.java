package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

/**
 * 质量管理
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date   : 2020年3月2日 下午2:30:02
 */
public interface QualityDAO {

	//缺陷录入列表
	public List<CMesDefectEntryT> findAllDefectEntry(CMesDefectEntryT t);

	//根据ID查询
	public CMesDefectEntryT findDefectEntryByid(Integer id);

	//添加
	public Integer addDefectEntry(CMesDefectEntryT t);

	//修改
	public Integer updateDefectEntry(CMesDefectEntryT t);

	//删除
	public Integer delDefectEntry(Integer id);

	//巡检记录列表
	public List<CMesPatrolT> findAllPatrol(CMesPatrolT t);

	//根据ID查询
	public CMesPatrolT findPatrolByid(Integer id);

	//添加
	public Integer addPatrol(CMesPatrolT t);

	//修改
	public Integer updatePatrol(CMesPatrolT t);

	//删除
	public Integer delPatrol(Integer id);

	//返厂维修列表
	public List<CMesReturnRepairT> findAllReturnRepair(CMesReturnRepairT t);

	//根据id查询
	public CMesReturnRepairT findReturnRepairByid(Integer id);

	//添加
	public Integer addReturnRepair(CMesReturnRepairT t);

	//修改状态
	public Integer updateReturnRepair(Integer id);

	//删除
	public Integer delReturnRepair(Integer id);

	//缺陷等级列表
	public List<CMesDefectGradeManagerT> findAllDefectGrade(CMesDefectGradeManagerT t);

	//根据ID查询
	public CMesDefectGradeManagerT findDefectGradeByid(Integer id);

	//添加
	public Integer addDefectGrade(CMesDefectGradeManagerT t);

	//修改
	public Integer updateDefectGrade(CMesDefectGradeManagerT t);

	//删除
	public Integer delDefectGrade(Integer id);

	//缺陷列表
	public List<CMesDefectManager> findAllDefectManager(CMesDefectManager t);

	//根据ID查询
	public CMesDefectManager findDefectManagerByid(Integer id);

	//添加
	public Integer addDefectManager(CMesDefectManager t);
	//修改
	public Integer updateDefectManager(CMesDefectManager t);

	//删除
	public Integer delDefectManager(Integer id);

	//原因列表
	public List<CMesDefectResionT> findAllReason(CMesDefectResionT t);

	//根据ID查询
	public CMesDefectResionT findReasonByid(Integer id);

	//添加
	public Integer addReason(CMesDefectResionT t);

	//修改
	public Integer updateReason(CMesDefectResionT t);

	//删除
	public Integer delReason(Integer id);

	//责任类型列表
	public List<CMesDutyTypeManagerT> findAllDutyType(CMesDutyTypeManagerT t);

	//根据ID查询
	public CMesDutyTypeManagerT findDutyTypeByid(Integer id);

	//添加
	public Integer addDutyType(CMesDutyTypeManagerT t);

	//修改
	public Integer updateDutyType(CMesDutyTypeManagerT t);

	//删除
	public Integer delDutyType(Integer id);

	//责任类型列表
	public List<CMesDutyManagerT> findAllDutyManager(CMesDutyManagerT t);

	//根据ID查询
	public CMesDutyManagerT findDutyManagerByid(Integer id);

	//添加
	public Integer addDutyManager(CMesDutyManagerT t);

	//修改
	public Integer updateDutyManager(CMesDutyManagerT t);

	//删除
	public Integer delDutyManager(Integer id);

	//IQC列表
	public List<CMesIqcCheckT> findAllIQC(CMesIqcCheckT t);

	//根据ID查询
	public CMesIqcCheckT findIQCByid(Integer id);

	//添加
	public Integer addIQC(CMesIqcCheckT t);

	//修改
	public Integer updateIQC(CMesIqcCheckT t);

	//复核
	public Integer updateStatus(@Param("id")Integer id,@Param("productionHandie")String name);

	//冻结/解冻
	public Integer updateFreeze(@Param("id")Integer id,@Param("freeze")Integer rid);

	//根据sn查询气密性表（P表）
	public List<PMesLeakageT> findPLeakage(@Param("sn")String sn);

	//根据sn查询气密性表（R表）
	public List<RMesLeakage> findRLeakage(@Param("sn")String sn);

	//查询关键件信息（R表)
	public List<RMesKeypart> findRKeypart(@Param("sn")String sn);

	//查询关键件信息（P表)
	public List<PMesKeypartT> findPKeypart(@Param("sn")String sn);

	//查询螺栓信息(R表)
	public List<RMesBolt> findRbolt(@Param("sn")String sn);

	//查询螺栓信息(p表)
	public List<PMesBoltT> findPBolt(@Param("sn")String sn);

	//查询总成表(R 表)
	public List<RTrackingT> findRTrack(@Param("sn")String sn);

	//查询总成表(P表)
	public List<PTrackingT> findPTrack(@Param("sn")String sn);

	//拆解关键件表R
	public Integer updateKeypartR(@Param("id")Integer id,@Param("reasons")String reasons);

	//拆解关键件表P
	public Integer updateKeypartP(@Param("id")Integer id,@Param("reasons")String reasons);

	//拆解螺栓表R
	public Integer updateBoltR(@Param("id")Integer id,@Param("reasons")String reasons);

	//拆解螺栓表P
	public Integer updateBoltP(@Param("id")Integer id,@Param("reasons")String reasons);

	//拆解气密性表
	public Integer updateLeakageR(@Param("id")Integer id,@Param("reasons")String reasons);

	//拆解气密性表
	public Integer updateLeakageP(@Param("id")Integer id,@Param("reasons")String reasons);

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
	void updateKeyparts(@Param("id")Integer id,@Param("num")String num);
	//替换螺栓
	void updateBolt(@Param("id")Integer id,@Param("a")String a,@Param("t")String t);
	//替换气密
	void updateLeakage(@Param("id")Integer id,@Param("leakagePv")String leakagePv,@Param("leakageLv")String leakageLv);

	//替换物料
	void updateKeypartsR(@Param("id")Integer id,@Param("num")String num);
	//替换螺栓
	void updateBoltsR(@Param("id")Integer id,@Param("a")String a,@Param("t")String t);
	//替换气密
	void updateLeakagesR(@Param("id")Integer id,@Param("leakagePv")String leakagePv,@Param("leakageLv")String leakageLv);
}
