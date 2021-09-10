package com.skeqi.mes.service.wf.jobManagement.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.jobManagement.MesJobConfigurationRuleTMapper;
import com.skeqi.mes.pojo.wf.jobManagement.MesJobConfigurationRuleT;
import com.skeqi.mes.service.wf.jobManagement.MesJobConfigurationRuleTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MesJobConfigurationRuleTServiceImpl implements MesJobConfigurationRuleTService{

    @Resource
    private MesJobConfigurationRuleTMapper mesJobConfigurationRuleTMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mesJobConfigurationRuleTMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(MesJobConfigurationRuleT record) throws Exception {
        MesJobConfigurationRuleT mesJobConfigurationRuleT = selectByPrimaryKey(record.getCode());
        if (!StringUtils.isEmpty(mesJobConfigurationRuleT)) {
            throw new Exception("编码已存在,请验证后重试!!!");
        }
        return mesJobConfigurationRuleTMapper.insertSelective(record);
    }

    @Override
    public MesJobConfigurationRuleT selectByPrimaryKey(String code) {
        return mesJobConfigurationRuleTMapper.selectByPrimaryKey(code);
    }

    @Override
    public int updateByPrimaryKeySelective(MesJobConfigurationRuleT record) {
        return mesJobConfigurationRuleTMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MesJobConfigurationRuleT> selectAll() {
        return mesJobConfigurationRuleTMapper.selectAll();
    }

}
