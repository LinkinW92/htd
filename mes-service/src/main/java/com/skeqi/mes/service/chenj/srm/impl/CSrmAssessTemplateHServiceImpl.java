package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.chenj.srm.CSrmAssessTemplateHMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmAssessTemplateRMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmIndicatorsDimensionMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmAssessTemplateH;
import com.skeqi.mes.pojo.chenj.srm.CSrmAssessTemplateR;
import com.skeqi.mes.pojo.chenj.srm.CSrmIndicatorsDimension;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmAssessTemplateHReq;
import com.skeqi.mes.service.chenj.srm.CSrmAssessTemplateHService;
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
 * @Classname CSrmAssessTemplateHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmAssessTemplateHServiceImpl implements CSrmAssessTemplateHService {

    @Resource
    private CSrmAssessTemplateHMapper cSrmAssessTemplateHMapper;
    @Resource
    private CSrmAssessTemplateRMapper cSrmAssessTemplateRMapper;
    @Resource
    private CSrmIndicatorsDimensionMapper cSrmIndicatorsDimensionMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmAssessTemplateHMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.insertSelective(record);
    }

    @Override
    public CSrmAssessTemplateH selectByPrimaryKey(Integer id) {
        return cSrmAssessTemplateHMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmAssessTemplateH> list) {
        return cSrmAssessTemplateHMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmAssessTemplateH> list) {
        return cSrmAssessTemplateHMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmAssessTemplateH> list) {
        return cSrmAssessTemplateHMapper.batchInsert(list);
    }

    @Override
    public Rjson createEvaluationTemplate(CSrmAssessTemplateHReq cSrmAssessTemplateHReq) {

        // 校验指标编码是否在评估指标表中存在
        CSrmIndicatorsDimension cSrmIndicatorsDimension = new CSrmIndicatorsDimension();
        cSrmIndicatorsDimension.setIndexCoding(cSrmAssessTemplateHReq.getIndexCode());
        CSrmIndicatorsDimension cSrmIndicatorsDimension1 = cSrmIndicatorsDimensionMapper.selectByPrimaryKey(cSrmIndicatorsDimension);
        if (null != cSrmIndicatorsDimension1) {
            // 校验指标编码是否在评估模板R表中存在
            CSrmAssessTemplateR cSrmAssessTemplateR = new CSrmAssessTemplateR();
            cSrmAssessTemplateR.setIndexCode(cSrmAssessTemplateHReq.getIndexCode());
            CSrmAssessTemplateR selectByPrimaryKey = cSrmAssessTemplateRMapper.selectByPrimaryKey(cSrmAssessTemplateR);
            if (selectByPrimaryKey == null) {
                // 生成模板编号（以PEM开头+年月日+3位流水号）、状态为新建，更新评估模板头行表
                CSrmAssessTemplateH cSrmAssessTemplateH = cSrmAssessTemplateHMapper.selectFinallyData();
                if (cSrmAssessTemplateH == null) {
                    cSrmAssessTemplateH = new CSrmAssessTemplateH();
                    // 未找到数据，从最新一条开始
                    String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                    cSrmAssessTemplateH.setTemplateCode("PEM" + yyyyMMdd + 100);
                } else {
                    String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                    int requestCode = Integer.parseInt(cSrmAssessTemplateH.getTemplateCode().substring(11)) + 1;
                    cSrmAssessTemplateH.setTemplateCode("PEM" + yyyyMMdd + requestCode);
                }

                // 新增评估模板头表
                String templateCode = cSrmAssessTemplateH.getTemplateCode();
                BeanUtils.copyProperties(cSrmAssessTemplateHReq, cSrmAssessTemplateH);
                cSrmAssessTemplateH.setStatus("1");
                cSrmAssessTemplateH.setTemplateCode(templateCode);
                cSrmAssessTemplateH.setCreateTime(new Date());
                cSrmAssessTemplateHMapper.insertOrUpdateSelective(cSrmAssessTemplateH);
                // 新增评估模板行表
                BeanUtils.copyProperties(cSrmAssessTemplateHReq, cSrmAssessTemplateR);
                cSrmAssessTemplateR.setCreateTime(new Date());
                cSrmAssessTemplateR.setTemplateCode(templateCode);
                cSrmAssessTemplateRMapper.insertOrUpdateSelective(cSrmAssessTemplateR);
                return Rjson.success("创建成功", cSrmAssessTemplateHReq);
            } else {
                return Rjson.error("指标编码在评估指标记录中已存在");
            }

        } else {
            return Rjson.error("指标编码在指标维度记录中不存在");
        }
    }

    @Override
    public Rjson updateEvaluationTemplate(CSrmAssessTemplateHReq cSrmAssessTemplateHReq) {
        // 校验模板编号是否存在
        CSrmAssessTemplateR cSrmAssessTemplateR = new CSrmAssessTemplateR();
        cSrmAssessTemplateR.setTemplateCode(cSrmAssessTemplateHReq.getTemplateCode());
        CSrmAssessTemplateR selectByPrimaryKey = cSrmAssessTemplateRMapper.selectByPrimaryKey(cSrmAssessTemplateR);
        if (null != selectByPrimaryKey) {
            // 更新版本
            cSrmAssessTemplateR = new CSrmAssessTemplateR();
            cSrmAssessTemplateR.setTemplateCode(cSrmAssessTemplateHReq.getTemplateCode());
            cSrmAssessTemplateR.setVersion(cSrmAssessTemplateHReq.getVersion());
            cSrmAssessTemplateR.setUpdateTime(new Date());
            cSrmAssessTemplateRMapper.updateByPrimaryKeySelective(cSrmAssessTemplateR);
            // 更新状态
            CSrmAssessTemplateH cSrmAssessTemplateH = new CSrmAssessTemplateH();
            cSrmAssessTemplateH.setTemplateCode(cSrmAssessTemplateHReq.getTemplateCode());
            cSrmAssessTemplateH.setVersion(cSrmAssessTemplateHReq.getVersion());
            cSrmAssessTemplateH.setStatus(cSrmAssessTemplateHReq.getUpdateSign());
            cSrmAssessTemplateH.setUpdateTime(new Date());
            cSrmAssessTemplateHMapper.updateByPrimaryKeySelective(cSrmAssessTemplateH);
            return Rjson.success("修改成功", null);
        } else {
            return Rjson.error("指标编码在评估指标记录中不存在");
        }


    }

    @Override
    public Rjson findEvaluationTemplate(CSrmAssessTemplateHReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmAssessTemplateHMapper.findEvaluationTemplate(req)));

    }
}

