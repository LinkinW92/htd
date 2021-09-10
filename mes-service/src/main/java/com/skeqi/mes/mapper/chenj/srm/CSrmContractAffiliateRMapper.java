package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmContractAffiliateR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmContractHReq;
import com.skeqi.mes.pojo.chenj.srm.req.PartnerOrClauseReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractAffiliateRMapper
 * @Description ${Description}
 */

public interface CSrmContractAffiliateRMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmContractAffiliateR record);

    int insertOrUpdate(CSrmContractAffiliateR record);

    int insertOrUpdateSelective(CSrmContractAffiliateR record);

    int insertSelective(CSrmContractAffiliateR record);

    List<CSrmContractAffiliateR> selectByPrimaryKey(CSrmContractAffiliateR record);

    int updateByPrimaryKeySelective(CSrmContractAffiliateR record);

    int updateByPrimaryKey(CSrmContractAffiliateR record);

    int updateBatch(List<CSrmContractAffiliateR> list);

    int updateBatchSelective(List<CSrmContractAffiliateR> list);

    int batchInsert(@Param("list") List<CSrmContractAffiliateR> list);


    List<PartnerOrClauseReq> selectByPrimaryList(CSrmContractHReq req);

    CSrmContractAffiliateR selectFinallyData(String contractNo);


    /**
     * 删除条款行数据
     * @param lineItemNoList
     * @return
     */
    int deleteBatchSelective(Integer[] lineItemNoList);


}
