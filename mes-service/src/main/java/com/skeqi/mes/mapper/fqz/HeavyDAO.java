package com.skeqi.mes.mapper.fqz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.RMesNewSn;
import com.skeqi.mes.pojo.RMesPlanPrintT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;

public interface HeavyDAO {

	//查询重工表
	public List<RMesNewSn> findAll();

	//查询所有计划
	public List<RMesPlanT> findRmesPlanT();

	//判断总成号是否可用
	public Integer jugdeSN(@Param("sn")String sn);

	//根据id查询明细
	public List<RMesWorkorderDetailT> findDetil(Integer id);

	//判断是否生成条码
	public Integer findPrint(Integer id);

	//获取所有条码
	public List<RMesPlanPrintT> findSn(Integer id);

	//添加新旧总成的关系表
	public void insertnewsn(@Param("newsn")String newsn,@Param("oldsn")String oldsn);

	//根据id查询sn
	public String findNewSN(Integer id);

	//判断旧的sn是否存在
	public Integer jugdeoldSN(@Param("sn")String sn);
	public Integer jugdeoldSN_New(@Param("sn")String sn);

	//检查新的sn是否存在
	public Integer jugdenewSN(@Param("sn")String sn);
}
