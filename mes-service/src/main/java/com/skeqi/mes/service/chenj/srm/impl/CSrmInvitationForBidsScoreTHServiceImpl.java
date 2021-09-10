package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.mapper.chenj.srm.CSrmInvitationForBidsScoreTHMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmInvitationForBidsScoreTRMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmInvitationForBidsScoreTH;
import com.skeqi.mes.pojo.chenj.srm.CSrmInvitationForBidsScoreTR;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvitationForBidsScoreTHReq;
import com.skeqi.mes.service.chenj.srm.CSrmInvitationForBidsScoreTHService;
import com.skeqi.mes.util.Rjson;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmInvitationForBidsScoreTHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmInvitationForBidsScoreTHServiceImpl implements CSrmInvitationForBidsScoreTHService {

    @Resource
    private CSrmInvitationForBidsScoreTHMapper cSrmInvitationForBidsScoreTHMapper;
    @Resource
    private CSrmInvitationForBidsScoreTRMapper cSrmInvitationForBidsScoreTRMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmInvitationForBidsScoreTHMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmInvitationForBidsScoreTH record) {
        return cSrmInvitationForBidsScoreTHMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmInvitationForBidsScoreTH record) {
        return cSrmInvitationForBidsScoreTHMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmInvitationForBidsScoreTH record) {
        return cSrmInvitationForBidsScoreTHMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmInvitationForBidsScoreTH record) {
        return cSrmInvitationForBidsScoreTHMapper.insertSelective(record);
    }

    @Override
    public List<CSrmInvitationForBidsScoreTH> selectByPrimaryKey(CSrmInvitationForBidsScoreTH record) {
        return cSrmInvitationForBidsScoreTHMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmInvitationForBidsScoreTH record) {
        return cSrmInvitationForBidsScoreTHMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmInvitationForBidsScoreTH record) {
        return cSrmInvitationForBidsScoreTHMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmInvitationForBidsScoreTH> list) {
        return cSrmInvitationForBidsScoreTHMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmInvitationForBidsScoreTH> list) {
        return cSrmInvitationForBidsScoreTHMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmInvitationForBidsScoreTH> list) {
        return cSrmInvitationForBidsScoreTHMapper.batchInsert(list);
    }

    @Override
    public Rjson createIndexScore(CSrmInvitationForBidsScoreTHReq cSrmInvitationForBidsScoreTHReq) {
        // 生成模板编号（以BIDM开头+年月日+3位流水号），更新招标平复模板头表及行表，禁用操作状态变成已禁用，启用操作状态变成已启用。
        CSrmInvitationForBidsScoreTH cSrmInvitationForBidsScoreTH = cSrmInvitationForBidsScoreTHMapper.selectFinallyData();
        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (cSrmInvitationForBidsScoreTH == null) {
            // 未找到数据，从最新一条开始
            cSrmInvitationForBidsScoreTHReq.setTemplateNumber("BIDM" + yyyyMMdd + 100);
        } else {
            int requestCode = Integer.parseInt(cSrmInvitationForBidsScoreTH.getTemplateNumber().substring(12)) + 1;
            cSrmInvitationForBidsScoreTHReq.setTemplateNumber("BIDM" + yyyyMMdd + requestCode);
        }
        // 新增招标评分模板头表
        cSrmInvitationForBidsScoreTH = new CSrmInvitationForBidsScoreTH();
        BeanUtils.copyProperties(cSrmInvitationForBidsScoreTHReq, cSrmInvitationForBidsScoreTH);
        cSrmInvitationForBidsScoreTH.setCreateTime(new Date());
        cSrmInvitationForBidsScoreTHMapper.insertOrUpdateSelective(cSrmInvitationForBidsScoreTH);
        // 新增招标评分模板行表
        CSrmInvitationForBidsScoreTR cSrmInvitationForBidsScoreTR = new CSrmInvitationForBidsScoreTR();
        BeanUtils.copyProperties(cSrmInvitationForBidsScoreTHReq, cSrmInvitationForBidsScoreTR);
        cSrmInvitationForBidsScoreTR.setCreateTime(new Date());
        cSrmInvitationForBidsScoreTRMapper.insertOrUpdateSelective(cSrmInvitationForBidsScoreTR);
        return Rjson.success("创建成功", cSrmInvitationForBidsScoreTHReq);

    }

    @Override
    public Rjson findIndexScore(CSrmInvitationForBidsScoreTHReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmInvitationForBidsScoreTHMapper.findIndexScore(req)));
    }

}


