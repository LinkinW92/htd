package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmBank;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmBankService
 * @Description ${Description}
 */

public interface CSrmBankService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmBank record);

    int insertOrUpdate(CSrmBank record);

    int insertOrUpdateSelective(CSrmBank record);

    int insertSelective(CSrmBank record);

    CSrmBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmBank record);

    int updateByPrimaryKey(CSrmBank record);

    int updateBatch(List<CSrmBank> list);

    int updateBatchSelective(List<CSrmBank> list);

    int batchInsert(List<CSrmBank> list);

}


