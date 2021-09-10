package com.skeqi.mes.service.wf.timer;

import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;

import java.util.List;

public interface CMesTimerConfigTService{
    List<CMesTimerConfigT> selectByCode(String code);

    Integer updateByStatus(CMesTimerConfigT record);

    Integer editTimerConfig(CMesTimerConfigT timerConfigT);

    Integer addTimerConfig(CMesTimerConfigT timerConfigT) throws Exception;

    Integer updateByStatusNoTime(CMesTimerConfigT produceLine);

    Integer deleteTimerTack(String code);
}
