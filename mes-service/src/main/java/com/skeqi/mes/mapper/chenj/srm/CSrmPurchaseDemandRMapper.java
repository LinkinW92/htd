package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseDemandR;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePORequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/9
 * @Classname CSrmPurchaseDemandRMapper
 * @Description ${Description}
 */

public interface CSrmPurchaseDemandRMapper {
    int deleteByPrimaryKey(Integer id);

    int delData(String requestCode);

    int insert(CSrmPurchaseDemandR record);

    int insertOrUpdate(CSrmPurchaseDemandR record);

    int insertOrUpdateSelective(CSrmPurchaseDemandR record);

    int insertSelective(CSrmPurchaseDemandR record);

    List<CSrmPurchaseDemandR> selectByPrimaryKey(CSrmPurchaseDemandR record);

    /**
     * 获取所有采购申请单号和物料编码
     * @return
     */
    List<CSrmPurchaseDemandR> selectByPrimaryKeyTransferOfOrder();

    int updateByPrimaryKeySelective(CSrmPurchaseDemandR record);

    int updateByPrimaryKey(CSrmPurchaseDemandR record);

    int updateBatch(List<CSrmPurchaseDemandR> list);

    int updateBatchSelective(List<CSrmPurchaseDemandR> list);

    int batchInsertKThree(@Param("list") List<KThreePORequest> list);

    int updateBatchSelectiveKThree(List<KThreePORequest> list);


    int updateSelectiveKThree(KThreePORequest list);



    int delKThreeData(List<CSrmKThreePurchaseAbutting> list);




    CSrmPurchaseDemandR selectFinallyData(String requestCode);
}
