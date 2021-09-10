package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmPurchaseOrderRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseOrderR;
import com.skeqi.mes.service.chenj.srm.CSrmPurchaseOrderRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseOrderRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmPurchaseOrderRServiceImpl implements CSrmPurchaseOrderRService {

    @Resource
    private CSrmPurchaseOrderRMapper cSrmPurchaseOrderRMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmPurchaseOrderRMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmPurchaseOrderR record) {
        return cSrmPurchaseOrderRMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmPurchaseOrderR record) {
        return cSrmPurchaseOrderRMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmPurchaseOrderR record) {
        return cSrmPurchaseOrderRMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmPurchaseOrderR record) {
        return cSrmPurchaseOrderRMapper.insertSelective(record);
    }

    @Override
    public CSrmPurchaseOrderR selectByPrimaryKey(CSrmPurchaseOrderR record) {
        return cSrmPurchaseOrderRMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmPurchaseOrderR record) {
        return cSrmPurchaseOrderRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmPurchaseOrderR record) {
        return cSrmPurchaseOrderRMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmPurchaseOrderR> list) {
        return cSrmPurchaseOrderRMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmPurchaseOrderR> list) {
        return cSrmPurchaseOrderRMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmPurchaseOrderR> list) {
        return cSrmPurchaseOrderRMapper.batchInsert(list);
    }

}

