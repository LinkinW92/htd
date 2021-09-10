package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmSourcingTemplate;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSourcingTemplateReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSourcingTemplateService
 * @Description ${Description}
 */

public interface CSrmSourcingTemplateService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSourcingTemplate record);

    int insertOrUpdate(CSrmSourcingTemplate record);

    int insertOrUpdateSelective(CSrmSourcingTemplate record);

    int insertSelective(CSrmSourcingTemplate record);

    List<CSrmSourcingTemplate> selectByPrimaryKey(CSrmSourcingTemplate cSrmSourcingTemplate);

    int updateByPrimaryKeySelective(CSrmSourcingTemplate record);

    int updateByPrimaryKey(CSrmSourcingTemplate record);

    int updateBatch(List<CSrmSourcingTemplate> list);

    int updateBatchSelective(List<CSrmSourcingTemplate> list);

    int batchInsert(List<CSrmSourcingTemplate> list);

    Rjson createSourcingTemplateOrUpdate(CSrmSourcingTemplateReq cSrmSourcingTemplateReq);
}


