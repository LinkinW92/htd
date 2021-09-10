package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSendCommodityHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSendCommodityHRsp;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSendCommodityHRspD;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmSendCommodityHMapper
 * @Description ${Description}
 */

public interface CSrmSendCommodityHMapper {
    int insertSelective(CSrmSendCommodityH record);

    CSrmSendCommodityH selectByPrimaryKey(CSrmSendCommodityH record);

    int updateByPrimaryKeySelective(CSrmSendCommodityH record);

    int updateBatchSelective(List<CSrmSendCommodityH> list);

    int insertOrUpdate(CSrmSendCommodityH record);

    int insertOrUpdateSelective(CSrmSendCommodityH record);

    CSrmSendCommodityH selectFinallyData();

    CSrmSendCommodityHRsp selectByPrimaryList(CSrmSendCommodityHReq req);

    List<CSrmSendCommodityH> selectByPrimaryListData(CSrmSendCommodityHReq req);

    List<CSrmSendCommodityHRspD> selectByPrimaryListDataS(CSrmSendCommodityHReq req);

    /**
     * 查询送货单的状态
     */
    CSrmSendCommodityH selectByPrimaryDevStatus(CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq);


    /**
     * 更新送货单状态为 5:已完成
     * @param list
     * @return
     */
   int updateBatchSelectiveSuccessStatus(List<CSrmKThreePurchaseAbutting> list);








}
