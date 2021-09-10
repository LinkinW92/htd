package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmAccountMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmAccount;
import com.skeqi.mes.service.chenj.srm.CSrmAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAccountServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmAccountServiceImpl implements CSrmAccountService {

    @Resource
    private CSrmAccountMapper cSrmAccountMapper;


    @Override
    public int insertOrUpdateSelective(CSrmAccount record) {
        return cSrmAccountMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmAccount record) {
        return cSrmAccountMapper.insertSelective(record);
    }

    @Override
    public CSrmAccount selectByPrimaryKey(Integer id) {
        return cSrmAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmAccount record) {
        return cSrmAccountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmAccount record) {
        return cSrmAccountMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmAccount> list) {
        return cSrmAccountMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmAccount> list) {
        return cSrmAccountMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmAccount> list) {
        return cSrmAccountMapper.batchInsert(list);
    }

}


