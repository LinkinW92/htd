package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmMakeOutAnInvoice;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmMakeOutAnInvoiceReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditHReq;
import com.skeqi.mes.util.Rjson;

import java.text.ParseException;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmMakeOutAnInvoiceService
 * @Description ${Description}
 */

public interface CSrmMakeOutAnInvoiceService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmMakeOutAnInvoice record);

    int insertOrUpdate(CSrmMakeOutAnInvoice record);

    int insertOrUpdateSelective(CSrmMakeOutAnInvoice record);

    int insertSelective(CSrmMakeOutAnInvoice record);

    CSrmMakeOutAnInvoice selectByPrimaryKey(CSrmMakeOutAnInvoice record);

    int updateByPrimaryKeySelective(CSrmMakeOutAnInvoice record);

    int updateByPrimaryKey(CSrmMakeOutAnInvoice record);

    int updateBatch(List<CSrmMakeOutAnInvoice> list);

    int updateBatchSelective(List<CSrmMakeOutAnInvoice> list);

    int batchInsert(List<CSrmMakeOutAnInvoice> list);

    Rjson updateInvoicingApplication(CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) throws ParseException;

    /**
     * 开票信息展示查询
     * @param cSrmMakeOutAnInvoiceReq
     * @return
     * @throws ParseException
     */
    Rjson findInvoicingApplication(CSrmMakeOutAnInvoiceReq cSrmMakeOutAnInvoiceReq);


    /**
     * 创建开票申请单新建头行查询
     */
    Rjson findNoConsignmentInvoiceHR(CSrmMakeOutAnInvoiceReq req);


    /**
     * 创建开票申请单头查询
     */
    Rjson findNoConsignmentInvoiceR(CSrmTheNumberAuditHReq req);

    /**
     *  查询开票申请转应收发票数据
     */
    Rjson findNoConsignmentInvoiceBatchHR(CSrmTheNumberAuditHReq req);



}


