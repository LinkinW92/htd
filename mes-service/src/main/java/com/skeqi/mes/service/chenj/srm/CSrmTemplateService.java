package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmTemplate;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTemplateReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmTemplateService
 * @Description ${Description}
 */

public interface CSrmTemplateService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmTemplate record);

    int insertOrUpdate(CSrmTemplate record);

    int insertOrUpdateSelective(CSrmTemplate record);

    int insertSelective(CSrmTemplate record);

    CSrmTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmTemplate record);

    int updateByPrimaryKey(CSrmTemplate record);

    int updateBatch(List<CSrmTemplate> list);

    int updateBatchSelective(List<CSrmTemplate> list);

    int batchInsert(List<CSrmTemplate> list);

    Rjson createTemplate(CSrmTemplateReq cSrmTemplateReq);

    Rjson findTemplate(CSrmTemplateReq cSrmTemplateReq);


}



