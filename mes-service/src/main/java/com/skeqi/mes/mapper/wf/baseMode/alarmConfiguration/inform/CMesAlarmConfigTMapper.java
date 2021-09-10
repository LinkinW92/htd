package com.skeqi.mes.mapper.wf.baseMode.alarmConfiguration.inform;

import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.inform.CMesAlarmConfigT;

import java.util.List;
import java.util.Map;

public interface CMesAlarmConfigTMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CMesAlarmConfigT record);

    List<CMesAlarmConfigT> selectAll();

    int updateByPrimaryKeySelective(CMesAlarmConfigT record);

    CMesAlarmConfigT selectByCode(String code);

    List<Map<String, Object>> selectSqlBySql(String sql);
}
