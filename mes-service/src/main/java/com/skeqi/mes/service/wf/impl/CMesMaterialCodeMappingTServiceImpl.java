package com.skeqi.mes.service.wf.impl;

import com.skeqi.mes.pojo.wf.baseMode.coderRule.CMesCodeRuleT;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.wf.baseMode.codeRule.CodeRuleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.CMesMaterialCodeMappingTMapper;
import com.skeqi.mes.pojo.wf.CMesMaterialCodeMappingT;
import com.skeqi.mes.service.wf.CMesMaterialCodeMappingTService;

import java.util.List;
import java.util.Map;

@Service
public class CMesMaterialCodeMappingTServiceImpl implements CMesMaterialCodeMappingTService{

    @Resource
    private CMesMaterialCodeMappingTMapper cMesMaterialCodeMappingTMapper;

    @Autowired
    CodeRuleService codeRuleService;

    @Override
    public List<CMesMaterialCodeMappingT> findMaterialCodeMapping(String supplierName) {
        return cMesMaterialCodeMappingTMapper.findMaterialCodeMapping(supplierName);
    }

    @Override
    public List<CMesMaterialCodeMappingT> selectCodeMapping(CMesMaterialCodeMappingT mapping) {
        return cMesMaterialCodeMappingTMapper.selectCodeMapping(mapping);
    }

    @Override
    public Rjson addMaterialCodeMapping(CMesMaterialCodeMappingT mapping) throws Exception {
        //验证数据重复
        List<CMesMaterialCodeMappingT> list = cMesMaterialCodeMappingTMapper.selectCodeMapping(mapping);
        if (list.size()>0){
            throw new Exception("编号映射数据已存在！");
        }
        Integer integer = cMesMaterialCodeMappingTMapper.addMaterialCodeMapping(mapping);
        if (integer<1){
            throw new Exception("添加编号映射数据失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson addMaterialCodesMapping(List<Map<String, Object>> data) throws Exception {
        for (Map<String, Object> object : data) {
            if (object.get("material_code") == null || object.get("material_code").equals("")) {
                //为空编码自动生成
                String latestCode = codeRuleService.getLatestCode(CodeRuleConstant.CODE_MAPPING);
                object.put("material_code", latestCode);
            }
        }

/*        //为空编码自动生成
        CMesCodeRuleT latestCode = codeRuleService.getLatestCode(CodeRuleConstant.CODE_MAPPING);
        Integer suffixValue = 0;
        int i =0;
        for (Map<String, Object> object : data) {
            if (object.get("material_code") == null || object.get("material_code").equals("")) {
                if (i != 0) {
                    suffixValue = Integer.parseInt(latestCode.getCodeRuleSuffixValue());
                    suffixValue++;
                    String format = String.format("%0" + latestCode.getCodeRuleSuffix() + "d", suffixValue);
                    latestCode.setLastCode(latestCode.getCodeRulePrefix()+latestCode.getCodeRuleValue()+format);
                }
                object.put("material_code", latestCode.getLastCode());
                i++;
            }
        }*/

        Integer integer = cMesMaterialCodeMappingTMapper.addMaterialCodesMapping(data);
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
