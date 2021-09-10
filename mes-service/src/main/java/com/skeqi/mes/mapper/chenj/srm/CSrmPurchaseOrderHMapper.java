package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseOrderH;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOOrder;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseOrderHFindReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseOrderHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseOrderHRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmPurchaseOrderHMapper
 * @Description ${Description}
 */

public interface CSrmPurchaseOrderHMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmPurchaseOrderH record);

    int delCSrmPurchaseOrderH(CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq);

    int insertOrUpdate(CSrmPurchaseOrderH record);

    int insertOrUpdateSelective(CSrmPurchaseOrderH record);

    int insertSelective(CSrmPurchaseOrderH record);

    List<CSrmPurchaseOrderH> selectByPrimaryKey(CSrmPurchaseOrderH record);

    int updateByPrimaryKeySelective(CSrmPurchaseOrderH record);

    int updateByPrimaryKey(CSrmPurchaseOrderH record);

    int updateBatch(List<CSrmPurchaseOrderH> list);

    int updateBatchSelective(List<CSrmPurchaseOrderH> list);

    /**
     * 更新采购订单头表为已创建
     * @param list
     * @return
     */
    int updateBatchSelectiveStatusTrue(List<CSrmPurchaseOrderH> list);

    /**
     * 更新采购订单头表为未创建
     * @param list
     * @return
     */
    int updateBatchSelectiveStatusFalse(List<CSrmPurchaseOrderH> list);





    int batchInsert(@Param("list") List<CSrmPurchaseOrderH> list);

    CSrmPurchaseOrderH selectFinallyData();

    CSrmPurchaseOrderHRsp selectCSrmPurchaseOrderH(CSrmPurchaseOrderH record);

    CSrmPurchaseOrderHRsp selectCSrmPurchaseOrderHKThree(CSrmPurchaseOrderH record);

    List<CSrmPurchaseOrderHRsp> selectByPrimaryList(CSrmPurchaseOrderHFindReq req);

    int batchInsertKThree(@Param("list") List<KThreePOOrder> list);


    int updateBatchSelectiveKThree(List<KThreePOOrder> list);


    int delKThreeData(@Param("list") List<CSrmKThreePurchaseAbutting> list);






}
