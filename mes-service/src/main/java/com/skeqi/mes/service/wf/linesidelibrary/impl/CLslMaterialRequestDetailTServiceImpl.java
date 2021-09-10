package com.skeqi.mes.service.wf.linesidelibrary.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.linesidelibrary.CLslMaterialRequestDetailT;
import com.skeqi.mes.mapper.wf.linesidelibrary.CLslMaterialRequestDetailTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.CLslMaterialRequestDetailTService;

import java.util.List;

@Service
public class CLslMaterialRequestDetailTServiceImpl implements CLslMaterialRequestDetailTService{

    @Resource
    private CLslMaterialRequestDetailTMapper cLslMaterialRequestDetailTMapper;


    @Override
    public List<CLslMaterialRequestDetailT> findMaterialRequestDetailByRequestId(String billNo) {
        return cLslMaterialRequestDetailTMapper.findMaterialRequestDetailByRequestId(billNo);
    }
}
