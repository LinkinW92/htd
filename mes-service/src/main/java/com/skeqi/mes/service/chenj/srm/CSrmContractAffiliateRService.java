package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmContractAffiliateR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractAffiliateRService
 * @Description ${Description}
 */

public interface CSrmContractAffiliateRService {


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

    int batchInsert(List<CSrmContractAffiliateR> list);

}

