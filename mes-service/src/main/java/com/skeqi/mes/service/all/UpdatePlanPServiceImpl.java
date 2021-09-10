package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.all.UpdatePlanPDao;
import com.skeqi.mes.pojo.api.MoveDataPT;
import com.skeqi.mes.pojo.api.MoveOrderDataPT;
import com.skeqi.mes.pojo.api.StationpassPT;
import com.skeqi.mes.pojo.api.UpdatePlanPT;
import com.skeqi.mes.service.all.UpdatePlanPService;

@Service
public class UpdatePlanPServiceImpl implements UpdatePlanPService {

	@Autowired
	UpdatePlanPDao dao;

	@Override
	public List<UpdatePlanPT> findList1(Integer runPlanId, Integer runLineId) {
		// TODO Auto-generated method stub
		return dao.findList1(runPlanId, runLineId);
	}

	@Override
	public List<UpdatePlanPT> findList2(String runLineId) {
		// TODO Auto-generated method stub
		return dao.findList2(runLineId);
	}

	@Override
	public List<UpdatePlanPT> findList3(String tempPlanLevel) {
		// TODO Auto-generated method stub
		return dao.findList3(tempPlanLevel);
	}

	@Override
	public List<UpdatePlanPT> findList4(String runPlanId) {
		// TODO Auto-generated method stub
		return dao.findList4(runPlanId);
	}

	@Override
	public void insert1(UpdatePlanPT dx) {
		// TODO Auto-generated method stub
		dao.insert1(dx);
	}

	@Override
	public void insert2(UpdatePlanPT dx) {
		// TODO Auto-generated method stub
		dao.insert2(dx);
	}

	@Override
	public void insert3(UpdatePlanPT dx) {
		// TODO Auto-generated method stub
		dao.insert3(dx);
	}

	@Override
	public void update1(String cPlanPlanLevel, String cPlanId) {
		// TODO Auto-generated method stub
		dao.update1(cPlanPlanLevel, cPlanId);
	}

	@Override
	public void insertAll(Integer planid, Integer lineId) {
		// TODO Auto-generated method stub
		List<UpdatePlanPT> list1 = dao.findList1(planid,lineId);   //打印
//		List<UpdatePlanPT> list2 = mapper.findList2(planid.toString());   //计划
//		List<UpdatePlanPT> list3 = mapper.findList3(planid.toString());   //计划
//		List<UpdatePlanPT> list4 = mapper.findList4(planid.toString());   //工单
		Map<String, Object> wo = dao.getWorkorderById(planid);

		for (UpdatePlanPT dx : list1) {
			dao.insert2(dx);   //打印
		}
//		for (UpdatePlanPT dx : list2) {
//			mapper.insert1(dx);   //计划
//		}
//		for (UpdatePlanPT dx : list4) {   //工单
//			mapper.insert3(dx);
//			mapper.update2(dx);
//		}
		dao.InsertWorkorderP(wo);
	}

	//
	//
	//
	//
	//
	//

	@Override
	public List<MoveDataPT> findListData1(String snMove) {
		// TODO Auto-generated method stub
		return dao.findListData1(snMove);
	}

	@Override
	public List<MoveDataPT> findListData2(String snMove) {
		// TODO Auto-generated method stub
		return dao.findListData2(snMove);
	}

	@Override
	public List<MoveDataPT> findListData3(String snMove) {
		// TODO Auto-generated method stub
		return dao.findListData3(snMove);
	}

	@Override
	public Integer insertData1(MoveDataPT dx) {
		// TODO Auto-generated method stub
		return dao.insertData1(dx);
	}

	@Override
	public Integer insertData2(MoveDataPT dx) {
		// TODO Auto-generated method stub
		return dao.insertData2(dx);
	}

	@Override
	public Integer insertData3(MoveDataPT dx) {
		// TODO Auto-generated method stub
		return dao.insertData3(dx);
	}

	public void mainData(String snMove) {

		List<MoveDataPT> list1 = findListData1(snMove);
		List<MoveDataPT> list2 = findListData2(snMove);
		List<MoveDataPT> list3 = findListData3(snMove);

		for (MoveDataPT dx : list1) {
			insertData1(dx);
		}
		for (MoveDataPT dx : list2) {
			insertData2(dx);
		}
		for (MoveDataPT dx : list3) {
			insertData3(dx);
		}

	}


	//
	//
	//
	//
	//
	//
	//
	@Override
	public List<MoveOrderDataPT> findOrder1(String runPrintCode) {
		// TODO Auto-generated method stub
		return dao.findOrder1(runPrintCode);
	}

	@Override
	public List<MoveOrderDataPT> findOrder2(String runOrderId) {
		// TODO Auto-generated method stub
		return dao.findOrder2(runOrderId);
	}

	@Override
	public void insertOrder1(MoveOrderDataPT dx) {
		// TODO Auto-generated method stub
		dao.insertOrder1(dx);
	}

	@Override
	public void insertOrder2(MoveOrderDataPT dx) {
		// TODO Auto-generated method stub
		dao.insertOrder2(dx);
	}

	@Override
	public void deleteOrder1(String cPlanPrintId) {
		// TODO Auto-generated method stub
		dao.deleteOrder1(cPlanPrintId);
	}

	@Override
	public void deleteOrder2(String cWorkOrderId) {
		// TODO Auto-generated method stub
		dao.deleteOrder2(cWorkOrderId);
	}


	//
	//
	//
	//
	//
	//
	@Override
	public List<StationpassPT> findStation1(String strLine) {
		// TODO Auto-generated method stub
		return dao.findStation1(strLine);
	}

	@Override
	public List<StationpassPT> findStation2(String strLine, String locationStationSt, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return dao.findStation2(strLine, locationStationSt, beginTime, endTime);
	}

	@Override
	public StationpassPT findStation3(String locationDataSn, String strLine, String locationStationSt) {
		// TODO Auto-generated method stub
		return dao.findStation3(locationDataSn, strLine, locationStationSt);
	}

	@Override
	public StationpassPT findStation4(String locationDataSn, String strLine, String locationStationSt) {
		// TODO Auto-generated method stub
		return dao.findStation4(locationDataSn, strLine, locationStationSt);
	}

	@Override
	public StationpassPT findStation5(String locationDataSn, String strLine, String locationStationSt) {
		// TODO Auto-generated method stub
		return dao.findStation5(locationDataSn, strLine, locationStationSt);
	}

	@Override
	public StationpassPT findStation6(String strLine, String locationStationSt, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return dao.findStation6(strLine, locationStationSt, beginTime, endTime);
	}

	@Override
	public StationpassPT findStation7(String strLine, String locationStationSt) {
		// TODO Auto-generated method stub
		return dao.findStation7(strLine, locationStationSt);
	}

	@Override
	public StationpassPT findStation8(String strLine, String locationStationSt, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return dao.findStation8(strLine, locationStationSt, beginTime, endTime);
	}

	@Override
	public StationpassPT findStation9(String strLine,String locationStationSt,String beginTime,String endTime) {
		// TODO Auto-generated method stub
		return dao.findStation9(strLine, locationStationSt, beginTime, endTime);
	}

	@Override
	public void insertPrint(String sn) {
		// TODO Auto-generated method stub
		UpdatePlanPT findListBySN = dao.findListBySN(sn);
		dao.insert2(findListBySN);
	}
}
