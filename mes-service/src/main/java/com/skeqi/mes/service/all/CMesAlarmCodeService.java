package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesAlarmCodeT;

public interface CMesAlarmCodeService {
	//报警列表
	public List<CMesAlarmCodeT> findAllAlarm(CMesAlarmCodeT t) throws ServicesException;

	//根据id查询
	public CMesAlarmCodeT findAlarmByid(Integer id) throws ServicesException;

	//添加
	public Integer addAlarm(CMesAlarmCodeT c) throws ServicesException;

	//修改
	public Integer updateAlarm(CMesAlarmCodeT c) throws ServicesException;

	//删除
	public Integer delAlarm(Integer id) throws ServicesException;
}
