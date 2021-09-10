package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmContractTemplate;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmContractTemplateReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmContractTemplateRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/12
 * @Classname CSrmContractTemplateMapper
 * @Description ${Description}
 */

public interface CSrmContractTemplateMapper {
    int insertSelective(CSrmContractTemplate record);

    int insertSelectiveReq(CSrmContractTemplateReq record);

    List<CSrmContractTemplateRsp> selectByPrimaryList(CSrmContractTemplateReq record);

    int updateByPrimaryKeySelective(CSrmContractTemplate record);

    int updateBatchSelective(List<CSrmContractTemplate> list);

    int updateBatchSelectiveReq(List<CSrmContractTemplateReq> list);

    int batchInsert(@Param("list") List<CSrmContractTemplate> list);

    int batchInsertReq(@Param("list") List<CSrmContractTemplateReq> list);

    CSrmContractTemplate selectFinallyData();

    Integer delContractTemplate(List<Integer> id);

    List<CSrmContractTemplateRsp> selectNumberOrCodeList(CSrmContractTemplateReq record);

}
