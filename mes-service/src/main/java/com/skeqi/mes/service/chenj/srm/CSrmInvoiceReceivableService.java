package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.pojo.chenj.srm.CSrmInvoiceReceivable;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableReq;
import com.skeqi.mes.util.Rjson;

import java.text.ParseException;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmInvoiceReceivableService
 * @Description ${Description}
 */

public interface CSrmInvoiceReceivableService {


    int deleteByPrimaryKey(Integer id);


    int insertSelective(CSrmInvoiceReceivable record);

    CSrmInvoiceReceivable selectByPrimaryKey(CSrmInvoiceReceivable record);

    int updateByPrimaryKeySelective(CSrmInvoiceReceivable record);

    Rjson updateByPrimaryKeyKThree(CSrmInvoiceReceivableReq record) throws ParseException;

    int updateBatchSelective(List<CSrmInvoiceReceivable> list);

    int batchInsert(List<CSrmInvoiceReceivable> list);

    Rjson updateInvoiceReceivable(CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq, SrmSupplierTimer srmSupplierTimer) throws Exception;

    Rjson findInvoiceReceivableH(CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq);

    Rjson findInvoiceReceivableHR(CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq);

    Rjson delInvoiceReceivable(CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq);


    /**
     * 创建开票申请单新建头行查询
     */
//    Rjson findNoConsignmentInvoiceHR(CSrmTheNumberAuditHReq req);
}






