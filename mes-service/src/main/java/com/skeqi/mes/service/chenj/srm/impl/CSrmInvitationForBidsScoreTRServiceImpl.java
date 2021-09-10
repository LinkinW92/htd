package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmInvitationForBidsScoreTRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmInvitationForBidsScoreTR;
import com.skeqi.mes.service.chenj.srm.CSrmInvitationForBidsScoreTRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmInvitationForBidsScoreTRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmInvitationForBidsScoreTRServiceImpl implements CSrmInvitationForBidsScoreTRService {

    @Resource
    private CSrmInvitationForBidsScoreTRMapper cSrmInvitationForBidsScoreTRMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmInvitationForBidsScoreTRMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmInvitationForBidsScoreTR record) {
        return cSrmInvitationForBidsScoreTRMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmInvitationForBidsScoreTR record) {
        return cSrmInvitationForBidsScoreTRMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmInvitationForBidsScoreTR record) {
        return cSrmInvitationForBidsScoreTRMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmInvitationForBidsScoreTR record) {
        return cSrmInvitationForBidsScoreTRMapper.insertSelective(record);
    }

    @Override
    public CSrmInvitationForBidsScoreTR selectByPrimaryKey(Integer id) {
        return cSrmInvitationForBidsScoreTRMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmInvitationForBidsScoreTR record) {
        return cSrmInvitationForBidsScoreTRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmInvitationForBidsScoreTR record) {
        return cSrmInvitationForBidsScoreTRMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmInvitationForBidsScoreTR> list) {
        return cSrmInvitationForBidsScoreTRMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmInvitationForBidsScoreTR> list) {
        return cSrmInvitationForBidsScoreTRMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmInvitationForBidsScoreTR> list) {
        return cSrmInvitationForBidsScoreTRMapper.batchInsert(list);
    }

}


