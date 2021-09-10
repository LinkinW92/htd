package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.mapper.chenj.srm.CSrmContractTemplateMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmContractTemplate;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmContractTemplateReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmContractTemplateRsp;
import com.skeqi.mes.service.chenj.srm.CSrmContractTemplateService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractTemplateServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmContractTemplateServiceImpl implements CSrmContractTemplateService {

    @Resource
    private CSrmContractTemplateMapper cSrmContractTemplateMapper;

    @Override
    public int insertSelective(CSrmContractTemplate record) {
        return cSrmContractTemplateMapper.insertSelective(record);
    }

    @Override
    public List<CSrmContractTemplateRsp> selectByPrimaryList(CSrmContractTemplateReq req) {
        return cSrmContractTemplateMapper.selectByPrimaryList(req);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmContractTemplate record) {
        return cSrmContractTemplateMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<CSrmContractTemplate> list) {
        return cSrmContractTemplateMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmContractTemplate> list) {
        return cSrmContractTemplateMapper.batchInsert(list);
    }

    @Override
    public Rjson createContractTemplate(List<CSrmContractTemplateReq> contractTemplateReq) {
        for (CSrmContractTemplateReq item : contractTemplateReq) {
            // 生成合同模板编码
            CSrmContractTemplate contractTemplate = null;
            if (!EqualsUtil.StringEqualsNull(item.getTemplateNumber())) {
                // 模板编号为空生成模板编号（以CB开头+年月日+3位流水号）
                contractTemplate = cSrmContractTemplateMapper.selectFinallyData();
                String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                if (contractTemplate == null) {
                    // 未找到数据，从最新一条开始
                    item.setTemplateNumber("CB" + yyyyMMdd + 100);
                } else {
                    int requestCode = Integer.parseInt(contractTemplate.getTemplateNumber().substring(10)) + 1;
                    item.setTemplateNumber("CB" + yyyyMMdd + requestCode);
                }
            } else {
                // 校验合同模板头表数据是否存在
                CSrmContractTemplateReq req = new CSrmContractTemplateReq();
                req.setTemplateNumber(item.getTemplateNumber());
                List<CSrmContractTemplateRsp> cSrmContractHS = cSrmContractTemplateMapper.selectByPrimaryList(req);
                if (cSrmContractHS.size() > 1) {
                    return Rjson.error("创建失败，合同模板数据已存在");
                }

            }
            cSrmContractTemplateMapper.insertSelectiveReq(item);
        }
//        cSrmContractTemplateMapper.batchInsertReq(contractTemplateReq);
        return Rjson.success("创建成功", contractTemplateReq);
    }


    @Override
    public Rjson updateContractTemplate(List<CSrmContractTemplateReq> contractTemplateReq) {
        return cSrmContractTemplateMapper.updateBatchSelectiveReq(contractTemplateReq) > 0 ? Rjson.success("更新成功", null) : Rjson.error("更新失败，记录不存在");
    }

    @Override
    public Rjson findContractTemplate(CSrmContractTemplateReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmContractTemplateMapper.selectByPrimaryList(req)));
    }

    @Override
    public Rjson delContractTemplate(List<Integer> id) {
        return cSrmContractTemplateMapper.delContractTemplate(id) > 0 ? Rjson.success("删除成功", null) : Rjson.error("删除失败，记录不存在");
    }

    @Override
    public Rjson findByPrimaryList(CSrmContractTemplateReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmContractTemplateMapper.selectNumberOrCodeList(req)));
    }
}






