package com.skeqi.mes.mapper.wf.jobManagement;

import com.skeqi.mes.pojo.wf.jobManagement.MesJobConfigurationRuleT;

import java.util.List;

public interface MesJobConfigurationRuleTMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MesJobConfigurationRuleT record);

    MesJobConfigurationRuleT selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MesJobConfigurationRuleT record);

    List<MesJobConfigurationRuleT> selectAll();

}
