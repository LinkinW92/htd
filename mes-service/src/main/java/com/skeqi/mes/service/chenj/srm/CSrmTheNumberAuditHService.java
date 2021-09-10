package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmTheNumberAuditH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditHReq;
import com.skeqi.mes.util.Rjson;

import java.text.ParseException;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/4
 * @Classname CSrmTheNumberAuditHService
 * @Description ${Description}
 */

public interface CSrmTheNumberAuditHService {


    int insertSelective(CSrmTheNumberAuditH record);

    CSrmTheNumberAuditH selectByPrimaryKey(CSrmTheNumberAuditH record);

    int updateByPrimaryKeySelective(CSrmTheNumberAuditH record);

    int updateBatchSelective(List<CSrmTheNumberAuditH> list);

    Rjson updateInvoicingApplication(CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) throws ParseException;

    int batchInsert(List<CSrmTheNumberAuditH> list);


    /**
     * 开票信息展示查询
     *
     * @param cSrmTheNumberAuditHReq
     * @return
     * @throws ParseException
     */
    Rjson findInvoicingApplication(CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq);


    /**
     * 非寄售开票单维护保存查询
     *
     * @param cSrmTheNumberAuditHReq
     * @return
     */
    Rjson findNoInvoiceMaintenanceSave(CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq);


    Rjson findNoConsignmentInvoiceHR(CSrmTheNumberAuditHReq req);

    /**
     * 创建开票申请单头查询
     */
    Rjson findNoConsignmentInvoiceR(CSrmTheNumberAuditHReq req);


    /**
     * 查询开票申请转应收发票数据
     */
    Rjson findNoConsignmentInvoiceBatchHR(CSrmTheNumberAuditHReq req);

    int deleteByPrimaryKey(Integer id);

    Rjson delInvoicingApplication(CSrmTheNumberAuditHReq req);
}




