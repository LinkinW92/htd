package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmContractR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractRService
 * @Description ${Description}
 */

public interface CSrmContractRService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmContractR record);

    int insertOrUpdate(CSrmContractR record);

    int insertOrUpdateSelective(CSrmContractR record);

    int insertSelective(CSrmContractR record);

    List<CSrmContractR> selectByPrimaryKey(CSrmContractR record);

    int updateByPrimaryKeySelective(CSrmContractR record);

    int updateByPrimaryKey(CSrmContractR record);

    int updateBatch(List<CSrmContractR> list);

    int updateBatchSelective(List<CSrmContractR> list);

    int batchInsert(List<CSrmContractR> list);

}

