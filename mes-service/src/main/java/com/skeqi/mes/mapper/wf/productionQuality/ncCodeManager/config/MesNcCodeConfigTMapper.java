package com.skeqi.mes.mapper.wf.productionQuality.ncCodeManager.config;

import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigT;

import java.util.List;

public interface MesNcCodeConfigTMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MesNcCodeConfigT record);

    MesNcCodeConfigT selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MesNcCodeConfigT record);

    List<MesNcCodeConfigT> selectAll(MesNcCodeConfigT mesNcCodeConfigT);

    List<MesNcCodeConfigT> verifySn(String sn);
}
