package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmContractAffiliateRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmContractAffiliateR;
import com.skeqi.mes.service.chenj.srm.CSrmContractAffiliateRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractAffiliateRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmContractAffiliateRServiceImpl implements CSrmContractAffiliateRService {

    @Resource
    private CSrmContractAffiliateRMapper cSrmContractAffiliateRMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmContractAffiliateRMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmContractAffiliateR record) {
        return cSrmContractAffiliateRMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmContractAffiliateR record) {
        return cSrmContractAffiliateRMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmContractAffiliateR record) {
        return cSrmContractAffiliateRMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmContractAffiliateR record) {
        return cSrmContractAffiliateRMapper.insertSelective(record);
    }

    @Override
    public List<CSrmContractAffiliateR> selectByPrimaryKey(CSrmContractAffiliateR record) {
        return cSrmContractAffiliateRMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmContractAffiliateR record) {
        return cSrmContractAffiliateRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmContractAffiliateR record) {
        return cSrmContractAffiliateRMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmContractAffiliateR> list) {
        return cSrmContractAffiliateRMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmContractAffiliateR> list) {
        return cSrmContractAffiliateRMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmContractAffiliateR> list) {
        return cSrmContractAffiliateRMapper.batchInsert(list);
    }

}

