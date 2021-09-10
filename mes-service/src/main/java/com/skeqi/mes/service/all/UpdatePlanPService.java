package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.pojo.api.MoveDataPT;
import com.skeqi.mes.pojo.api.MoveOrderDataPT;
import com.skeqi.mes.pojo.api.StationpassPT;
import com.skeqi.mes.pojo.api.UpdatePlanPT;

/**
 * @date 2020年1月13日
 * @author yinp
 *
 */
public interface UpdatePlanPService {

	public List<UpdatePlanPT> findList1(Integer runPlanId, Integer runLineId);

	public List<UpdatePlanPT> findList2(String runLineId);

	public List<UpdatePlanPT> findList3(String tempPlanLevel);

	public List<UpdatePlanPT> findList4(String runPlanId);

	public void insert1(UpdatePlanPT dx);

	public void insert2(UpdatePlanPT dx);

	public void insert3(UpdatePlanPT dx);

	public void update1(String cPlanPlanLevel, String cPlanId);

	public void insertAll(Integer planId,Integer lineId);

	public void insertPrint(String sn);
	//
	//
	//
	//
	//
	//
	//

	public List<MoveDataPT> findListData1(String snMove);

	public List<MoveDataPT> findListData2(String snMove);

	public List<MoveDataPT> findListData3(String snMove);

	public Integer insertData1(MoveDataPT dx);

	public Integer insertData2(MoveDataPT dx);

	public Integer insertData3(MoveDataPT dx);

	public void mainData(String snMove);

	//
	//
	//
	//
	//
	//
	public List<MoveOrderDataPT> findOrder1(String runPrintCode);

	public List<MoveOrderDataPT> findOrder2(String runOrderId);

	public void insertOrder1(MoveOrderDataPT dx);

	public void insertOrder2(MoveOrderDataPT dx);

	public void deleteOrder1(String cPlanPrintId);

	public void deleteOrder2(String cWorkOrderId);

	//
	//
	//
	//
	//
	//
	//
	public List<StationpassPT> findStation1(String strLine);

	public List<StationpassPT> findStation2(String strLine, String locationStationSt, String beginTime, String endTime);

	public StationpassPT findStation3(String locationDataSn, String strLine, String locationStationSt);

	public StationpassPT findStation4(String locationDataSn, String strLine, String locationStationSt);

	public StationpassPT findStation5(String locationDataSn, String strLine, String locationStationSt);

	public StationpassPT findStation6(String strLine, String locationStationSt, String beginTime, String endTime);

	public StationpassPT findStation7(String strLine, String locationStationSt);

	public StationpassPT findStation8(String strLine, String locationStationSt, String beginTime, String endTime);

	public StationpassPT findStation9(String strLine,String locationStationSt,String beginTime,String endTime);


}
