package com.skeqi.mes.service.rework;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.qh.APIResult;
import com.skeqi.mes.pojo.qh.ReworkEntity;

public interface ReworkService {

	//当前工位返修
	void currentStation(ReworkEntity re);

	//总成判废接口
	void Annulment(String sn,String reason);

	//查询此总成是否判废
	Map<String,Object> findisNg(@Param("sn")String sn);

	//查询此总成需要做的工位
	List<Map<String, Object>> findStationBySn(@Param("sn")String sn);

	//恢复OK状态
	void updateOK(String sn);

	//报废接口
	APIResult ScrapSN(String sn,String type,String reason);

	//查询总成是否下线
	Integer findRoute(String sn);

	//查询所有产品
	List<Map<String,Object>> findP();

	//查询所有产线
	List<Map<String, Object>> findL();

	//查询总配方
	List<Map<String,Object>> findTotalRecipe(String lineId,String proId);

	//查询工艺路线
	List<Map<String,Object>> findProRoute(String lineId,String proId);

	//制定返修工单
	void addReworkWork(String lineId,String proId,String recipeId,String routeId,String sn);

	//删除P表数据
	void deleteP(String sn);

	List<Map<String,Object>> findIdAndWorkOrderId(Integer id,String workOrderId);
}
