package com.skeqi.mes.mapper.wf.timer.log;

import com.skeqi.mes.pojo.wf.timer.log.CMesTimerPerformLogT;

import java.util.List;

public interface CMesTimerPerformLogTMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CMesTimerPerformLogT record);

    CMesTimerPerformLogT selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CMesTimerPerformLogT record);

    List<CMesTimerPerformLogT> selectAll();

}
