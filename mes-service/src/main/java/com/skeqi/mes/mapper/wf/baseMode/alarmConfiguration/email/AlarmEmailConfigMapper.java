package com.skeqi.mes.mapper.wf.baseMode.alarmConfiguration.email;

import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.email.AlarmEmailConfig;

import java.util.List;

public interface AlarmEmailConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(AlarmEmailConfig record);

    AlarmEmailConfig selectBySenderEmail(String senderEmail);

    int updateByPrimaryKeySelective(AlarmEmailConfig record);

    List<AlarmEmailConfig> selectAll();

    Integer updateIfEnableBySenderEmail(AlarmEmailConfig record);

    AlarmEmailConfig selectByIfEnable(int i);
}
