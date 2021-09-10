package com.skeqi.mes.service.wf.timer.log.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.timer.log.CMesTimerPerformLogTMapper;
import com.skeqi.mes.pojo.wf.timer.log.CMesTimerPerformLogT;
import com.skeqi.mes.service.wf.timer.log.CMesTimerPerformLogTService;

import java.util.List;

@Service
public class CMesTimerPerformLogTServiceImpl implements CMesTimerPerformLogTService{

    @Resource
    private CMesTimerPerformLogTMapper cMesTimerPerformLogTMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cMesTimerPerformLogTMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CMesTimerPerformLogT record) {
        return cMesTimerPerformLogTMapper.insertSelective(record);
    }

    @Override
    public CMesTimerPerformLogT selectByPrimaryKey(Integer id) {
        return cMesTimerPerformLogTMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CMesTimerPerformLogT record) {
        return cMesTimerPerformLogTMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<CMesTimerPerformLogT> selectAll() {
        return cMesTimerPerformLogTMapper.selectAll();
    }

}
