package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmEnquiryInvitationForBidsTRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmEnquiryInvitationForBidsTR;
import com.skeqi.mes.service.chenj.srm.CSrmEnquiryInvitationForBidsTRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmEnquiryInvitationForBidsTRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmEnquiryInvitationForBidsTRServiceImpl implements CSrmEnquiryInvitationForBidsTRService {

    @Resource
    private CSrmEnquiryInvitationForBidsTRMapper cSrmEnquiryInvitationForBidsTRMapper;

    @Override
    public int insertOrUpdateSelective(CSrmEnquiryInvitationForBidsTR record) {
        return cSrmEnquiryInvitationForBidsTRMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmEnquiryInvitationForBidsTR record) {
        return cSrmEnquiryInvitationForBidsTRMapper.insertSelective(record);
    }

    @Override
    public CSrmEnquiryInvitationForBidsTR selectByPrimaryKey(Integer id) {
        return cSrmEnquiryInvitationForBidsTRMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmEnquiryInvitationForBidsTR record) {
        return cSrmEnquiryInvitationForBidsTRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmEnquiryInvitationForBidsTR> list) {
        return cSrmEnquiryInvitationForBidsTRMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmEnquiryInvitationForBidsTR> list) {
        return cSrmEnquiryInvitationForBidsTRMapper.batchInsert(list);
    }

}

