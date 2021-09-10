package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmSendCommodityLogisticsMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmSendCommodityLogistics;
import com.skeqi.mes.service.chenj.srm.CSrmSendCommodityLogisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSendCommodityLogisticsServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmSendCommodityLogisticsServiceImpl implements CSrmSendCommodityLogisticsService {

    @Resource
    private CSrmSendCommodityLogisticsMapper cSrmSendCommodityLogisticsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmSendCommodityLogisticsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmSendCommodityLogistics record) {
        return cSrmSendCommodityLogisticsMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmSendCommodityLogistics record) {
        return cSrmSendCommodityLogisticsMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmSendCommodityLogistics record) {
        return cSrmSendCommodityLogisticsMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmSendCommodityLogistics record) {
        return cSrmSendCommodityLogisticsMapper.insertSelective(record);
    }

    @Override
    public CSrmSendCommodityLogistics selectByPrimaryKey(Integer id) {
        return cSrmSendCommodityLogisticsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmSendCommodityLogistics record) {
        return cSrmSendCommodityLogisticsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmSendCommodityLogistics record) {
        return cSrmSendCommodityLogisticsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmSendCommodityLogistics> list) {
        return cSrmSendCommodityLogisticsMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmSendCommodityLogistics> list) {
        return cSrmSendCommodityLogisticsMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmSendCommodityLogistics> list) {
        return cSrmSendCommodityLogisticsMapper.batchInsert(list);
    }

}

