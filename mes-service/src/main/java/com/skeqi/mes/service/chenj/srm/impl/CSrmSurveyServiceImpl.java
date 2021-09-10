package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.chenj.srm.CSrmSupplierMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmSurveyDetailsMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmSurveyMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplier;
import com.skeqi.mes.pojo.chenj.srm.CSrmSurvey;
import com.skeqi.mes.pojo.chenj.srm.CSrmSurveyDetails;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSurveyReq;
import com.skeqi.mes.service.chenj.srm.CSrmSurveyService;
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
 * @Classname CSrmSurveyServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmSurveyServiceImpl implements CSrmSurveyService {

    @Resource
    private CSrmSurveyMapper cSrmSurveyMapper;
    @Resource
    private CSrmSurveyDetailsMapper cSrmSurveyDetailsMapper;
    @Resource
    private CSrmSupplierMapper cSrmSupplierMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmSurveyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmSurvey record) {
        return cSrmSurveyMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmSurvey record) {
        return cSrmSurveyMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmSurvey record) {
        return cSrmSurveyMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmSurvey record) {
        return cSrmSurveyMapper.insertSelective(record);
    }

    @Override
    public CSrmSurvey selectSurveyData(CSrmSurvey record) {
        return cSrmSurveyMapper.selectSurveyData(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmSurvey record) {
        return cSrmSurveyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmSurvey record) {
        return cSrmSurveyMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmSurvey> list) {
        return cSrmSurveyMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmSurvey> list) {
        return cSrmSurveyMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmSurvey> list) {
        return cSrmSurveyMapper.batchInsert(list);
    }

    @Override
    public Rjson createSurvey(CSrmSurveyReq cSrmSurveyReq) {
        // 公司名称、创建人、模板编号、附属类别、属性字段、字段值、供应商代码
        // 获取模板信息，生成调查表单号（以R开头+年月日+3位流水号），更新调查表主表及附属表

        // 查询供应商代码是否存在
        CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmSurveyReq.getSupplierCode());
        if (cSrmSupplier == null) {
            return Rjson.error("供应商代码不存在");
        } else {
            // 生成调查表单号（以R开头+年月日+3位流水号）
            // 获取最新最后一条记录
            CSrmSurvey cSrmSurvey = cSrmSurveyMapper.selectFinallyData();
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (null == cSrmSurvey) {
                cSrmSurvey = new CSrmSurvey();
                // 未找到数据，从最新一条开始
                cSrmSurvey.setSurveyFormNumber("R" + yyyyMMdd + 100);
            } else {
                int requestCode = Integer.parseInt(cSrmSurvey.getSurveyFormNumber().substring(9)) + 1;
                cSrmSurvey.setSurveyFormNumber("R" + yyyyMMdd + requestCode);
            }

            // 新增调查表信息
            String surveyFormNumber = cSrmSurvey.getSurveyFormNumber();
            BeanUtils.copyProperties(cSrmSurveyReq, cSrmSurvey);
            cSrmSurvey.setSurveyFormNumber(surveyFormNumber);
            // 防止执行修改操作
            cSrmSurvey.setId(null);
            cSrmSurvey.setCreateTime(new Date());
            cSrmSurveyMapper.insertOrUpdateSelective(cSrmSurvey);

            // 新增调查附属表信息
            CSrmSurveyDetails cSrmSurveyDetails = new CSrmSurveyDetails();
            BeanUtils.copyProperties(cSrmSurveyReq, cSrmSurveyDetails);
            cSrmSurveyDetails.setSurveyFormNumber(surveyFormNumber);
            cSrmSurveyDetails.setCreateTime(new Date());
            cSrmSurveyDetailsMapper.insertOrUpdateSelective(cSrmSurveyDetails);
            return Rjson.success("创建成功", cSrmSurveyReq);
        }

    }

    @Override
    public Rjson examineSurvey(CSrmSurveyReq cSrmSurveyReq) {
        // 调查表单号、附属类别、属性字段、字段值、供应商代码、审批标记
        // 查询调查表信息，更新调查表主表及附属表，审批不通过状态更新为已拒绝，审批通过更新为待发布，发布后状态更新为待回复，回复后更新为已完成

        // 查询供应商代码是否存在
        CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmSurveyReq.getSupplierCode());
        if (cSrmSupplier == null) {
            return Rjson.error("供应商代码不存在");
        } else {
            // 校验调查表单号是否存在
            CSrmSurvey cSrmSurvey = new CSrmSurvey();
            cSrmSurvey.setSurveyFormNumber(cSrmSurveyReq.getSurveyFormNumber());
            CSrmSurvey srmSurvey = cSrmSurveyMapper.selectSurveyData(cSrmSurvey);
            if (null != srmSurvey) {
                // 根据调查表单号更新记录
                cSrmSurvey = new CSrmSurvey();
                // 更新调查表信息
                BeanUtils.copyProperties(cSrmSurveyReq, cSrmSurvey);
                cSrmSurvey.setStatus(cSrmSurveyReq.getExamineSign());
                cSrmSurvey.setUpdateTime(new Date());
                cSrmSurveyMapper.updateByPrimaryKeySelective(cSrmSurvey);

                // 更新调查附属表信息
                CSrmSurveyDetails cSrmSurveyDetails = new CSrmSurveyDetails();
                BeanUtils.copyProperties(cSrmSurveyReq, cSrmSurveyDetails);
                cSrmSurveyDetails.setSurveyFormNumber(cSrmSurvey.getSurveyFormNumber());
                cSrmSurveyDetails.setUpdateTime(new Date());
                cSrmSurveyDetailsMapper.updateByPrimaryKeySelective(cSrmSurveyDetails);
                return Rjson.success("操作成功", cSrmSurveyReq);
            } else {
                return Rjson.error("调查表单号不存在");
            }

        }

    }

    @Override
    public Rjson findCSrmSurveyData(CSrmSurveyReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmSurveyMapper.findCSrmSurveyData(req)));
    }
}


