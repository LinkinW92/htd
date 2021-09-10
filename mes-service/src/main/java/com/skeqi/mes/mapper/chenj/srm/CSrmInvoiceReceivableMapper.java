package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmInvoiceReceivable;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmMakeOutAnInvoiceReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmInvoiceReceivableRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmMakeOutAnInvoiceRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/8/21
 * @Classname CSrmInvoiceReceivableMapper
 * @Description ${Description}
 */

public interface CSrmInvoiceReceivableMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CSrmInvoiceReceivable record);

    CSrmInvoiceReceivable selectByPrimaryKey(CSrmInvoiceReceivable record);


    CSrmInvoiceReceivableRsp selectByPrimaryKeyRsp(CSrmInvoiceReceivable record);

    int updateByPrimaryKeySelective(CSrmInvoiceReceivable record);

    int updateBatchSelective(List<CSrmInvoiceReceivable> list);

    int batchInsert(@Param("list") List<CSrmInvoiceReceivable> list);

    CSrmInvoiceReceivable selectFinallyData();

    List<CSrmInvoiceReceivableRsp> selectByPrimaryList(CSrmInvoiceReceivable record);

    /**
     * 开票信息展示查询
     */
    List<CSrmMakeOutAnInvoiceRsp> findInvoicingApplication(CSrmMakeOutAnInvoiceReq req);


    /**
     * 开票申请  头展示数据
     */
    NoConsignmentInvoiceHRsp findNoConsignmentInvoiceH(CSrmInvoiceReceivableReq req);


    /**
     * 开票申请  行展示数据
     */
    List<NoConsignmentInvoiceRRsp> findNoConsignmentInvoiceR(CSrmInvoiceReceivableReq req);

    /**
     * 创建开票申请单头查询
     */
    List<NoConsignmentInvoiceRRsp> findCreateApplicationOrder(CSrmInvoiceReceivableReq req);

}
