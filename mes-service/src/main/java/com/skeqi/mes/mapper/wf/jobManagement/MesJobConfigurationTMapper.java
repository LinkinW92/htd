package com.skeqi.mes.mapper.wf.jobManagement;

import com.skeqi.mes.pojo.wf.jobManagement.MesJobConfigurationT;

import java.util.List;

public interface MesJobConfigurationTMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MesJobConfigurationT record);

    MesJobConfigurationT selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MesJobConfigurationT record);

    List<MesJobConfigurationT> selectAll();

}
