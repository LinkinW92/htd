package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmMakeOutAnInvoice;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmMakeOutAnInvoiceReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmMakeOutAnInvoiceRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.NoConsignmentInvoiceRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmMakeOutAnInvoiceMapper
 * @Description ${Description}
 */

public interface CSrmMakeOutAnInvoiceMapper {
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

    int batchInsert(@Param("list") List<CSrmMakeOutAnInvoice> list);

    CSrmMakeOutAnInvoice selectFinallyData();


    /**
     * 开票信息展示查询
     */
    List<CSrmMakeOutAnInvoiceRsp> findInvoicingApplication(CSrmMakeOutAnInvoiceReq req);


    /**
     * 开票申请  头展示数据
     */
    NoConsignmentInvoiceHRsp findNoConsignmentInvoiceH(CSrmMakeOutAnInvoiceReq req);


    /**
     * 开票申请  行展示数据
     */
    List<NoConsignmentInvoiceRRsp> findNoConsignmentInvoiceR(CSrmMakeOutAnInvoiceReq req);

    /**
     * 创建开票申请单头查询
     */
    List<NoConsignmentInvoiceRRsp> findCreateApplicationOrder(CSrmMakeOutAnInvoiceReq req);




}
