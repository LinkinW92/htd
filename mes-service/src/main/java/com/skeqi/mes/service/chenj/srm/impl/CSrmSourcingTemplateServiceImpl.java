package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.CSrmEnquiryInvitationForBidsTHMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmSourcingTemplateAffiliateMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmSourcingTemplateMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmSourcingTemplate;
import com.skeqi.mes.pojo.chenj.srm.CSrmSourcingTemplateAffiliate;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSourcingTemplateReq;
import com.skeqi.mes.service.chenj.srm.CSrmSourcingTemplateService;
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
 * @Classname CSrmSourcingTemplateServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmSourcingTemplateServiceImpl implements CSrmSourcingTemplateService {

    @Resource
    private CSrmSourcingTemplateMapper cSrmSourcingTemplateMapper;

    @Resource
    private CSrmSourcingTemplateAffiliateMapper cSrmSourcingTemplateAffiliateMapper;

    @Resource
    private CSrmEnquiryInvitationForBidsTHMapper cSrmEnquiryInvitationForBidsTHMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmSourcingTemplateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmSourcingTemplate record) {
        return cSrmSourcingTemplateMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmSourcingTemplate record) {
        return cSrmSourcingTemplateMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmSourcingTemplate record) {
        return cSrmSourcingTemplateMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmSourcingTemplate record) {
        return cSrmSourcingTemplateMapper.insertSelective(record);
    }

    @Override
    public List<CSrmSourcingTemplate> selectByPrimaryKey(CSrmSourcingTemplate cSrmSourcingTemplate) {
        return cSrmSourcingTemplateMapper.selectByPrimaryKey(cSrmSourcingTemplate);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmSourcingTemplate record) {
        return cSrmSourcingTemplateMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmSourcingTemplate record) {
        return cSrmSourcingTemplateMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmSourcingTemplate> list) {
        return cSrmSourcingTemplateMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmSourcingTemplate> list) {
        return cSrmSourcingTemplateMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmSourcingTemplate> list) {
        return cSrmSourcingTemplateMapper.batchInsert(list);
    }

    @Override
    public Rjson createSourcingTemplateOrUpdate(CSrmSourcingTemplateReq cSrmSourcingTemplateReq) {
        /**
         * 输入：模板名称、类型、状态、版本、附属类别、属性字段、字段名称、字段值
         * 处理：生成模板编号（以RFXM开头+年月日+3位流水号），更新寻源模板主表及附属表，禁用操作状态变成已禁用，启用操作状态变成已启用，同一编号只能有一个版本生效。
         */
        CSrmSourcingTemplate cSrmSourcingTemplate = null;
        if (("1").equals(cSrmSourcingTemplateReq.getOperationSign())) {
            //  生成模板编号（以RFXM开头+年月日+3位流水号），更新寻源模板主表及附属表，禁用操作状态变成已禁用，启用操作状态变成已启用，同一编号只能有一个版本生效。
            cSrmSourcingTemplate = cSrmSourcingTemplateMapper.selectFinallyData();
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (cSrmSourcingTemplate == null) {
                // 未找到数据，从最新一条开始
                cSrmSourcingTemplateReq.setTemplateCode("RFXM" + yyyyMMdd + 100);
            } else {
                int requestCode = Integer.parseInt(cSrmSourcingTemplate.getTemplateCode().substring(12)) + 1;
                cSrmSourcingTemplateReq.setTemplateCode("RFXM" + yyyyMMdd + requestCode);
            }
            // 新增寻源模板表
            cSrmSourcingTemplate = new CSrmSourcingTemplate();
            BeanUtils.copyProperties(cSrmSourcingTemplateReq, cSrmSourcingTemplate);
            cSrmSourcingTemplate.setCreateTime(new Date());
            cSrmSourcingTemplateMapper.insertOrUpdateSelective(cSrmSourcingTemplate);
            // 新增寻源模板附属信息表
            CSrmSourcingTemplateAffiliate cSrmSourcingTemplateAffiliate = new CSrmSourcingTemplateAffiliate();
            BeanUtils.copyProperties(cSrmSourcingTemplateReq, cSrmSourcingTemplateAffiliate);
            cSrmSourcingTemplateAffiliate.setCreateTime(new Date());
            cSrmSourcingTemplateAffiliateMapper.insertOrUpdateSelective(cSrmSourcingTemplateAffiliate);
            return Rjson.success("创建成功", cSrmSourcingTemplateReq);
        }else if (("2").equals(cSrmSourcingTemplateReq.getOperationSign())) {
            // 关闭寻源模板中已开启模板
            cSrmSourcingTemplate = new CSrmSourcingTemplate();
            cSrmSourcingTemplate.setStatus("1");
            cSrmSourcingTemplate.setUpdateTime(new Date());
            cSrmSourcingTemplateMapper.updateByPrimaryKeySelective(cSrmSourcingTemplate);

            // 校验模板编号是否存在
            cSrmSourcingTemplate = new CSrmSourcingTemplate();
            cSrmSourcingTemplate.setTemplateCode(cSrmSourcingTemplateReq.getTemplateCode());
            List<CSrmSourcingTemplate> sourcingTemplates = cSrmSourcingTemplateMapper.selectByPrimaryKey(cSrmSourcingTemplate);
            if (sourcingTemplates.size() == 0) {
                return Rjson.error("操作失败，模板编号不存在");
            } else {
                // 修改寻源模板表
                cSrmSourcingTemplate = new CSrmSourcingTemplate();
                BeanUtils.copyProperties(cSrmSourcingTemplateReq, cSrmSourcingTemplate);
                cSrmSourcingTemplate.setUpdateTime(new Date());
                cSrmSourcingTemplateMapper.updateByPrimaryKeySelective(cSrmSourcingTemplate);
                // 修改寻源模板附属信息表
                CSrmSourcingTemplateAffiliate cSrmSourcingTemplateAffiliate = new CSrmSourcingTemplateAffiliate();
                BeanUtils.copyProperties(cSrmSourcingTemplateReq, cSrmSourcingTemplateAffiliate);
                cSrmSourcingTemplateAffiliate.setUpdateTime(new Date());
                cSrmSourcingTemplateAffiliateMapper.updateByPrimaryKeySelective(cSrmSourcingTemplateAffiliate);
                return Rjson.success("操作成功", null);
            }
            // 新增版本
        } else if (("3").equals(cSrmSourcingTemplateReq.getOperationSign())) {
            // 查询版本是否重复
            cSrmSourcingTemplate = new CSrmSourcingTemplate();
            cSrmSourcingTemplate.setTemplateCode(cSrmSourcingTemplateReq.getTemplateCode());
            cSrmSourcingTemplate.setVersion(cSrmSourcingTemplateReq.getVersion());
            List<CSrmSourcingTemplate> sourcingTemplates = cSrmSourcingTemplateMapper.selectByPrimaryKey(cSrmSourcingTemplate);
            if (sourcingTemplates.size() > 0) {
                return Rjson.error("新增失败版本号重复");
            } else {
                // 新增寻源模板表
                cSrmSourcingTemplate = new CSrmSourcingTemplate();
                BeanUtils.copyProperties(cSrmSourcingTemplateReq, cSrmSourcingTemplate);
                cSrmSourcingTemplate.setCreateTime(new Date());
                cSrmSourcingTemplateMapper.insertOrUpdateSelective(cSrmSourcingTemplate);
                // 新增寻源模板附属信息表
                CSrmSourcingTemplateAffiliate cSrmSourcingTemplateAffiliate = new CSrmSourcingTemplateAffiliate();
                BeanUtils.copyProperties(cSrmSourcingTemplateReq, cSrmSourcingTemplateAffiliate);
                cSrmSourcingTemplateAffiliate.setCreateTime(new Date());
                cSrmSourcingTemplateAffiliateMapper.insertOrUpdateSelective(cSrmSourcingTemplateAffiliate);
                return Rjson.success("创建成功", null);
            }
        }else if (("4").equals(cSrmSourcingTemplateReq.getOperationSign())) {
            // 校验模板编号是否存在
            cSrmSourcingTemplate = new CSrmSourcingTemplate();
            cSrmSourcingTemplate.setTemplateCode(cSrmSourcingTemplateReq.getTemplateCode());
            List<CSrmSourcingTemplate> sourcingTemplates = cSrmSourcingTemplateMapper.selectByPrimaryKey(cSrmSourcingTemplate);
            if (sourcingTemplates.size() == 0) {
                return Rjson.error("操作失败，模板编号不存在");
            } else {
                // 修改寻源模板表
                cSrmSourcingTemplate = new CSrmSourcingTemplate();
                BeanUtils.copyProperties(cSrmSourcingTemplateReq, cSrmSourcingTemplate);
                cSrmSourcingTemplate.setUpdateTime(new Date());
                cSrmSourcingTemplateMapper.updateByPrimaryKeySelective(cSrmSourcingTemplate);
                // 修改寻源模板附属信息表
                CSrmSourcingTemplateAffiliate cSrmSourcingTemplateAffiliate = new CSrmSourcingTemplateAffiliate();
                BeanUtils.copyProperties(cSrmSourcingTemplateReq, cSrmSourcingTemplateAffiliate);
                cSrmSourcingTemplateAffiliate.setUpdateTime(new Date());
                cSrmSourcingTemplateAffiliateMapper.updateByPrimaryKeySelective(cSrmSourcingTemplateAffiliate);
                return Rjson.success("操作成功", null);
            }
            // 新增版本
        }else {
            return Rjson.error("操作标识错误");
        }

    }
}

