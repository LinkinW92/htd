package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmBankMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmBank;
import com.skeqi.mes.service.chenj.srm.CSrmBankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmBankServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmBankServiceImpl implements CSrmBankService {

    @Resource
    private CSrmBankMapper cSrmBankMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmBankMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmBank record) {
        return cSrmBankMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmBank record) {
        return cSrmBankMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmBank record) {
        return cSrmBankMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmBank record) {
        return cSrmBankMapper.insertSelective(record);
    }

    @Override
    public CSrmBank selectByPrimaryKey(Integer id) {
        return cSrmBankMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmBank record) {
        return cSrmBankMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmBank record) {
        return cSrmBankMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmBank> list) {
        return cSrmBankMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmBank> list) {
        return cSrmBankMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmBank> list) {
        return cSrmBankMapper.batchInsert(list);
    }

}


