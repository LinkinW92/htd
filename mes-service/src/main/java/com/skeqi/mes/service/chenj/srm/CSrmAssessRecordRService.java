package com.skeqi.mes.service.chenj.srm;



import com.skeqi.mes.pojo.chenj.srm.CSrmAssessRecordR;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessRecordRService
 * @Description ${Description}
 */

public interface CSrmAssessRecordRService {

    int insertSelective(CSrmAssessRecordR record);

    CSrmAssessRecordR selectByPrimaryKey(CSrmAssessRecordR record);

    int updateByPrimaryKeySelective(CSrmAssessRecordR record);

    int updateBatchSelective(List<CSrmAssessRecordR> list);

    int batchInsert(List<CSrmAssessRecordR> list);

}



