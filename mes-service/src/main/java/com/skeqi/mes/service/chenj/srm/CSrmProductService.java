package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmProduct;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmProductService
 * @Description ${Description}
 */

public interface CSrmProductService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmProduct record);

    int insertOrUpdate(CSrmProduct record);

    int insertOrUpdateSelective(CSrmProduct record);

    int insertSelective(CSrmProduct record);

    CSrmProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmProduct record);

    int updateByPrimaryKey(CSrmProduct record);

    int updateBatch(List<CSrmProduct> list);

    int updateBatchSelective(List<CSrmProduct> list);

    int batchInsert(List<CSrmProduct> list);

}


