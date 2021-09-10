package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmAlteration;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAlterationService
 * @Description ${Description}
 */

public interface CSrmAlterationService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmAlteration record);

    int insertOrUpdate(CSrmAlteration record);

    int insertOrUpdateSelective(CSrmAlteration record);

    int insertSelective(CSrmAlteration record);

    CSrmAlteration selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmAlteration record);

    int updateByPrimaryKey(CSrmAlteration record);

    int updateBatch(List<CSrmAlteration> list);

    int updateBatchSelective(List<CSrmAlteration> list);

    int batchInsert(List<CSrmAlteration> list);

}

