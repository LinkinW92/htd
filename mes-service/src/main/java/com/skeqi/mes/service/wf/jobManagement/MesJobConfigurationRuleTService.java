package com.skeqi.mes.service.wf.jobManagement;

import com.skeqi.mes.pojo.wf.jobManagement.MesJobConfigurationRuleT;

import java.util.List;

public interface MesJobConfigurationRuleTService{


    int deleteByPrimaryKey(Integer id);

    int insertSelective(MesJobConfigurationRuleT record) throws Exception;

    MesJobConfigurationRuleT selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MesJobConfigurationRuleT record);

    List<MesJobConfigurationRuleT> selectAll();

}
