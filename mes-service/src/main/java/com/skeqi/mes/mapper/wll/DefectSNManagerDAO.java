package com.skeqi.mes.mapper.wll;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.PMesDefectReasonT;

public interface DefectSNManagerDAO {

	//查询责任表
	public List<CMesDutyManagerT> findDuty();

	//原因表
	public List<CMesDefectResionT> findDefect();

	//缺陷表
	public List<CMesDefectManager> findDefactManager();

	//添加
	public void add(@Param("t")PMesDefectReasonT t);

	//查询该总成是否存在
	public Integer findId(@Param("sn")String SN);

	//修改
	public void updates(@Param("sn")String SN);


}
