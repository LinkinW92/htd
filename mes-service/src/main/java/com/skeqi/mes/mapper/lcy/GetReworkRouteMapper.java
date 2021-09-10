package com.skeqi.mes.mapper.lcy;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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

public interface GetReworkRouteMapper {

	// 通过总成查询产品
	RMesTrackingT getReworkRoute(@Param("sn") String sn);

	// 查询螺栓
	List<PMesBoltT> queryBoltList(@Param("sn") String sn);

	// 查询物料
	List<PMesKeypartT> queryKeypartList(@Param("sn") String sn);

	// 查询气密性
	List<PMesLeakageT> queryLeakageList(@Param("sn") String sn);

	// 根据总成查询数据
	PMesDefectReasonT queryProductRepairDownLine(@Param("sn") String sn);

	// 缺陷原因管理
	List<CMesDefectResionT> getDefectResionList();

	// 责任管理列表
	List<CMesDutyManagerT> getDutyManagerList();

	// 缺陷管理列表
	List<CMesDefectManager> getDefectManagerList();

	// 添加原因信息
	void addDefectReason(@Param("sn") String sn, @Param("date") Date date, @Param("addReason") String addReason,
			@Param("defectManager") Integer defectManager, @Param("dutyManager") Integer dutyManager,
			@Param("reasonManager") Integer reasonManager, @Param("reasonDetail") String reasonDetail);

	// 获取产品运行表的信息
	RMesTrackingT getRTracking(@Param("sn") String sn);

	// 给产品下线表添加信息
	void addPTracking(@Param("dt") Date dt, @Param("st") String st, @Param("bst") String bst, @Param("sn") String sn,
			@Param("enginesn") String enginesn, @Param("gearboxsn") String gearboxsn,
			@Param("typename") String typename, @Param("traynum") String traynum,
			@Param("productnum") String productnum, @Param("status") String status, @Param("dis") String dis,
			@Param("planId") Integer planId, @Param("reworkFlag") String reworkFlag,
			@Param("productionId") Integer productionId, @Param("lineId") Integer lineId);

	// 删除产品运行表信息
	void deleteRTracking(@Param("sn") String sn);

	// 根据产线id查询工位
	List<CMesStationT> queryStList(@Param("lineId") Integer lineId);

	// 根据总成查询返修数据
	List<ReworkWayT> queryReworkStList(@Param("sn") String sn);

	// 添加返工
	void addReworkWay(@Param("date") Date date, @Param("sn") String sn, @Param("stName") String stName,
			@Param("stId") int stId, @Param("serialNo") int serialNo);

	// 修改返工数据
	void updatRTracking(@Param("sn") String sn);

	//
	List<PMesDefectReasonT> queryDefectResionList(@Param("sn") String sn, @Param("getStartTime") String getStartTime,
			@Param("getEndTime") String getEndTime, @Param("getLine") String getLine);

	// 删除返修路线
	void removeBackWay(@Param("sn") String sn);


	Integer addDefectReasonl(PMesDefectReasonT defectReason);

}
