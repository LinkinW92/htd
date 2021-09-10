package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierH;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHRDelReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHRReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHReq;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSupplierHService
 * @Description ${Description}
 */

public interface CSrmSupplierHService {


    int deleteByPrimaryKey(Integer id);

    int insert(CSrmSupplierH record);

    int insertOrUpdate(CSrmSupplierH record);

    int insertOrUpdateSelective(CSrmSupplierH record);

    int insertSelective(CSrmSupplierH record);

    CSrmSupplierH selectByPrimaryKey(CSrmSupplierH record);

    int updateByPrimaryKeySelective(CSrmSupplierH record);

    int updateByPrimaryKey(CSrmSupplierH record);

    int updateBatch(List<CSrmSupplierH> list);

    int updateBatchSelective(List<CSrmSupplierH> list);

    int batchInsert(List<CSrmSupplierH> list);

    Rjson createAnApplicationForm(CSrmSupplierHReq cSrmSupplierHReq, SrmSupplierTimer srmSupplierTimer) throws Exception;

    /**
     * 根据申请单编号查询头行数据
     * @param cSrmSupplierHReq
     * @return
     */
    Rjson findApplicationFormHR(CSrmSupplierHRReq cSrmSupplierHReq);

    /**
     * 根据申请单编号查询头数据
     * @param cSrmSupplierHReq
     * @return
     */
    Rjson findApplicationFormH(CSrmSupplierHRReq cSrmSupplierHReq);

    /**
     * 根据申请单编号查询行数据
     * @param cSrmSupplierHReq
     * @return
     */
    Rjson findApplicationFormR(CSrmSupplierHRReq cSrmSupplierHReq);

    /**
     * 删除申请编号
     */
    Rjson delApplicationForm(CSrmSupplierHRDelReq delReq);





}


