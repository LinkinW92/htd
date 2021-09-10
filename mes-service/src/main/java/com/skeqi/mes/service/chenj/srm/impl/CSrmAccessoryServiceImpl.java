package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmAccessoryMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmAccessory;
import com.skeqi.mes.service.chenj.srm.CSrmAccessoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmAccessoryServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmAccessoryServiceImpl implements CSrmAccessoryService {

    @Resource
    private CSrmAccessoryMapper cSrmAccessoryMapper;

    @Override
    public int insertSelective(CSrmAccessory record) {
        return cSrmAccessoryMapper.insertSelective(record);
    }

    @Override
    public CSrmAccessory selectByPrimaryKey(Integer id) {
        return cSrmAccessoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmAccessory record) {
        return cSrmAccessoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmAccessory> list) {
        return cSrmAccessoryMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmAccessory> list) {
        return cSrmAccessoryMapper.batchInsert(list);
    }

}


