package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmTheNumberAuditR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditRReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmTheNumberAuditRRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/4
 * @Classname CSrmTheNumberAuditRMapper
 * @Description ${Description}
 */

public interface CSrmTheNumberAuditRMapper {
    int insertSelective(CSrmTheNumberAuditR record);

    CSrmTheNumberAuditR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmTheNumberAuditR record);

    int updateBatchSelective(List<CSrmTheNumberAuditRReq> list);

    int batchInsert(@Param("list") List<CSrmTheNumberAuditRReq> list);

    int insertOrUpdate(CSrmTheNumberAuditR record);

    int insertOrUpdateSelective(CSrmTheNumberAuditR record);

    /**
     * 查询行数据
     * @param record
     * @return
     */
    List<CSrmTheNumberAuditRRsp> selectByPrimaryList(CSrmTheNumberAuditR record);



    /**
     * 根据多个开票申请单号查询行数据
     * @param record
     * @return
     */
    List<CSrmTheNumberAuditRRsp> selectByPrimaryListRList(CSrmTheNumberAuditHReq record);


    /**
     * 开票申请  头展示数据
     */
    NoConsignmentInvoiceHRsp findNoConsignmentInvoiceH(CSrmTheNumberAuditHReq req);


    /**
     * 开票申请  行展示数据
     */
    List<NoConsignmentInvoiceRRsp> findNoConsignmentInvoiceR(CSrmTheNumberAuditHReq req);

    /**
     * 创建开票申请单头查询
     */
    List<NoConsignmentInvoiceRRsp> findCreateApplicationOrder(CSrmTheNumberAuditHReq req);

}
