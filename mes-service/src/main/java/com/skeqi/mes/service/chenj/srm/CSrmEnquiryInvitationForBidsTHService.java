package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmEnquiryInvitationForBidsTH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTHReq;
import com.skeqi.mes.util.Rjson;

import java.text.ParseException;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmEnquiryInvitationForBidsTHService
 * @Description ${Description}
 */

public interface CSrmEnquiryInvitationForBidsTHService {

    int insertOrUpdateSelective(CSrmEnquiryInvitationForBidsTH record);

    int insertSelective(CSrmEnquiryInvitationForBidsTH record);

    CSrmEnquiryInvitationForBidsTH selectByPrimaryKey(CSrmEnquiryInvitationForBidsTH record);

    int updateByPrimaryKeySelective(CSrmEnquiryInvitationForBidsTH record);

    int updateBatchSelective(List<CSrmEnquiryInvitationForBidsTH> list);

    int batchInsert(List<CSrmEnquiryInvitationForBidsTH> list);

    Rjson updateSearchForTheSourceInfo(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq) throws ParseException;

    Rjson createForQuotationOrCallForBids(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq) throws Exception;

    /**
     *  查询头表
     * @param cSrmEnquiryInvitationForBidsTHReq
     * @return
     */
    Rjson findForQuotationOrCallForBidsH(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq);


    /**
     *  查询头,携带条件查询
     * @param cSrmEnquiryInvitationForBidsTHReq
     * @return
     */
    Rjson findForQuotationOrCallForBidsHS(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq);


    /**
     *  查询头行表
     * @param cSrmEnquiryInvitationForBidsTHReq
     * @return
     */
    Rjson findForQuotationOrCallForBidsHR(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq);

    /**
     *  招标变更查询
     * @param cSrmEnquiryInvitationForBidsTHReq
     * @return
     */
    Rjson findUpdateCall(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq);

    /**
     *  招标变更修改
     * @param cSrmEnquiryInvitationForBidsTHReq
     * @return
     */
    Rjson updateCall(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq) throws Exception;


    /**
     *  查询开标
     * @param cSrmEnquiryInvitationForBidsTHReq
     * @return
     */
    Rjson findBidOpening(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq);

   /**
     *  专家评分
     * @param cSrmEnquiryInvitationForBidsTHReq
     * @return
     */
    Rjson expertRating(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq);


   /**
     *  查询定标
     * @param cSrmEnquiryInvitationForBidsTHReq
     * @return
     */
    Rjson scalingQuery(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq);


    Rjson findExamineNumber(CSrmEnquiryInvitationForBidsTHReq req);

}



