package com.skeqi.mes.service.wf.timer.log;

import com.skeqi.mes.pojo.wf.timer.log.CMesTimerPerformLogT;

import java.util.List;

public interface CMesTimerPerformLogTService{


    int deleteByPrimaryKey(Integer id);

    int insertSelective(CMesTimerPerformLogT record);

    CMesTimerPerformLogT selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CMesTimerPerformLogT record);

    List<CMesTimerPerformLogT> selectAll();
}
