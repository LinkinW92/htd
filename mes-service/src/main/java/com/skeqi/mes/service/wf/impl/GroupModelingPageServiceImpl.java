package com.skeqi.mes.service.wf.impl;

import com.skeqi.mes.mapper.wf.GroupModelingPageDao;
import com.skeqi.mes.service.wf.GroupModelingPageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class GroupModelingPageServiceImpl implements GroupModelingPageService {
    @Resource
    private GroupModelingPageDao dao;
    @Override
    public List<Map<String, Object>> findProductionLine() {
        return dao.findProductionLine();
    }

    @Override
    public List<Map<String, Object>> findStation() {
        return dao.findStation();
    }
}
