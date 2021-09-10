package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmInvoiceReceivableR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableRReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmInvoiceReceivableRRsp;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/8/21
 * @Classname CSrmInvoiceReceivableRService
 * @Description ${Description}
 */

public interface CSrmInvoiceReceivableRService{


    int deleteByPrimaryKey(Integer id);

    int insertSelective(CSrmInvoiceReceivableR record);

    List<CSrmInvoiceReceivableRRsp> selectByPrimaryKey(CSrmInvoiceReceivableReq record);

    int updateByPrimaryKeySelective(CSrmInvoiceReceivableR record);

    int updateBatchSelective(List<CSrmInvoiceReceivableR> list);

    int batchInsert(List<CSrmInvoiceReceivableRReq> list);

}
