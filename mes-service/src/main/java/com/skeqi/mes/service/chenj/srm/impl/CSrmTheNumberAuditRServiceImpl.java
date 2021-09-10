package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmTheNumberAuditRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmTheNumberAuditR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditRReq;
import com.skeqi.mes.service.chenj.srm.CSrmTheNumberAuditRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/4
 * @Classname CSrmTheNumberAuditRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmTheNumberAuditRServiceImpl implements CSrmTheNumberAuditRService {

    @Resource
    private CSrmTheNumberAuditRMapper cSrmTheNumberAuditRMapper;

    @Override
    public int insertSelective(CSrmTheNumberAuditR record) {
        return cSrmTheNumberAuditRMapper.insertSelective(record);
    }

    @Override
    public CSrmTheNumberAuditR selectByPrimaryKey(Integer id) {
        return cSrmTheNumberAuditRMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmTheNumberAuditR record) {
        return cSrmTheNumberAuditRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmTheNumberAuditRReq> list) {
        return cSrmTheNumberAuditRMapper.updateBatchSelective(list);
    }

    @Override
    public int insertOrUpdate(CSrmTheNumberAuditR record) {
        return cSrmTheNumberAuditRMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmTheNumberAuditR record) {
        return cSrmTheNumberAuditRMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int batchInsert(List<CSrmTheNumberAuditRReq> list) {
        return cSrmTheNumberAuditRMapper.batchInsert(list);
    }
}



