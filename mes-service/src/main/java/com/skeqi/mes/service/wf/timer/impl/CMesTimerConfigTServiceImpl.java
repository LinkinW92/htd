package com.skeqi.mes.service.wf.timer.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.timer.CMesTimerConfigTMapper;
import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;
import com.skeqi.mes.service.wf.timer.CMesTimerConfigTService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CMesTimerConfigTServiceImpl implements CMesTimerConfigTService{

    @Resource
    private CMesTimerConfigTMapper cMesTimerConfigTMapper;


    @Override
    public List<CMesTimerConfigT> selectByCode(String code) {
        return cMesTimerConfigTMapper.selectByCode(code);
    }

    @Override
    public Integer updateByStatus(CMesTimerConfigT record) {
        return cMesTimerConfigTMapper.updateByStatus( record);
    }

    @Override
    public Integer editTimerConfig(CMesTimerConfigT timerConfigT) {
        return cMesTimerConfigTMapper.editTimerConfig(timerConfigT);
    }

    @Override
    public Integer addTimerConfig(CMesTimerConfigT timerConfigT) throws Exception {
        List<CMesTimerConfigT> timerConfigTList = cMesTimerConfigTMapper.selectByCode(timerConfigT.getCode());
        if (timerConfigTList.size()>0){
            throw new Exception("已存在定时器不能重复创建!!!");
        }
        return cMesTimerConfigTMapper.addTimerConfig(timerConfigT);
    }

    @Override
    public Integer updateByStatusNoTime(CMesTimerConfigT produceLine) {
        return cMesTimerConfigTMapper.updateByStatusNoTime( produceLine);
    }

    @Override
    public Integer deleteTimerTack(String code) {
        return cMesTimerConfigTMapper.deleteTimerTack(code);
    }

}
