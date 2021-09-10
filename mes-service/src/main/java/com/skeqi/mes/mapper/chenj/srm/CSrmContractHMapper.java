package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmContractH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmContractHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmContractHRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmContractHMapper
 * @Description ${Description}
 */

public interface CSrmContractHMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmContractH record);

    int insertOrUpdate(CSrmContractH record);

    int insertOrUpdateSelective(CSrmContractH record);

    int insertSelective(CSrmContractH record);

    List<CSrmContractH> selectByPrimaryKey(CSrmContractH record);

    List<CSrmContractHRsp> selectByPrimaryAllList(CSrmContractHReq record);

    int updateByPrimaryKeySelective(CSrmContractH record);

    int updateByPrimaryKey(CSrmContractH record);

    int updateBatch(List<CSrmContractH> list);

    int updateBatchSelective(List<CSrmContractH> list);

    int batchInsert(@Param("list") List<CSrmContractH> list);
    CSrmContractH selectFinallyData();

    Integer delPurchaseContract(CSrmContractHReq cSrmContractHReq);

    List<CSrmContractHRsp> selectByPrimaryList(CSrmContractHReq req);

    CSrmContractHRsp selectByPrimaryData(CSrmContractHReq req);




}
