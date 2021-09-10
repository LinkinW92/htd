package com.skeqi.mes.service.wjp;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesAlarmCodeT;

public interface WarningMessageService {

		//报警列表
		@SuppressWarnings("rawtypes")
		public List<CMesAlarmCodeT> findAll(Map map);

		//删除报警信息
		public void delAlarm(int id);

		//条件查询报警信息
		@SuppressWarnings("rawtypes")
		public List<CMesAlarmCodeT> selAlarm(Map map);

		//报警新增
		@SuppressWarnings("rawtypes")
		public void addAlarm(Map map);

		//根据id查询
		@SuppressWarnings("rawtypes")
		public CMesAlarmCodeT findById(Map map);

		//报警修改
		@SuppressWarnings("rawtypes")
		public void updateAlarm(Map map);

}
