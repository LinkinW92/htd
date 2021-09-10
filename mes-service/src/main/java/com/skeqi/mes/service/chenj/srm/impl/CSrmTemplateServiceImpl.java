package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.mapper.chenj.srm.CSrmTemplateDetailsMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmTemplateMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmTemplate;
import com.skeqi.mes.pojo.chenj.srm.CSrmTemplateDetails;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmTemplateReq;
import com.skeqi.mes.service.chenj.srm.CSrmTemplateService;
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
 * @Classname CSrmTemplateServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmTemplateServiceImpl implements CSrmTemplateService {

    @Resource
    private CSrmTemplateMapper cSrmTemplateMapper;

    @Resource
    private CSrmTemplateDetailsMapper cSrmTemplateDetailsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmTemplateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmTemplate record) {
        return cSrmTemplateMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmTemplate record) {
        return cSrmTemplateMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmTemplate record) {
        return cSrmTemplateMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmTemplate record) {
        return cSrmTemplateMapper.insertSelective(record);
    }

    @Override
    public CSrmTemplate selectByPrimaryKey(Integer id) {
        return cSrmTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmTemplate record) {
        return cSrmTemplateMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmTemplate record) {
        return cSrmTemplateMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmTemplate> list) {
        return cSrmTemplateMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmTemplate> list) {
        return cSrmTemplateMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmTemplate> list) {
        return cSrmTemplateMapper.batchInsert(list);
    }

    @Override
    public Rjson createTemplate(CSrmTemplateReq cSrmTemplateReq) {
        // 模板名称、类型、状态、版本、附属类别、属性字段、字段名称、字段类型
        // 生成模板编号（以RM开头+年月日+3位流水号），更新模板主表及附属表
        // 校验模板名称是否已存在  生成模板编号（以RM开头+年月日+3位流水号） 结束
        // 校验模板名称
        CSrmTemplate cSrmTemplate = cSrmTemplateMapper.selectTemplateName(cSrmTemplateReq.getTemplateName());
        if (cSrmTemplate == null) {
            // 生成模板编号（以RM开头+年月日+3位流水号）
            // 获取最新最后一条记录
            CSrmTemplate selectFinallyData = cSrmTemplateMapper.selectFinallyData();
            if (null == selectFinallyData) {
                selectFinallyData = new CSrmTemplate();
                // 未找到数据，从最新一条开始
                String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                selectFinallyData.setTemplateCode("RM" + yyyyMMdd + 100);
            } else {
                String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                int requestCode = Integer.parseInt(selectFinallyData.getTemplateCode().substring(10)) + 1;
                selectFinallyData.setTemplateCode("RM" + yyyyMMdd + requestCode);
            }

            // 新增模板主表信息
            BeanUtils.copyProperties(cSrmTemplateReq, selectFinallyData);
            selectFinallyData.setCreateTime(new Date());
            cSrmTemplateMapper.insertOrUpdateSelective(selectFinallyData);

            // 新增模板附属表信息
            CSrmTemplateDetails cSrmSupplierHReq = new CSrmTemplateDetails();
            BeanUtils.copyProperties(cSrmTemplateReq, cSrmSupplierHReq);
            cSrmSupplierHReq.setTemplateCode(selectFinallyData.getTemplateCode());
            cSrmSupplierHReq.setCreateTime(new Date());
            cSrmTemplateDetailsMapper.insertOrUpdateSelective(cSrmSupplierHReq);
            return Rjson.success("创建成功", null);
        } else {
            return Rjson.error("创建失败,模板名称已存在");
        }
    }

    @Override
    public Rjson findTemplate(CSrmTemplateReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmTemplateMapper.findTemplate(req)));
    }
}



