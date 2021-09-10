package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmContractH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmContractHReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractHService
 * @Description ${Description}
 */

public interface CSrmContractHService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmContractH record);

    int insertOrUpdate(CSrmContractH record);

    int insertOrUpdateSelective(CSrmContractH record);

    int insertSelective(CSrmContractH record);

    List<CSrmContractH> selectByPrimaryKey(CSrmContractH record);

    int updateByPrimaryKeySelective(CSrmContractH record);

    int updateByPrimaryKey(CSrmContractH record);

    int updateBatch(List<CSrmContractH> list);

    int updateBatchSelective(List<CSrmContractH> list);

    int batchInsert(List<CSrmContractH> list);

    Rjson updatePurchaseContract(CSrmContractHReq cSrmContractHReq) throws Exception;

    Rjson findPurchaseContractH(CSrmContractHReq req);

    Rjson findPurchaseContractHR(CSrmContractHReq req);


    Rjson findPurchaseContractAll(CSrmContractHReq req);



    Rjson delPurchaseContract(CSrmContractHReq cSrmContractHReq);

}


