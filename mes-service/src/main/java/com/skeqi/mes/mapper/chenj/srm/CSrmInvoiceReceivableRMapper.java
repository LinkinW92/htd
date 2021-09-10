package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmInvoiceReceivableR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableRReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmInvoiceReceivableRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author ChenJ
* @date 2021/8/21
* @Classname CSrmInvoiceReceivableRMapper
* @Description ${Description}
*/

public interface CSrmInvoiceReceivableRMapper {
   int deleteByPrimaryKey(Integer id);

   int insertSelective(CSrmInvoiceReceivableR record);

   List<CSrmInvoiceReceivableRRsp> selectByPrimaryKey(CSrmInvoiceReceivableReq req);

   int updateByPrimaryKeySelective(CSrmInvoiceReceivableR record);

   int updateBatchSelective(List<CSrmInvoiceReceivableR> list);

   int batchInsert(@Param("list") List<CSrmInvoiceReceivableRReq> list);

	CSrmInvoiceReceivableR selectFinallyData(String invoiceReceivableNo);


}
