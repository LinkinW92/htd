package com.skeqi.mes.service.wf.impl;

import com.skeqi.mes.pojo.wf.baseMode.coderRule.CMesCodeRuleT;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.wf.baseMode.codeRule.CodeRuleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.CMesMaterialMapping;
import com.skeqi.mes.mapper.wf.CMesMaterialMappingMapper;
import com.skeqi.mes.service.wf.CMesMaterialMappingService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CMesMaterialMappingServiceImpl implements CMesMaterialMappingService{

    @Resource
    private CMesMaterialMappingMapper CMesMaterialMappingMapper;

    @Autowired
    CodeRuleService codeRuleService;

    @Override
    public List<CMesMaterialMapping> findMaterialMapping(String supplierName) {
        return CMesMaterialMappingMapper.findMaterialBatchMapping(supplierName);
    }

    @Override
    public Rjson addMapping(CMesMaterialMapping mapping) throws Exception {
        //验证数据重复
        List<CMesMaterialMapping> list = CMesMaterialMappingMapper.selectBatchMapping(mapping);
        if (list.size()>0){
            throw new Exception("供应商信息或批次信息重复！");
        }
        //判断批次是否为空
        if (mapping.getBatch()==null||mapping.getBatch().equals("")){
            //为空批次自动生成
            String latestCode = codeRuleService.getLatestCode(CodeRuleConstant.BATCH_MAPPING);
            mapping.setBatch(latestCode);
        }

        Integer integer = CMesMaterialMappingMapper.addBatchMapping(mapping);
        if (integer<1){
            throw new Exception("添加批次映射数据失败");
        }

/*        //更新条码规则数据
        if (!StringUtils.isEmpty(latestCode)){
            //更新条码规则数据
            Integer latestCode1 = codeRuleService.updateLatestCode(latestCode);
            if (latestCode1<1){
                throw new Exception("更新条码规则失败");
            }
        }*/
        return Rjson.success();
    }

    @Override
    public List<CMesMaterialMapping> selectMapping(CMesMaterialMapping mapping) {
        return CMesMaterialMappingMapper.selectBatchMapping(mapping);
    }

    @Override
    public Rjson addsMapping(List<Map<String, Object>> data) throws Exception {
        for (Map<String, Object> object : data) {
            if (object.get("batch") == null || object.get("batch").equals("")) {
                //为空批次自动生成
                String latestCode = codeRuleService.getLatestCode(CodeRuleConstant.BATCH_MAPPING);
                object.put("batch", latestCode);
            }
        }
/*        //为空批次自动生成
        CMesCodeRuleT latestCode = codeRuleService.getLatestCode(CodeRuleConstant.BATCH_MAPPING);
        Integer suffixValue = 0;
        int i =0;
        for (Map<String, Object> object : data) {
            if (object.get("batch") == null || object.get("batch").equals("")) {
                if (i != 0) {
                    suffixValue = Integer.parseInt(latestCode.getCodeRuleSuffixValue());
                    suffixValue++;
                    String format = String.format("%0" + latestCode.getCodeRuleSuffix() + "d", suffixValue);
                    latestCode.setCodeRuleValue(latestCode.getCodeRuleValue());
                    latestCode.setCodeRuleSuffixValue(format);
                    latestCode.setLastCode(latestCode.getCodeRulePrefix()+latestCode.getCodeRuleValue()+format);
                }
                object.put("batch", latestCode.getLastCode());
                i++;
            }
        }*/

        Integer integer = CMesMaterialMappingMapper.addBatchsMapping(data);
        if (integer<1){
            throw new Exception("添加批次映射失败");
        }
/*        //更新条码规则数据
        Integer latestCode1 = codeRuleService.updateLatestCode(latestCode);
        if (latestCode1<1){
            throw new Exception("更新条码规则失败");
        }*/
        return Rjson.success();
    }
}
