package com.skeqi.mes.service.qh.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.skeqi.mes.mapper.qh.PMesTrackingTMapper;
import com.skeqi.mes.service.qh.PMesTrackingTService;
@Service
public class PMesTrackingTServiceImpl implements PMesTrackingTService{

    @Resource
    private PMesTrackingTMapper pMesTrackingTMapper;

    @Override
    public List<Map<String,Object>> findPMesTrackingByProductIDAndWorkOrderId(Integer productId,String workOrderId) {
        return pMesTrackingTMapper.findPMesTrackingByProductIDAndWorkOrderId(productId,workOrderId);
    }

    @Override
    public List<Map<String, Object>> findSNByLineId(String lineId) {
        return pMesTrackingTMapper.findSNByLineId(lineId);
    }
}
