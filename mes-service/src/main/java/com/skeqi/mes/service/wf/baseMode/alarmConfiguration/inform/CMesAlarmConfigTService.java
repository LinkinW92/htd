package com.skeqi.mes.service.wf.baseMode.alarmConfiguration.inform;

import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.inform.CMesAlarmConfigT;

import java.util.List;
import java.util.Map;

public interface CMesAlarmConfigTService{

    CMesAlarmConfigT selectByCode(String code);

    int deleteByPrimaryKey(Integer id);

    Integer insertSelective(CMesAlarmConfigT record) throws Exception;

    int updateByPrimaryKeySelective(CMesAlarmConfigT record);

    List<CMesAlarmConfigT> selectAll();

    List<Map<String, Object>> selectSqlBySql(String sql);
}
