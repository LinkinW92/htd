package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmTheNumberAuditR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTheNumberAuditRReq;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/4
 * @Classname CSrmTheNumberAuditRService
 * @Description ${Description}
 */

public interface CSrmTheNumberAuditRService {


    int insertSelective(CSrmTheNumberAuditR record);

    CSrmTheNumberAuditR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmTheNumberAuditR record);

    int updateBatchSelective(List<CSrmTheNumberAuditRReq> list);

    int insertOrUpdate(CSrmTheNumberAuditR record);

    int insertOrUpdateSelective(CSrmTheNumberAuditR record);

    int batchInsert(List<CSrmTheNumberAuditRReq> list);
}



