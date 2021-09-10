package com.skeqi.mes.mapper.all;

import java.util.List;

import com.skeqi.mes.pojo.CMesAlarmCodeT;

/**
 * 报警信息dao
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date   : 2020年2月22日 下午12:18:27
 */
public interface CMesAlarmCodeTDAO {

	//报警列表
	public List<CMesAlarmCodeT> findAllAlarm(CMesAlarmCodeT t);

	//根据id查询
	public CMesAlarmCodeT findAlarmByid(Integer id);

	//添加
	public Integer addAlarm(CMesAlarmCodeT c);

	//修改
	public Integer updateAlarm(CMesAlarmCodeT c);

	//删除
	public Integer delAlarm(Integer id);
}
