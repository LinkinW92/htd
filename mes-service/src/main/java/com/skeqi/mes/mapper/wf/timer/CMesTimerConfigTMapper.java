package com.skeqi.mes.mapper.wf.timer;

import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;

import java.util.List;

public interface CMesTimerConfigTMapper {
    List<CMesTimerConfigT> selectByCode(String code);

    Integer updateByStatus(CMesTimerConfigT record);

    Integer editTimerConfig(CMesTimerConfigT timerConfigT);

    Integer addTimerConfig(CMesTimerConfigT timerConfigT);

    Integer updateByStatusNoTime(CMesTimerConfigT produceLine);

    Integer deleteTimerTack(String code);
}
