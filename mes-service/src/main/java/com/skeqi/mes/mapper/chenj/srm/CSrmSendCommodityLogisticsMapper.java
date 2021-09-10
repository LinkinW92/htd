package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityLogistics;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSendCommodityHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSendCommodityLogisticsReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSendCommodityLogisticsMapper
 * @Description ${Description}
 */

public interface CSrmSendCommodityLogisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSendCommodityLogistics record);

    int insertOrUpdate(CSrmSendCommodityLogistics record);

    int insertOrUpdateSelective(CSrmSendCommodityLogistics record);

    int insertSelective(CSrmSendCommodityLogistics record);

    CSrmSendCommodityLogistics selectByPrimaryKey(Integer id);


    CSrmSendCommodityLogistics selectByPrimaryKeyList(CSrmSendCommodityLogistics cSrmSendCommodityLogistics);

    int updateByPrimaryKeySelective(CSrmSendCommodityLogistics record);

    int updateByPrimaryKey(CSrmSendCommodityLogistics record);

    int updateBatch(List<CSrmSendCommodityLogistics> list);

    int updateBatchSelective(List<CSrmSendCommodityLogistics> list);

    int batchInsert(@Param("list") List<CSrmSendCommodityLogistics> list);

    /**
     * 查询物流行数据
     * @param req
     * @return
     */
    List<CSrmSendCommodityLogisticsReq> selectByPrimaryList(CSrmSendCommodityHReq req);

    CSrmSendCommodityLogistics selectFinallyData();
}
