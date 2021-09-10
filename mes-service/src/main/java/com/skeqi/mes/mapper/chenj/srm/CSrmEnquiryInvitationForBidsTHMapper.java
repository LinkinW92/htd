package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmEnquiryInvitationForBidsTH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryInvitationForBidsTHMapper
 * @Description ${Description}
 */

public interface CSrmEnquiryInvitationForBidsTHMapper {
    int insertOrUpdateSelective(CSrmEnquiryInvitationForBidsTH record);

    int insertSelective(CSrmEnquiryInvitationForBidsTH record);

    CSrmEnquiryInvitationForBidsTH selectByPrimaryKey(CSrmEnquiryInvitationForBidsTH record);

    int updateByPrimaryKeySelective(CSrmEnquiryInvitationForBidsTH record);

    int updateBatchSelective(List<CSrmEnquiryInvitationForBidsTH> list);

    int batchInsert(@Param("list") List<CSrmEnquiryInvitationForBidsTH> list);

    CSrmEnquiryInvitationForBidsTH selectFinallyData();

    /**
     * 查询OA审批单号
     * @return
     */
    String findExamineNumber(CSrmEnquiryInvitationForBidsTHReq req);

    List<CSrmEnquiryInvitationForBidsTHRsps> findForQuotationOrCallForBidsH(CSrmEnquiryInvitationForBidsTHReq req);

    /**
     * 根据询价单标题、RFX单号、物料编码、负责人查询
     *
     * @param req
     * @return
     */
    List<CSrmEnquiryInvitationForBidsTHRsps> findForQuotationOrCallForBidsHS(CSrmEnquiryInvitationForBidsTHReq req);

    List<CSrmEnquiryInvitationForBidsTHUpdateRsp> findUpdateCall(CSrmEnquiryInvitationForBidsTHReq req);

    /**
     * 查询开标
     *
     * @param req
     * @return
     */
    List<CSrmEnquiryInvitationForBidsTHOpenBidRsp> findBidOpening(CSrmEnquiryInvitationForBidsTHReq req);

    /**
     * 专家评分
     *
     * @param req
     * @return
     */
    List<CSrmEnquiryInvitationForBidsTHScoreRsp> expertRating(CSrmEnquiryInvitationForBidsTHReq req);

    /**
     * 查询定标
     *
     * @param req
     * @return
     */
    List<CSrmEnquiryInvitationForBidsTHScalingRsp> scalingQuery(CSrmEnquiryInvitationForBidsTHReq req);

}
