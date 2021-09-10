package com.skeqi.mes.service.chenj.srm.impl;


import com.skeqi.mes.mapper.chenj.srm.CSrmFinanceMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmFinance;
import com.skeqi.mes.service.chenj.srm.CSrmFinanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmFinanceServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmFinanceServiceImpl implements CSrmFinanceService {

    @Resource
    private CSrmFinanceMapper cSrmFinanceMapper;

    @Override
    public int insertSelective(CSrmFinance record) {
        return cSrmFinanceMapper.insertSelective(record);
    }

    @Override
    public CSrmFinance selectByPrimaryKey(Integer id) {
        return cSrmFinanceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmFinance record) {
        return cSrmFinanceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmFinance> list) {
        return cSrmFinanceMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmFinance> list) {
        return cSrmFinanceMapper.batchInsert(list);
    }

}


