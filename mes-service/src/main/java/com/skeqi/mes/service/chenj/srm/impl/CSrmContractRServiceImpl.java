package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmContractRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmContractR;
import com.skeqi.mes.service.chenj.srm.CSrmContractRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmContractRServiceImpl implements CSrmContractRService {

    @Resource
    private CSrmContractRMapper cSrmContractRMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmContractRMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmContractR record) {
        return cSrmContractRMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmContractR record) {
        return cSrmContractRMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmContractR record) {
        return cSrmContractRMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmContractR record) {
        return cSrmContractRMapper.insertSelective(record);
    }

    @Override
    public List<CSrmContractR> selectByPrimaryKey(CSrmContractR record) {
        return cSrmContractRMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmContractR record) {
        return cSrmContractRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmContractR record) {
        return cSrmContractRMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmContractR> list) {
        return cSrmContractRMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmContractR> list) {
        return cSrmContractRMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmContractR> list) {
        return cSrmContractRMapper.batchInsert(list);
    }

}

