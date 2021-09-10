package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmContractR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmContractHReq;
import com.skeqi.mes.pojo.chenj.srm.req.ContractObjectReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractRMapper
 * @Description ${Description}
 */

public interface CSrmContractRMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmContractR record);

    int insertOrUpdate(CSrmContractR record);

    int insertOrUpdateSelective(CSrmContractR record);

    int insertSelective(CSrmContractR record);

    List<CSrmContractR> selectByPrimaryKey(CSrmContractR record);

    int updateByPrimaryKeySelective(CSrmContractR record);

    int updateByPrimaryKey(CSrmContractR record);

    int updateBatch(List<CSrmContractR> list);

    int updateBatchSelective(List<CSrmContractR> list);

    /**
     * 删除合同行数据
     * @param lineItemNoList
     * @return
     */
    int deleteBatchSelective(Integer[] lineItemNoList);

    int batchInsert(@Param("list") List<CSrmContractR> list);

    List<ContractObjectReq> selectByPrimaryList(CSrmContractHReq req);

    CSrmContractR selectFinallyData(String contractNo);
}
