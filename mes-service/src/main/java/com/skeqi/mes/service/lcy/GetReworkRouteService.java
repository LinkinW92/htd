package com.skeqi.mes.service.lcy;

import java.util.Date;
import java.util.List;

import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesDefectReasonT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.RMesTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;

public interface GetReworkRouteService {

	// 通过总成查询产品
	RMesTrackingT getReworkRoute(String sn);

	// 查询螺栓
	List<PMesBoltT> queryBoltList(String sn);

	// 查询物料
	List<PMesKeypartT> queryKeypartList(String sn);

	// 查询气密性
	List<PMesLeakageT> queryLeakageList(String sn);

	// 查询维修和报废的信息
	PMesDefectReasonT queryProductRepairDownLine(String sn);

	// 缺陷管理列表
	List<CMesDefectManager> getDefectManagerList();

	// 责任管理列表
	List<CMesDutyManagerT> getDutyManagerList();

	// 原因管理列表
	List<CMesDefectResionT> getDefectResionList();

	// 插入原因信息
	void addDefectReason(String sn, Date date, String addReason, Integer defectManager, Integer dutyManager,
			Integer reasonManager, String reasonDetail);

	RMesTrackingT getRTracking(String sn);

	void addPTracking(Date dt, String st, String bst, String sn, String enginesn, String gearboxsn, String typename,
			String traynum, String productnum, String status, String dis, Integer planId, String reworkFlag,
			Integer productionId, Integer lineId);

	// 删除运行表中的数据
	void deleteRTracking(String sn);

	// 根据产线id查询工位
	List<CMesStationT> queryStList(Integer lineId);

	// 根据总成查询返修工位
	List<ReworkWayT> queryReworkStList(String sn);

	void addReworkWay(Date date, String sn, String stName, int stId, int serialNo);

	// 返工
	void updatRTracking(String sn);

	List<PMesDefectReasonT> queryDefectResionList(String sn, String getStartTime, String getEndTime, String getLine);

	// 删除返修路线
	void removeBackWay(String sn);

	//插入原因信息
	Integer addDefectReasonl(PMesDefectReasonT defectReason);

}
