package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmEnquiryForBidsTSupplier;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryForBidsTSupplierReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTHReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmEnquiryForBidsTSupplierService
 * @Description ${Description}
 */

public interface CSrmEnquiryForBidsTSupplierService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmEnquiryForBidsTSupplier record);

    int insertOrUpdate(CSrmEnquiryForBidsTSupplier record);

    int insertOrUpdateSelective(CSrmEnquiryForBidsTSupplier record);

    int insertSelective(CSrmEnquiryForBidsTSupplier record);

    CSrmEnquiryForBidsTSupplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmEnquiryForBidsTSupplier record);

    int updateByPrimaryKey(CSrmEnquiryForBidsTSupplier record);

    int updateBatch(List<CSrmEnquiryForBidsTSupplier> list);

    int updateBatchSelective(List<CSrmEnquiryForBidsTSupplier> list);

    int batchInsert(List<CSrmEnquiryForBidsTSupplier> list);

    /**
     * 招标大厅
     * @param req
     * @return
     */
    Rjson findTheTenderHall(CSrmEnquiryInvitationForBidsTHReq req);


    /**
     * 查询供应商最新报价信息
     * @param req
     * @return
     */
    Rjson findSupplierNewList(CSrmEnquiryForBidsTSupplierReq req);

    /**
     * 查询供应商报价信息
     * @param req
     * @return
     */
    Rjson findSupplierList(CSrmEnquiryForBidsTSupplierReq req);


    /**
     * 根据物料查询历史询价及订单的供应商
     * @param req
     * @return
     */
    Rjson  findHistorySupplierList(CSrmEnquiryForBidsTSupplierReq req);


}



