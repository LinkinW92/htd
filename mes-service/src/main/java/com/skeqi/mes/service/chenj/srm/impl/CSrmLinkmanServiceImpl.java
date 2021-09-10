package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmLinkmanMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmLinkman;
import com.skeqi.mes.service.chenj.srm.CSrmLinkmanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmLinkmanServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmLinkmanServiceImpl implements CSrmLinkmanService {

    @Resource
    private CSrmLinkmanMapper cSrmLinkmanMapper;

    @Override
    public int insertSelective(CSrmLinkman record) {
        return cSrmLinkmanMapper.insertSelective(record);
    }

    @Override
    public CSrmLinkman selectByPrimaryKey(Integer id) {
        return cSrmLinkmanMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmLinkman record) {
        return cSrmLinkmanMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmLinkman> list) {
        return cSrmLinkmanMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmLinkman> list) {
        return cSrmLinkmanMapper.batchInsert(list);
    }

}



