package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmFinance;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmFinanceService
 * @Description ${Description}
 */

public interface CSrmFinanceService {


    int insertSelective(CSrmFinance record);

    CSrmFinance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmFinance record);

    int updateBatchSelective(List<CSrmFinance> list);

    int batchInsert(List<CSrmFinance> list);

}


