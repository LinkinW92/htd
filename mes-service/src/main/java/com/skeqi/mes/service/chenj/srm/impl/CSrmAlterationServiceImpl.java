package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmAlterationMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmAlteration;
import com.skeqi.mes.service.chenj.srm.CSrmAlterationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAlterationServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmAlterationServiceImpl implements CSrmAlterationService {

    @Resource
    private CSrmAlterationMapper cSrmAlterationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmAlterationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmAlteration record) {
        return cSrmAlterationMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmAlteration record) {
        return cSrmAlterationMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmAlteration record) {
        return cSrmAlterationMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmAlteration record) {
        return cSrmAlterationMapper.insertSelective(record);
    }

    @Override
    public CSrmAlteration selectByPrimaryKey(Integer id) {
        return cSrmAlterationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmAlteration record) {
        return cSrmAlterationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmAlteration record) {
        return cSrmAlterationMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmAlteration> list) {
        return cSrmAlterationMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmAlteration> list) {
        return cSrmAlterationMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmAlteration> list) {
        return cSrmAlterationMapper.batchInsert(list);
    }

}

