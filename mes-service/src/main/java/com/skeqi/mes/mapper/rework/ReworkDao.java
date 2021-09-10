package com.skeqi.mes.mapper.rework;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ReworkDao{

	//转移物料数据
	void moveKeypart(@Param("sn")String sn,@Param("st")String st,@Param("step")String step);
	//转移物料（不回收）
	void moveKeypartLi(@Param("sn")String sn,@Param("st")String st,@Param("step")String step);

	//替换物料数据
	void replaceKeypart(@Param("sn")String sn,@Param("st")String st,@Param("step")String step,@Param("barcode")String barcode);
	//拆解物料数据
	void dismanKeypart(@Param("sn")String sn,@Param("st")String st,@Param("step")String step);

	//转移螺栓数据
	void moveBolt(@Param("sn")String sn,@Param("st")String st,@Param("step")String step);
	//拆解螺栓数据
	void dismanBolt(@Param("sn")String sn,@Param("st")String st,@Param("step")String step);

	//转移气密数据
	void moveLeakage(@Param("sn")String sn,@Param("st")String st,@Param("step")String step);
	//拆解气密数据
	void dismanLeakage(@Param("sn")String sn,@Param("st")String st,@Param("step")String step);

	//总成判废接口
	void Annulment(@Param("sn")String sn,@Param("reason")String reason);

	//查询此总成是否判废
	Map<String,Object> findisNg(@Param("sn")String sn);

	//查询此总成需要做的工位
	List<Map<String, Object>> findStBySn(@Param("sn")String sn);

	//恢复OK状态
	void updateOK(@Param("sn")String sn);

	//转移track
	void moveTrack(@Param("sn")String sn,@Param("reason")String reason);

	//转移过站表
	void moveStation(@Param("sn")String sn);

	//删除此总成的信息
	void delDataBySN(@Param("sn")String sn);

	//查询是否在线（判断是否符合判废条件）
	Integer findIsScrap(@Param("sn")String sn);

	//查询总成是否下线
	Integer findRoute(@Param("sn")String sn);

	//查询所有产品
	List<Map<String,Object>> findP();

	//查询所有产线
	List<Map<String, Object>> findL();

	//查询总配方
	List<Map<String,Object>> findTotalRecipe(@Param("lineId")String lineId,@Param("proId")String proId);

	//查询工艺路线
	List<Map<String,Object>> findProRoute(@Param("lineId")String lineId,@Param("proId")String proId);

	//制定返修工单
	void addReworkWork(@Param("sim")String sim,@Param("lineId")String lineId,@Param("proId")String proId,@Param("recipeId")String recipeId,@Param("routeId")String routeId);

	//查询最大的工单id
	Integer findMaxWid();

	//添加打印表
	void addPlanPrint(@Param("lineId")String lineId,@Param("proId")String proId,@Param("sn")String sn,@Param("wid")String wid);

	//添加R运行表
	void addRTrack(@Param("sn")String sn,@Param("proId")String proId,@Param("lineId")String lineId,@Param("wid")String wid);

	//删除P表
	void deletePtrack(@Param("sn")String sn);

	//修改物料状态为返修
	void updateKeypartFlag(@Param("sn")String sn);

	List<Map<String,Object>> findIdAndWorkOrderId(@Param("id") Integer id,@Param("workOrderId") String workOrderId);
}
