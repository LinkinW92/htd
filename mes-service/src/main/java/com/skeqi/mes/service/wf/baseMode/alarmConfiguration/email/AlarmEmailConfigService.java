package com.skeqi.mes.service.wf.baseMode.alarmConfiguration.email;

import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.email.AlarmEmailConfig;

import java.util.List;

public interface AlarmEmailConfigService {


    int deleteByPrimaryKey(Integer id);

    int insertSelective(AlarmEmailConfig record) throws Exception;

    AlarmEmailConfig selectBySenderEmail(String senderEmail);

    int updateByPrimaryKeySelective(AlarmEmailConfig record);

    List<AlarmEmailConfig> selectAll();

    AlarmEmailConfig selectByIfEnable(int i);
}
