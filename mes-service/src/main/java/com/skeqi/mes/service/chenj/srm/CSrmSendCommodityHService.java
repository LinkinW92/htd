package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSendCommodityHReq;
import com.skeqi.mes.pojo.chenj.srm.req.ReceiveTheActualReceiptReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSendCommodityHService
 * @Description ${Description}
 */

public interface CSrmSendCommodityHService {


    int insertOrUpdateSelective(CSrmSendCommodityH record);

    int insertSelective(CSrmSendCommodityH record);

    CSrmSendCommodityH selectByPrimaryKey(CSrmSendCommodityH record);

    int updateByPrimaryKeySelective(CSrmSendCommodityH record);

    int updateBatchSelective(List<CSrmSendCommodityH> list);


    Rjson updateDeliverySlip(CSrmSendCommodityHReq cSrmSendCommodityHReq, SrmSupplierTimer srmSupplierTimer) throws Exception;

    Rjson findDeliverySlipHR(CSrmSendCommodityHReq cSrmSendCommodityHReq);

    Rjson findDeliverySlipH(CSrmSendCommodityHReq cSrmSendCommodityHReq);

    int insertOrUpdate(CSrmSendCommodityH record);

    Rjson ReceiveTheActualReceipt(ReceiveTheActualReceiptReq receiptReq);

    Rjson delDeliverySlip(CSrmSendCommodityHReq req);
}


