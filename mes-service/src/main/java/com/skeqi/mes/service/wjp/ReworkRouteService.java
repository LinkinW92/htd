package com.skeqi.mes.service.wjp;


import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.PMesDefectReasonT;
import com.skeqi.mes.pojo.PMesStation;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;

public interface ReworkRouteService {

	//根据总成查询
	RTrackingT findReworkRoute(@SuppressWarnings("rawtypes") Map map);

	//原因列表
	List<RTrackingT> findReason();

	//详细原因的修改
	void updReworkRoute(@SuppressWarnings("rawtypes") Map map);

	//数据转移
	void insPtrakingT(@SuppressWarnings("rawtypes") Map map);

	//删除信息
	void delReworkRoute(@SuppressWarnings("rawtypes") Map map);

	//工位路径列表
	List<CMesStationT> findStationName();

	//工位移动
	void updateReworkWayT(@SuppressWarnings("rawtypes") Map map);

	//返工
	void updRework(@SuppressWarnings("rawtypes") Map map);

	//新增工位
	void insert(@SuppressWarnings("rawtypes") Map map);

	//工位下表判断
	CMesStationT stationName(@SuppressWarnings("rawtypes") Map map);

	//根据工位查ID
	int findStationId(@SuppressWarnings("rawtypes") Map map);

	//查询原有的序列号
	ReworkWayT selectNo(@SuppressWarnings("rawtypes") Map map);

	//删除原有的序号
	int deleteNo(@SuppressWarnings("rawtypes") Map map);
	@SuppressWarnings("rawtypes")
	public List<ReworkWayT> findReworkBySn(Map map);
	@SuppressWarnings("rawtypes")
	List<PMesDefectReasonT> defectReason(Map map);
	@SuppressWarnings("rawtypes")
	void adddefectReason(Map map);
	@SuppressWarnings("rawtypes")
	void delRTracking(Map map);
	@SuppressWarnings("rawtypes")
	void addPTracking(Map map);
	List<CMesStationT> listStationByLineId(Map map);

}
