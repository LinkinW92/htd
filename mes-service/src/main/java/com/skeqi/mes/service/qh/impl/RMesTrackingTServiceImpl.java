package com.skeqi.mes.service.qh.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.skeqi.mes.mapper.qh.RMesTrackingTMapper;
import com.skeqi.mes.service.qh.RMesTrackingTService;
@Service
public class RMesTrackingTServiceImpl implements RMesTrackingTService{

    @Resource
    private RMesTrackingTMapper rMesTrackingTMapper;


    @Override
    public List<Map<String,Object>> findRMesTrackingByWorkOrderId(Integer workOrderId) {
        return rMesTrackingTMapper.findRMesTrackingByWorkOrderId(workOrderId);
    }

    @Override
    public List<Map<String, Object>> findPMesTrackingByProductIDAndWorkOrderId(Integer productId, String workOrderId) {
        return rMesTrackingTMapper.findPMesTrackingByProductIDAndWorkOrderId(productId,workOrderId);
    }
}
