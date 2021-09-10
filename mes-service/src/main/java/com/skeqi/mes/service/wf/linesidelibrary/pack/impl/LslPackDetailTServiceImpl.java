package com.skeqi.mes.service.wf.linesidelibrary.pack.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.linesidelibrary.pack.LslPackDetailTMapper;
import com.skeqi.mes.pojo.wf.linesidelibrary.pack.LslPackDetailT;
import com.skeqi.mes.service.wf.linesidelibrary.pack.LslPackDetailTService;
@Service
public class LslPackDetailTServiceImpl implements LslPackDetailTService{

    @Resource
    private LslPackDetailTMapper lslPackDetailTMapper;

    @Override
    public LslPackDetailT selectByPrimaryKey(Integer id) {
        return lslPackDetailTMapper.selectByPrimaryKey(id);
    }

}
