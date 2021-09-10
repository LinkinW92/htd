package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmPurchaseDemandRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurchaseDemandR;
import com.skeqi.mes.service.chenj.srm.CSrmPurchaseDemandRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmPurchaseDemandRServiceImpl implements CSrmPurchaseDemandRService {

    @Resource
    private CSrmPurchaseDemandRMapper cSrmPurchaseDemandRMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmPurchaseDemandRMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmPurchaseDemandR record) {
        return cSrmPurchaseDemandRMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmPurchaseDemandR record) {
        return cSrmPurchaseDemandRMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmPurchaseDemandR record) {
        return cSrmPurchaseDemandRMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmPurchaseDemandR record) {
        return cSrmPurchaseDemandRMapper.insertSelective(record);
    }

    @Override
    public List<CSrmPurchaseDemandR> selectByPrimaryKey(CSrmPurchaseDemandR record) {
        return cSrmPurchaseDemandRMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmPurchaseDemandR record) {
        return cSrmPurchaseDemandRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmPurchaseDemandR record) {
        return cSrmPurchaseDemandRMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmPurchaseDemandR> list) {
        return cSrmPurchaseDemandRMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmPurchaseDemandR> list) {
        return cSrmPurchaseDemandRMapper.updateBatchSelective(list);
    }


}




