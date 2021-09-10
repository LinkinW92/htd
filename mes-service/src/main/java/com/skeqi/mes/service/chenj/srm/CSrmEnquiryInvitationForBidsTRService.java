package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmEnquiryInvitationForBidsTR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmEnquiryInvitationForBidsTRService
 * @Description ${Description}
 */

public interface CSrmEnquiryInvitationForBidsTRService {
    int insertOrUpdateSelective(CSrmEnquiryInvitationForBidsTR record);

    int insertSelective(CSrmEnquiryInvitationForBidsTR record);

    CSrmEnquiryInvitationForBidsTR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmEnquiryInvitationForBidsTR record);

    int updateBatchSelective(List<CSrmEnquiryInvitationForBidsTR> list);

    int batchInsert(List<CSrmEnquiryInvitationForBidsTR> list);

}

