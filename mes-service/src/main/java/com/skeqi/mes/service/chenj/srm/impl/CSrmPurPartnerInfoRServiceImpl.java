package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmPurPartnerInfoRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmPurPartnerInfoR;
import com.skeqi.mes.service.chenj.srm.CSrmPurPartnerInfoRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/5
 * @Classname CSrmPurPartnerInfoRServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmPurPartnerInfoRServiceImpl implements CSrmPurPartnerInfoRService {

    @Resource
    private CSrmPurPartnerInfoRMapper cSrmPurPartnerInfoRMapper;

    @Override
    public int insertSelective(CSrmPurPartnerInfoR record) {
        return cSrmPurPartnerInfoRMapper.insertSelective(record);
    }

    @Override
    public List<CSrmPurPartnerInfoR> selectByPrimaryKey(CSrmPurPartnerInfoR record) {
        return cSrmPurPartnerInfoRMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmPurPartnerInfoR record) {
        return cSrmPurPartnerInfoRMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmPurPartnerInfoR> list) {
        return cSrmPurPartnerInfoRMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmPurPartnerInfoR> list) {
        return cSrmPurPartnerInfoRMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(CSrmPurPartnerInfoR record) {
        return cSrmPurPartnerInfoRMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmPurPartnerInfoR record) {
        return cSrmPurPartnerInfoRMapper.insertOrUpdateSelective(record);
    }

}


