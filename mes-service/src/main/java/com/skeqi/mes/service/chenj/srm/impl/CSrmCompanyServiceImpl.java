package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.mapper.chenj.srm.CSrmCompanyMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmCompany;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmCompanyReq;
import com.skeqi.mes.service.chenj.srm.CSrmCompanyService;
import com.skeqi.mes.util.Rjson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmCompanyServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmCompanyServiceImpl implements CSrmCompanyService {

    @Resource
    private CSrmCompanyMapper cSrmCompanyMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmCompanyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmCompany record) {
        return cSrmCompanyMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmCompany record) {
        return cSrmCompanyMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmCompany record) {
        return cSrmCompanyMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmCompany record) {
        return cSrmCompanyMapper.insertSelective(record);
    }

    @Override
    public CSrmCompany selectByPrimaryKey(CSrmCompany record) {
        return cSrmCompanyMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmCompany record) {
        return cSrmCompanyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmCompany record) {
        return cSrmCompanyMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmCompany> list) {
        return cSrmCompanyMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmCompany> list) {
        return cSrmCompanyMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmCompany> list) {
        return cSrmCompanyMapper.batchInsert(list);
    }

    @Override
    public Rjson selectCompanyList(CSrmCompanyReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmCompanyMapper.selectCompanyList(req)));


    }
}

