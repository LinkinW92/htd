package com.skeqi.mes.service.wll;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.PMesDefectReasonT;

public interface DefectSNManagerService {

	//责任类型表
	public List<CMesDutyManagerT> findDuty();

	//缺陷原因表
	public List<CMesDefectResionT> findDefect();

	//缺陷类型表
	public List<CMesDefectManager> findDefactManager();
	//添加
	public void add(PMesDefectReasonT t);
	//查询该总成是否存在
	public Integer findId(String SN);
	//更新
	public void updates(String SN);
}
