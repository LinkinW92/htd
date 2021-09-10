package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmSupplierHMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmSupplierRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierH;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierRReq;
import com.skeqi.mes.service.chenj.srm.CSrmSupplierRService;
import com.skeqi.mes.util.Rjson;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSupplierRServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmSupplierRServiceImpl implements CSrmSupplierRService {

    @Resource
    private CSrmSupplierRMapper cSrmSupplierRMapper;
    @Resource
    private CSrmSupplierHMapper cSrmSupplierHMapper;


    @Override
    public int insertSelective(CSrmSupplierR record) {
        return cSrmSupplierRMapper.insertSelective(record);
    }

    @Override
    public CSrmSupplierR selectByPrimaryKey(Integer id) {
        return cSrmSupplierRMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmSupplierR record) {
        return cSrmSupplierRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmSupplierR> list) {
        return cSrmSupplierRMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmSupplierR> list) {
        return cSrmSupplierRMapper.batchInsert(list);
    }

    @Override
    public Rjson supplierExamineAndScore(CSrmSupplierRReq cSrmSupplierRReq) {
        // 查询申请单号是否存在
        Integer count = cSrmSupplierRMapper.selectRequestCode(cSrmSupplierRReq.getRequestCode());
        if (count > 0) {
            // 修改申请单审批
            CSrmSupplierR cSrmSupplierR = new CSrmSupplierR();
            BeanUtils.copyProperties(cSrmSupplierRReq, cSrmSupplierR);
            cSrmSupplierR.setUpdateTime(new Date());
            cSrmSupplierRMapper.updateByPrimaryKeySelective(cSrmSupplierR);
            // 更新状态为申请成功
            CSrmSupplierH cSrmSupplierH = new CSrmSupplierH();
            cSrmSupplierH.setRequestCode(cSrmSupplierRReq.getRequestCode());
            cSrmSupplierH.setUpdateTime(new Date());
            cSrmSupplierHMapper.updateByPrimaryKeySelective(cSrmSupplierH);
            return Rjson.success("审批失败", null);
        } else {
            return Rjson.error("申请单号不存在");
        }


    }
}


