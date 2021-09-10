package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmTheNumberAuditH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmMakeOutAnInvoiceRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmTheNumberAuditHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/8/21
 * @Classname CSrmTheNumberAuditHMapper
 * @Description ${Description}
 */

public interface CSrmTheNumberAuditHMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CSrmTheNumberAuditH record);

    CSrmTheNumberAuditH selectByPrimaryKey(CSrmTheNumberAuditH record);

    int updateByPrimaryKeySelective(CSrmTheNumberAuditH record);

    int updateBatchSelective(List<CSrmTheNumberAuditH> list);

    int batchInsert(@Param("list") List<CSrmTheNumberAuditH> list);

    CSrmTheNumberAuditH selectFinallyData();

    List<CSrmTheNumberAuditHRsp> selectByPrimaryList(CSrmTheNumberAuditH record);

    /**
     * 开票信息展示查询
     */
    List<CSrmMakeOutAnInvoiceRsp> findInvoicingApplication(CSrmTheNumberAuditHReq req);

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
