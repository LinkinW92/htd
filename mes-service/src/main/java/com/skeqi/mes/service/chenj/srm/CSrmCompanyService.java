package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmCompany;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmCompanyReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmCompanyService
 * @Description ${Description}
 */

public interface CSrmCompanyService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmCompany record);

    int insertOrUpdate(CSrmCompany record);

    int insertOrUpdateSelective(CSrmCompany record);

    int insertSelective(CSrmCompany record);

    CSrmCompany selectByPrimaryKey(CSrmCompany record);

    int updateByPrimaryKeySelective(CSrmCompany record);

    int updateByPrimaryKey(CSrmCompany record);

    int updateBatch(List<CSrmCompany> list);

    int updateBatchSelective(List<CSrmCompany> list);

    int batchInsert(List<CSrmCompany> list);

    Rjson selectCompanyList(CSrmCompanyReq req);

}

