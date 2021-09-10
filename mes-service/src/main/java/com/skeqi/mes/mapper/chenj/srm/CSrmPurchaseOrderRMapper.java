package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseDemandR;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseOrderR;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOOrder;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseOrderHFindReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseOrderRRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseOrderRMapper
 * @Description ${Description}
 */

public interface CSrmPurchaseOrderRMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmPurchaseOrderR record);

    int insertOrUpdate(CSrmPurchaseOrderR record);

    int insertOrUpdateSelective(CSrmPurchaseOrderR record);

    int insertSelective(CSrmPurchaseOrderR record);

    CSrmPurchaseOrderR selectByPrimaryKey(CSrmPurchaseOrderR record);

    List<CSrmPurchaseOrderRRsp> selectByPrimaryList(CSrmPurchaseOrderR srmPurchaseOrderR);

    /**
     * 获取所有采购申请订单和物料编码
     * @return
     */
    List<CSrmPurchaseDemandR> selectByPrimaryKeyTransferOfOrder();


    /**
     * 根据订单号查询行数据
     * @param req
     * @return
     */
    List<CSrmPurchaseOrderRRsp> selectByPrimaryRowList(@Param("req") CSrmPurchaseOrderHFindReq req);

    int updateByPrimaryKeySelective(CSrmPurchaseOrderR record);

    int updateSelectiveKThree(KThreePOOrder poOrder);

    int updateByPrimaryKey(CSrmPurchaseOrderR record);

    int updateBatch(List<CSrmPurchaseOrderR> list);

    int updateBatchSelective(List<CSrmPurchaseOrderR> list);

    int batchInsert(@Param("list") List<CSrmPurchaseOrderR> list);

    CSrmPurchaseOrderR selectFinallyData(String orderNumber);


    int batchInsertKThree(@Param("list") List<KThreePOOrder> list);


    int updateBatchSelectiveKThree(List<KThreePOOrder> list);


    int delKThreeData(@Param("list") List<CSrmKThreePurchaseAbutting> list);

    CSrmPurchaseOrderR selectByPrimaryKeyThree(CSrmPurchaseOrderR record);





}
