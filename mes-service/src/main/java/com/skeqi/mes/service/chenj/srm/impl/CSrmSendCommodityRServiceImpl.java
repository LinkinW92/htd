package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmSendCommodityRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityR;
import com.skeqi.mes.service.chenj.srm.CSrmSendCommodityRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSendCommodityRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmSendCommodityRServiceImpl implements CSrmSendCommodityRService {

    @Resource
    private CSrmSendCommodityRMapper cSrmSendCommodityRMapper;

    @Override
    public int insertOrUpdate(CSrmSendCommodityR record) {
        return cSrmSendCommodityRMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmSendCommodityR record) {
        return cSrmSendCommodityRMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmSendCommodityR record) {
        return cSrmSendCommodityRMapper.insertSelective(record);
    }

    @Override
    public CSrmSendCommodityR selectByPrimaryKey(Integer id) {
        return cSrmSendCommodityRMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmSendCommodityR record) {
        return cSrmSendCommodityRMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int updateBatchSelective(List<CSrmSendCommodityR> list) {
        return cSrmSendCommodityRMapper.updateBatchSelective(list);
    }


}



