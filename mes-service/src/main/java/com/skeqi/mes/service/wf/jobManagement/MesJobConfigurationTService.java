package com.skeqi.mes.service.wf.jobManagement;

import com.skeqi.mes.pojo.wf.jobManagement.MesJobConfigurationT;

import java.util.List;

public interface MesJobConfigurationTService{

    int deleteByPrimaryKey(Integer id);

    int insertSelective(MesJobConfigurationT record) throws Exception;

    MesJobConfigurationT selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MesJobConfigurationT record);

    List<MesJobConfigurationT> selectAll();

}
