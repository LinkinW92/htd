package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmAccessory;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmAccessoryService
 * @Description ${Description}
 */

public interface CSrmAccessoryService {


    int insertSelective(CSrmAccessory record);

    CSrmAccessory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmAccessory record);

    int updateBatchSelective(List<CSrmAccessory> list);

    int batchInsert(List<CSrmAccessory> list);

}


