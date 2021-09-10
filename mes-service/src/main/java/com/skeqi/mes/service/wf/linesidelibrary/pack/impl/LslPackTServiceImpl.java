package com.skeqi.mes.service.wf.linesidelibrary.pack.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.linesidelibrary.pack.LslPackT;
import com.skeqi.mes.mapper.wf.linesidelibrary.pack.LslPackTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.pack.LslPackTService;
@Service
public class LslPackTServiceImpl implements LslPackTService{

    @Resource
    private LslPackTMapper lslPackTMapper;

    @Override
    public LslPackT selectBySn(String sn) {
        return lslPackTMapper.selectBySn(sn);
    }

}
