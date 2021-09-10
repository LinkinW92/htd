package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmKThreePurchaseAbutting;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmKThreePurchaseAbuttingReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmKThreePurchaseAbuttingMapper
 * @Description ${Description}
 */

public interface CSrmKThreePurchaseAbuttingMapper {
    int insertSelective(CSrmKThreePurchaseAbutting record);

    CSrmKThreePurchaseAbutting selectByPrimaryKey(CSrmKThreePurchaseAbutting record);


    /**
     * 获取卡状态订单，进行特殊处理
     */
    List<CSrmKThreePurchaseAbutting> selectByPrimaryKeyListStatus();

    /**
     * 处理卡状态订单，进行特殊处理
     */
    int updateByPrimaryKeyListStatus(@Param("list") List<CSrmKThreePurchaseAbutting> list);


    int updateByPrimaryKeySelective(CSrmKThreePurchaseAbutting record);

    int updateBatchSelective(List<CSrmKThreePurchaseAbutting> list);

    int updateBatchAlterStatus(List<CSrmKThreePurchaseAbutting> list);

    /**
     * 获取K3采购对接日志表
     */
    List<CSrmKThreePurchaseAbutting> findAlterRecord(CSrmKThreePurchaseAbuttingReq req);

    /**
     * 根据单据类型查询采购对接日志数据
     *
     * @param doType
     * @return
     */
    List<CSrmKThreePurchaseAbutting> findAlterRecordList(@Param("doType") String doType);
}
