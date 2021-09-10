package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmFinance;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnterpriseReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmFinanceChangeRecordReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmFinanceChangeRecordRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmFinanceMapper
 * @Description ${Description}
 */

public interface CSrmFinanceMapper {
    int insertSelective(CSrmFinance record);

    CSrmFinance selectByPrimaryKey(Integer id);

    List<CSrmFinanceChangeRecordRsp> selectByPrimaryKeyList(CSrmEnterpriseReq req);

    int delData(CSrmFinance cSrmFinance);

    int updateByPrimaryKeySelective(CSrmFinance record);

    int updateBatchSelective(List<CSrmFinance> list);

    int updateBatchSelectiveData(List<CSrmFinanceChangeRecordReq> list);

    int batchInsert(@Param("list") List<CSrmFinance> list);


    int batchInsertData(@Param("list") List<CSrmFinanceChangeRecordRsp> list);


    // 查询供应商代码是否存在
    CSrmFinance selectSupplierCode(String supplierCode);
}
