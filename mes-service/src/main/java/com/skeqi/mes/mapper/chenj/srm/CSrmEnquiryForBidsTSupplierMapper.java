package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmEnquiryForBidsTSupplier;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryForBidsTSupplierReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmEnquiryForBidsTSupplierHallRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmEnquiryForBidsTSupplierRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryForBidsTSupplierMapper
 * @Description ${Description}
 */

public interface CSrmEnquiryForBidsTSupplierMapper {
    int deleteByPrimaryKey(Integer id);
    int delData(String rfqOrTenderFormCode);
    int insert(CSrmEnquiryForBidsTSupplier record);

    int insertOrUpdate(CSrmEnquiryForBidsTSupplier record);

    int insertOrUpdateSelective(CSrmEnquiryForBidsTSupplier record);

    int insertSelective(CSrmEnquiryForBidsTSupplier record);

    CSrmEnquiryForBidsTSupplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmEnquiryForBidsTSupplier record);

    int updateByPrimaryKey(CSrmEnquiryForBidsTSupplier record);

    int updateBatch(List<CSrmEnquiryForBidsTSupplier> list);

    int updateBatchSelective(List<CSrmEnquiryForBidsTSupplier> list);

    int batchInsert(@Param("list") List<CSrmEnquiryForBidsTSupplier> list);

    List<CSrmEnquiryForBidsTSupplierRsp> selectByPrimaryList(CSrmEnquiryInvitationForBidsTHReq req);

    /**
     * 查询招标大厅
     * @param req
     * @return
     */
    List<CSrmEnquiryForBidsTSupplierHallRsp> findTheTenderHall(CSrmEnquiryInvitationForBidsTHReq req);

    /**
     * 查询供应商最新报价信息
     * @param req
     * @return
     */
    List<CSrmEnquiryForBidsTSupplierRsp> findSupplierNewList(CSrmEnquiryForBidsTSupplierReq req);

    /**
     * 查询供应商最新报价信息
     * @param req
     * @return
     */
    List<CSrmEnquiryForBidsTSupplierRsp> findSupplierList(CSrmEnquiryForBidsTSupplierReq req);


    CSrmEnquiryForBidsTSupplier selectFinallyData(String rfqOrTenderFormCode);


    /**
     * 查询合作过的供应商并进行按合作次数降序
     * @param req
     * @return
     */
    List<CSrmEnquiryForBidsTSupplierRsp> findHistorySupplierList(CSrmEnquiryForBidsTSupplierReq req);

    /**
     * 查询近期合作过的供应商并进行按合作时间降序
     * @param req
     * @return
     */
    List<CSrmEnquiryForBidsTSupplierRsp> findHistorySupplierListByTime(CSrmEnquiryForBidsTSupplierReq req);

}
