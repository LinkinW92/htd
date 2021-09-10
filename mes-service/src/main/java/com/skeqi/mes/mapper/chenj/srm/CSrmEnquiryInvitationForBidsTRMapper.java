package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmEnquiryInvitationForBidsTR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmEnquiryInvitationForBidsTRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmEnquiryInvitationForBidsTRMapper
 * @Description ${Description}
 */

public interface CSrmEnquiryInvitationForBidsTRMapper {

    int insertOrUpdateSelective(CSrmEnquiryInvitationForBidsTR record);

    int insertSelective(CSrmEnquiryInvitationForBidsTR record);

    CSrmEnquiryInvitationForBidsTR selectByPrimaryKey(Integer id);

    List<CSrmEnquiryInvitationForBidsTRRsp> selectByPrimaryList(CSrmEnquiryInvitationForBidsTHReq req);

    List<CSrmEnquiryInvitationForBidsTRRsp> selectByPrimaryListNoOffer(CSrmEnquiryInvitationForBidsTHReq req);

    int updateByPrimaryKeySelective(CSrmEnquiryInvitationForBidsTR record);

    int updateBatchSelective(List<CSrmEnquiryInvitationForBidsTR> list);

    int delData(String rfqOrTenderFormCode);

    int batchInsert(@Param("list") List<CSrmEnquiryInvitationForBidsTR> list);

    CSrmEnquiryInvitationForBidsTR selectFinallyData(String rfqOrTenderFormCode);

}
