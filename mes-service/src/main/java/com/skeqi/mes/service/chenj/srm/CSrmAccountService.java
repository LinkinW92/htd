package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmAccount;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAccountService
 * @Description ${Description}
 */

public interface CSrmAccountService {


    int insertOrUpdateSelective(CSrmAccount record);

    int insertSelective(CSrmAccount record);

    CSrmAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmAccount record);

    int updateByPrimaryKey(CSrmAccount record);

    int updateBatch(List<CSrmAccount> list);

    int updateBatchSelective(List<CSrmAccount> list);

    int batchInsert(List<CSrmAccount> list);

}


