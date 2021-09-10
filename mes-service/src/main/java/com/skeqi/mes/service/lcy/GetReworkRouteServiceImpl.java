package com.skeqi.mes.service.lcy;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lcy.GetReworkRouteMapper;
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

@Service
public class GetReworkRouteServiceImpl implements GetReworkRouteService {

	@Autowired
	private GetReworkRouteMapper rrd;

	// 通过总成查询产品
	@Override
	public RMesTrackingT getReworkRoute(String sn) {
		// TODO Auto-generated method stub
		return rrd.getReworkRoute(sn);
	}

	// 查询螺栓
	@Override
	public List<PMesBoltT> queryBoltList(String sn) {
		return rrd.queryBoltList(sn);
	};

	// 查询物料
	@Override
	public List<PMesKeypartT> queryKeypartList(String sn) {
		return rrd.queryKeypartList(sn);
	};

	// 查询气密性
	@Override
	public List<PMesLeakageT> queryLeakageList(String sn) {
		return rrd.queryLeakageList(sn);
	}

	// 查询维修下线和报废的数据
	@Override
	public PMesDefectReasonT queryProductRepairDownLine(String sn) {
		// TODO Auto-generated method stub
		return rrd.queryProductRepairDownLine(sn);
	}

	// 缺陷管理列表
	@Override
	public List<CMesDefectManager> getDefectManagerList() {
		// TODO Auto-generated method stub
		return rrd.getDefectManagerList();
	}

	// 责任管理列表
	@Override
	public List<CMesDutyManagerT> getDutyManagerList() {
		// TODO Auto-generated method stub
		return rrd.getDutyManagerList();
	}

	// 缺陷原因管理列表
	@Override
	public List<CMesDefectResionT> getDefectResionList() {
		// TODO Auto-generated method stub
		return rrd.getDefectResionList();
	}

	// ************************产品报废维修**********************************
	@Override
	public void addDefectReason(String sn, Date date, String addReason, Integer defectManager, Integer dutyManager,
			Integer reasonManager, String reasonDetail) {
		// TODO Auto-generated method stub
		rrd.addDefectReason(sn, date, addReason, defectManager, dutyManager, reasonManager, reasonDetail);
	}

	@Override
	public RMesTrackingT getRTracking(String sn) {
		return rrd.getRTracking(sn);
	}

	@Override
	public void addPTracking(Date dt, String st, String bst, String sn, String enginesn, String gearboxsn,
			String typename, String traynum, String productnum, String status, String dis, Integer planId,
			String reworkFlag, Integer productionId, Integer lineId) {
		rrd.addPTracking(dt, st, bst, sn, enginesn, gearboxsn, typename, traynum, productnum, status, dis, planId,
				reworkFlag, productionId, lineId);

	}

	@Override
	public void deleteRTracking(String sn) {
		rrd.deleteRTracking(sn);
	}

	// 根据产线id 查询工位
	@Override
	public List<CMesStationT> queryStList(Integer lineId) {
		// TODO Auto-generated method stub
		return rrd.queryStList(lineId);
	}

	// 根据总成查询返修工位
	@Override
	public List<ReworkWayT> queryReworkStList(String sn) {
		// TODO Auto-generated method stub
		return rrd.queryReworkStList(sn);
	}

	@Override
	public void addReworkWay(Date date, String sn, String stName, int stId, int serialNo) {
		rrd.addReworkWay(date, sn, stName, stId, serialNo);

	}

	@Override
	public void updatRTracking(String sn) {
		rrd.updatRTracking(sn);
	}

	@Override
	public List<PMesDefectReasonT> queryDefectResionList(String sn, String getStartTime, String getEndTime,
			String getLine) {
		// TODO Auto-generated method stub
		return rrd.queryDefectResionList(sn, getStartTime, getEndTime, getLine);
	}

	@Override
	public void removeBackWay(String sn) {
		rrd.removeBackWay(sn);
	}

	@Override
	public Integer addDefectReasonl(PMesDefectReasonT defectReason) {
		// TODO Auto-generated method stub
		return rrd.addDefectReasonl( defectReason);
	}

}
