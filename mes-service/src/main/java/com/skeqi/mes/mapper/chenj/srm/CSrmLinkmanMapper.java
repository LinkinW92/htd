package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmLinkman;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnterpriseReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmLinkmanChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmLinkmanChangeRecordRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmLinkmanMapper
 * @Description ${Description}
 */

public interface CSrmLinkmanMapper {
    int insertSelective(CSrmLinkman record);

    CSrmLinkman selectByPrimaryKey(Integer id);

    CSrmLinkman selectByPrimaryKeyList(CSrmLinkman cSrmLinkman);

    int delData(CSrmLinkman cSrmLinkman);


    List<CSrmLinkmanChangeRecordRsp> selectByPrimaryKeyListD(CSrmEnterpriseReq req);

    int updateByPrimaryKeySelective(CSrmLinkman record);

    int updateBatchSelective(List<CSrmLinkman> list);

    int updateBatchSelectiveData(List<CSrmLinkmanChangeRecordReq> list);



    int batchInsert(@Param("list") List<CSrmLinkman> list);

    int batchInsertData(@Param("list") List<CSrmLinkmanChangeRecordRsp> list);

    // 查询供应商代码是否存在
    CSrmLinkman selectSupplierCode(String supplierCode);

}
