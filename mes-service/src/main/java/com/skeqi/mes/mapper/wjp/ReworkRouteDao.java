package com.skeqi.mes.mapper.wjp;


import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.PMesDefectReasonT;
import com.skeqi.mes.pojo.PMesStation;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;

public interface ReworkRouteDao {

	//根据总成查询
	@SuppressWarnings("rawtypes")
	RTrackingT findReworkRoute(Map map);

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

	//查看工位
	void findstation();

	//返工
	void updRework(@SuppressWarnings("rawtypes") Map map);

	//工位添加
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

	//返修原因
	@SuppressWarnings("rawtypes")
	List<PMesDefectReasonT> defectReason(Map map);

	@SuppressWarnings("rawtypes")
	void adddefectReason(Map map);

	//下线或报废后删除临时表的总成信息
	@SuppressWarnings("rawtypes")
	void delRTracking(Map map);

	//删除后添加到永久表
	@SuppressWarnings("rawtypes")
	void addPTracking(Map map);
	List<CMesStationT> listStationByLineId(Map map);


}
