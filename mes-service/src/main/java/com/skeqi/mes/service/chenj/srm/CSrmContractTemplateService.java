package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmContractTemplate;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmContractTemplateReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmContractTemplateRsp;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractTemplateService
 * @Description ${Description}
 */

public interface CSrmContractTemplateService {

    int insertSelective(CSrmContractTemplate record);
    List<CSrmContractTemplateRsp> selectByPrimaryList(CSrmContractTemplateReq record);
    int updateByPrimaryKeySelective(CSrmContractTemplate record);
    int updateBatchSelective(List<CSrmContractTemplate> list);

    int batchInsert(List<CSrmContractTemplate> list);

    /**
     * 合同模板创建
     * @param contractTemplateReq
     * @return
     */
    Rjson createContractTemplate(List<CSrmContractTemplateReq> contractTemplateReq);
    /**
     * 合同模板修改
     * @param contractTemplateReq
     * @return
     */
    Rjson updateContractTemplate(List<CSrmContractTemplateReq> contractTemplateReq);

    /**
     * 合同模板查询
     * @param contractTemplateReq
     * @return
     */
    Rjson findContractTemplate(CSrmContractTemplateReq contractTemplateReq);

    /**
     * 合同模板删除
     * @param id
     * @return
     */
    Rjson delContractTemplate(List<Integer> id);


    /**
     * 查询合同模板编码及名称
     */
    Rjson findByPrimaryList(CSrmContractTemplateReq req);

}






