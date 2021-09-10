package com.skeqi.mes.service.wf.linesidelibrary.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.linesidelibrary.CLslRockConfigTMapper;
import com.skeqi.mes.pojo.wf.linesidelibrary.CLslRockConfigT;
import com.skeqi.mes.service.wf.linesidelibrary.CLslRockConfigTService;

import java.util.List;
import java.util.Map;

@Service
public class CLslRockConfigTServiceImpl implements CLslRockConfigTService{

    @Resource
    private CLslRockConfigTMapper cLslRockConfigTMapper;

    @Override
    public List<CLslRockConfigT> findAllWorkplaceConfig() {
        return cLslRockConfigTMapper.findAllWorkplaceConfig();
    }

    @Override
    public List<Map<String, Object>> findWorkOrderRecipeByLineId(CLslRockConfigT next) {
        return cLslRockConfigTMapper.findWorkOrderRecipeByLineId(next);
    }
}
