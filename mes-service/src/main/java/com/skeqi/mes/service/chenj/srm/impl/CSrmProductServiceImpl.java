package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmProductMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmProduct;
import com.skeqi.mes.service.chenj.srm.CSrmProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmProductServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmProductServiceImpl implements CSrmProductService {

    @Resource
    private CSrmProductMapper cSrmProductMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmProduct record) {
        return cSrmProductMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmProduct record) {
        return cSrmProductMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmProduct record) {
        return cSrmProductMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmProduct record) {
        return cSrmProductMapper.insertSelective(record);
    }

    @Override
    public CSrmProduct selectByPrimaryKey(Integer id) {
        return cSrmProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmProduct record) {
        return cSrmProductMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmProduct record) {
        return cSrmProductMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmProduct> list) {
        return cSrmProductMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmProduct> list) {
        return cSrmProductMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmProduct> list) {
        return cSrmProductMapper.batchInsert(list);
    }

}


